package com.gacrnd.gcs.generictest.inject;

import android.app.Activity;
import android.os.Binder;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.view.View;

import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.Arrays;

public class InjectUtils {

    public static void injectView(Activity activity) {
        Class<? extends Activity> cls = activity.getClass();
        Field[] fields = cls.getDeclaredFields();
        for (Field field : fields) {
            //如果有InjectView属性
            if (field.isAnnotationPresent(InjectView.class)) {
                InjectView injectView = field.getAnnotation(InjectView.class);
                int sourceId = injectView.value();
                View view = activity.findViewById(sourceId);
                field.setAccessible(true);
                try {
                    //通过反射赋值给tv
                    field.set(activity, view);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void injectShow(Activity activity) {
        Class<? extends Activity> cls = activity.getClass();
        Bundle bundle = activity.getIntent().getExtras();
        if (bundle == null) {
            return;
        }

        Field[] fields = cls.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(Autowired.class)) {
                Autowired autowired = field.getAnnotation(Autowired.class);
                //判断注解是否被赋值，如果被赋值了就用赋值的值，如果没有被赋值就用变量名。
                String key = TextUtils.isEmpty(autowired.value()) ? field.getName() : autowired.value();

                if (bundle.containsKey(key)) {
                    Object obj = bundle.get(key);

                    //获得数组单个元素类型
                    Class<?> componentType = field.getType().getComponentType();

                    if (field.getType().isArray() && Parcelable.class.isAssignableFrom(componentType)) {
                        Object[] objects = (Object[]) obj;
                        //创建对应的数组，并且copy出去
                        objects = Arrays.copyOf(objects, objects.length, (Class<? extends Object[]>) field.getType());
                        obj = objects;
                    }

                    field.setAccessible(true);
                    try {
                        //set 第一个参数：在那个对象上去设置属性。
                        //如果参数是static的就可以传null
                        field.set(activity, obj);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
