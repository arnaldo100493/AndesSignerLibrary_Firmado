// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AES.java

package co.org.bouncy.jcajce.provider.symmetric;

import co.org.bouncy.jcajce.provider.symmetric.util.PBESecretKeyFactory;

// Referenced classes of package co.org.bouncy.jcajce.provider.symmetric:
//            AES

public static class AES$PBEWithMD5And128BitAESCBCOpenSSL extends PBESecretKeyFactory
{

    public AES$PBEWithMD5And128BitAESCBCOpenSSL()
    {
        super("PBEWithMD5And128BitAES-CBC-OpenSSL", null, true, 3, 0, 128, 128);
    }
}
