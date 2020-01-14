// Decompiled by DJ v3.12.12.98 Copyright 2014 Atanas Neshkov  Date: 17/11/2017 7:06:57 p. m.
// Home Page: http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   CRLClient.java
package co.com.andesscd.pki.clases;

import co.org.bouncy.asn1.ASN1InputStream;
import co.org.bouncy.asn1.ASN1Primitive;
import co.org.bouncy.asn1.DERIA5String;
import co.org.bouncy.asn1.DEROctetString;
import co.org.bouncy.asn1.x509.CRLDistPoint;
import co.org.bouncy.asn1.x509.DistributionPoint;
import co.org.bouncy.asn1.x509.DistributionPointName;
import co.org.bouncy.asn1.x509.GeneralName;
import co.org.bouncy.asn1.x509.GeneralNames;
import co.org.bouncy.asn1.x509.X509Extension;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CRLException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.CertificateParsingException;
import java.security.cert.X509CRL;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;

// Referenced classes of package co.com.andesscd.pki.clases:
//            RESULTADO_VERIFICACION, CMS
public class CRLClient {

    public CRLClient() {

    }

    public static RESULTADO_VERIFICACION consultarCRL(X509Certificate cert, String url) {
        try {
            List<String> crlDistPoints = null;
            if (url == null || url.isEmpty()) {
                crlDistPoints = getCrlDistributionPoints(cert);
                if (crlDistPoints.isEmpty()) {
                    return RESULTADO_VERIFICACION.ESTADO_DE_REVOCACION_DESCONOCIDO;
                }
            } else {
                crlDistPoints = new ArrayList<>();
                crlDistPoints.add(url);
            }
            for (String crlDP : crlDistPoints) {
                X509CRL crl = downloadCRL(crlDP);
                if (crl.isRevoked(cert)) {
                    return RESULTADO_VERIFICACION.CERTIFICADO_REVOCADO;
                }
            }
            return RESULTADO_VERIFICACION.VALIDO;
        } catch (IOException | CRLException | CertificateException | NamingException ex) {
            return RESULTADO_VERIFICACION.ESTADO_DE_REVOCACION_DESCONOCIDO;
        }
    }

    public static RESULTADO_VERIFICACION consultarCRL(KeyStore keyStore, String alias, String url) throws KeyStoreException, IOException, NoSuchAlgorithmException, CertificateException, Exception {
        if (keyStore == null) {
            keyStore = KeyStore.getInstance("Windows-MY");
            keyStore.load(null, null);
        }
        Certificate certificado = keyStore.getCertificate(alias);
        if (certificado == null) {
            throw new Exception("El almacen de certificados no posee el alias " + alias);
        }
        return consultarCRL((X509Certificate) certificado, url);
    }

    private static X509CRL downloadCRL(String crlURL) throws IOException, CertificateException, CRLException, IOException, NamingException {
        if (crlURL.startsWith("http://") || crlURL.startsWith("https://") || crlURL.startsWith("ftp://")) {
            return downloadCRLFromWeb(crlURL);
        }
        if (crlURL.startsWith("ldap://")) {
            return downloadCRLFromLDAP(crlURL);
        }
        throw new IOException("No fue posible descargar la CRL del certificado desde " + crlURL);
    }

    private static X509CRL downloadCRLFromLDAP(String ldapURL) throws CertificateException, NamingException, CRLException, IOException {
        Hashtable<String, String> env = new Hashtable<>();
        env.put("java.naming.factory.initial", "com.sun.jndi.ldap.LdapCtxFactory");
        env.put("java.naming.provider.url", ldapURL);
        DirContext ctx = new InitialDirContext(env);
        Attributes avals = ctx.getAttributes("");
        Attribute aval = avals.get("certificateRevocationList;binary");
        byte[] val = (byte[]) aval.get();
        if (val == null || val.length == 0) {
            throw new IOException("No fue posible descargar la CRL del certificado desde " + ldapURL);
        }
        InputStream inStream = new ByteArrayInputStream(val);
        CertificateFactory cf = CertificateFactory.getInstance("X.509");
        return (X509CRL) cf.generateCRL(inStream);
    }

    private static X509CRL downloadCRLFromWeb(String crlURL) throws MalformedURLException, IOException, CertificateException, CRLException {
        HttpURLConnection conexionCRL;
        URL url = new URL(crlURL);
        if (CMS.getProxy() != null) {
            conexionCRL = (HttpURLConnection) url.openConnection(CMS.getProxy());
        } else {
            conexionCRL = (HttpURLConnection) url.openConnection();
        }
        conexionCRL.setConnectTimeout(30000);
        InputStream crlStream = conexionCRL.getInputStream();
        try {
            CertificateFactory cf = CertificateFactory.getInstance("X.509");
            return (X509CRL) cf.generateCRL(crlStream);
        } finally {
            crlStream.close();
        }
    }

    private static List<String> getCrlDistributionPoints(X509Certificate cert) throws CertificateParsingException, IOException {
        byte[] crldpExt = cert.getExtensionValue(X509Extension.cRLDistributionPoints.getId());
        if (crldpExt == null) {
            return new ArrayList<>();
        }
        ASN1InputStream oAsnInStream = new ASN1InputStream(new ByteArrayInputStream(crldpExt));
        ASN1Primitive aSN1Primitive1 = oAsnInStream.readObject();
        DEROctetString dosCrlDP = (DEROctetString) aSN1Primitive1;
        byte[] crldpExtOctets = dosCrlDP.getOctets();
        ASN1InputStream oAsnInStream2 = new ASN1InputStream(new ByteArrayInputStream(crldpExtOctets));
        ASN1Primitive aSN1Primitive2 = oAsnInStream2.readObject();
        CRLDistPoint distPoint = CRLDistPoint.getInstance(aSN1Primitive2);
        List<String> crlUrls = new ArrayList<>();
        for (DistributionPoint dp : distPoint.getDistributionPoints()) {
            DistributionPointName dpn = dp.getDistributionPoint();
            if (dpn != null
                    && dpn.getType() == 0) {
                GeneralName[] genNames = GeneralNames.getInstance(dpn.getName()).getNames();
                for (GeneralName genName : genNames) {
                    if (genName.getTagNo() == 6) {
                        String url = DERIA5String.getInstance(genName.getName()).getString();
                        crlUrls.add(url);
                    }
                }
            }
        }
        return crlUrls;
    }
}
