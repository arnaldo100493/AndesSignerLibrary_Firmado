// Decompiled by DJ v3.12.12.98 Copyright 2014 Atanas Neshkov  Date: 17/11/2017 7:06:57 p. m.
// Home Page: http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   CRLManager.java
package co.com.andesscd.pki.clases;

import co.org.bouncy.asn1.ASN1Encodable;
import co.org.bouncy.asn1.DEREnumerated;
import co.org.bouncy.asn1.x500.X500Name;
import co.org.bouncy.asn1.x509.CRLNumber;
import co.org.bouncy.asn1.x509.Extension;
import co.org.bouncy.asn1.x509.X509Extension;
import co.org.bouncy.cert.CertIOException;
import co.org.bouncy.cert.X509CRLEntryHolder;
import co.org.bouncy.cert.X509CRLHolder;
import co.org.bouncy.cert.X509v2CRLBuilder;
import co.org.bouncy.cert.jcajce.JcaX509CRLConverter;
import co.org.bouncy.cert.jcajce.JcaX509ExtensionUtils;
import co.org.bouncy.jce.provider.BouncyCastleProvider;
import co.org.bouncy.operator.ContentSigner;
import co.org.bouncy.operator.OperatorCreationException;
import co.org.bouncy.operator.jcajce.JcaContentSignerBuilder;
import co.org.bouncy.operator.jcajce.JcaContentVerifierProviderBuilder;
import co.org.bouncy.x509.extension.X509ExtensionUtil;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.Security;
import java.security.SignatureException;
import java.security.cert.CRLException;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateParsingException;
import java.security.cert.X509CRL;
import java.security.cert.X509Certificate;
import java.util.Date;

public class CRLManager {

    public CRLManager() {

    }

    public static X509CRLHolder createCRL(X509Certificate pub, PrivateKey priv) throws CertificateParsingException, InvalidKeyException, NoSuchProviderException, SecurityException, SignatureException, CertificateEncodingException, CertIOException, NoSuchAlgorithmException, OperatorCreationException, FileNotFoundException {
        Date now = new Date();
        X509v2CRLBuilder crlGen = new X509v2CRLBuilder(new X500Name(pub.getSubjectDN().getName()), now);
        Date nextUpdate = new Date(now.getTime() + -1702967296L);
        PrivateKey caCrlPrivateKey = priv;
        crlGen.setNextUpdate(nextUpdate);
        crlGen.addExtension(X509Extension.cRLNumber, false, (ASN1Encodable) new CRLNumber(BigInteger.valueOf(1L)));
        ContentSigner contentSigner = (new JcaContentSignerBuilder("SHA1withRSA")).setProvider("BC").build(caCrlPrivateKey);
        X509CRLHolder crlholder = crlGen.build(contentSigner);
        return crlholder;
    }

    public static X509CRLHolder updateCRL(X509CRLHolder crl, X509Certificate pub, PrivateKey priv, BigInteger serial, int reason) {
        Security.addProvider((Provider) new BouncyCastleProvider());
        try {
            Date now = new Date();
            X509v2CRLBuilder crlGen = new X509v2CRLBuilder(crl.getIssuer(), now);
            Date nextUpdate = new Date(now.getTime() + -1702967296L);
            crlGen.addCRL(crl);
            crlGen.addCRLEntry(serial, now, reason);
            crlGen.setNextUpdate(nextUpdate);
            Extension ex = crl.getExtension(X509Extension.cRLNumber);
            BigInteger newnumber = (new BigInteger(ex.getParsedValue().toString())).add(BigInteger.ONE);
            crlGen.addExtension(X509Extension.authorityKeyIdentifier, false, (ASN1Encodable) (new JcaX509ExtensionUtils()).createAuthorityKeyIdentifier(pub));
            crlGen.addExtension(X509Extension.cRLNumber, false, (ASN1Encodable) new CRLNumber(newnumber));
            ContentSigner contentSigner = (new JcaContentSignerBuilder("SHA1withRSA")).setProvider("BC").build(priv);
            X509CRLHolder crlholder = crlGen.build(contentSigner);
            return crlholder;
        } catch (NoSuchAlgorithmException | CertificateEncodingException | CertIOException | OperatorCreationException e) {
            return null;
        }
    }

    public static boolean isCRLValid(X509CRLHolder crl, X509Certificate caCert) {
        try {
            return crl.isSignatureValid((new JcaContentVerifierProviderBuilder()).setProvider("BC").build(caCert));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean serialNotInCRL(X509CRLHolder crl, BigInteger serial) {
        X509CRLEntryHolder entry = crl.getRevokedCertificate(serial);
        if (entry == null) {
            return true;
        }
        System.out.println("Revocation Details:");
        System.out.println("Certificate number: " + entry.getSerialNumber());
        System.out.println("Issuer            : " + crl.getIssuer());
        if (entry.hasExtensions()) {
            Extension ext = entry.getExtension(X509Extension.reasonCode);
            if (ext != null) {
                try {
                    DEREnumerated reasonCode = (DEREnumerated) X509ExtensionUtil.fromExtensionValue(ext.getExtnValue().getEncoded());
                    System.out.println("Reason Code      : " + reasonCode.getValue());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    public static X509CRL CRLFromCrlHolder(X509CRLHolder crlh) {
        Security.addProvider((Provider) new BouncyCastleProvider());
        JcaX509CRLConverter crlConverter = (new JcaX509CRLConverter()).setProvider("BC");
        try {
            return crlConverter.getCRL(crlh);
        } catch (CRLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
