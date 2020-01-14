// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JsonParsingException.java

package javax.json.stream;

import javax.json.JsonException;

// Referenced classes of package javax.json.stream:
//            JsonLocation

public class JsonParsingException extends JsonException
{

    public JsonParsingException(String message, JsonLocation location)
    {
        super(message);
        this.location = location;
    }

    public JsonParsingException(String message, Throwable cause, JsonLocation location)
    {
        super(message, cause);
        this.location = location;
    }

    public JsonLocation getLocation()
    {
        return location;
    }

    private final JsonLocation location;
}
