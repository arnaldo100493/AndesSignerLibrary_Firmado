// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ISOSignatureSpi.java

package co.org.bouncy.jcajce.provider.asymmetric.rsa;

import co.org.bouncy.crypto.digests.SHA1Digest;
import co.org.bouncy.crypto.engines.RSABlindedEngine;

// Referenced classes of package co.org.bouncy.jcajce.provider.asymmetric.rsa:
//            ISOSignatureSpi

public static class ISOSignatureSpi$SHA1WithRSAEncryption extends ISOSignatureSpi
{

    public ISOSignatureSpi$SHA1WithRSAEncryption()
    {
        super(new SHA1Digest(), new RSABlindedEngine());
    }
}
