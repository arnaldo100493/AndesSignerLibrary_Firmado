// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ProxyInputStream.java

package org.apache.commons.io.input;

import java.io.*;

public abstract class ProxyInputStream extends FilterInputStream
{

    public ProxyInputStream(InputStream proxy)
    {
        super(proxy);
    }

    public int read()
        throws IOException
    {
        try
        {
            beforeRead(1);
            int b = in.read();
            afterRead(b == -1 ? -1 : 1);
            return b;
        }
        catch(IOException e)
        {
            handleIOException(e);
        }
        return -1;
    }

    public int read(byte bts[])
        throws IOException
    {
        try
        {
            beforeRead(bts == null ? 0 : bts.length);
            int n = in.read(bts);
            afterRead(n);
            return n;
        }
        catch(IOException e)
        {
            handleIOException(e);
        }
        return -1;
    }

    public int read(byte bts[], int off, int len)
        throws IOException
    {
        try
        {
            beforeRead(len);
            int n = in.read(bts, off, len);
            afterRead(n);
            return n;
        }
        catch(IOException e)
        {
            handleIOException(e);
        }
        return -1;
    }

    public long skip(long ln)
        throws IOException
    {
        try
        {
            return in.skip(ln);
        }
        catch(IOException e)
        {
            handleIOException(e);
        }
        return 0L;
    }

    public int available()
        throws IOException
    {
        try
        {
            return super.available();
        }
        catch(IOException e)
        {
            handleIOException(e);
        }
        return 0;
    }

    public void close()
        throws IOException
    {
        try
        {
            in.close();
        }
        catch(IOException e)
        {
            handleIOException(e);
        }
    }

    public synchronized void mark(int readlimit)
    {
        in.mark(readlimit);
    }

    public synchronized void reset()
        throws IOException
    {
        try
        {
            in.reset();
        }
        catch(IOException e)
        {
            handleIOException(e);
        }
    }

    public boolean markSupported()
    {
        return in.markSupported();
    }

    protected void beforeRead(int i)
        throws IOException
    {
    }

    protected void afterRead(int i)
        throws IOException
    {
    }

    protected void handleIOException(IOException e)
        throws IOException
    {
        throw e;
    }
}
