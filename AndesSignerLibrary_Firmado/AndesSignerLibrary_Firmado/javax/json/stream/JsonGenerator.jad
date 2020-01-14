// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JsonGenerator.java

package javax.json.stream;

import java.io.Closeable;
import java.io.Flushable;
import java.math.BigDecimal;
import java.math.BigInteger;
import javax.json.JsonValue;

public interface JsonGenerator
    extends Flushable, Closeable
{

    public abstract JsonGenerator writeStartObject();

    public abstract JsonGenerator writeStartObject(String s);

    public abstract JsonGenerator writeStartArray();

    public abstract JsonGenerator writeStartArray(String s);

    public abstract JsonGenerator write(String s, JsonValue jsonvalue);

    public abstract JsonGenerator write(String s, String s1);

    public abstract JsonGenerator write(String s, BigInteger biginteger);

    public abstract JsonGenerator write(String s, BigDecimal bigdecimal);

    public abstract JsonGenerator write(String s, int i);

    public abstract JsonGenerator write(String s, long l);

    public abstract JsonGenerator write(String s, double d);

    public abstract JsonGenerator write(String s, boolean flag);

    public abstract JsonGenerator writeNull(String s);

    public abstract JsonGenerator writeEnd();

    public abstract JsonGenerator write(JsonValue jsonvalue);

    public abstract JsonGenerator write(String s);

    public abstract JsonGenerator write(BigDecimal bigdecimal);

    public abstract JsonGenerator write(BigInteger biginteger);

    public abstract JsonGenerator write(int i);

    public abstract JsonGenerator write(long l);

    public abstract JsonGenerator write(double d);

    public abstract JsonGenerator write(boolean flag);

    public abstract JsonGenerator writeNull();

    public abstract void close();

    public abstract void flush();

    public static final String PRETTY_PRINTING = "javax.json.stream.JsonGenerator.prettyPrinting";
}
