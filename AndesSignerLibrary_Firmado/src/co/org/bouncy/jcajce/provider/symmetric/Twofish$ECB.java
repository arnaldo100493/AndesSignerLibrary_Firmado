// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Twofish.java

package co.org.bouncy.jcajce.provider.symmetric;

import co.org.bouncy.crypto.BlockCipher;
import co.org.bouncy.crypto.engines.TwofishEngine;
import co.org.bouncy.jcajce.provider.symmetric.util.BaseBlockCipher;
import co.org.bouncy.jcajce.provider.symmetric.util.BlockCipherProvider;

// Referenced classes of package co.org.bouncy.jcajce.provider.symmetric:
//            Twofish

public static class Twofish$ECB extends BaseBlockCipher
{

    public Twofish$ECB()
    {
        super(new BlockCipherProvider() {

            public BlockCipher get()
            {
                return new TwofishEngine();
            }

        }
);
    }
}
