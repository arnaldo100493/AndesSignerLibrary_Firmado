// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EACIOException.java

package co.org.bouncy.eac;

import java.io.IOException;

public class EACIOException extends IOException
{

    public EACIOException(String msg, Throwable cause)
    {
        super(msg);
        this.cause = cause;
    }

    public EACIOException(String msg)
    {
        super(msg);
    }

    public Throwable getCause()
    {
        return cause;
    }

    private Throwable cause;
}
