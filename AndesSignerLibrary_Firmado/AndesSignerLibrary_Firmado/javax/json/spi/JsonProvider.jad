// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JsonProvider.java

package javax.json.spi;

import java.io.*;
import java.util.*;
import javax.json.*;
import javax.json.stream.*;

public abstract class JsonProvider
{

    protected JsonProvider()
    {
    }

    public static JsonProvider provider()
    {
        ServiceLoader loader = ServiceLoader.load(javax/json/spi/JsonProvider);
        Iterator it = loader.iterator();
        if(it.hasNext())
            return (JsonProvider)it.next();
        try
        {
            Class clazz = Class.forName("org.glassfish.json.JsonProviderImpl");
            return (JsonProvider)clazz.newInstance();
        }
        catch(ClassNotFoundException x)
        {
            throw new JsonException("Provider org.glassfish.json.JsonProviderImpl not found", x);
        }
        catch(Exception x)
        {
            throw new JsonException((new StringBuilder()).append("Provider org.glassfish.json.JsonProviderImpl could not be instantiated: ").append(x).toString(), x);
        }
    }

    public abstract JsonParser createParser(Reader reader);

    public abstract JsonParser createParser(InputStream inputstream);

    public abstract JsonParserFactory createParserFactory(Map map);

    public abstract JsonGenerator createGenerator(Writer writer);

    public abstract JsonGenerator createGenerator(OutputStream outputstream);

    public abstract JsonGeneratorFactory createGeneratorFactory(Map map);

    public abstract JsonReader createReader(Reader reader);

    public abstract JsonReader createReader(InputStream inputstream);

    public abstract JsonWriter createWriter(Writer writer);

    public abstract JsonWriter createWriter(OutputStream outputstream);

    public abstract JsonWriterFactory createWriterFactory(Map map);

    public abstract JsonReaderFactory createReaderFactory(Map map);

    public abstract JsonObjectBuilder createObjectBuilder();

    public abstract JsonArrayBuilder createArrayBuilder();

    public abstract JsonBuilderFactory createBuilderFactory(Map map);

    private static final String DEFAULT_PROVIDER = "org.glassfish.json.JsonProviderImpl";
}
