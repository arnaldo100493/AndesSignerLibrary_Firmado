// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AES.java

package co.org.bouncy.jcajce.provider.symmetric;

import co.org.bouncy.crypto.engines.AESFastEngine;
import co.org.bouncy.crypto.macs.GMac;
import co.org.bouncy.crypto.modes.GCMBlockCipher;
import co.org.bouncy.jcajce.provider.symmetric.util.BaseMac;

// Referenced classes of package co.org.bouncy.jcajce.provider.symmetric:
//            AES

public static class AES$AESGMAC extends BaseMac
{

    public AES$AESGMAC()
    {
        super(new GMac(new GCMBlockCipher(new AESFastEngine())));
    }
}
