// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   IssuerSerial.java

package co.org.bouncy.asn1.x509;

import co.org.bouncy.asn1.*;
import java.math.BigInteger;

// Referenced classes of package co.org.bouncy.asn1.x509:
//            GeneralNames

public class IssuerSerial extends ASN1Object
{

    public static IssuerSerial getInstance(Object obj)
    {
        if(obj instanceof IssuerSerial)
            return (IssuerSerial)obj;
        if(obj != null)
            return new IssuerSerial(ASN1Sequence.getInstance(obj));
        else
            return null;
    }

    public static IssuerSerial getInstance(ASN1TaggedObject obj, boolean explicit)
    {
        return getInstance(ASN1Sequence.getInstance(obj, explicit));
    }

    private IssuerSerial(ASN1Sequence seq)
    {
        if(seq.size() != 2 && seq.size() != 3)
            throw new IllegalArgumentException((new StringBuilder()).append("Bad sequence size: ").append(seq.size()).toString());
        issuer = GeneralNames.getInstance(seq.getObjectAt(0));
        serial = ASN1Integer.getInstance(seq.getObjectAt(1));
        if(seq.size() == 3)
            issuerUID = DERBitString.getInstance(seq.getObjectAt(2));
    }

    public IssuerSerial(GeneralNames issuer, BigInteger serial)
    {
        this(issuer, new ASN1Integer(serial));
    }

    public IssuerSerial(GeneralNames issuer, ASN1Integer serial)
    {
        this.issuer = issuer;
        this.serial = serial;
    }

    public GeneralNames getIssuer()
    {
        return issuer;
    }

    public ASN1Integer getSerial()
    {
        return serial;
    }

    public DERBitString getIssuerUID()
    {
        return issuerUID;
    }

    public ASN1Primitive toASN1Primitive()
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(issuer);
        v.add(serial);
        if(issuerUID != null)
            v.add(issuerUID);
        return new DERSequence(v);
    }

    GeneralNames issuer;
    ASN1Integer serial;
    DERBitString issuerUID;
}