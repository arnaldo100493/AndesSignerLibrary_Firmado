// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ContentSigner.java

package co.org.bouncy.operator;

import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import java.io.OutputStream;

public interface ContentSigner
{

    public abstract AlgorithmIdentifier getAlgorithmIdentifier();

    public abstract OutputStream getOutputStream();

    public abstract byte[] getSignature();
}
