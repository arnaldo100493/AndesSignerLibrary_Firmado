// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MD2.java

package co.org.bouncy.jcajce.provider.digest;

import co.org.bouncy.crypto.digests.MD2Digest;
import co.org.bouncy.crypto.macs.HMac;
import co.org.bouncy.jcajce.provider.symmetric.util.BaseMac;

// Referenced classes of package co.org.bouncy.jcajce.provider.digest:
//            MD2

public static class MD2$HashMac extends BaseMac
{

    public MD2$HashMac()
    {
        super(new HMac(new MD2Digest()));
    }
}