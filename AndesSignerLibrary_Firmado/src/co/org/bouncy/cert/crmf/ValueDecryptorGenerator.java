// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ValueDecryptorGenerator.java

package co.org.bouncy.cert.crmf;

import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.operator.InputDecryptor;

// Referenced classes of package co.org.bouncy.cert.crmf:
//            CRMFException

public interface ValueDecryptorGenerator
{

    public abstract InputDecryptor getValueDecryptor(AlgorithmIdentifier algorithmidentifier, AlgorithmIdentifier algorithmidentifier1, byte abyte0[])
        throws CRMFException;
}
