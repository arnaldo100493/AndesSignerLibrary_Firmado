// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   KeyTransRecipient.java

package co.org.bouncy.cms;

import co.org.bouncy.asn1.x509.AlgorithmIdentifier;

// Referenced classes of package co.org.bouncy.cms:
//            Recipient, CMSException, RecipientOperator

public interface KeyTransRecipient
    extends Recipient
{

    public abstract RecipientOperator getRecipientOperator(AlgorithmIdentifier algorithmidentifier, AlgorithmIdentifier algorithmidentifier1, byte abyte0[])
        throws CMSException;
}
