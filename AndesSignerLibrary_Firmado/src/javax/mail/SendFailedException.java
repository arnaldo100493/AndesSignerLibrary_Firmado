// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SendFailedException.java

package javax.mail;


// Referenced classes of package javax.mail:
//            MessagingException, Address

public class SendFailedException extends MessagingException
{

    public SendFailedException()
    {
    }

    public SendFailedException(String s)
    {
        super(s);
    }

    public SendFailedException(String s, Exception e)
    {
        super(s, e);
    }

    public SendFailedException(String msg, Exception ex, Address validSent[], Address validUnsent[], Address invalid[])
    {
        super(msg, ex);
        this.validSent = validSent;
        this.validUnsent = validUnsent;
        this.invalid = invalid;
    }

    public Address[] getValidSentAddresses()
    {
        return validSent;
    }

    public Address[] getValidUnsentAddresses()
    {
        return validUnsent;
    }

    public Address[] getInvalidAddresses()
    {
        return invalid;
    }

    protected transient Address invalid[];
    protected transient Address validSent[];
    protected transient Address validUnsent[];
    private static final long serialVersionUID = 0xa6623d341bc51ecfL;
}
