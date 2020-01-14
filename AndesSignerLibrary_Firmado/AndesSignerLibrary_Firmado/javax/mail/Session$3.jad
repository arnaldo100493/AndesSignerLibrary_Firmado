// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Session.java

package javax.mail;

import java.security.PrivilegedAction;

// Referenced classes of package javax.mail:
//            Session

static class Session$3
    implements PrivilegedAction
{

    public Object run()
    {
        ClassLoader cl = null;
        try
        {
            cl = Thread.currentThread().getContextClassLoader();
        }
        catch(SecurityException ex) { }
        return cl;
    }

    Session$3()
    {
    }
}
