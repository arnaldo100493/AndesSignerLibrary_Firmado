// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DigestAlgorithmIdentifierFinder.java

package co.org.bouncy.operator;

import co.org.bouncy.asn1.x509.AlgorithmIdentifier;

public interface DigestAlgorithmIdentifierFinder
{

    public abstract AlgorithmIdentifier find(AlgorithmIdentifier algorithmidentifier);

    public abstract AlgorithmIdentifier find(String s);
}
