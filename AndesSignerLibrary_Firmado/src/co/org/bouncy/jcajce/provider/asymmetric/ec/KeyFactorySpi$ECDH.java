// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   KeyFactorySpi.java

package co.org.bouncy.jcajce.provider.asymmetric.ec;

import co.org.bouncy.jce.provider.BouncyCastleProvider;

// Referenced classes of package co.org.bouncy.jcajce.provider.asymmetric.ec:
//            KeyFactorySpi

public static class KeyFactorySpi$ECDH extends KeyFactorySpi
{

    public KeyFactorySpi$ECDH()
    {
        super("ECDH", BouncyCastleProvider.CONFIGURATION);
    }
}
