// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JCEStreamCipher.java

package co.org.bouncy.jce.provider;

import co.org.bouncy.crypto.engines.DESEngine;
import co.org.bouncy.crypto.modes.CFBBlockCipher;

// Referenced classes of package co.org.bouncy.jce.provider:
//            JCEStreamCipher

public static class JCEStreamCipher$DES_CFB8 extends JCEStreamCipher
{

    public JCEStreamCipher$DES_CFB8()
    {
        super(new CFBBlockCipher(new DESEngine(), 8), 64);
    }
}
