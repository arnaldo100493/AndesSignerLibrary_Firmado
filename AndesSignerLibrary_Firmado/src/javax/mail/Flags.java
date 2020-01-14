// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Flags.java

package javax.mail;

import java.io.Serializable;
import java.util.*;

public class Flags
    implements Cloneable, Serializable
{
    public static final class Flag
    {

        public static final Flag ANSWERED = new Flag(1);
        public static final Flag DELETED = new Flag(2);
        public static final Flag DRAFT = new Flag(4);
        public static final Flag FLAGGED = new Flag(8);
        public static final Flag RECENT = new Flag(16);
        public static final Flag SEEN = new Flag(32);
        public static final Flag USER = new Flag(0x80000000);
        private int bit;



        private Flag(int bit)
        {
            this.bit = bit;
        }
    }


    public Flags()
    {
        system_flags = 0;
        user_flags = null;
    }

    public Flags(Flags flags)
    {
        system_flags = 0;
        user_flags = null;
        system_flags = flags.system_flags;
        if(flags.user_flags != null)
            user_flags = (Hashtable)flags.user_flags.clone();
    }

    public Flags(Flag flag)
    {
        system_flags = 0;
        user_flags = null;
        system_flags |= flag.bit;
    }

    public Flags(String flag)
    {
        system_flags = 0;
        user_flags = null;
        user_flags = new Hashtable(1);
        user_flags.put(flag.toLowerCase(Locale.ENGLISH), flag);
    }

    public void add(Flag flag)
    {
        system_flags |= flag.bit;
    }

    public void add(String flag)
    {
        if(user_flags == null)
            user_flags = new Hashtable(1);
        user_flags.put(flag.toLowerCase(Locale.ENGLISH), flag);
    }

    public void add(Flags f)
    {
        system_flags |= f.system_flags;
        if(f.user_flags != null)
        {
            if(user_flags == null)
                user_flags = new Hashtable(1);
            String s;
            for(Enumeration e = f.user_flags.keys(); e.hasMoreElements(); user_flags.put(s, f.user_flags.get(s)))
                s = (String)e.nextElement();

        }
    }

    public void remove(Flag flag)
    {
        system_flags &= ~flag.bit;
    }

    public void remove(String flag)
    {
        if(user_flags != null)
            user_flags.remove(flag.toLowerCase(Locale.ENGLISH));
    }

    public void remove(Flags f)
    {
        system_flags &= ~f.system_flags;
        if(f.user_flags != null)
        {
            if(user_flags == null)
                return;
            for(Enumeration e = f.user_flags.keys(); e.hasMoreElements(); user_flags.remove(e.nextElement()));
        }
    }

    public boolean contains(Flag flag)
    {
        return (system_flags & flag.bit) != 0;
    }

    public boolean contains(String flag)
    {
        if(user_flags == null)
            return false;
        else
            return user_flags.containsKey(flag.toLowerCase(Locale.ENGLISH));
    }

    public boolean contains(Flags f)
    {
label0:
        {
            if((f.system_flags & system_flags) != f.system_flags)
                return false;
            if(f.user_flags == null)
                break label0;
            if(user_flags == null)
                return false;
            Enumeration e = f.user_flags.keys();
            do
                if(!e.hasMoreElements())
                    break label0;
            while(user_flags.containsKey(e.nextElement()));
            return false;
        }
        return true;
    }

    public boolean equals(Object obj)
    {
        if(!(obj instanceof Flags))
            return false;
        Flags f = (Flags)obj;
        if(f.system_flags != system_flags)
            return false;
        if(f.user_flags == null && user_flags == null)
            return true;
        if(f.user_flags != null && user_flags != null && f.user_flags.size() == user_flags.size())
        {
            for(Enumeration e = f.user_flags.keys(); e.hasMoreElements();)
                if(!user_flags.containsKey(e.nextElement()))
                    return false;

            return true;
        } else
        {
            return false;
        }
    }

    public int hashCode()
    {
        int hash = system_flags;
        if(user_flags != null)
        {
            for(Enumeration e = user_flags.keys(); e.hasMoreElements();)
                hash += ((String)e.nextElement()).hashCode();

        }
        return hash;
    }

    public Flag[] getSystemFlags()
    {
        Vector v = new Vector();
        if((system_flags & 1) != 0)
            v.addElement(Flag.ANSWERED);
        if((system_flags & 2) != 0)
            v.addElement(Flag.DELETED);
        if((system_flags & 4) != 0)
            v.addElement(Flag.DRAFT);
        if((system_flags & 8) != 0)
            v.addElement(Flag.FLAGGED);
        if((system_flags & 0x10) != 0)
            v.addElement(Flag.RECENT);
        if((system_flags & 0x20) != 0)
            v.addElement(Flag.SEEN);
        if((system_flags & 0x80000000) != 0)
            v.addElement(Flag.USER);
        Flag f[] = new Flag[v.size()];
        v.copyInto(f);
        return f;
    }

    public String[] getUserFlags()
    {
        Vector v = new Vector();
        if(user_flags != null)
        {
            for(Enumeration e = user_flags.elements(); e.hasMoreElements(); v.addElement(e.nextElement()));
        }
        String f[] = new String[v.size()];
        v.copyInto(f);
        return f;
    }

    public Object clone()
    {
        Flags f = null;
        try
        {
            f = (Flags)super.clone();
        }
        catch(CloneNotSupportedException cex) { }
        if(user_flags != null)
            f.user_flags = (Hashtable)user_flags.clone();
        return f;
    }

    private int system_flags;
    private Hashtable user_flags;
    private static final int ANSWERED_BIT = 1;
    private static final int DELETED_BIT = 2;
    private static final int DRAFT_BIT = 4;
    private static final int FLAGGED_BIT = 8;
    private static final int RECENT_BIT = 16;
    private static final int SEEN_BIT = 32;
    private static final int USER_BIT = 0x80000000;
    private static final long serialVersionUID = 0x56a5b06539097bc4L;
}
