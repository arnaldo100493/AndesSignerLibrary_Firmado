// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   VMPC.java

package co.org.bouncy.jcajce.provider.symmetric;

import co.org.bouncy.crypto.engines.VMPCEngine;
import co.org.bouncy.jcajce.provider.symmetric.util.BaseStreamCipher;

// Referenced classes of package co.org.bouncy.jcajce.provider.symmetric:
//            VMPC

public static class VMPC$Base extends BaseStreamCipher
{

    public VMPC$Base()
    {
        super(new VMPCEngine(), 16);
    }
}
