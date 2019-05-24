package com.jiefeng.ssm.annotation;


import java.lang.annotation.*;

@Target({ElementType.PARAMETER, ElementType.METHOD})  //注解可以用于参数或者方法上
@Retention(RetentionPolicy.RUNTIME)  //保留至运行时
@Documented//被javadoc所记录
public @interface LoggerOrException {
    /**
     * 操作名称
     *
     * @return
     */
    public String operationName() default "";
}
