// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RIPEMD320.java

package co.org.bouncy.jcajce.provider.digest;

import co.org.bouncy.crypto.CipherKeyGenerator;
import co.org.bouncy.jcajce.provider.symmetric.util.BaseKeyGenerator;

// Referenced classes of package co.org.bouncy.jcajce.provider.digest:
//            RIPEMD320

public static class RIPEMD320$KeyGenerator extends BaseKeyGenerator
{

    public RIPEMD320$KeyGenerator()
    {
        super("HMACRIPEMD320", 320, new CipherKeyGenerator());
    }
}
