package com.nutfreedom;

import com.itextpdf.text.pdf.PdfContentByte;

class DashedLine implements LineDash {
    public void applyLineDash(PdfContentByte canvas) {
        canvas.setLineDash(3, 3);
    }
}