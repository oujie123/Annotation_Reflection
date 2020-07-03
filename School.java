package com.gacrnd.gcs.generictest.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Jack_Ou  created on 2020/7/1.
 */
@Target({ElementType.TYPE,ElementType.FIELD})
@Retention(RetentionPolicy.SOURCE)
public @interface School {
    String value();
    String schoolArea() default "chongqing";
}
