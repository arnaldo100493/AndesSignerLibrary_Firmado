// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JsonString.java

package javax.json;


// Referenced classes of package javax.json:
//            JsonValue

public interface JsonString
    extends JsonValue
{

    public abstract String getString();

    public abstract CharSequence getChars();

    public abstract boolean equals(Object obj);

    public abstract int hashCode();
}
