// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CMSEnvelopedDataGenerator.java

package co.org.bouncy.cms;

import co.org.bouncy.asn1.ASN1ObjectIdentifier;
import co.org.bouncy.asn1.cms.CMSObjectIdentifiers;
import java.io.IOException;
import java.io.OutputStream;

// Referenced classes of package co.org.bouncy.cms:
//            CMSTypedData, CMSException, CMSEnvelopedDataGenerator, CMSProcessable

class CMSEnvelopedDataGenerator$1
    implements CMSTypedData
{

    public ASN1ObjectIdentifier getContentType()
    {
        return CMSObjectIdentifiers.data;
    }

    public void write(OutputStream out)
        throws IOException, CMSException
    {
        val$content.write(out);
    }

    public Object getContent()
    {
        return val$content;
    }

    final CMSProcessable val$content;
    final CMSEnvelopedDataGenerator this$0;

    CMSEnvelopedDataGenerator$1()
    {
        this$0 = final_cmsenvelopeddatagenerator;
        val$content = CMSProcessable.this;
        super();
    }
}
