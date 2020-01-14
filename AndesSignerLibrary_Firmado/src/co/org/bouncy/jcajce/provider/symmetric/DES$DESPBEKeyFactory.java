// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DES.java

package co.org.bouncy.jcajce.provider.symmetric;

import co.org.bouncy.asn1.ASN1ObjectIdentifier;
import co.org.bouncy.crypto.CipherParameters;
import co.org.bouncy.crypto.params.*;
import co.org.bouncy.jcajce.provider.symmetric.util.BCPBEKey;
import co.org.bouncy.jcajce.provider.symmetric.util.BaseSecretKeyFactory;
import co.org.bouncy.jcajce.provider.symmetric.util.PBE;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import javax.crypto.SecretKey;
import javax.crypto.spec.PBEKeySpec;

// Referenced classes of package co.org.bouncy.jcajce.provider.symmetric:
//            DES

public static class DES$DESPBEKeyFactory extends BaseSecretKeyFactory
{

    protected SecretKey engineGenerateSecret(KeySpec keySpec)
        throws InvalidKeySpecException
    {
        if(keySpec instanceof PBEKeySpec)
        {
            PBEKeySpec pbeSpec = (PBEKeySpec)keySpec;
            if(pbeSpec.getSalt() == null)
                return new BCPBEKey(algName, algOid, scheme, digest, keySize, ivSize, pbeSpec, null);
            CipherParameters param;
            if(forCipher)
                param = co.org.bouncy.jcajce.provider.symmetric.util.PBE.Util.makePBEParameters(pbeSpec, scheme, digest, keySize, ivSize);
            else
                param = co.org.bouncy.jcajce.provider.symmetric.util.PBE.Util.makePBEMacParameters(pbeSpec, scheme, digest, keySize);
            KeyParameter kParam;
            if(param instanceof ParametersWithIV)
                kParam = (KeyParameter)((ParametersWithIV)param).getParameters();
            else
                kParam = (KeyParameter)param;
            DESParameters.setOddParity(kParam.getKey());
            return new BCPBEKey(algName, algOid, scheme, digest, keySize, ivSize, pbeSpec, param);
        } else
        {
            throw new InvalidKeySpecException("Invalid KeySpec");
        }
    }

    private boolean forCipher;
    private int scheme;
    private int digest;
    private int keySize;
    private int ivSize;

    public DES$DESPBEKeyFactory(String algorithm, ASN1ObjectIdentifier oid, boolean forCipher, int scheme, int digest, int keySize, int ivSize)
    {
        super(algorithm, oid);
        this.forCipher = forCipher;
        this.scheme = scheme;
        this.digest = digest;
        this.keySize = keySize;
        this.ivSize = ivSize;
    }
}
