// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Json.java

package javax.json;

import java.io.*;
import java.util.Map;
import javax.json.spi.JsonProvider;
import javax.json.stream.JsonGenerator;
import javax.json.stream.JsonGeneratorFactory;
import javax.json.stream.JsonParser;
import javax.json.stream.JsonParserFactory;

// Referenced classes of package javax.json:
//            JsonWriter, JsonReader, JsonReaderFactory, JsonWriterFactory, 
//            JsonArrayBuilder, JsonObjectBuilder, JsonBuilderFactory

public class Json
{

    private Json()
    {
    }

    public static JsonParser createParser(Reader reader)
    {
        return JsonProvider.provider().createParser(reader);
    }

    public static JsonParser createParser(InputStream in)
    {
        return JsonProvider.provider().createParser(in);
    }

    public static JsonGenerator createGenerator(Writer writer)
    {
        return JsonProvider.provider().createGenerator(writer);
    }

    public static JsonGenerator createGenerator(OutputStream out)
    {
        return JsonProvider.provider().createGenerator(out);
    }

    public static JsonParserFactory createParserFactory(Map config)
    {
        return JsonProvider.provider().createParserFactory(config);
    }

    public static JsonGeneratorFactory createGeneratorFactory(Map config)
    {
        return JsonProvider.provider().createGeneratorFactory(config);
    }

    public static JsonWriter createWriter(Writer writer)
    {
        return JsonProvider.provider().createWriter(writer);
    }

    public static JsonWriter createWriter(OutputStream out)
    {
        return JsonProvider.provider().createWriter(out);
    }

    public static JsonReader createReader(Reader reader)
    {
        return JsonProvider.provider().createReader(reader);
    }

    public static JsonReader createReader(InputStream in)
    {
        return JsonProvider.provider().createReader(in);
    }

    public static JsonReaderFactory createReaderFactory(Map config)
    {
        return JsonProvider.provider().createReaderFactory(config);
    }

    public static JsonWriterFactory createWriterFactory(Map config)
    {
        return JsonProvider.provider().createWriterFactory(config);
    }

    public static JsonArrayBuilder createArrayBuilder()
    {
        return JsonProvider.provider().createArrayBuilder();
    }

    public static JsonObjectBuilder createObjectBuilder()
    {
        return JsonProvider.provider().createObjectBuilder();
    }

    public static JsonBuilderFactory createBuilderFactory(Map config)
    {
        return JsonProvider.provider().createBuilderFactory(config);
    }
}
