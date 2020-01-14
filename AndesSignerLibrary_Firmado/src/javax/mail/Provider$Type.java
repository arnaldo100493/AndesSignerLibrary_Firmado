// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Provider.java

package javax.mail;


// Referenced classes of package javax.mail:
//            Provider

public static class Provider$Type
{

    public String toString()
    {
        return type;
    }

    public static final Provider$Type STORE = new Provider$Type("STORE");
    public static final Provider$Type TRANSPORT = new Provider$Type("TRANSPORT");
    private String type;


    private Provider$Type(String type)
    {
        this.type = type;
    }
}
