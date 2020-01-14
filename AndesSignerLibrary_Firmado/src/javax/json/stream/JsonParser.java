// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JsonParser.java

package javax.json.stream;

import java.io.Closeable;
import java.math.BigDecimal;

// Referenced classes of package javax.json.stream:
//            JsonLocation

public interface JsonParser
    extends Closeable
{
    public static final class Event extends Enum
    {

        public static Event[] values()
        {
            return (Event[])$VALUES.clone();
        }

        public static Event valueOf(String name)
        {
            return (Event)Enum.valueOf(javax/json/stream/JsonParser$Event, name);
        }

        public static final Event START_ARRAY;
        public static final Event START_OBJECT;
        public static final Event KEY_NAME;
        public static final Event VALUE_STRING;
        public static final Event VALUE_NUMBER;
        public static final Event VALUE_TRUE;
        public static final Event VALUE_FALSE;
        public static final Event VALUE_NULL;
        public static final Event END_OBJECT;
        public static final Event END_ARRAY;
        private static final Event $VALUES[];

        static 
        {
            START_ARRAY = new Event("START_ARRAY", 0);
            START_OBJECT = new Event("START_OBJECT", 1);
            KEY_NAME = new Event("KEY_NAME", 2);
            VALUE_STRING = new Event("VALUE_STRING", 3);
            VALUE_NUMBER = new Event("VALUE_NUMBER", 4);
            VALUE_TRUE = new Event("VALUE_TRUE", 5);
            VALUE_FALSE = new Event("VALUE_FALSE", 6);
            VALUE_NULL = new Event("VALUE_NULL", 7);
            END_OBJECT = new Event("END_OBJECT", 8);
            END_ARRAY = new Event("END_ARRAY", 9);
            $VALUES = (new Event[] {
                START_ARRAY, START_OBJECT, KEY_NAME, VALUE_STRING, VALUE_NUMBER, VALUE_TRUE, VALUE_FALSE, VALUE_NULL, END_OBJECT, END_ARRAY
            });
        }

        private Event(String s, int i)
        {
            super(s, i);
        }
    }


    public abstract boolean hasNext();

    public abstract Event next();

    public abstract String getString();

    public abstract boolean isIntegralNumber();

    public abstract int getInt();

    public abstract long getLong();

    public abstract BigDecimal getBigDecimal();

    public abstract JsonLocation getLocation();

    public abstract void close();
}
