// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   FetchProfile.java

package javax.mail;

import java.util.Vector;

public class FetchProfile
{
    public static class Item
    {

        public String toString()
        {
            return (new StringBuilder()).append(getClass().getName()).append("[").append(name).append("]").toString();
        }

        public static final Item ENVELOPE = new Item("ENVELOPE");
        public static final Item CONTENT_INFO = new Item("CONTENT_INFO");
        public static final Item SIZE = new Item("SIZE");
        public static final Item FLAGS = new Item("FLAGS");
        private String name;


        protected Item(String name)
        {
            this.name = name;
        }
    }


    public FetchProfile()
    {
        specials = null;
        headers = null;
    }

    public void add(Item item)
    {
        if(specials == null)
            specials = new Vector();
        specials.addElement(item);
    }

    public void add(String headerName)
    {
        if(headers == null)
            headers = new Vector();
        headers.addElement(headerName);
    }

    public boolean contains(Item item)
    {
        return specials != null && specials.contains(item);
    }

    public boolean contains(String headerName)
    {
        return headers != null && headers.contains(headerName);
    }

    public Item[] getItems()
    {
        if(specials == null)
        {
            return new Item[0];
        } else
        {
            Item s[] = new Item[specials.size()];
            specials.copyInto(s);
            return s;
        }
    }

    public String[] getHeaderNames()
    {
        if(headers == null)
        {
            return new String[0];
        } else
        {
            String s[] = new String[headers.size()];
            headers.copyInto(s);
            return s;
        }
    }

    private Vector specials;
    private Vector headers;
}
