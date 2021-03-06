// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DERUniversalString.java

package co.org.bouncy.asn1;

import co.org.bouncy.util.Arrays;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

// Referenced classes of package co.org.bouncy.asn1:
//            ASN1Primitive, ASN1OctetString, ASN1OutputStream, ASN1String, 
//            ASN1TaggedObject, StreamUtil

public class DERUniversalString extends ASN1Primitive
    implements ASN1String
{

    public static DERUniversalString getInstance(Object obj)
    {
        if(obj == null || (obj instanceof DERUniversalString))
            return (DERUniversalString)obj;
        if(obj instanceof byte[])
            try
            {
                return (DERUniversalString)fromByteArray((byte[])(byte[])obj);
            }
            catch(Exception e)
            {
                throw new IllegalArgumentException((new StringBuilder()).append("encoding error getInstance: ").append(e.toString()).toString());
            }
        else
            throw new IllegalArgumentException((new StringBuilder()).append("illegal object in getInstance: ").append(obj.getClass().getName()).toString());
    }

    public static DERUniversalString getInstance(ASN1TaggedObject obj, boolean explicit)
    {
        ASN1Primitive o = obj.getObject();
        if(explicit || (o instanceof DERUniversalString))
            return getInstance(o);
        else
            return new DERUniversalString(((ASN1OctetString)o).getOctets());
    }

    public DERUniversalString(byte string[])
    {
        this.string = string;
    }

    public String getString()
    {
        StringBuffer buf = new StringBuffer("#");
        ByteArrayOutputStream bOut = new ByteArrayOutputStream();
        ASN1OutputStream aOut = new ASN1OutputStream(bOut);
        try
        {
            aOut.writeObject(this);
        }
        catch(IOException e)
        {
            throw new RuntimeException("internal error encoding BitString");
        }
        byte string[] = bOut.toByteArray();
        for(int i = 0; i != string.length; i++)
        {
            buf.append(table[string[i] >>> 4 & 0xf]);
            buf.append(table[string[i] & 0xf]);
        }

        return buf.toString();
    }

    public String toString()
    {
        return getString();
    }

    public byte[] getOctets()
    {
        return string;
    }

    boolean isConstructed()
    {
        return false;
    }

    int encodedLength()
    {
        return 1 + StreamUtil.calculateBodyLength(string.length) + string.length;
    }

    void encode(ASN1OutputStream out)
        throws IOException
    {
        out.writeEncoded(28, getOctets());
    }

    boolean asn1Equals(ASN1Primitive o)
    {
        if(!(o instanceof DERUniversalString))
            return false;
        else
            return Arrays.areEqual(string, ((DERUniversalString)o).string);
    }

    public int hashCode()
    {
        return Arrays.hashCode(string);
    }

    private static final char table[] = {
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 
        'A', 'B', 'C', 'D', 'E', 'F'
    };
    private byte string[];

}
