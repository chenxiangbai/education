package com.education.annoation;

import java.lang.annotation.*;

/**
 * @Author admin
 * @Date 2021-02-22 14:43
 * @Version 1.0
 * @Description 日志切点
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AspectLog {
    /**
     * 接口中文名
     * @return
     */
    String value() default "";
}
