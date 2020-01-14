// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   NoSuchProviderException.java

package javax.mail;


// Referenced classes of package javax.mail:
//            MessagingException

public class NoSuchProviderException extends MessagingException
{

    public NoSuchProviderException()
    {
    }

    public NoSuchProviderException(String message)
    {
        super(message);
    }

    public NoSuchProviderException(String message, Exception e)
    {
        super(message, e);
    }

    private static final long serialVersionUID = 0x6fd4e6b3cb29cd5bL;
}
