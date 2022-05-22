package com.ijunfu.easycsv.autoconfig.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Title : 自定义日志注解
 * @Remarks:
 * @Author : Weizhiguo
 * @Version: 1.0.0
 * @Date : 2022-05-22
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface PathLog {

    String name() default "";

    String notes() default "";
}
