// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ASN1OctetStringParser.java

package co.org.bouncy.asn1;

import java.io.InputStream;

// Referenced classes of package co.org.bouncy.asn1:
//            ASN1Encodable, InMemoryRepresentable

public interface ASN1OctetStringParser
    extends ASN1Encodable, InMemoryRepresentable
{

    public abstract InputStream getOctetStream();
}
