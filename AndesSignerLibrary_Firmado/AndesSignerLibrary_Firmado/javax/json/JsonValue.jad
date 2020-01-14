// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JsonValue.java

package javax.json;


public interface JsonValue
{
    public static final class ValueType extends Enum
    {

        public static ValueType[] values()
        {
            return (ValueType[])$VALUES.clone();
        }

        public static ValueType valueOf(String name)
        {
            return (ValueType)Enum.valueOf(javax/json/JsonValue$ValueType, name);
        }

        public static final ValueType ARRAY;
        public static final ValueType OBJECT;
        public static final ValueType STRING;
        public static final ValueType NUMBER;
        public static final ValueType TRUE;
        public static final ValueType FALSE;
        public static final ValueType NULL;
        private static final ValueType $VALUES[];

        static 
        {
            ARRAY = new ValueType("ARRAY", 0);
            OBJECT = new ValueType("OBJECT", 1);
            STRING = new ValueType("STRING", 2);
            NUMBER = new ValueType("NUMBER", 3);
            TRUE = new ValueType("TRUE", 4);
            FALSE = new ValueType("FALSE", 5);
            NULL = new ValueType("NULL", 6);
            $VALUES = (new ValueType[] {
                ARRAY, OBJECT, STRING, NUMBER, TRUE, FALSE, NULL
            });
        }

        private ValueType(String s, int i)
        {
            super(s, i);
        }
    }


    public abstract ValueType getValueType();

    public abstract String toString();

    public static final JsonValue NULL = new JsonValue() {

        public ValueType getValueType()
        {
            return ValueType.NULL;
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
            return ValueType.NULL.hashCode();
        }

        public String toString()
        {
            return "null";
        }

    }
;
    public static final JsonValue TRUE = new JsonValue() {

        public ValueType getValueType()
        {
            return ValueType.TRUE;
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
            return ValueType.TRUE.hashCode();
        }

        public String toString()
        {
            return "true";
        }

    }
;
    public static final JsonValue FALSE = new JsonValue() {

        public ValueType getValueType()
        {
            return ValueType.FALSE;
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
            return ValueType.FALSE.hashCode();
        }

        public String toString()
        {
            return "false";
        }

    }
;

}
