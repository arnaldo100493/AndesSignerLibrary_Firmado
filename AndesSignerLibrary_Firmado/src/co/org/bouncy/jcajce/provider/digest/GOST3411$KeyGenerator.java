// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   GOST3411.java

package co.org.bouncy.jcajce.provider.digest;

import co.org.bouncy.crypto.CipherKeyGenerator;
import co.org.bouncy.jcajce.provider.symmetric.util.BaseKeyGenerator;

// Referenced classes of package co.org.bouncy.jcajce.provider.digest:
//            GOST3411

public static class GOST3411$KeyGenerator extends BaseKeyGenerator
{

    public GOST3411$KeyGenerator()
    {
        super("HMACGOST3411", 256, new CipherKeyGenerator());
    }
}
