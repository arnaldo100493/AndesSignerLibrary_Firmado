// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Session.java

package javax.mail;

import java.io.IOException;
import java.io.InputStream;

// Referenced classes of package javax.mail:
//            StreamLoader, Session

class Session$1
    implements StreamLoader
{

    public void load(InputStream is)
        throws IOException
    {
        Session.access$000(Session.this, is);
    }

    final Session this$0;

    Session$1()
    {
        this$0 = Session.this;
        super();
    }
}
