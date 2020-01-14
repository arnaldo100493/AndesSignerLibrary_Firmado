// Decompiled by DJ v3.12.12.98 Copyright 2014 Atanas Neshkov  Date: 17/11/2017 7:07:02 p. m.
// Home Page: http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   ExceptionConverter.java

package co.com.pdf.text;

import java.io.PrintStream;
import java.io.PrintWriter;

public class ExceptionConverter extends RuntimeException
{

    public ExceptionConverter(Exception ex)
    {
        super(ex);
        this.ex = ex;
        prefix = (ex instanceof RuntimeException) ? "" : "ExceptionConverter: ";
    }

    public static final RuntimeException convertException(Exception ex)
    {
        if(ex instanceof RuntimeException)
            return (RuntimeException)ex;
        else
            return new ExceptionConverter(ex);
    }

    public Exception getException()
    {
        return ex;
    }

    public String getMessage()
    {
        return ex.getMessage();
    }

    public String getLocalizedMessage()
    {
        return ex.getLocalizedMessage();
    }

    public String toString()
    {
        return (new StringBuilder()).append(prefix).append(ex).toString();
    }

    public void printStackTrace()
    {
        printStackTrace(System.err);
    }

    public void printStackTrace(PrintStream s)
    {
        synchronized(s)
        {
            s.print(prefix);
            ex.printStackTrace(s);
        }
    }

    public void printStackTrace(PrintWriter s)
    {
        synchronized(s)
        {
            s.print(prefix);
            ex.printStackTrace(s);
        }
    }

    public Throwable fillInStackTrace()
    {
        return this;
    }

    private static final long serialVersionUID = 0x782614f206d87cb7L;
    private Exception ex;
    private String prefix;
}