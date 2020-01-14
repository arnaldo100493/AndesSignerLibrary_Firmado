// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Session.java

package javax.mail;

import java.io.IOException;
import java.net.URL;
import java.security.PrivilegedAction;
import java.util.Enumeration;
import java.util.Vector;

// Referenced classes of package javax.mail:
//            Session

static class Session$5
    implements PrivilegedAction
{

    public Object run()
    {
        URL ret[] = null;
        try
        {
            Vector v = new Vector();
            Enumeration e = val$cl.getResources(val$name);
            do
            {
                if(e == null || !e.hasMoreElements())
                    break;
                URL url = (URL)e.nextElement();
                if(url != null)
                    v.addElement(url);
            } while(true);
            if(v.size() > 0)
            {
                ret = new URL[v.size()];
                v.copyInto(ret);
            }
        }
        catch(IOException ioex) { }
        catch(SecurityException ex) { }
        return ret;
    }

    final ClassLoader val$cl;
    final String val$name;

    Session$5(ClassLoader classloader, String s)
    {
        val$cl = classloader;
        val$name = s;
        super();
    }
}
