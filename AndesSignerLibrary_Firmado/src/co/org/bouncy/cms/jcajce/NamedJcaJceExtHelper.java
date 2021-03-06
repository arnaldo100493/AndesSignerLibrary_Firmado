// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   NamedJcaJceExtHelper.java

package co.org.bouncy.cms.jcajce;

import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.jcajce.NamedJcaJceHelper;
import co.org.bouncy.operator.SymmetricKeyUnwrapper;
import co.org.bouncy.operator.jcajce.JceAsymmetricKeyUnwrapper;
import co.org.bouncy.operator.jcajce.JceSymmetricKeyUnwrapper;
import java.security.PrivateKey;
import javax.crypto.SecretKey;

// Referenced classes of package co.org.bouncy.cms.jcajce:
//            JcaJceExtHelper

class NamedJcaJceExtHelper extends NamedJcaJceHelper
    implements JcaJceExtHelper
{

    public NamedJcaJceExtHelper(String providerName)
    {
        super(providerName);
    }

    public JceAsymmetricKeyUnwrapper createAsymmetricUnwrapper(AlgorithmIdentifier keyEncryptionAlgorithm, PrivateKey keyEncryptionKey)
    {
        return (new JceAsymmetricKeyUnwrapper(keyEncryptionAlgorithm, keyEncryptionKey)).setProvider(providerName);
    }

    public SymmetricKeyUnwrapper createSymmetricUnwrapper(AlgorithmIdentifier keyEncryptionAlgorithm, SecretKey keyEncryptionKey)
    {
        return (new JceSymmetricKeyUnwrapper(keyEncryptionAlgorithm, keyEncryptionKey)).setProvider(providerName);
    }
}
