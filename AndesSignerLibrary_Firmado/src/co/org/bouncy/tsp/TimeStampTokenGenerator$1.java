// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TimeStampTokenGenerator.java

package co.org.bouncy.tsp;

import co.org.bouncy.asn1.cms.AttributeTable;
import co.org.bouncy.asn1.ess.ESSCertID;
import co.org.bouncy.asn1.ess.SigningCertificate;
import co.org.bouncy.asn1.pkcs.PKCSObjectIdentifiers;
import co.org.bouncy.cms.*;
import java.util.Map;

// Referenced classes of package co.org.bouncy.tsp:
//            TimeStampTokenGenerator

class TimeStampTokenGenerator$1
    implements CMSAttributeTableGenerator
{

    public AttributeTable getAttributes(Map parameters)
        throws CMSAttributeTableGenerationException
    {
        AttributeTable table = val$signerInfoGen.getSignedAttributeTableGenerator().getAttributes(parameters);
        if(table.get(PKCSObjectIdentifiers.id_aa_signingCertificate) == null)
            return table.add(PKCSObjectIdentifiers.id_aa_signingCertificate, new SigningCertificate(val$essCertid));
        else
            return table;
    }

    final SignerInfoGenerator val$signerInfoGen;
    final ESSCertID val$essCertid;
    final TimeStampTokenGenerator this$0;

    TimeStampTokenGenerator$1()
    {
        this$0 = final_timestamptokengenerator;
        val$signerInfoGen = signerinfogenerator;
        val$essCertid = ESSCertID.this;
        super();
    }
}
