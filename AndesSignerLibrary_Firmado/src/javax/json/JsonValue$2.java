// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JsonValue.java

package javax.json;


// Referenced classes of package javax.json:
//            JsonValue

static class JsonValue$2
    implements JsonValue
{

    public lueType getValueType()
    {
        return lueType.TRUE;
    }

    public boolean equals(Object obj)
    {
        if(obj instanceof JsonValue)
            return getValueType().equals(((JsonValue)obj).getValueType());
        else
            return false;
    }

    public int hashCode()
    {
        return lueType.TRUE.hashCode();
    }

    public String toString()
    {
        return "true";
    }

    JsonValue$2()
    {
    }
}
