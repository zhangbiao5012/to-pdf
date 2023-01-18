package com.github.zhangbiao.builder;

import com.github.zhangbiao.annotation.ToPdfField;

import java.lang.reflect.Field;

/**
 * @author zhangbiao
 */
public class AnnField {

    private ToPdfField annotation;

    private Field field;

    public AnnField(ToPdfField annotation, Field field) {
        this.annotation = annotation;
        this.field = field;
    }

    public ToPdfField getAnnotation() {
        return annotation;
    }

    public void setAnnotation(ToPdfField annotation) {
        this.annotation = annotation;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }
}
