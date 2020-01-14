// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JsonArrayBuilder.java

package javax.json;

import java.math.BigDecimal;
import java.math.BigInteger;

// Referenced classes of package javax.json:
//            JsonValue, JsonObjectBuilder, JsonArray

public interface JsonArrayBuilder
{

    public abstract JsonArrayBuilder add(JsonValue jsonvalue);

    public abstract JsonArrayBuilder add(String s);

    public abstract JsonArrayBuilder add(BigDecimal bigdecimal);

    public abstract JsonArrayBuilder add(BigInteger biginteger);

    public abstract JsonArrayBuilder add(int i);

    public abstract JsonArrayBuilder add(long l);

    public abstract JsonArrayBuilder add(double d);

    public abstract JsonArrayBuilder add(boolean flag);

    public abstract JsonArrayBuilder addNull();

    public abstract JsonArrayBuilder add(JsonObjectBuilder jsonobjectbuilder);

    public abstract JsonArrayBuilder add(JsonArrayBuilder jsonarraybuilder);

    public abstract JsonArray build();
}
