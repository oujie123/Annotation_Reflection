package com.gacrnd.gcs.generictest.inject.outerclass;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author Jack_Ou  created on 2020/7/2.
 */
public class TypeReference<T> {

    private Type type;

    //使用protected是为了在创建时必须加{}，不能以创建对象的方式创建
    //有{}是创建一个匿名内部类实例对象，在编译的时候会翻译出具体T，会重新创建一个class文件！
    //没有{}就只是实例化对象，编译后T是object。只是一个对象
    protected TypeReference() {
        //getGenericSuperclass就是TypeReference填了传入类型的值
        Type type1 = getClass().getGenericSuperclass();
        System.out.println(type1);
        //ParameterizedType也是TypeReference填了传入类型的值，可以直接强转
        ParameterizedType parameterizedType = (ParameterizedType) type1;
        System.out.println(parameterizedType);
        //得到定义的泛型类型T，返回数组，是因为可以定义多个泛型
        Type[] types = parameterizedType.getActualTypeArguments();
        type = types[0];
        System.out.println(types[0]);
    }

    public Type getType() {
        return type;
    }
}
