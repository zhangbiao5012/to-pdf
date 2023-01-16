package com.github.zhangbiao.entity;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author zhangbiao
 */
public class List2PdfEntity<T> {

    private final List<T> dataList;

    private List<List2PdfFieldEntity<T, ?>> fieldList = new ArrayList<>();

    private final float[] columnWidths;

    public List2PdfEntity(List<T> dataList, List<List2PdfFieldEntity<T, ?>> fieldList) {
        this.dataList = dataList;
        if (fieldList != null && !fieldList.isEmpty()) {
            // 排序
            this.fieldList = fieldList;
            this.fieldList.sort(Comparator.comparing(List2PdfFieldEntity::getIndex));
        }
        this.columnWidths = new float[this.fieldList.size()];
        for (int i = 0; i < this.fieldList.size(); i++) {
            this.columnWidths[i] = this.fieldList.get(i).getWidth();
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
