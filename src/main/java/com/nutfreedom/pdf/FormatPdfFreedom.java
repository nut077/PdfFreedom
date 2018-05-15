package com.nutfreedom.pdf;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Element;

import java.util.HashMap;

public class FormatPdfFreedom {
    private HashMap<String, Integer> formatHorizontal;
    private HashMap<String, Integer> formatVertical;
    private HashMap<String, BaseColor> formatColor;
    public FormatPdfFreedom() {
        setFormatHorizontal();
        setFormatVertical();
        setFormatColor();
    }

    private void setFormatHorizontal() {
        formatHorizontal = new HashMap<>();
        formatHorizontal.put("left", Element.ALIGN_LEFT);
        formatHorizontal.put("center", Element.ALIGN_CENTER);
        formatHorizontal.put("right", Element.ALIGN_RIGHT);
    }

    public HashMap<String, Integer> getFormatHorizontal() {
        return formatHorizontal;
    }

    private void setFormatVertical() {
        formatVertical = new HashMap<>();
        formatVertical.put("top", Element.ALIGN_TOP);
        formatVertical.put("middle", Element.ALIGN_MIDDLE);
        formatVertical.put("bottom", Element.ALIGN_BOTTOM);
    }

    public HashMap<String, Integer> getFormatVertical() {
        return formatVertical;
    }

    private void setFormatColor() {
        formatColor = new HashMap<>();
        formatColor.put("white", BaseColor.WHITE);
        formatColor.put("light_gray", BaseColor.LIGHT_GRAY);
        formatColor.put("gray", BaseColor.GRAY);
        formatColor.put("dark_gray", BaseColor.DARK_GRAY);
        formatColor.put("black", BaseColor.BLACK);
        formatColor.put("red", BaseColor.RED);
        formatColor.put("pink", BaseColor.PINK);
        formatColor.put("orange", BaseColor.ORANGE);
        formatColor.put("yellow", BaseColor.YELLOW);
        formatColor.put("green", BaseColor.GREEN);
        formatColor.put("magenta", BaseColor.MAGENTA);
        formatColor.put("cyan", BaseColor.CYAN);
        formatColor.put("blue", BaseColor.BLUE);
    }

    public HashMap<String, BaseColor> getFormatColor() {
        return formatColor;
    }
}
