package com.github.zhangbiao.builder;

import com.github.zhangbiao.annotation.ToPdfField;
import com.github.zhangbiao.entity.List2PdfEntity;
import com.github.zhangbiao.entity.List2PdfFieldEntity;
import com.github.zhangbiao.handler.List2Pdf;
import com.github.zhangbiao.handler.ToPDF;
import com.itextpdf.kernel.geom.PageSize;

import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangbiao
 */
public class List2PdfBuilder<T> {

    private List2Pdf<T> pdfHandler;

    private List<T> sources;

    private Class<T> sourceClass;

    private String fontPath;

    private String outPath;

    private PageSize pageSize;

    public List2PdfBuilder(Class<T> sourceClass, List<T> sources, String outPath, String fontPath) {
        this(sourceClass, sources, outPath, fontPath, PageSize.A4);
    }

    public List2PdfBuilder(Class<T> sourceClass, List<T> sources, String outPath, String fontPath, PageSize pageSize) {
        this.sourceClass = sourceClass;
        this.sources = sources;
        this.outPath = outPath;
        this.fontPath = fontPath;
        this.pageSize = pageSize;
    }

    private List<AnnField> getFields() {
        List<AnnField> annFields = new ArrayList<>();
        for (Field field : this.sourceClass.getDeclaredFields()) {
            field.setAccessible(true);
            ToPdfField ann = field.getAnnotation(ToPdfField.class);
            if (ann != null) {
                annFields.add(new AnnField(ann, field));
            }
        }
        if (annFields.isEmpty()) {
            throw new RuntimeException("没有找到可用的字段");
        }
        return annFields;
    }

    private void genPdfHandler(List<AnnField> annFields) {
        List<List2PdfFieldEntity<T, ?>> fieldList = new ArrayList<>();
        for (AnnField annField : annFields) {
            fieldList.add(
                    new List2PdfFieldEntity<>(
                            annField.getAnnotation().name(),
                            annField.getAnnotation().index(),
                            annField.getAnnotation().width(),
                            (t) -> {
                                try {
                                    return annField.getField().get(t);
                                } catch (IllegalAccessException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                    )
            );
        }
        List2PdfEntity<T> entity = new List2PdfEntity<>(sources, fieldList);
        try {
            this.pdfHandler = new List2Pdf<>(
                    entity,
                    Files.newOutputStream(Paths.get(this.outPath)),
                    this.pageSize,
                    this.fontPath
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ToPDF build() {
        List<AnnField> annFields = getFields();
        genPdfHandler(annFields);
        return this.pdfHandler;
    }

}
