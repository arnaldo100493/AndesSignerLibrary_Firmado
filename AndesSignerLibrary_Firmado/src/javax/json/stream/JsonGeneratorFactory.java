// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JsonGeneratorFactory.java

package javax.json.stream;

import java.io.OutputStream;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.Map;

// Referenced classes of package javax.json.stream:
//            JsonGenerator

public interface JsonGeneratorFactory
{

    public abstract JsonGenerator createGenerator(Writer writer);

    public abstract JsonGenerator createGenerator(OutputStream outputstream);

    public abstract JsonGenerator createGenerator(OutputStream outputstream, Charset charset);

    public abstract Map getConfigInUse();
}
