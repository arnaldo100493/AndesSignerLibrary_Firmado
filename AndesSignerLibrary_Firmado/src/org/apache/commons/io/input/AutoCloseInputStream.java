// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AutoCloseInputStream.java

package org.apache.commons.io.input;

import java.io.IOException;
import java.io.InputStream;

// Referenced classes of package org.apache.commons.io.input:
//            ProxyInputStream, ClosedInputStream

public class AutoCloseInputStream extends ProxyInputStream
{

    public AutoCloseInputStream(InputStream in)
    {
        super(in);
    }

    public void close()
        throws IOException
    {
        in.close();
        in = new ClosedInputStream();
    }

    protected void afterRead(int n)
        throws IOException
    {
        if(n == -1)
            close();
    }

    protected void finalize()
        throws Throwable
    {
        close();
        super.finalize();
    }
}
