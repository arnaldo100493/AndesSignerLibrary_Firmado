// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PdfPTableEventAfterSplit.java

package co.com.pdf.text.pdf;


// Referenced classes of package co.com.pdf.text.pdf:
//            PdfPTableEventSplit, PdfPTable, PdfPRow

public interface PdfPTableEventAfterSplit
    extends PdfPTableEventSplit
{

    public abstract void afterSplitTable(PdfPTable pdfptable, PdfPRow pdfprow, int i);
}
