// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MessagingException.java

package javax.mail;


public class MessagingException extends Exception
{

    public MessagingException()
    {
        initCause(null);
    }

    public MessagingException(String s)
    {
        super(s);
        initCause(null);
    }

    public MessagingException(String s, Exception e)
    {
        super(s);
        next = e;
        initCause(null);
    }

    public synchronized Exception getNextException()
    {
        return next;
    }

    public synchronized Throwable getCause()
    {
        return next;
    }

    public synchronized boolean setNextException(Exception ex)
    {
        Exception theEnd;
        for(theEnd = this; (theEnd instanceof MessagingException) && ((MessagingException)theEnd).next != null; theEnd = ((MessagingException)theEnd).next);
        if(theEnd instanceof MessagingException)
        {
            ((MessagingException)theEnd).next = ex;
            return true;
        } else
        {
            return false;
        }
    }

    public synchronized String toString()
    {
        String s = super.toString();
        Exception n = next;
        if(n == null)
            return s;
        StringBuffer sb = new StringBuffer(s != null ? s : "");
        while(n != null) 
        {
            sb.append(";\n  nested exception is:\n\t");
            if(n instanceof MessagingException)
            {
                MessagingException mex = (MessagingException)n;
                sb.append(mex.superToString());
                n = mex.next;
            } else
            {
                sb.append(n.toString());
                n = null;
            }
        }
        return sb.toString();
    }

    private final String superToString()
    {
        return super.toString();
    }

    private Exception next;
    private static final long serialVersionUID = 0x96f4d3b738a7e02bL;
}
