// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Grainv1.java

package co.org.bouncy.jcajce.provider.symmetric;

import co.org.bouncy.crypto.CipherKeyGenerator;
import co.org.bouncy.jcajce.provider.symmetric.util.BaseKeyGenerator;

// Referenced classes of package co.org.bouncy.jcajce.provider.symmetric:
//            Grainv1

public static class Grainv1$KeyGen extends BaseKeyGenerator
{

    public Grainv1$KeyGen()
    {
        super("Grainv1", 80, new CipherKeyGenerator());
    }
}
