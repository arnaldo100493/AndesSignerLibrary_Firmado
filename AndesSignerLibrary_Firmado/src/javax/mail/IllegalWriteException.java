// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   IllegalWriteException.java

package javax.mail;


// Referenced classes of package javax.mail:
//            MessagingException

public class IllegalWriteException extends MessagingException
{

    public IllegalWriteException()
    {
    }

    public IllegalWriteException(String s)
    {
        super(s);
    }

    public IllegalWriteException(String s, Exception e)
    {
        super(s, e);
    }

    private static final long serialVersionUID = 0x3727cca9375f0eedL;
}
