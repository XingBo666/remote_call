package com.xb.remote_call.annotation;

/**
 * @Author KAJ
 * @Date 2022/9/2 11:13
 * @Description //传参时忽略的字段
 */
public @interface CheckIgnore {

    /* 只要有一个不为空就忽略 */
    Class[] existFields() default {};

    /* 所有的都不为空才会忽略 */
    Class[] aboveFields() default {};
}
