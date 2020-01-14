// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Message.java

package javax.mail;

import java.io.*;

// Referenced classes of package javax.mail:
//            Message

public static class Message$RecipientType
    implements Serializable
{

    protected Object readResolve()
        throws ObjectStreamException
    {
        if(type.equals("To"))
            return TO;
        if(type.equals("Cc"))
            return CC;
        if(type.equals("Bcc"))
            return BCC;
        else
            throw new InvalidObjectException((new StringBuilder()).append("Attempt to resolve unknown RecipientType: ").append(type).toString());
    }

    public String toString()
    {
        return type;
    }

    public static final Message$RecipientType TO = new Message$RecipientType("To");
    public static final Message$RecipientType CC = new Message$RecipientType("Cc");
    public static final Message$RecipientType BCC = new Message$RecipientType("Bcc");
    protected String type;
    private static final long serialVersionUID = 0x983271084f364058L;


    protected Message$RecipientType(String type)
    {
        this.type = type;
    }
}
