// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JsonParserFactory.java

package javax.json.stream;

import java.io.InputStream;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.Map;
import javax.json.JsonArray;
import javax.json.JsonObject;

// Referenced classes of package javax.json.stream:
//            JsonParser

public interface JsonParserFactory
{

    public abstract JsonParser createParser(Reader reader);

    public abstract JsonParser createParser(InputStream inputstream);

    public abstract JsonParser createParser(InputStream inputstream, Charset charset);

    public abstract JsonParser createParser(JsonObject jsonobject);

    public abstract JsonParser createParser(JsonArray jsonarray);

    public abstract Map getConfigInUse();
}
