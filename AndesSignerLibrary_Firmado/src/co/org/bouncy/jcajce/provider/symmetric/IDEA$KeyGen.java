// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   IDEA.java

package co.org.bouncy.jcajce.provider.symmetric;

import co.org.bouncy.crypto.CipherKeyGenerator;
import co.org.bouncy.jcajce.provider.symmetric.util.BaseKeyGenerator;

// Referenced classes of package co.org.bouncy.jcajce.provider.symmetric:
//            IDEA

public static class IDEA$KeyGen extends BaseKeyGenerator
{

    public IDEA$KeyGen()
    {
        super("IDEA", 128, new CipherKeyGenerator());
    }
}
