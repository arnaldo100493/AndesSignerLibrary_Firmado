// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PSSSignatureSpi.java

package co.org.bouncy.jcajce.provider.asymmetric.rsa;

import co.org.bouncy.crypto.engines.RSABlindedEngine;

// Referenced classes of package co.org.bouncy.jcajce.provider.asymmetric.rsa:
//            PSSSignatureSpi

public static class PSSSignatureSpi$nonePSS extends PSSSignatureSpi
{

    public PSSSignatureSpi$nonePSS()
    {
        super(new RSABlindedEngine(), null, true);
    }
}
