package com.xb.remote_call.annotation;

import java.lang.annotation.*;

/**
 * @Author KAJ
 * @Date 2022/9/2 11:13
 * @Description //传参时忽略的字段
 */
@Target(value = ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface JsonIgnore {

    /* 只要有一个不为空就忽略 */
    Class[] existFields() default {};

    /* 所有的都不为空才会忽略 */
    Class[] aboveFields() default {};

    boolean value() default false;
}
