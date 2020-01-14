// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Folder.java

package javax.mail;

import javax.mail.event.MailEvent;

// Referenced classes of package javax.mail:
//            Folder

static class Folder$TerminatorEvent extends MailEvent
{

    public void dispatch(Object listener)
    {
        Thread.currentThread().interrupt();
    }

    private static final long serialVersionUID = 0x3442ac84f29e98b5L;

    Folder$TerminatorEvent()
    {
        super(new Object());
    }
}
