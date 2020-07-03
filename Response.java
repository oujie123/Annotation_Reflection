package com.gacrnd.gcs.generictest.inject;

import com.gacrnd.gcs.generictest.inject.outerclass.TypeReference;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author Jack_Ou  created on 2020/7/2.
 */
public class Response<T> {
    T data;
    int code;
    String message;

    public Response(T data, int code, String message) {
        this.data = data;
        this.code = code;
        this.message = message;
    }

    @Override
    public String toString() {
        return "Response{" + "data=" + data + ", code=" + code + ", message='" + message + '}';
    }

//    public static class TypeReference<T> {
//
//        private Type type;
//
//        protected TypeReference() {
//            //getGenericSuperclass就是TypeReference填了传入类型的值
//            Type type1 = getClass().getGenericSuperclass();
//            System.out.println(type1);
//            //ParameterizedType也是TypeReference填了传入类型的值，可以直接强转
//            ParameterizedType parameterizedType = (ParameterizedType) type1;
//            System.out.println(parameterizedType);
//            //得到定义的泛型类型T，返回数组，是因为可以定义多个泛型
//            Type[] types = parameterizedType.getActualTypeArguments();
//            type = types[0];
//            System.out.println(types[0]);
//        }
//
//        public Type getType() {
//            return type;
//        }
//    }

    public static void main(String[] args) {
        Response<Data> dataResponse = new Response<>(new Data("data"), 0, "request success");
        Gson gson = new Gson();
        //使用Gson打包成json
        String json = gson.toJson(dataResponse);
        //System.out.println(json);

        //反序列
        //TypeToken<Response<Data>> typeToken = new TypeToken<Response<Data>>(){};

        //没有{},是对类的实例化，编译后的签名是object
        //T: Ljava/lang/Object;>Ljava/lang/Object;
        //TypeReference<Response<Data>> typeReference1 = new TypeReference<Response<Data>>();

        //加了大括号new的是一个类，编译后的参数签名T：Lcom/gacrnd/gcs/generictest/inject/Response$TypeReference<Lcom/gacrnd/gcs/generictest/inject/Response<Lcom/gacrnd/gcs/generictest/inject/Data;>;>;
        TypeReference<Response<Data>> typeReference2 = new TypeReference<Response<Data>>(){};
        Response<Data> response = gson.fromJson(json, typeReference2.getType());
        System.out.println(response);
    }
}
