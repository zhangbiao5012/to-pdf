package com.github.zhangbiao.entity;

import java.util.List;

/**
 * @author zhangbiao
 */
public class List2PdfEntity<T> {

    private final List<T> dataList;

    private final List<List2PdfFieldEntity<T, ?>> fieldList;

    private final float[] columnWidths;

    public List2PdfEntity(List<T> dataList, List<List2PdfFieldEntity<T, ?>> fieldList) {
        this.dataList = dataList;
        this.fieldList = fieldList;
        this.columnWidths = new float[fieldList.size()];
        for (int i = 0; i < fieldList.size(); i++) {
            this.columnWidths[i] = fieldList.get(i).getWidth();
        }
    }


    public List<T> getDataList() {
        return dataList;
    }

    public List<List2PdfFieldEntity<T, ?>> getFieldList() {
        return fieldList;
    }

    public float[] getColumnWidths() {
        return columnWidths;
    }
}
