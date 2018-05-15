package com.nutfreedom.pdf;

import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPCellEvent;
import com.itextpdf.text.pdf.PdfPTable;

public class DoubleCell implements PdfPCellEvent {

    @Override
    public void cellLayout(PdfPCell pdfPCell, Rectangle position, PdfContentByte[] pdfContentBytes) {
        PdfContentByte canvas = pdfContentBytes[PdfPTable.LINECANVAS];
        canvas.moveTo(position.getLeft(), position.getBottom());
        canvas.lineTo(position.getRight(), position.getBottom());
        // construct second line (4 user units lower):
        canvas.moveTo(position.getLeft(), position.getBottom() - 2);
        canvas.lineTo(position.getRight(), position.getBottom() - 2);
        // draw lines
        canvas.stroke();
    }
}
