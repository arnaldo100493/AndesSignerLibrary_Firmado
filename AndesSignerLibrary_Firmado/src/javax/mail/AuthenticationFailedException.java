// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AuthenticationFailedException.java

package javax.mail;


// Referenced classes of package javax.mail:
//            MessagingException

public class AuthenticationFailedException extends MessagingException
{

    public AuthenticationFailedException()
    {
    }

    public AuthenticationFailedException(String message)
    {
        super(message);
    }

    public AuthenticationFailedException(String message, Exception e)
    {
        super(message, e);
    }

    private static final long serialVersionUID = 0x6d438d6b238969fL;
}
