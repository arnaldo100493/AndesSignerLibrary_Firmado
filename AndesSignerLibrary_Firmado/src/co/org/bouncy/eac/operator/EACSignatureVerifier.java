// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EACSignatureVerifier.java

package co.org.bouncy.eac.operator;

import co.org.bouncy.asn1.ASN1ObjectIdentifier;
import java.io.OutputStream;

public interface EACSignatureVerifier
{

    public abstract ASN1ObjectIdentifier getUsageIdentifier();

    public abstract OutputStream getOutputStream();

    public abstract boolean verify(byte abyte0[]);
}
