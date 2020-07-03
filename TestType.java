package com.gacrnd.gcs.generictest.reflection;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.List;
import java.util.Map;

public class TestType<K extends Comparable<K> & Serializable,V> {
    List<String>[] lists;
    Map<String, String> map;
    K key;
    V value;
    private List<? extends Number> a;  // 上限
    private List<? super String> b;    //下限


    public static void main(String[] args) throws Exception {
        //testGenericArrayType();
        //testParameterizedType();
        //testTypeVariable();
        testWildcardType();
    }

    //测试GenericArrayType
    public static void testGenericArrayType() throws Exception{
        Field f = TestType.class.getDeclaredField("lists");
        GenericArrayType genericType = (GenericArrayType) f.getGenericType();
        System.out.println(genericType.getGenericComponentType());
    }

    //测试ParameterizedType
    public static void testParameterizedType() throws Exception{
        Field f = TestType.class.getDeclaredField("map");
        System.out.println(f.getGenericType());  // java.util.Map<java.lang.String, java.lang.String>
        ParameterizedType pType = (ParameterizedType) f.getGenericType();
        System.out.println(pType.getRawType());   // interface java.util.Map
        for (Type type : pType.getActualTypeArguments()) {
            System.out.println(type);             // 打印两遍: class java.lang.String
        }
    }

    //测试TypeVariable
    public static void testTypeVariable() throws Exception{
        // 获取字段的类型
        Field fk = TestType.class.getDeclaredField("key");
        Field fv = TestType.class.getDeclaredField("value");
        TypeVariable keyType = (TypeVariable)fk.getGenericType();
        TypeVariable valueType = (TypeVariable)fv.getGenericType();

        // getName 方法
        System.out.println(keyType.getName());    // K
        System.out.println(valueType.getName());  // V
        // getGenericDeclaration 方法
        System.out.println(keyType.getGenericDeclaration());// class com.test.TestType
        System.out.println(valueType.getGenericDeclaration());// class com.test.TestType

        // getBounds 方法
        System.out.println("K 的上界:");   // 有两个
        for (Type type : keyType.getBounds()) { // interface java.lang.Comparable
            System.out.println(type);            // interface java.io.Serializable
        }
        System.out.println("V 的上界:");         //没明确声明上界的, 默认上界是Object
        for (Type type : valueType.getBounds()) {  // class java.lang.Object
            System.out.println(type);
        }
    }

    //测试WildcardType
    public static void testWildcardType() throws Exception{
        Field fieldA = TestType.class.getDeclaredField("a");
        Field fieldB = TestType.class.getDeclaredField("b");
        // 先拿到范型类型
        ParameterizedType pTypeA = (ParameterizedType) fieldA.getGenericType();
        ParameterizedType pTypeB = (ParameterizedType) fieldB.getGenericType();
        // 再从范型里拿到通配符类型
        WildcardType wTypeA = (WildcardType) pTypeA.getActualTypeArguments()[0];
        WildcardType wTypeB =  (WildcardType) pTypeB.getActualTypeArguments()[0]; // 方法测试
        System.out.println(wTypeA.getUpperBounds()[0]); // class java.lang.Number
        System.out.println(wTypeB.getLowerBounds()[0]); // class java.lang.String
        // 看看通配符类型到底是什么, 打印结果为: ? extends java.lang.Number
        System.out.println(wTypeA);
        System.out.println(wTypeB);
    }
}
