// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TEA.java

package co.org.bouncy.jcajce.provider.symmetric;

import co.org.bouncy.crypto.engines.TEAEngine;
import co.org.bouncy.jcajce.provider.symmetric.util.BaseBlockCipher;

// Referenced classes of package co.org.bouncy.jcajce.provider.symmetric:
//            TEA

public static class TEA$ECB extends BaseBlockCipher
{

    public TEA$ECB()
    {
        super(new TEAEngine());
    }
}
