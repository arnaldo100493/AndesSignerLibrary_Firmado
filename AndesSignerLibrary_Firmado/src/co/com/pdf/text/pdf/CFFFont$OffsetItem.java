// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CFFFont.java

package co.com.pdf.text.pdf;


// Referenced classes of package co.com.pdf.text.pdf:
//            CFFFont

protected static abstract class CFFFont$OffsetItem extends CFFFont.Item
{

    public void set(int offset)
    {
        value = offset;
    }

    public int value;

    protected CFFFont$OffsetItem()
    {
    }
}
