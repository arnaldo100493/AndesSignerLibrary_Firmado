// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Provider.java

package javax.mail;


public class Provider
{
    public static class Type
    {

        public String toString()
        {
            return type;
        }

        public static final Type STORE = new Type("STORE");
        public static final Type TRANSPORT = new Type("TRANSPORT");
        private String type;


        private Type(String type)
        {
            this.type = type;
        }
    }


    public Provider(Type type, String protocol, String classname, String vendor, String version)
    {
        this.type = type;
        this.protocol = protocol;
        className = classname;
        this.vendor = vendor;
        this.version = version;
    }

    public Type getType()
    {
        return type;
    }

    public String getProtocol()
    {
        return protocol;
    }

    public String getClassName()
    {
        return className;
    }

    public String getVendor()
    {
        return vendor;
    }

    public String getVersion()
    {
        return version;
    }

    public String toString()
    {
        String s = (new StringBuilder()).append("javax.mail.Provider[").append(type).append(",").append(protocol).append(",").append(className).toString();
        if(vendor != null)
            s = (new StringBuilder()).append(s).append(",").append(vendor).toString();
        if(version != null)
            s = (new StringBuilder()).append(s).append(",").append(version).toString();
        s = (new StringBuilder()).append(s).append("]").toString();
        return s;
    }

    private Type type;
    private String protocol;
    private String className;
    private String vendor;
    private String version;
}
