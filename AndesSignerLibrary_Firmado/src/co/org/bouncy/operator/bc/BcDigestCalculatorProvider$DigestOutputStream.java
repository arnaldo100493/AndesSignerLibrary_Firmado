// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BcDigestCalculatorProvider.java

package co.org.bouncy.operator.bc;

import co.org.bouncy.crypto.Digest;
import java.io.IOException;
import java.io.OutputStream;

// Referenced classes of package co.org.bouncy.operator.bc:
//            BcDigestCalculatorProvider

private class BcDigestCalculatorProvider$DigestOutputStream extends OutputStream
{

    public void write(byte bytes[], int off, int len)
        throws IOException
    {
        dig.update(bytes, off, len);
    }

    public void write(byte bytes[])
        throws IOException
    {
        dig.update(bytes, 0, bytes.length);
    }

    public void write(int b)
        throws IOException
    {
        dig.update((byte)b);
    }

    byte[] getDigest()
    {
        byte d[] = new byte[dig.getDigestSize()];
        dig.doFinal(d, 0);
        return d;
    }

    private Digest dig;
    final BcDigestCalculatorProvider this$0;

    BcDigestCalculatorProvider$DigestOutputStream(Digest dig)
    {
        this$0 = BcDigestCalculatorProvider.this;
        super();
        this.dig = dig;
    }
}
