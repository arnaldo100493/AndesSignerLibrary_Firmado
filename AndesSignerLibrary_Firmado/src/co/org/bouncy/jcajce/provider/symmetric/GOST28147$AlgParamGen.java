// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   GOST28147.java

package co.org.bouncy.jcajce.provider.symmetric;

import co.org.bouncy.jcajce.provider.symmetric.util.BaseAlgorithmParameterGenerator;
import java.security.*;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.spec.IvParameterSpec;

// Referenced classes of package co.org.bouncy.jcajce.provider.symmetric:
//            GOST28147

public static class GOST28147$AlgParamGen extends BaseAlgorithmParameterGenerator
{

    protected void engineInit(AlgorithmParameterSpec genParamSpec, SecureRandom random)
        throws InvalidAlgorithmParameterException
    {
        throw new InvalidAlgorithmParameterException("No supported AlgorithmParameterSpec for AES parameter generation.");
    }

    protected AlgorithmParameters engineGenerateParameters()
    {
        byte iv[] = new byte[16];
        if(random == null)
            random = new SecureRandom();
        random.nextBytes(iv);
        AlgorithmParameters params;
        try
        {
            params = AlgorithmParameters.getInstance("GOST28147", "BC");
            params.init(new IvParameterSpec(iv));
        }
        catch(Exception e)
        {
            throw new RuntimeException(e.getMessage());
        }
        return params;
    }

    public GOST28147$AlgParamGen()
    {
    }
}