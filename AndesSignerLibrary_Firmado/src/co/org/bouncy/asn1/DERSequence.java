// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DERSequence.java

package co.org.bouncy.asn1;

import java.io.IOException;
import java.util.Enumeration;

// Referenced classes of package co.org.bouncy.asn1:
//            ASN1Sequence, ASN1Encodable, ASN1OutputStream, ASN1Primitive, 
//            StreamUtil, ASN1EncodableVector

public class DERSequence extends ASN1Sequence
{

    public DERSequence()
    {
        bodyLength = -1;
    }

    public DERSequence(ASN1Encodable obj)
    {
        super(obj);
        bodyLength = -1;
    }

    public DERSequence(ASN1EncodableVector v)
    {
        super(v);
        bodyLength = -1;
    }

    public DERSequence(ASN1Encodable array[])
    {
        super(array);
        bodyLength = -1;
    }

    private int getBodyLength()
        throws IOException
    {
        if(bodyLength < 0)
        {
            int length = 0;
            for(Enumeration e = getObjects(); e.hasMoreElements();)
            {
                Object obj = e.nextElement();
                length += ((ASN1Encodable)obj).toASN1Primitive().toDERObject().encodedLength();
            }

            bodyLength = length;
        }
        return bodyLength;
    }

    int encodedLength()
        throws IOException
    {
        int length = getBodyLength();
        return 1 + StreamUtil.calculateBodyLength(length) + length;
    }

    void encode(ASN1OutputStream out)
        throws IOException
    {
        ASN1OutputStream dOut = out.getDERSubStream();
        int length = getBodyLength();
        out.write(48);
        out.writeLength(length);
        Object obj;
        for(Enumeration e = getObjects(); e.hasMoreElements(); dOut.writeObject((ASN1Encodable)obj))
            obj = e.nextElement();

    }

    private int bodyLength;
}
