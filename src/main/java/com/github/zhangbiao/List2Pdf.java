package com.github.zhangbiao;

import com.github.zhangbiao.entity.List2PdfEntity;
import com.github.zhangbiao.entity.List2PdfFieldEntity;
import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * list转换为pdf
 *
 * @author zhangbiao
 */
public class List2Pdf<T> implements ToPDF {

    private List2PdfEntity<T> entity;

    private PdfDocument pdfDocument;

    private Document document;


    private float[] columnWidths;

    private float allColWidth = 0f;

    private int fontSize = 10;

    private final String fontPath;

    public List2Pdf(List2PdfEntity<T> entity, OutputStream outputStream, String fontPath) {
        this(entity, outputStream, PageSize.A4, fontPath);
    }

    public List2Pdf(List2PdfEntity<T> entity, OutputStream outputStream, PageSize targetPdfPageSize, String fontPath) {
        PdfDocument pdfDocument = new PdfDocument(new PdfWriter(outputStream));
        this.pdfDocument = pdfDocument;
        this.document = new Document(pdfDocument, targetPdfPageSize.rotate());
        this.entity = entity;
        this.fontPath = fontPath;
        this.columnWidths = this.entity.getColumnWidths();
        for (float columnWidth : this.columnWidths) {
            this.allColWidth += columnWidth;
        }
    }

    @Override
    public void exec() {
        Table table = new Table(columnWidths);
        doCell(table);
        document.add(table);
        document.close();
    }

    public void doCell(Table table) {
        writeHead(table);
        List<T> dataList = this.entity.getDataList();
        List<List2PdfFieldEntity<T, ?>> fieldList = this.entity.getFieldList();
        dataList.forEach(item -> {
            fieldList.forEach(field -> {
                table.addCell(genCell(field, item, false));
            });
        });
    }

    private void writeHead(Table table) {
        List<List2PdfFieldEntity<T, ?>> fieldList = this.entity.getFieldList();
        fieldList.forEach(field -> {
            table.addCell(genCellForValue(field, field.getName(), true));
        });
    }

    private Cell genCell(List2PdfFieldEntity<T, ?> field, T rowData, boolean bold) {
        Object value = field.getValue().apply(rowData);
        return genCellForValue(field, value, bold);
    }

    private Cell genCellForValue(List2PdfFieldEntity<T, ?> field, Object value, boolean bold) {
        String v = value == null ? "" : value.toString();

        Cell pdfCell = new Cell(1, 1);
        float cloW = field.getWidth();
        float realH = v.length() * fontSize;
        float height = ((realH / cloW) + 1) * fontSize * 5f;

        pdfCell.setHeight(height);
        pdfCell.setPadding(0f);

        Text text = new Text(v);
        setPdfCellFont(text, bold);
        Paragraph paragraph = new Paragraph(text).setPadding(0f).setMargin(0f);

        paragraph.setMaxWidth(this.allColWidth);
        pdfCell.add(paragraph);
        // 布局
        pdfCell.setVerticalAlignment(VerticalAlignment.MIDDLE);
        pdfCell.setTextAlignment(TextAlignment.CENTER);

        //边框
        SolidBorder solidBorder = new SolidBorder(ColorConstants.BLACK, 0.3f);
        pdfCell.setBorderBottom(solidBorder);
        pdfCell.setBorderLeft(solidBorder);
        pdfCell.setBorderRight(solidBorder);
        pdfCell.setBorderTop(solidBorder);
        //背景色
        pdfCell.setBackgroundColor(ColorConstants.WHITE);
        return pdfCell;
    }

    /**
     * 设置字体
     * @param text
     * @param bold
     */
    private void setPdfCellFont(Text text, boolean bold) {
        try {
            text.setFont(PdfFontFactory.createFont(fontPath, PdfEncodings.IDENTITY_H));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        text.setFontSize(fontSize);
        text.setFontColor(ColorConstants.BLACK);
        if (bold) {
            text.setBold();
        }
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }
}
