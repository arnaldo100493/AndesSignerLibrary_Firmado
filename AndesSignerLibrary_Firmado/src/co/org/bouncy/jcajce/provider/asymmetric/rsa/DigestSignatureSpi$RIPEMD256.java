// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DigestSignatureSpi.java

package co.org.bouncy.jcajce.provider.asymmetric.rsa;

import co.org.bouncy.asn1.teletrust.TeleTrusTObjectIdentifiers;
import co.org.bouncy.crypto.digests.RIPEMD256Digest;
import co.org.bouncy.crypto.encodings.PKCS1Encoding;
import co.org.bouncy.crypto.engines.RSABlindedEngine;

// Referenced classes of package co.org.bouncy.jcajce.provider.asymmetric.rsa:
//            DigestSignatureSpi

public static class DigestSignatureSpi$RIPEMD256 extends DigestSignatureSpi
{

    public DigestSignatureSpi$RIPEMD256()
    {
        super(TeleTrusTObjectIdentifiers.ripemd256, new RIPEMD256Digest(), new PKCS1Encoding(new RSABlindedEngine()));
    }
}
