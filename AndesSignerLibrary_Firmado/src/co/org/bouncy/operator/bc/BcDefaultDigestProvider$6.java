// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BcDefaultDigestProvider.java

package co.org.bouncy.operator.bc;

import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.crypto.ExtendedDigest;
import co.org.bouncy.crypto.digests.MD5Digest;

// Referenced classes of package co.org.bouncy.operator.bc:
//            BcDigestProvider, BcDefaultDigestProvider

static class BcDefaultDigestProvider$6
    implements BcDigestProvider
{

    public ExtendedDigest get(AlgorithmIdentifier digestAlgorithmIdentifier)
    {
        return new MD5Digest();
    }

    BcDefaultDigestProvider$6()
    {
    }
}
