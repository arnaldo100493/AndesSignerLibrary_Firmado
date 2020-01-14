// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Flags.java

package javax.mail;


// Referenced classes of package javax.mail:
//            Flags

public static final class Flags$Flag
{

    public static final Flags$Flag ANSWERED = new Flags$Flag(1);
    public static final Flags$Flag DELETED = new Flags$Flag(2);
    public static final Flags$Flag DRAFT = new Flags$Flag(4);
    public static final Flags$Flag FLAGGED = new Flags$Flag(8);
    public static final Flags$Flag RECENT = new Flags$Flag(16);
    public static final Flags$Flag SEEN = new Flags$Flag(32);
    public static final Flags$Flag USER = new Flags$Flag(0x80000000);
    private int bit;



    private Flags$Flag(int bit)
    {
        this.bit = bit;
    }
}
