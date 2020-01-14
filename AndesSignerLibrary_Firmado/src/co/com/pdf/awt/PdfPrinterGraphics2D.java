// Decompiled by DJ v3.12.12.98 Copyright 2014 Atanas Neshkov  Date: 17/11/2017 7:06:58 p. m.
// Home Page: http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   PdfPrinterGraphics2D.java
package co.com.pdf.awt;

import co.com.pdf.text.pdf.PdfContentByte;
import java.awt.print.PrinterGraphics;
import java.awt.print.PrinterJob;

// Referenced classes of package co.com.pdf.awt:
//            PdfGraphics2D, FontMapper
public class PdfPrinterGraphics2D extends PdfGraphics2D implements PrinterGraphics {

    private PrinterJob printerJob;

    public PdfPrinterGraphics2D() {
        super();
        this.printerJob = null;
    }

    public PdfPrinterGraphics2D(PdfContentByte cb, float width, float height, PrinterJob printerJob) {
        super(cb, width, height);
        this.printerJob = printerJob;
    }

    public PdfPrinterGraphics2D(PdfContentByte cb, float width, float height, boolean onlyShapes, PrinterJob printerJob) {
        super(cb, width, height, onlyShapes);
        this.printerJob = printerJob;
    }

    public PdfPrinterGraphics2D(PdfContentByte cb, float width, float height, FontMapper fontMapper, PrinterJob printerJob) {
        super(cb, width, height, fontMapper, false, false, 0.0F);
        this.printerJob = printerJob;
    }

    public PdfPrinterGraphics2D(PdfContentByte cb, float width, float height, FontMapper fontMapper, boolean onlyShapes, boolean convertImagesToJPEG, float quality, PrinterJob printerJob) {
        super(cb, width, height, fontMapper, onlyShapes, convertImagesToJPEG, quality);
        this.printerJob = printerJob;
    }

    @Override
    public PrinterJob getPrinterJob() {
        return this.printerJob;
    }
}
