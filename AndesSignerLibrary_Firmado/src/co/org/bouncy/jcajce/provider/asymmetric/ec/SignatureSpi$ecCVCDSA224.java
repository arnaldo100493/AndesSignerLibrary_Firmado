// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SignatureSpi.java

package co.org.bouncy.jcajce.provider.asymmetric.ec;

import co.org.bouncy.crypto.digests.SHA224Digest;
import co.org.bouncy.crypto.signers.ECDSASigner;

// Referenced classes of package co.org.bouncy.jcajce.provider.asymmetric.ec:
//            SignatureSpi

public static class SignatureSpi$ecCVCDSA224 extends SignatureSpi
{

    public SignatureSpi$ecCVCDSA224()
    {
        super(new SHA224Digest(), new ECDSASigner(), new r(null));
    }
}
