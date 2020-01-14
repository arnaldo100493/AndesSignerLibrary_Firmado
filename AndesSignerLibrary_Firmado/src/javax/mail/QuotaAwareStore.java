// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   QuotaAwareStore.java

package javax.mail;


// Referenced classes of package javax.mail:
//            MessagingException, Quota

public interface QuotaAwareStore
{

    public abstract Quota[] getQuota(String s)
        throws MessagingException;

    public abstract void setQuota(Quota quota)
        throws MessagingException;
}
