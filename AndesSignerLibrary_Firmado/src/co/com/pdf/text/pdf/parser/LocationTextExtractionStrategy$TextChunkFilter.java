// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   LocationTextExtractionStrategy.java

package co.com.pdf.text.pdf.parser;


// Referenced classes of package co.com.pdf.text.pdf.parser:
//            LocationTextExtractionStrategy

public static interface LocationTextExtractionStrategy$TextChunkFilter
{

    public abstract boolean accept(LocationTextExtractionStrategy.TextChunk textchunk);
}
