// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BrokenJCEBlockCipher.java

package co.org.bouncy.jce.provider;

import co.org.bouncy.crypto.engines.DESEngine;
import co.org.bouncy.crypto.modes.CBCBlockCipher;

// Referenced classes of package co.org.bouncy.jce.provider:
//            BrokenJCEBlockCipher

public static class BrokenJCEBlockCipher$BrokePBEWithMD5AndDES extends BrokenJCEBlockCipher
{

    public BrokenJCEBlockCipher$BrokePBEWithMD5AndDES()
    {
        super(new CBCBlockCipher(new DESEngine()), 0, 0, 64, 64);
    }
}
