package com.nutfreedom.pdf;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.nutfreedom.utilities.*;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspWriter;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;

public class PdfFreedom {
    private ServletContext context;
    private HttpServletResponse response;
    private JspWriter out;
    private Rectangle page = null;
    private float marginLeftDocument = 36;
    private float marginRightDocument = 36;
    private float marginTopDocument = 20;
    private float marginBottomDocument = 36;
    private String title;
    private String pathFont = "";
    private String fontname = "THSarabun";
    private int fontSize = 12;
    private float padding = 6f;
    private String pathFile = "";
    private String filename;
    private String table;
    private String tableHeader = "";
    private SplitFreedom split = new SplitFreedom();
    private SubstringFreedom sub = new SubstringFreedom();
    private ParseNumberFreedom parse = new ParseNumberFreedom();
    private CheckFreedom check = new CheckFreedom();
    private MapFreedom mapFormatHorizontal;
    private MapFreedom mapFormatVertical;
    private HashMap<String, BaseColor> mapFormatColor;
    private BaseFont baseFont;
    private float paddingTop;
    private boolean isShowPageNumber = false;
    private String textFirst = "";
    private String textOf = "";
    private int pageNumberFontSize = 0;
    private String pageNumberAlign = "right";
    private boolean isSetPageNumberToBottom = false;
    private String defaultFormat = "border-center-middle";
    private LineDash solid = null;
    private LineDash dotted = null;
    private LineDash dashed = null;
    private int paddingLeftPageNumber = 0;
    private int paddingTopPageNumber = 25;

    public PdfFreedom(ServletContext context, HttpServletResponse response, JspWriter out, String title, String table) {
        this.context = context;
        this.response = response;
        this.out = out;
        this.title = title;
        this.table = table;
    }

    public PdfFreedom(ServletContext context, HttpServletResponse response, JspWriter out, String title, String table, String tableHeader) {
        this.context = context;
        this.response = response;
        this.out = out;
        this.title = title;
        this.table = table;
        this.tableHeader = tableHeader;
    }

    public PdfFreedom(String pathFile, String filename, String pathFont, String fontname, String title, String table) {
        this.pathFile = pathFile;
        this.filename = filename;
        this.pathFont = pathFont;
        this.fontname = fontname;
        this.title = title;
        this.table = table;
    }

    public PdfFreedom(String pathFile, String filename, String pathFont, String fontname, String title, String table, String tableHeader) {
        this.pathFile = pathFile;
        this.filename = filename;
        this.pathFont = pathFont;
        this.fontname = fontname;
        this.title = title;
        this.table = table;
        this.tableHeader = tableHeader;
    }

    public void setHorizontal() {
        this.page = PageSize.A4.rotate();
    }

    private Rectangle getPage() {
        if (page == null) {
            page = PageSize.A4;
        }
        return this.page;
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }

    public void setMarginDocument(
        float marginLeftDocument, float marginRightDocument,
        float marginTopDocument, float marginBottomDocument) {
        this.marginLeftDocument = marginLeftDocument;
        this.marginRightDocument = marginRightDocument;
        this.marginTopDocument = marginTopDocument;
        this.marginBottomDocument = marginBottomDocument;
    }

    public void setMarginLeftDocument(float marginLeftDocument) {
        this.marginLeftDocument = marginLeftDocument;
    }

    public void setMarginRightDocument(float marginRightDocument) {
        this.marginRightDocument = marginRightDocument;
    }

    public void setMarginTopDocument(float marginTopDocument) {
        this.marginTopDocument = marginTopDocument;
    }

