package com.nutfreedom;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

public class HeaderTable extends PdfPageEventHelper {
    private float marginTop = 0;
    private float defaultHeight = 0;
    private BaseFont baseFont;
    private PdfTemplate totalPages;
    private float fontSize;
    private int pageNumberAlignment = Element.ALIGN_RIGHT;
    private boolean isShowPageNumber = false;
    private boolean isSetPageNumberToBottom = false;
    private String textFirst = "tmpTextFirst";
    private String textOf = "tmpTextOf";
    private CheckFreedom check;
    private PdfPTable table;
    private int paddingLeft = 0;
    private int paddingTop = 25;

    public HeaderTable() {
        check = new CheckFreedom();
    }

    public void setMarginTop(float marginTop) {
        this.marginTop = marginTop;
    }

    public float getMarginTop() {
        return marginTop;
    }

    public float getDefaultHeight() {
        return defaultHeight;
    }

    public void setDefaultHeight(float defaultHeight) {
        this.defaultHeight = defaultHeight;
    }

    public void setBaseFont(BaseFont baseFont) {
        this.baseFont = baseFont;
    }

    public void setFontSize(float fontSize) {
        this.fontSize = fontSize;
    }

    public void setPageNumberAlignment(int pageNumberAlignment) {
        this.pageNumberAlignment = pageNumberAlignment;
    }

    public void setPageNumberShow(boolean showPageNumber) {
        isShowPageNumber = showPageNumber;
    }

    public void setPageNumberText(String textFirst, String textOf) {
        this.textFirst = textFirst;
        this.textOf = textOf;
    }

    public int getPaddingLeft() {
        return paddingLeft;
    }

    public void setPaddingLeft(int paddingLeft) {
        this.paddingLeft = paddingLeft;
    }

    public int getPaddingTop() {
        return paddingTop;
    }

    public void setPaddingTop(int paddingTop) {
        this.paddingTop = paddingTop;
    }

    public void setPageNumberToBottom(boolean isSetPageNumberToBottom) {
        this.isSetPageNumberToBottom = isSetPageNumberToBottom;
    }

    public void setTable(PdfPTable table) {
        this.table = table;
    }

    @Override
    public void onOpenDocument(PdfWriter writer, Document document) {
        if (isShowPageNumber) {
            totalPages = writer.getDirectContent().createTemplate(100, 100);
            totalPages.setBoundingBox(new Rectangle(-20, -20, 100, 100));
        }
    }

    @Override
    public void onEndPage(PdfWriter writer, Document document) {
        table.writeSelectedRows(0, -1, document.left(), getDefaultHeight() - getMarginTop(), writer.getDirectContent());
        if (isShowPageNumber) {
            PdfContentByte cb = writer.getDirectContent();
            cb.saveState();
            if (check.isEquals(textFirst, "tmpTextFirst")) {
                textFirst = "หน้าที่";
            }
            if (check.isEquals(textOf, "tmpTextOf")) {
                textOf = "/";
            }
            String text = String.format(textFirst +" %s " + textOf + " " , writer.getPageNumber());

            float textBase = getDefaultHeight() - getPaddingTop();
            if (isSetPageNumberToBottom) {
                textBase = document.bottom() - getPaddingTop();
            }
            float textSize = baseFont.getWidthPoint(text, fontSize);

            cb.beginText();
            cb.setFontAndSize(baseFont, fontSize);
            if(Element.ALIGN_CENTER == pageNumberAlignment) {
                cb.setTextMatrix((document.right() / 2 + getPaddingLeft()), textBase);
                cb.showText(text);
                cb.endText();
                cb.addTemplate(totalPages, (document.right() / 2 + getPaddingLeft()) + textSize, textBase);
            } else if(Element.ALIGN_LEFT == pageNumberAlignment) {
                cb.setTextMatrix(document.left() + getPaddingLeft(), textBase);
                cb.showText(text);
                cb.endText();
                cb.addTemplate(totalPages, document.left() + textSize + getPaddingLeft(), textBase);
            } else {
                float adjust = baseFont.getWidthPoint("0", fontSize);
                cb.setTextMatrix(document.right() - textSize - adjust + getPaddingLeft(), textBase);
                cb.showText(text);
                cb.endText();
                cb.addTemplate(totalPages, document.right() - adjust + getPaddingLeft(), textBase);
            }
            cb.restoreState();
        }
    }

    @Override
    public void onCloseDocument(PdfWriter writer, Document document) {
        if (isShowPageNumber) {
            totalPages.beginText();
            totalPages.setFontAndSize(baseFont, fontSize);
            totalPages.setTextMatrix(0, 0);
            totalPages.showText(String.valueOf(writer.getPageNumber() - 1));
            totalPages.endText();
        }
    }
}