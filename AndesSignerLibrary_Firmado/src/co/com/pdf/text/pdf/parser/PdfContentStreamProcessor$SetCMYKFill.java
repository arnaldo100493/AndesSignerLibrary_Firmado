// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PdfContentStreamProcessor.java

package co.com.pdf.text.pdf.parser;

import co.com.pdf.text.pdf.PdfLiteral;
import java.util.ArrayList;

// Referenced classes of package co.com.pdf.text.pdf.parser:
//            ContentOperator, PdfContentStreamProcessor, GraphicsState

private static class PdfContentStreamProcessor$SetCMYKFill
    implements ContentOperator
{

    public void invoke(PdfContentStreamProcessor processor, PdfLiteral operator, ArrayList operands)
    {
        PdfContentStreamProcessor.access$3700(processor).fillColor = PdfContentStreamProcessor.access$4400(4, operands);
    }

    private PdfContentStreamProcessor$SetCMYKFill()
    {
    }

    PdfContentStreamProcessor$SetCMYKFill(PdfContentStreamProcessor._cls1 x0)
    {
        this();
    }
}
