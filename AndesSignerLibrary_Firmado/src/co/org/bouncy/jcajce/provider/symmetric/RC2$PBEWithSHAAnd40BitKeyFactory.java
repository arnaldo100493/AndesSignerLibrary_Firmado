// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RC2.java

package co.org.bouncy.jcajce.provider.symmetric;

import co.org.bouncy.asn1.pkcs.PKCSObjectIdentifiers;
import co.org.bouncy.jcajce.provider.symmetric.util.PBESecretKeyFactory;

// Referenced classes of package co.org.bouncy.jcajce.provider.symmetric:
//            RC2

public static class RC2$PBEWithSHAAnd40BitKeyFactory extends PBESecretKeyFactory
{

    public RC2$PBEWithSHAAnd40BitKeyFactory()
    {
        super("PBEwithSHAand40BitRC2-CBC", PKCSObjectIdentifiers.pbeWithSHAAnd40BitRC2_CBC, true, 2, 1, 40, 64);
    }
}