    public void setMarginBottomDocument(float marginBottomDocument) {
        this.marginBottomDocument = marginBottomDocument;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPadding(float padding) {
        this.padding = padding;
    }

    public void setPageNumberShow() {
        isShowPageNumber = true;
    }

    public void setPageNumberFontSize(int pageNumberFontSize) {
        this.pageNumberFontSize = pageNumberFontSize;
    }

    public void setPageNumberAlign(String pageNumberAlign) {
        this.pageNumberAlign = pageNumberAlign;
    }

    public void setPageNumberToBottom() {
        this.isSetPageNumberToBottom = true;
    }

    public void setPageNumberText(String textFirst, String textOf) {
        this.textFirst = textFirst;
        this.textOf = textOf;
    }

    public void setPageNumberPaddingLeft(int paddingLeftPageNumber) {
        this.paddingLeftPageNumber = paddingLeftPageNumber;
    }

    public void setPageNumberPaddingTop(int paddingTopPageNumber) {
        this.paddingTopPageNumber = paddingTopPageNumber;
    }

    public void setDefaultFormat(String defaultFormat) {
        this.defaultFormat = defaultFormat;
    }

    private Float getPaddingTop(BaseFont baseFont) {
        Phrase phrase = new Phrase("Blah blah blah", getFont(baseFont, this.fontSize));
        Float fontSize = phrase.getFont().getSize();
        Float capHeight = phrase.getFont().getBaseFont().getFontDescriptor(BaseFont.CAPHEIGHT, fontSize);
        return capHeight - fontSize + padding;
    }

    private Font getFont(BaseFont baseFont, int fontSize) {
        return new Font(baseFont, fontSize);
    }

    private BaseFont getBaseFont() {
        BaseFont baseFont = null;
        try {
            if (check.isNotBlank(pathFile)) {
                if (check.isNotBlank(pathFont) && check.isNotBlank(fontname)) {
                    baseFont = BaseFont.createFont(pathFont + "/" + fontname + ".ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED, true);
                } else {
                    baseFont = BaseFont.createFont();
                }
            } else {
                baseFont = BaseFont.createFont(context.getRealPath("FONTS/" + fontname + ".ttf"), BaseFont.IDENTITY_H, BaseFont.EMBEDDED, true);
            }
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
        return baseFont;
    }

    private boolean isTrueData(String data) {
        return check.isNotBlank(data) && check.isNotData(data, "<table-width>") && check.isNotData(data, "<table-margin-top>") && check.isNotData(data, "<table-align>");
    }

    private int getSizeTable(String table) {
        int sizeTable = 0;
        String[] spTr = split.split(table, "<tr>");
        for (String valTr : spTr) {
            if (isTrueData(valTr)) {
                String[] spTd = split.split(valTr, "<td>");
                for (String valTd : spTd) {
                    if (check.isNotBlank(valTd)) {
                        sizeTable += getValueDataInt(valTd, "<colspan>", "</colspan>", 1);
                    }
                }
                break;
            }
        }
        return sizeTable;
    }

    private float setValueColumn(float oldValue, float newValue) {
        if (check.isEquals(oldValue, 0) || check.isNotEquals(newValue, 1)) {
            return newValue;
        }
        return oldValue;
    }

    private float[] getColumnWidth(String table, int size) {
        float[] column = new float[size];
        String[] spTr = split.split(table, "<tr>");
        int lengthSpTr = spTr.length - 1;
        int indexUsedColumn = 0;
        int[][] usedColumn = new int[lengthSpTr][size];
        for (int index = 0; index <= lengthSpTr; index++) {
            if (isTrueData(spTr[index])) {
                String[] spTd = split.split(spTr[index], "<td>");
                int length = 0;
                for (int i = 0; i < size; i++) {
                    length++;
                    if (spTd.length > length) {
                        String valTd = spTd[length];
                        float columnSize = getValueDataFloat(valTd, "<width>", "</width>", 0);
                        if (check.isEquals(columnSize, 0)) {
                            columnSize = 1;
                        }
                        int rowspan = getValueDataInt(valTd, "<rowspan>", "</rowspan>", 0);
                        if (rowspan > 1) {
                            if (indexUsedColumn + rowspan <= lengthSpTr) {
                                for (int a = indexUsedColumn + rowspan - 1; a > 0; a--) {
                                    usedColumn[a][i] = 1;
                                }
                            }
                        }
                        int colspan = getValueDataInt(valTd, "<colspan>", "</colspan>", 0);
                        if (colspan > 1) {
                            for (int j = 1; j <= colspan; j++) {
                                if (check.isEquals(usedColumn[indexUsedColumn][i], 0)) {
                                    column[i] = setValueColumn(column[i], columnSize);
                                    usedColumn[indexUsedColumn][i] = 1;
                                    if (check.isNotEquals(j, colspan)) {
                                        i++;
                                    }
                                } else {
                                    int tmpI = getIndex(i, size, usedColumn, indexUsedColumn);
                                    column[tmpI] = setValueColumn(column[tmpI], columnSize);
                                    usedColumn[indexUsedColumn][tmpI] = 1;
                                    if (check.isNotEquals(j, colspan)) {
                                        i++;
                                    }
                                }
                            }
                        } else {
                            if (check.isEquals(usedColumn[indexUsedColumn][i], 0)) {
                                column[i] = setValueColumn(column[i], columnSize);
                                usedColumn[indexUsedColumn][i] = 1;
                            } else {
                                int tmpI = getIndex(i, size, usedColumn, indexUsedColumn);
                                column[tmpI] = setValueColumn(column[tmpI], columnSize);
                                usedColumn[indexUsedColumn][tmpI] = 1;
                            }
                        }
                    } else {
                        if (check.isEquals(usedColumn[indexUsedColumn][i], 0)) {
                            column[i] = setValueColumn(column[i], 1);
                            usedColumn[indexUsedColumn][i] = 1;
                        } else {
                            int tmpI = getIndex(i, size, usedColumn, indexUsedColumn);
                            column[tmpI] = setValueColumn(column[tmpI], 1);
                            usedColumn[indexUsedColumn][tmpI] = 1;
                        }
                    }
                }
                indexUsedColumn++;
            }
        }
        return column;
    }

    private int getIndex(int tmp, int size, int[][] usedColumn, int indexUsedColumn) {
        int tmpI = tmp + 1;
        if (check.isEquals(tmpI, size)) {
            tmpI -= 1;
        }
        while (usedColumn[indexUsedColumn][tmpI] == 1) {
            tmpI++;
            if (check.isEquals(tmpI, size)) {
                tmpI -= 1;
                break;
            }
        }
        return tmpI;
    }

    private String getValueConditionString(String data, String start, String end) {
        String val = sub.substring(data, data.indexOf(start) + start.length(), data.indexOf(end));
        if (check.isBlank(val)) {
            printError("tag => " + start + " : " + end + "\n data => " + data);
        }
        return val;
    }

    private int getValueConditionInt(String data, String start, String end) {
        String num = sub.substring(data, data.indexOf(start) + start.length(), data.indexOf(end));
        if (check.isBlank(num)) {
            printError("tag => " + start + " : " + end + "\n data => " + data);
            return 1;
        }
        return parse.parseInt(num);
    }

    private float getValueConditionFloat(String data, String start, String end) {
        String num = sub.substring(data, data.indexOf(start) + start.length(), data.indexOf(end));
        if (check.isBlank(num)) {
            printError("tag => " + start + " : " + end + "\n data => " + data);
            return 1;
        }
        return (float) parse.parseDouble(num);
    }

    private void printError(String msg) {
        System.out.println("PdfFreedom error => " + msg);
    }

    private String getValueDataString(String data, String first, String last, String defaultValue) {
        String val = defaultValue;
        if (check.isHaveData(data, first)) {
            val = getValueConditionString(data, first, last);
        }
        return val;
    }

    private float getValueDataFloat(String data, String first, String last, float defaultValue) {
        float val = defaultValue;
        if (check.isHaveData(data, first)) {
            val = getValueConditionFloat(data, first, last);
        }
        return val;
    }

    private int getValueDataInt(String data, String first, String last, int defaultValue) {
        int val = defaultValue;
        if (check.isHaveData(data, first)) {
            val = getValueConditionInt(data, first, last);
        }
        return val;
    }

    private BaseColor getNewColor(String color) {
        int red = parse.parseInt(split.splitByIndex(color, ",", 0));
        int green = parse.parseInt(split.splitByIndex(color, ",", 1));
        int blue = parse.parseInt(split.splitByIndex(color, ",", 2));
        return new BaseColor(red, green, blue);
    }

    private PdfPCell getPdfPCell(String valTd) {
        String data = sub.substring(valTd, 0, valTd.indexOf("</td>"));
        if (check.isBlank(data)) {
            if (check.isNotData(valTd, "</td>")) {
                printError("expect </td> but this code => " + valTd);
            }
        }

        String border = "true";
        String align = "center";
        String valign = "middle";

        String format = getValueDataString(valTd, "<format>", "</format>", defaultFormat);
        String[] spFormat = split.split(format, "-");
        if (check.isEquals(spFormat.length, 1)) {
            border = "false";
            align = spFormat[0];
        } else if (check.isEquals(spFormat.length, 2)) {
            if (check.isEquals(spFormat[0], "border")) {
                align = spFormat[1];
            } else {
                border = "false";
                align = spFormat[0];
                valign = spFormat[1];
            }
        } else if (check.isEquals(spFormat.length, 3)) {
            align = spFormat[1];
            valign = spFormat[2];
        }

        int colspan = getValueDataInt(valTd, "<colspan>", "</colspan>", 0);
        int rowspan = getValueDataInt(valTd, "<rowspan>", "</rowspan>", 0);
        int height = getValueDataInt(valTd, "<height>", "</height>", 0);
        int fs = getValueDataInt(valTd, "<size>", "</size>", fontSize);
        if (check.isEquals(fs, fontSize)) {
            fs = getValueDataInt(valTd, "<font-size>", "</font-size>", fontSize);
        }
        String bold = getValueDataString(valTd, "<b>", "</b>", "false");
        String italic = getValueDataString(valTd, "<i>", "</i>", "false");
        String underline = getValueDataString(valTd, "<u>", "</u>", "false");
        String fontColor = getValueDataString(valTd, "<color>", "</color>", "");
        String fontColorNew = getValueDataString(valTd, "<color-new>", "</color-new>", "");
        String background = getValueDataString(valTd, "<background>", "</background>", "");
        String backgroundNew = getValueDataString(valTd, "<background-new>", "</background-new>", "");
        float padding = getValueDataFloat(valTd, "<padding>", "</padding>", 7777);
        float paddingLeft = getValueDataFloat(valTd, "<padding-left>", "</padding-left>", 7777);
        float paddingRight = getValueDataFloat(valTd, "<padding-right>", "</padding-right>", 7777);
        float paddingTop = getValueDataFloat(valTd, "<padding-top>", "</padding-top>", 7777);
        float paddingBottom = getValueDataFloat(valTd, "<padding-bottom>", "</padding-bottom>", 7777);
        String image = getValueDataString(valTd, "<image>", "</image>", "");
        float imageWidth = getValueDataFloat(valTd, "<image-width>", "</image-width>", 20);
        float imageWidthFix = getValueDataFloat(valTd, "<image-width-fix>", "</image-width-fix>", 0);
        float imageHeightFix = getValueDataFloat(valTd, "<image-height-fix>", "</image-height-fix>", 0);
        String doubleLine = getValueDataString(valTd, "<double-line>", "</double-line>", "false");

        String borderLeft = getValueDataString(valTd, "<border-left>", "</border-left>", "false");
        String borderRight = getValueDataString(valTd, "<border-right>", "</border-right>", "false");
        String borderTop = getValueDataString(valTd, "<border-top>", "</border-top>", "false");
        String borderBottom = getValueDataString(valTd, "<border-bottom>", "</border-bottom>", "false");

        String borderLeftDotted = getValueDataString(valTd, "<border-left-dotted>", "</border-left-dotted>", "false");
        String borderRightDotted = getValueDataString(valTd, "<border-right-dotted>", "</border-right-dotted>", "false");
        String borderTopDotted = getValueDataString(valTd, "<border-top-dotted>", "</border-top-dotted>", "false");
        String borderBottomDotted = getValueDataString(valTd, "<border-bottom-dotted>", "</border-bottom-dotted>", "false");

        String borderLeftDashed = getValueDataString(valTd, "<border-left-dashed>", "</border-left-dashed>", "false");
        String borderRightDashed = getValueDataString(valTd, "<border-right-dashed>", "</border-right-dashed>", "false");
        String borderTopDashed = getValueDataString(valTd, "<border-top-dashed>", "</border-top-dashed>", "false");
        String borderBottomDashed = getValueDataString(valTd, "<border-bottom-dashed>", "</border-bottom-dashed>", "false");

        if (check.isHaveData(data, "<")) {
            String tmp = sub.substring(data, data.lastIndexOf("</") + 2, data.length());
            data = sub.substring(tmp, tmp.indexOf(">") + 1, tmp.length());
        }
        Font f = getFont(baseFont, fs);
        if (check.isEquals(bold, "true")) {
            f.setStyle(Font.BOLD);
        }
        if (check.isEquals(italic, "true")) {
            f.setStyle(Font.ITALIC);
        }
        if (check.isEquals(underline, "true")) {
            f.setStyle(Font.UNDERLINE);
        }
        if (check.isNotBlank(fontColor)) {
            if (mapFormatColor.containsKey(fontColor)) {
                f.setColor(mapFormatColor.get(fontColor));
            }
        }
        if (check.isNotBlank(fontColorNew)) {
            f.setColor(getNewColor(fontColorNew));
        }

        PdfPCell cell = null;
        if (check.isNotBlank(image)) {
            Image img = null;
            try {
                img = Image.getInstance(image);
            } catch (BadElementException | IOException e) {
                e.printStackTrace();
            }
            if (img != null) {
                img.scalePercent(imageWidth);
                if (check.isMoreThanZero(imageWidthFix) && check.isMoreThanZero(imageHeightFix)) {
                    img.scaleAbsolute(imageWidthFix, imageHeightFix);
                }
                cell = new PdfPCell(img);
            }
        } else {
            cell = new PdfPCell(new Phrase(data, f));
        }

        if (cell == null) {
            cell = new PdfPCell(new Phrase("", f));
        }

        if (check.isEquals(padding, 7777)) {
            if (check.isEquals(paddingLeft + paddingRight + paddingTop + paddingBottom, 31108)) {
                cell.setPadding(this.padding);
            }
        } else {
            cell.setPadding(padding);
        }

        if ( check.isEquals(padding + paddingLeft + paddingRight + paddingTop + paddingBottom, 38885)) {
            cell.setPaddingTop(this.paddingTop);
        }

        cell.setHorizontalAlignment(this.mapFormatHorizontal.getInt(align));
        cell.setVerticalAlignment(this.mapFormatVertical.getInt(valign));

        if (check.isEquals(border, "false")) {
            cell.setBorder(Rectangle.NO_BORDER);
        }

        if (check.isEquals(borderLeft, "true")
            || check.isEquals(borderRight, "true")
            || check.isEquals(borderTop, "true")
            || check.isEquals(borderBottom, "true")) {
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setCellEvent(new CustomBorder(getLine(borderLeft), getLine(borderRight), getLine(borderTop), getLine(borderBottom)));
        }

        if (check.isEquals(borderLeftDotted, "true")
            || check.isEquals(borderRightDotted, "true")
            || check.isEquals(borderTopDotted, "true")
            || check.isEquals(borderBottomDotted, "true")) {
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setCellEvent(new CustomBorder(getLineDotted(borderLeftDotted), getLineDotted(borderRightDotted), getLineDotted(borderTopDotted), getLineDotted(borderBottomDotted)));
        }

        if (check.isEquals(borderLeftDashed, "true")
            || check.isEquals(borderRightDashed, "true")
            || check.isEquals(borderTopDashed, "true")
            || check.isEquals(borderBottomDashed, "true")) {
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setCellEvent(new CustomBorder(getLineDashed(borderLeftDashed), getLineDashed(borderRightDashed), getLineDashed(borderTopDashed), getLineDashed(borderBottomDashed)));
        }

        if (check.isEquals(doubleLine, "true")) {
            cell.setCellEvent(new DoubleCell());
        }
        if (check.isMoreThanZero(colspan)) {
            cell.setColspan(colspan);
        }
        if (check.isMoreThanZero(rowspan)) {
            cell.setRowspan(rowspan);
        }
        if (check.isNotBlank(background)) {
            if (this.mapFormatColor.containsKey(background)) {
                cell.setBackgroundColor(this.mapFormatColor.get(background));
            }
        }
        if (check.isNotBlank(backgroundNew)) {
            cell.setBackgroundColor(getNewColor(backgroundNew));
        }
        if (check.isMoreThanZero(height)) {
            cell.setMinimumHeight(height);
        }
        if (check.isNotEquals(paddingLeft, 7777)) {
            cell.setPaddingLeft(paddingLeft);
        }
        if (check.isNotEquals(paddingRight, 7777)) {
            cell.setPaddingRight(paddingRight);
        }
        if (check.isNotEquals(paddingTop, 7777)) {
            cell.setPaddingTop(paddingTop);
        }
        if (check.isNotEquals(paddingBottom, 7777)) {
            cell.setPaddingBottom(paddingBottom);
        }

        return cell;
    }

    private LineDash getLine(String condition) {
        if (check.isEquals(condition, "true")) {
            return solid;
        }
        return null;
    }

    private LineDash getLineDotted(String condition) {
        if (check.isEquals(condition, "true")) {
            return dotted;
        }
        return null;
    }

    private LineDash getLineDashed(String condition) {
        if (check.isEquals(condition, "true")) {
            return dashed;
        }
        return null;
    }

    private PdfPTable getTable(String valTable, PdfPTable table) {
        String[] spTr = split.split(valTable, "<tr>");
        for (String valTr : spTr) {
            if (isTrueData(valTr)) {
                String[] spTd = split.split(valTr, "<td>");
                for (String valTd : spTd) {
                    if (check.isNotBlank(valTd)) {
                        table.addCell(getPdfPCell(valTd));
                    }
                }
            }
        }
        return table;
    }

    private PdfPTable getTableHeader(String valTableHeader) {
        PdfPTable table = null;
        try {
            int sizeTable = getSizeTable(valTableHeader);
            table = new PdfPTable(sizeTable);
            table.setTotalWidth(((page.getWidth() - (marginLeftDocument * 2)) * getValueDataInt(valTableHeader, "<table-width>", "</table-width>", 100)) / 100);
            table.setWidths(getColumnWidth(valTableHeader, sizeTable));
            table.setHorizontalAlignment(mapFormatHorizontal.getInt(getValueDataString(valTableHeader, "<table-align>", "</table-align>", "center")));
            table = getTable(valTableHeader, table);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return table;
    }

    public void write() {
        try {
            solid = new SolidLine();
            dotted = new DottedLine();
            dashed = new DashedLine();
            FormatPdfFreedom formatPdfFreedom = new FormatPdfFreedom();
            mapFormatHorizontal = new MapFreedom(formatPdfFreedom.getFormatHorizontal());
            mapFormatVertical = new MapFreedom(formatPdfFreedom.getFormatVertical());
            mapFormatColor = formatPdfFreedom.getFormatColor();
            baseFont = getBaseFont();
            paddingTop = getPaddingTop(baseFont);

            Document document = new Document(getPage(),
                marginLeftDocument, marginRightDocument,
                marginTopDocument, marginBottomDocument);

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PdfWriter writer;
            if (check.isNotBlank(pathFile) && check.isNotBlank(filename)) {
                writer = PdfWriter.getInstance(document, new FileOutputStream(pathFile + "/" + filename + ".pdf"));
            } else {
                writer = PdfWriter.getInstance(document, baos);
            }

            if (check.isNotBlank(tableHeader)) {
                String[] spTableHeader = split.split(tableHeader, "<table>");
                for (String valTableHeader : spTableHeader) {
                    if (check.isNotBlank(valTableHeader)) {
                        HeaderTable event = new HeaderTable();
                        event.setDefaultHeight(page.getHeight());
                        event.setMarginTop(getValueDataFloat(valTableHeader, "<table-margin-top>", "</table-margin-top>", 40));
                        event.setBaseFont(baseFont);
                        event.setFontSize((float) check.setValueNotZero(pageNumberFontSize, fontSize - 1));
                        event.setPageNumberAlignment(mapFormatHorizontal.getInt(pageNumberAlign));
                        if (isShowPageNumber) {
                            event.setPageNumberShow(isShowPageNumber);
                            isShowPageNumber = false;
                        }
                        event.setPageNumberText(textFirst, textOf);
                        event.setPaddingLeft(paddingLeftPageNumber);
                        event.setPaddingTop(paddingTopPageNumber);
                        event.setPageNumberToBottom(isSetPageNumberToBottom);
                        event.setTable(getTableHeader(valTableHeader));
                        writer.setPageEvent(event);
                    }
                }
            }

            document.open();
            document.addTitle(title);

            String[] spTable = split.split(table, "<table>");
            for (String valTable : spTable) {
                if (check.isNotBlank(valTable)) {
                    if (check.isEquals(valTable, "<new-page>true</new-page></table>")) {
                        document.newPage();
                        continue;
                    }
                    int sizeTable = getSizeTable(valTable);
                    PdfPTable table = new PdfPTable(sizeTable);
                    table.setWidthPercentage(getValueDataInt(valTable, "<table-width>", "</table-width>", 100));
                    table.setWidths(getColumnWidth(valTable, sizeTable));
                    table.setHorizontalAlignment(mapFormatHorizontal.getInt(getValueDataString(valTable, "<table-align>", "</table-align>", "center")));
                    document.add(getTable(valTable, table));
                }
            }
            document.close();

            if (check.isBlank(pathFile)) {
                response.setHeader("Expires", "0");
                response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
                response.setHeader("Pragma", "public");
                response.setContentType("application/pdf");
                response.setContentLength(baos.size());
                OutputStream os = response.getOutputStream();
                baos.writeTo(os);
                os.flush();
                os.close();
                out.clear();
            }

        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
    }
}