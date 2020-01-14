// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JsonWriterFactory.java

package javax.json;

import java.io.OutputStream;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.Map;

// Referenced classes of package javax.json:
//            JsonWriter

public interface JsonWriterFactory
{

    public abstract JsonWriter createWriter(Writer writer);

    public abstract JsonWriter createWriter(OutputStream outputstream);

    public abstract JsonWriter createWriter(OutputStream outputstream, Charset charset);

    public abstract Map getConfigInUse();
}
