// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JsonWriter.java

package javax.json;

import java.io.Closeable;

// Referenced classes of package javax.json:
//            JsonArray, JsonObject, JsonStructure

public interface JsonWriter
    extends Closeable
{

    public abstract void writeArray(JsonArray jsonarray);

    public abstract void writeObject(JsonObject jsonobject);

    public abstract void write(JsonStructure jsonstructure);

    public abstract void close();
}
