// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   KeyAgreementSpi.java

package co.org.bouncy.jcajce.provider.asymmetric.ec;

import co.org.bouncy.crypto.agreement.ECDHCBasicAgreement;

// Referenced classes of package co.org.bouncy.jcajce.provider.asymmetric.ec:
//            KeyAgreementSpi

public static class KeyAgreementSpi$DHC extends KeyAgreementSpi
{

    public KeyAgreementSpi$DHC()
    {
        super("ECDHC", new ECDHCBasicAgreement(), null);
    }
}
