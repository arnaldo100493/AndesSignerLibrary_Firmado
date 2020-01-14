// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MessageContext.java

package javax.mail;


// Referenced classes of package javax.mail:
//            MessagingException, Message, BodyPart, Multipart, 
//            Session, Part

public class MessageContext
{

    public MessageContext(Part part)
    {
        this.part = part;
    }

    public Part getPart()
    {
        return part;
    }

    public Message getMessage()
    {
        try
        {
            return getMessage(part);
        }
        catch(MessagingException ex)
        {
            return null;
        }
    }

    private static Message getMessage(Part p)
        throws MessagingException
    {
        Multipart mp;
        for(; p != null; p = mp.getParent())
        {
            if(p instanceof Message)
                return (Message)p;
            BodyPart bp = (BodyPart)p;
            mp = bp.getParent();
            if(mp == null)
                return null;
        }

        return null;
    }

    public Session getSession()
    {
        Message msg = getMessage();
        return msg == null ? null : msg.getSession();
    }

    private Part part;
}
