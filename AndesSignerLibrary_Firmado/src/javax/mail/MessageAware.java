// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MessageAware.java

package javax.mail;


// Referenced classes of package javax.mail:
//            MessageContext

public interface MessageAware
{

    public abstract MessageContext getMessageContext();
}
