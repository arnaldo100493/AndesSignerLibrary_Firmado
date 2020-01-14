// Decompiled by DJ v3.12.12.98 Copyright 2014 Atanas Neshkov  Date: 17/11/2017 7:06:57 p. m.
// Home Page: http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   CertificateVerifier.java
package co.com.andesscd.pki.clases;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PublicKey;
import java.security.SignatureException;
import java.security.cert.CertPathBuilder;
import java.security.cert.CertPathBuilderException;
import java.security.cert.CertPathBuilderResult;
import java.security.cert.CertStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CollectionCertStoreParameters;
import java.security.cert.PKIXBuilderParameters;
import java.security.cert.TrustAnchor;
import java.security.cert.X509CertSelector;
import java.security.cert.X509Certificate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// Referenced classes of package co.com.andesscd.pki.clases:
//            RESULTADO_VERIFICACION, OCSPClient, CRLClient
public class CertificateVerifier {

    public static boolean esAutoFirmado(X509Certificate cert) throws CertificateException, NoSuchAlgorithmException, NoSuchProviderException {
        try {
            PublicKey key = cert.getPublicKey();
            cert.verify(key);
            return true;
        } catch (SignatureException sigEx) {
            return false;
        } catch (InvalidKeyException keyEx) {
            return false;
        }
    }

    public static RESULTADO_VERIFICACION validarCertificado(X509Certificate cert, Set<X509Certificate> trustedRootCerts, Set<X509Certificate> intermediateCerts, String urlOCSP, String urlCRL) throws InvalidAlgorithmParameterException, NoSuchAlgorithmException, NoSuchProviderException, CertificateException {
        PKIXBuilderParameters pkixParams;
        CertPathBuilderResult result;
        if (esAutoFirmado(cert)) {
            return RESULTADO_VERIFICACION.RAIZ_NO_CONFIABLE;
        }
        X509CertSelector selector = new X509CertSelector();
        selector.setCertificate(cert);
        Set<TrustAnchor> trustAnchors = new HashSet<>();
        for (X509Certificate trustedRootCert : trustedRootCerts) {
            trustAnchors.add(new TrustAnchor(trustedRootCert, null));
        }
        try {
            pkixParams = new PKIXBuilderParameters(trustAnchors, selector);
        } catch (InvalidAlgorithmParameterException ex) {
            return RESULTADO_VERIFICACION.CADENA_INCOMPLETA;
        }
        pkixParams.setRevocationEnabled(false);
        CertStore intermediateCertStore = CertStore.getInstance("Collection", new CollectionCertStoreParameters(intermediateCerts), "BC");
        pkixParams.addCertStore(intermediateCertStore);
        CertPathBuilder builder = CertPathBuilder.getInstance("PKIX", "BC");
        try {
            result = builder.build(pkixParams);
        } catch (CertPathBuilderException ex) {
            return RESULTADO_VERIFICACION.CADENA_INCOMPLETA;
        }
        List<X509Certificate> certificados = (List) result.getCertPath().getCertificates();
        X509Certificate[] cadenaCertificacion = new X509Certificate[certificados.size() + 1];
        cadenaCertificacion[0] = cert;
        for (int i = 0; i < certificados.size(); i++) {
            cadenaCertificacion[i + 1] = certificados.get(i);
        }
        RESULTADO_VERIFICACION resultado = OCSPClient.consultarOCSP((Certificate[]) cadenaCertificacion, urlOCSP);
        if (resultado == RESULTADO_VERIFICACION.CERTIFICADO_REVOCADO || resultado == RESULTADO_VERIFICACION.VALIDO) {
            return resultado;
        }
        return CRLClient.consultarCRL(cert, urlCRL);
    }

    public static RESULTADO_VERIFICACION validarCertificado(X509Certificate cert, Set<X509Certificate> additionalCerts, String urlOCSP, String urlCRL) throws InvalidAlgorithmParameterException, NoSuchAlgorithmException, NoSuchProviderException, CertificateException {
        Set<X509Certificate> trustedRootCerts = new HashSet<>();
        Set<X509Certificate> intermediateCerts = new HashSet<>();
        if (additionalCerts != null) {
            for (X509Certificate additionalCert : additionalCerts) {
                if (esAutoFirmado(additionalCert)) {
                    trustedRootCerts.add(additionalCert);
                    continue;
                }
                intermediateCerts.add(additionalCert);
            }
        }
        return validarCertificado(cert, trustedRootCerts, intermediateCerts, urlOCSP, urlCRL);
    }

    public static RESULTADO_VERIFICACION validarCertificado(X509Certificate cert, String urlOCSP, String urlCRL) throws KeyStoreException, IOException, NoSuchAlgorithmException, CertificateException, InvalidAlgorithmParameterException, NoSuchProviderException {
        HashSet<X509Certificate> additionalCerts = new HashSet<>();
        KeyStore keyStore = KeyStore.getInstance("Windows-MY");
        keyStore.load(null, null);
        String alias = keyStore.getCertificateAlias(cert);
        if (alias == null) {
            keyStore.deleteEntry("temporal");
            keyStore.setCertificateEntry("temporal", cert);
            keyStore.load(null, null);
            alias = "temporal";
        }
        Certificate[] cadenaCertificacion = keyStore.getCertificateChain(alias);
        keyStore.deleteEntry("temporal");
        for (Certificate c : cadenaCertificacion) {
            additionalCerts.add((X509Certificate) c);
        }
        return validarCertificado(cert, additionalCerts, urlOCSP, urlCRL);
    }
}
