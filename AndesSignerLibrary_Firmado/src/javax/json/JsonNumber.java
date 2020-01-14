// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JsonNumber.java

package javax.json;

import java.math.BigDecimal;
import java.math.BigInteger;

// Referenced classes of package javax.json:
//            JsonValue

public interface JsonNumber
    extends JsonValue
{

    public abstract boolean isIntegral();

    public abstract int intValue();

    public abstract int intValueExact();

    public abstract long longValue();

    public abstract long longValueExact();

    public abstract BigInteger bigIntegerValue();

    public abstract BigInteger bigIntegerValueExact();

    public abstract double doubleValue();

    public abstract BigDecimal bigDecimalValue();

    public abstract String toString();

    public abstract boolean equals(Object obj);

    public abstract int hashCode();
}
