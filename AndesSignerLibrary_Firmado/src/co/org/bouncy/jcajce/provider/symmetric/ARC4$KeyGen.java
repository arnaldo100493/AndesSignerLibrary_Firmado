// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ARC4.java

package co.org.bouncy.jcajce.provider.symmetric;

import co.org.bouncy.crypto.CipherKeyGenerator;
import co.org.bouncy.jcajce.provider.symmetric.util.BaseKeyGenerator;

// Referenced classes of package co.org.bouncy.jcajce.provider.symmetric:
//            ARC4

public static class ARC4$KeyGen extends BaseKeyGenerator
{

    public ARC4$KeyGen()
    {
        super("RC4", 128, new CipherKeyGenerator());
    }
}
