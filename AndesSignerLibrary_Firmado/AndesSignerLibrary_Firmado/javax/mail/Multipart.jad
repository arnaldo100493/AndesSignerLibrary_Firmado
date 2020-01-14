// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Multipart.java

package javax.mail;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Vector;

// Referenced classes of package javax.mail:
//            BodyPart, MessagingException, MultipartDataSource, Part

public abstract class Multipart
{

    protected Multipart()
    {
        parts = new Vector();
        contentType = "multipart/mixed";
    }

    protected synchronized void setMultipartDataSource(MultipartDataSource mp)
        throws MessagingException
    {
        contentType = mp.getContentType();
        int count = mp.getCount();
        for(int i = 0; i < count; i++)
            addBodyPart(mp.getBodyPart(i));

    }

    public synchronized String getContentType()
    {
        return contentType;
    }

    public synchronized int getCount()
        throws MessagingException
    {
        if(parts == null)
            return 0;
        else
            return parts.size();
    }

    public synchronized BodyPart getBodyPart(int index)
        throws MessagingException
    {
        if(parts == null)
            throw new IndexOutOfBoundsException("No such BodyPart");
        else
            return (BodyPart)parts.elementAt(index);
    }

    public synchronized boolean removeBodyPart(BodyPart part)
        throws MessagingException
    {
        if(parts == null)
        {
            throw new MessagingException("No such body part");
        } else
        {
            boolean ret = parts.removeElement(part);
            part.setParent(null);
            return ret;
        }
    }

    public synchronized void removeBodyPart(int index)
        throws MessagingException
    {
        if(parts == null)
        {
            throw new IndexOutOfBoundsException("No such BodyPart");
        } else
        {
            BodyPart part = (BodyPart)parts.elementAt(index);
            parts.removeElementAt(index);
            part.setParent(null);
            return;
        }
    }

    public synchronized void addBodyPart(BodyPart part)
        throws MessagingException
    {
        if(parts == null)
            parts = new Vector();
        parts.addElement(part);
        part.setParent(this);
    }

    public synchronized void addBodyPart(BodyPart part, int index)
        throws MessagingException
    {
        if(parts == null)
            parts = new Vector();
        parts.insertElementAt(part, index);
        part.setParent(this);
    }

    public abstract void writeTo(OutputStream outputstream)
        throws IOException, MessagingException;

    public synchronized Part getParent()
    {
        return parent;
    }

    public synchronized void setParent(Part parent)
    {
        this.parent = parent;
    }

    protected Vector parts;
    protected String contentType;
    protected Part parent;
}
