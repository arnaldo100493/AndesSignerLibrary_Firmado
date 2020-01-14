// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JsonParser.java

package javax.json.stream;


// Referenced classes of package javax.json.stream:
//            JsonParser

public static final class JsonParser$Event extends Enum
{

    public static JsonParser$Event[] values()
    {
        return (JsonParser$Event[])$VALUES.clone();
    }

    public static JsonParser$Event valueOf(String name)
    {
        return (JsonParser$Event)Enum.valueOf(javax/json/stream/JsonParser$Event, name);
    }

    public static final JsonParser$Event START_ARRAY;
    public static final JsonParser$Event START_OBJECT;
    public static final JsonParser$Event KEY_NAME;
    public static final JsonParser$Event VALUE_STRING;
    public static final JsonParser$Event VALUE_NUMBER;
    public static final JsonParser$Event VALUE_TRUE;
    public static final JsonParser$Event VALUE_FALSE;
    public static final JsonParser$Event VALUE_NULL;
    public static final JsonParser$Event END_OBJECT;
    public static final JsonParser$Event END_ARRAY;
    private static final JsonParser$Event $VALUES[];

    static 
    {
        START_ARRAY = new JsonParser$Event("START_ARRAY", 0);
        START_OBJECT = new JsonParser$Event("START_OBJECT", 1);
        KEY_NAME = new JsonParser$Event("KEY_NAME", 2);
        VALUE_STRING = new JsonParser$Event("VALUE_STRING", 3);
        VALUE_NUMBER = new JsonParser$Event("VALUE_NUMBER", 4);
        VALUE_TRUE = new JsonParser$Event("VALUE_TRUE", 5);
        VALUE_FALSE = new JsonParser$Event("VALUE_FALSE", 6);
        VALUE_NULL = new JsonParser$Event("VALUE_NULL", 7);
        END_OBJECT = new JsonParser$Event("END_OBJECT", 8);
        END_ARRAY = new JsonParser$Event("END_ARRAY", 9);
        $VALUES = (new JsonParser$Event[] {
            START_ARRAY, START_OBJECT, KEY_NAME, VALUE_STRING, VALUE_NUMBER, VALUE_TRUE, VALUE_FALSE, VALUE_NULL, END_OBJECT, END_ARRAY
        });
    }

    private JsonParser$Event(String s, int i)
    {
        super(s, i);
    }
}
