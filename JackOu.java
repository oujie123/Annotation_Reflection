package com.gacrnd.gcs.generictest.annotation;

import androidx.annotation.DrawableRes;
import androidx.annotation.IntDef;

import com.gacrnd.gcs.generictest.R;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 1.注解处理器
 *   Step 1 : 新建Module，实现自己的Processor
 *   Step 2 ：在Main目录下新建resources/META-INF.services/javax.annotation.processing.Processor
 *   Step 3 ：在以上文件中将自己实现的Processor的包+类名写入，例如：com.gacrnd.gcs.libcompile.JackOuProcessor
 *   Step 4 ：新建自己要实现的处理器(JackOuProcessor extends AbstractProcessor)，实现具体的process方法即可
 *
 * 2.通过注解限制传入参数
 *   (即限制了参数范围，又节约了内存)，将goToSchool()方法中说明；
 *
 * @author Jack_Ou  created on 2020/7/1.
 */
//@School("chongqing university")
@School(value = "chongqing university",schoolArea = "shapingba")
public class JackOu extends Student {

    private static final int SLJ = 0;
    private static final int YXX = 1;
    private static final int HDJ = 2;

    @Type(toSchoolBy = "bus")
    @Override
    public void goToSchool() {
        //没有使用注解的调用
        //setDrawable(123456);  防止调用者传入非法值

        //DrawableRes使用了注解的调用
        setDrawable(R.drawable.ic_launcher_background);

        //枚举限制传参，但是每个枚举对象都是一个对象,
        //一个对象是由 12个字节对象头 + 成员所在字节 + 8个字节对齐
        //采用枚举占用内存比较浪费。可以使用常量来代替枚举类型；
        with(Students.SLJ);

        //节约了内存，但是没有限制了。
        with(SLJ);

        //使用注解限定参数
        with1(SLJ); //ok
        //with1(0);  error IDE插件会检测到传入参数不对
    }

    //使用@DrawableRes告诉调用者该方法一定要传入图片的id，而不是任意值
    public void setDrawable(@DrawableRes int resId){

    }

    //DrawableRes是系统实现了的IntDef，需要自定义
    public enum Students{
        SLJ,
        YXX,
        HDJ;
    }

    @IntDef({SLJ,YXX,HDJ})  //使用IntDef完成参数检测
    @Target({ElementType.PARAMETER})
    @Retention(RetentionPolicy.SOURCE)
    @interface StudentsAnnotation{

    }

    public void with(Students students){

    }

    public void with(int students){

    }

    public void with1(@StudentsAnnotation int students){

    }
}
