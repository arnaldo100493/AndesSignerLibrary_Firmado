// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RC2.java

package co.org.bouncy.jcajce.provider.symmetric;

import co.org.bouncy.crypto.engines.RC2Engine;
import co.org.bouncy.jcajce.provider.symmetric.util.BaseBlockCipher;

// Referenced classes of package co.org.bouncy.jcajce.provider.symmetric:
//            RC2

public static class RC2$ECB extends BaseBlockCipher
{

    public RC2$ECB()
    {
        super(new RC2Engine());
    }
}