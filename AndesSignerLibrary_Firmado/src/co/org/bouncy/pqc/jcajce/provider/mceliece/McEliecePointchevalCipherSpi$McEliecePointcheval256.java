// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   McEliecePointchevalCipherSpi.java

package co.org.bouncy.pqc.jcajce.provider.mceliece;

import co.org.bouncy.crypto.digests.SHA256Digest;
import co.org.bouncy.pqc.crypto.mceliece.McEliecePointchevalCipher;

// Referenced classes of package co.org.bouncy.pqc.jcajce.provider.mceliece:
//            McEliecePointchevalCipherSpi

public static class McEliecePointchevalCipherSpi$McEliecePointcheval256 extends McEliecePointchevalCipherSpi
{

    public McEliecePointchevalCipherSpi$McEliecePointcheval256()
    {
        super(new SHA256Digest(), new McEliecePointchevalCipher());
    }
}
