package com.github.zhangbiao.entity;

import java.util.function.Function;

/**
 * @author zhangbiao
 */
public class List2PdfFieldEntity<T, R> {

    private String name;

    private int index;
    private Function<T, R> value;

    private float width;

    public List2PdfFieldEntity(String name, int index, float width, Function<T, R> value) {
        this.name = name;
        this.index = index;
        this.width = width;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Function<T, R> getValue() {
        return value;
    }

    public void setValue(Function<T, R> value) {
        this.value = value;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }
}
