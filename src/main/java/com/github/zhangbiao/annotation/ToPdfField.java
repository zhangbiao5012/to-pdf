package com.github.zhangbiao.annotation;

import java.lang.annotation.*;

/**
 * @author zhangbiao
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface ToPdfField {

    /**
     * 列名
     * @return
     */
    String name() default "";

    /**
     * 排序值
     * @return
     */
    int index() default -1;

    /**
     * 宽度
     * @return
     */
    float width() default 100;
}
