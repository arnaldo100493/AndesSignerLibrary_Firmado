// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Serpent.java

package co.org.bouncy.jcajce.provider.symmetric;

import co.org.bouncy.crypto.BlockCipher;
import co.org.bouncy.crypto.engines.SerpentEngine;
import co.org.bouncy.jcajce.provider.symmetric.util.BlockCipherProvider;

// Referenced classes of package co.org.bouncy.jcajce.provider.symmetric:
//            Serpent

class Serpent$ECB$1
    implements BlockCipherProvider
{

    public BlockCipher get()
    {
        return new SerpentEngine();
    }

    Serpent$ECB$1()
    {
    }
}
