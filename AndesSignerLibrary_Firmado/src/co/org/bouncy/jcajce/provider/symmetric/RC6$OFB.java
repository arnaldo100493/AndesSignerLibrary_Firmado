// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RC6.java

package co.org.bouncy.jcajce.provider.symmetric;

import co.org.bouncy.crypto.BufferedBlockCipher;
import co.org.bouncy.crypto.engines.RC6Engine;
import co.org.bouncy.crypto.modes.OFBBlockCipher;
import co.org.bouncy.jcajce.provider.symmetric.util.BaseBlockCipher;

// Referenced classes of package co.org.bouncy.jcajce.provider.symmetric:
//            RC6

public static class RC6$OFB extends BaseBlockCipher
{

    public RC6$OFB()
    {
        super(new BufferedBlockCipher(new OFBBlockCipher(new RC6Engine(), 128)), 128);
    }
}
