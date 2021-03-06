// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Message.java

package javax.mail;

import java.io.*;
import java.util.Date;
import javax.mail.search.SearchTerm;

// Referenced classes of package javax.mail:
//            Address, MethodNotSupportedException, Flags, Part, 
//            MessagingException, Folder, Store, Session

public abstract class Message
    implements Part
{
    public static class RecipientType
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

        public static final RecipientType TO = new RecipientType("To");
        public static final RecipientType CC = new RecipientType("Cc");
        public static final RecipientType BCC = new RecipientType("Bcc");
        protected String type;
        private static final long serialVersionUID = 0x983271084f364058L;


        protected RecipientType(String type)
        {
            this.type = type;
        }
    }


    protected Message()
    {
        msgnum = 0;
        expunged = false;
        folder = null;
        session = null;
    }

    protected Message(Folder folder, int msgnum)
    {
        this.msgnum = 0;
        expunged = false;
        this.folder = null;
        session = null;
        this.folder = folder;
        this.msgnum = msgnum;
        session = folder.store.session;
    }

    protected Message(Session session)
    {
        msgnum = 0;
        expunged = false;
        folder = null;
        this.session = null;
        this.session = session;
    }

    public Session getSession()
    {
        return session;
    }

    public abstract Address[] getFrom()
        throws MessagingException;

    public abstract void setFrom()
        throws MessagingException;

    public abstract void setFrom(Address address)
        throws MessagingException;

    public abstract void addFrom(Address aaddress[])
        throws MessagingException;

    public abstract Address[] getRecipients(RecipientType recipienttype)
        throws MessagingException;

    public Address[] getAllRecipients()
        throws MessagingException
    {
        Address to[] = getRecipients(RecipientType.TO);
        Address cc[] = getRecipients(RecipientType.CC);
        Address bcc[] = getRecipients(RecipientType.BCC);
        if(cc == null && bcc == null)
            return to;
        int numRecip = (to == null ? 0 : to.length) + (cc == null ? 0 : cc.length) + (bcc == null ? 0 : bcc.length);
        Address addresses[] = new Address[numRecip];
        int pos = 0;
        if(to != null)
        {
            System.arraycopy(to, 0, addresses, pos, to.length);
            pos += to.length;
        }
        if(cc != null)
        {
            System.arraycopy(cc, 0, addresses, pos, cc.length);
            pos += cc.length;
        }
        if(bcc != null)
        {
            System.arraycopy(bcc, 0, addresses, pos, bcc.length);
            pos += bcc.length;
        }
        return addresses;
    }

    public abstract void setRecipients(RecipientType recipienttype, Address aaddress[])
        throws MessagingException;

    public void setRecipient(RecipientType type, Address address)
        throws MessagingException
    {
        Address a[] = new Address[1];
        a[0] = address;
        setRecipients(type, a);
    }

    public abstract void addRecipients(RecipientType recipienttype, Address aaddress[])
        throws MessagingException;

    public void addRecipient(RecipientType type, Address address)
        throws MessagingException
    {
        Address a[] = new Address[1];
        a[0] = address;
        addRecipients(type, a);
    }

    public Address[] getReplyTo()
        throws MessagingException
    {
        return getFrom();
    }

    public void setReplyTo(Address addresses[])
        throws MessagingException
    {
        throw new MethodNotSupportedException("setReplyTo not supported");
    }

    public abstract String getSubject()
        throws MessagingException;

    public abstract void setSubject(String s)
        throws MessagingException;

    public abstract Date getSentDate()
        throws MessagingException;

    public abstract void setSentDate(Date date)
        throws MessagingException;

    public abstract Date getReceivedDate()
        throws MessagingException;

    public abstract Flags getFlags()
        throws MessagingException;

    public boolean isSet(Flags.Flag flag)
        throws MessagingException
    {
        return getFlags().contains(flag);
    }

    public abstract void setFlags(Flags flags, boolean flag)
        throws MessagingException;

    public void setFlag(Flags.Flag flag, boolean set)
        throws MessagingException
    {
        Flags f = new Flags(flag);
        setFlags(f, set);
    }

    public int getMessageNumber()
    {
        return msgnum;
    }

    protected void setMessageNumber(int msgnum)
    {
        this.msgnum = msgnum;
    }

    public Folder getFolder()
    {
        return folder;
    }

    public boolean isExpunged()
    {
        return expunged;
    }

    protected void setExpunged(boolean expunged)
    {
        this.expunged = expunged;
    }

    public abstract Message reply(boolean flag)
        throws MessagingException;

    public abstract void saveChanges()
        throws MessagingException;

    public boolean match(SearchTerm term)
        throws MessagingException
    {
        return term.match(this);
    }

    protected int msgnum;
    protected boolean expunged;
    protected Folder folder;
    protected Session session;
}
