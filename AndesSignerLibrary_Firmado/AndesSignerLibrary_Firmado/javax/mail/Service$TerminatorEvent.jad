// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Service.java

package javax.mail;

import javax.mail.event.MailEvent;

// Referenced classes of package javax.mail:
//            Service

static class Service$TerminatorEvent extends MailEvent
{

    public void dispatch(Object listener)
    {
        Thread.currentThread().interrupt();
    }

    private static final long serialVersionUID = 0x4ce9c033019effa0L;

    Service$TerminatorEvent()
    {
        super(new Object());
    }
}
