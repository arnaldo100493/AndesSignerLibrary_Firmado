// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CertID.java

package co.org.bouncy.asn1.ocsp;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;

public class CertID extends ASN1Object
{

    public CertID(AlgorithmIdentifier hashAlgorithm, ASN1OctetString issuerNameHash, ASN1OctetString issuerKeyHash, ASN1Integer serialNumber)
    {
        this.hashAlgorithm = hashAlgorithm;
        this.issuerNameHash = issuerNameHash;
        this.issuerKeyHash = issuerKeyHash;
        this.serialNumber = serialNumber;
    }

    private CertID(ASN1Sequence seq)
    {
        hashAlgorithm = AlgorithmIdentifier.getInstance(seq.getObjectAt(0));
        issuerNameHash = (ASN1OctetString)seq.getObjectAt(1);
        issuerKeyHash = (ASN1OctetString)seq.getObjectAt(2);
        serialNumber = (ASN1Integer)seq.getObjectAt(3);
    }

    public static CertID getInstance(ASN1TaggedObject obj, boolean explicit)
    {
        return getInstance(ASN1Sequence.getInstance(obj, explicit));
    }

    public static CertID getInstance(Object obj)
    {
        if(obj instanceof CertID)
            return (CertID)obj;
        if(obj != null)
            return new CertID(ASN1Sequence.getInstance(obj));
        else
            return null;
    }

    public AlgorithmIdentifier getHashAlgorithm()
    {
        return hashAlgorithm;
    }

    public ASN1OctetString getIssuerNameHash()
    {
        return issuerNameHash;
    }

    public ASN1OctetString getIssuerKeyHash()
    {
        return issuerKeyHash;
    }

    public ASN1Integer getSerialNumber()
    {
        return serialNumber;
    }

    public ASN1Primitive toASN1Primitive()
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(hashAlgorithm);
        v.add(issuerNameHash);
        v.add(issuerKeyHash);
        v.add(serialNumber);
        return new DERSequence(v);
    }

    AlgorithmIdentifier hashAlgorithm;
    ASN1OctetString issuerNameHash;
    ASN1OctetString issuerKeyHash;
    ASN1Integer serialNumber;
}
