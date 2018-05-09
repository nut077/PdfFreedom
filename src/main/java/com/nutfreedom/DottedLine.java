package com.nutfreedom;

import com.itextpdf.text.pdf.PdfContentByte;

class DottedLine implements LineDash {
    public void applyLineDash(PdfContentByte canvas) {
        canvas.setLineCap(PdfContentByte.LINE_CAP_ROUND);
        canvas.setLineDash(0, 4, 2);
    }
}