// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Blowfish.java

package co.org.bouncy.jcajce.provider.symmetric;

import co.org.bouncy.crypto.CipherKeyGenerator;
import co.org.bouncy.jcajce.provider.symmetric.util.BaseKeyGenerator;

// Referenced classes of package co.org.bouncy.jcajce.provider.symmetric:
//            Blowfish

public static class Blowfish$KeyGen extends BaseKeyGenerator
{

    public Blowfish$KeyGen()
    {
        super("Blowfish", 128, new CipherKeyGenerator());
    }
}
