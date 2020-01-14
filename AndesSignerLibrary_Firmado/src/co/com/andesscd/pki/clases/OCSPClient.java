// Decompiled by DJ v3.12.12.98 Copyright 2014 Atanas Neshkov  Date: 17/11/2017 7:06:57 p. m.
// Home Page: http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   OCSPClient.java
package co.com.andesscd.pki.clases;

import co.org.bouncy.asn1.ASN1Encodable;
import co.org.bouncy.asn1.ASN1Object;
import co.org.bouncy.asn1.ASN1OctetString;
import co.org.bouncy.asn1.ASN1Sequence;
import co.org.bouncy.asn1.ASN1TaggedObject;
import co.org.bouncy.asn1.DERBoolean;
import co.org.bouncy.asn1.DERIA5String;
import co.org.bouncy.asn1.DERObjectIdentifier;
import co.org.bouncy.asn1.DEROctetString;
import co.org.bouncy.asn1.ocsp.OCSPObjectIdentifiers;
import co.org.bouncy.asn1.x509.Extension;
import co.org.bouncy.asn1.x509.Extensions;
import co.org.bouncy.asn1.x509.GeneralName;
import co.org.bouncy.asn1.x509.X509Extension;
import co.org.bouncy.cert.X509CertificateHolder;
import co.org.bouncy.cert.ocsp.BasicOCSPResp;
import co.org.bouncy.cert.ocsp.CertificateID;
import co.org.bouncy.cert.ocsp.OCSPException;
import co.org.bouncy.cert.ocsp.OCSPReq;
import co.org.bouncy.cert.ocsp.OCSPReqBuilder;
import co.org.bouncy.cert.ocsp.OCSPResp;
import co.org.bouncy.cert.ocsp.SingleResp;
import co.org.bouncy.operator.OperatorCreationException;
import co.org.bouncy.operator.jcajce.JcaDigestCalculatorProviderBuilder;
import co.org.bouncy.util.Arrays;
import co.org.bouncy.x509.extension.X509ExtensionUtil;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Enumeration;

// Referenced classes of package co.com.andesscd.pki.clases:
//            CMS, RESULTADO_VERIFICACION
public class OCSPClient {

    public OCSPClient() {

    }

    private static ASN1Object getExtensionValue(X509Certificate cert, String oid) throws IOException {
        if (cert == null) {
            return null;
        }
        byte[] bytes = cert.getExtensionValue(oid);
        if (bytes == null) {
            return null;
        }
        return (ASN1Object) X509ExtensionUtil.fromExtensionValue(bytes);
    }

    private static ArrayList getAuthorityInformationAccessOcspUrl(X509Certificate cert) throws IOException {
        ArrayList<String> ocspUrls = new ArrayList();
        try {
            ASN1Object obj = getExtensionValue(cert, X509Extension.authorityInfoAccess.getId());
            ASN1Sequence s = (ASN1Sequence) obj;
            Enumeration<ASN1Sequence> elements = s.getObjects();
            while (elements.hasMoreElements()) {
                ASN1Sequence element = elements.nextElement();
                DERObjectIdentifier oid = (DERObjectIdentifier) element.getObjectAt(0);
                if (oid.getId().compareTo("1.3.6.1.5.5.7.48.1") == 0) {
                    ASN1TaggedObject taggedObject = (ASN1TaggedObject) element.getObjectAt(1);
                    GeneralName gn = GeneralName.getInstance(taggedObject);
                    ocspUrls.add(DERIA5String.getInstance(gn.getName()).getString());
                }
            }
        } catch (IOException ex) {
            throw new IOException("Error obtenindo la ruta OCSP: " + ex.getMessage(), ex);
        }
        return ocspUrls;
    }

    private static OCSPReq generateOcspRequest(CertificateID id) throws OCSPException, IOException, OCSPException {
        OCSPReqBuilder ocspRequestBuilder = new OCSPReqBuilder();
        ocspRequestBuilder.addRequest(id);
        DEROctetString dEROctetString = new DEROctetString((ASN1Encodable) new DEROctetString(new byte[]{1, 3, 6, 1, 5, 5, 7, 48, 1, 1}));
        Extensions e = new Extensions(new Extension(OCSPObjectIdentifiers.id_pkix_ocsp, DERBoolean.FALSE, (ASN1OctetString) dEROctetString));
        return ocspRequestBuilder.setRequestExtensions(e).build();
    }

    private static OCSPReq generateOcspRequest(X509Certificate issuerCert, BigInteger serialNumber) throws OCSPException, OCSPException, OperatorCreationException, IOException, CertificateEncodingException {
        CertificateID id = new CertificateID((new JcaDigestCalculatorProviderBuilder()).setProvider("BC").build().get(CertificateID.HASH_SHA1), new X509CertificateHolder(issuerCert.getEncoded()), serialNumber);
        return generateOcspRequest(id);
    }

    private static InputStream postData(String ocspurl, byte[] data) throws MalformedURLException, IOException, Exception {
        HttpURLConnection conexionOCSP;
        URL url = new URL(ocspurl);
        if (CMS.getProxy() != null) {
            conexionOCSP = (HttpURLConnection) url.openConnection(CMS.getProxy());
        } else {
            conexionOCSP = (HttpURLConnection) url.openConnection();
        }
        conexionOCSP.setRequestProperty("Content-Type", "application/ocsp-request");
        conexionOCSP.setRequestProperty("Accept", "application/ocsp-response");
        conexionOCSP.setDoOutput(true);
        conexionOCSP.setReadTimeout(20000);
        OutputStream outputStream = conexionOCSP.getOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(new BufferedOutputStream(outputStream));
        dataOutputStream.write(data);
        dataOutputStream.flush();
        dataOutputStream.close();
        if (conexionOCSP.getResponseCode() / 100 != 2) {
            throw new Exception("Error: " + conexionOCSP.getResponseCode());
        }
        return (InputStream) conexionOCSP.getContent();
    }

    private static void validateCertificateId(X509Certificate issuerCert, X509Certificate eeCert, CertificateID certificateId) throws OCSPException, Exception {
        CertificateID expectedId = new CertificateID((new JcaDigestCalculatorProviderBuilder()).setProvider("BC").build().get(CertificateID.HASH_SHA1), new X509CertificateHolder(issuerCert.getEncoded()), eeCert.getSerialNumber());
        if (expectedId.getSerialNumber().compareTo(certificateId.getSerialNumber()) != 0) {
            throw new Exception("Invalid certificate ID in response");
        }
        if (!Arrays.areEqual(expectedId.getIssuerNameHash(), certificateId.getIssuerNameHash())) {
            throw new Exception("Invalid certificate Issuer in response");
        }
    }

    private static RESULTADO_VERIFICACION processOcspResponse(X509Certificate eeCert, X509Certificate issuerCert, InputStream RespStream) throws IOException, OCSPException, Exception {
        OCSPResp r = new OCSPResp(RespStream);
        if (r.getStatus() == 0) {
            BasicOCSPResp obr = (BasicOCSPResp) r.getResponseObject();
            if ((obr.getResponses()).length == 1) {
                SingleResp resp = obr.getResponses()[0];
                validateCertificateId(issuerCert, eeCert, resp.getCertID());
                Object localStatus = resp.getCertStatus();
                if (localStatus instanceof co.org.bouncy.cert.ocsp.RevokedStatus) {
                    return RESULTADO_VERIFICACION.CERTIFICADO_REVOCADO;
                }
                if (localStatus instanceof co.org.bouncy.cert.ocsp.UnknownStatus) {
                    return RESULTADO_VERIFICACION.ESTADO_DE_REVOCACION_DESCONOCIDO;
                }
                return RESULTADO_VERIFICACION.VALIDO;
            }
        }
        return RESULTADO_VERIFICACION.ERROR_DESCONOCIDO;
    }

    private static RESULTADO_VERIFICACION consultarOCSP(X509Certificate eeCert, X509Certificate issuerCert, String url) {
        try {
            if (url == null || url.isEmpty()) {
                ArrayList<String> urls = getAuthorityInformationAccessOcspUrl(eeCert);
                if (urls == null || urls.isEmpty()) {
                    return RESULTADO_VERIFICACION.SIN_INFORMACION_OCSP;
                }
                url = urls.get(0);
            }
            OCSPReq req = generateOcspRequest(issuerCert, eeCert.getSerialNumber());
            InputStream resp = postData(url, req.getEncoded());
            return processOcspResponse(eeCert, issuerCert, resp);
        } catch (Exception ex) {
            return RESULTADO_VERIFICACION.ERROR_DESCONOCIDO;
        }
    }

    public static RESULTADO_VERIFICACION consultarOCSP(Certificate[] cadenaCertificacion, String url) {
        try {
            if (cadenaCertificacion.length < 2) {
                return RESULTADO_VERIFICACION.CADENA_INCOMPLETA;
            }
            X509Certificate eeCert = (X509Certificate) cadenaCertificacion[0];
            X509Certificate certificadoPadre = (X509Certificate) cadenaCertificacion[1];
            return consultarOCSP(eeCert, certificadoPadre, url);
        } catch (Exception e) {
            return RESULTADO_VERIFICACION.ERROR_DESCONOCIDO;
        }
    }

    public static RESULTADO_VERIFICACION consultarOCSP(KeyStore trustKeyStore, KeyStore keyStore, String alias, String url) throws KeyStoreException, IOException, NoSuchAlgorithmException, CertificateException, Exception {
        if (keyStore == null) {
            keyStore = KeyStore.getInstance("Windows-MY");
            keyStore.load(null, null);
        }
        Certificate certificado = keyStore.getCertificate(alias);
        if (certificado == null) {
            throw new Exception("El almacen de llaves no posee el alias " + alias);
        }
        return consultarOCSP(trustKeyStore, (X509Certificate) certificado, url);
    }

    public static RESULTADO_VERIFICACION consultarOCSP(KeyStore trustKeyStore, X509Certificate certificado, String url) throws KeyStoreException, IOException, NoSuchAlgorithmException, CertificateException {
        if (trustKeyStore == null) {
            trustKeyStore = KeyStore.getInstance("Windows-MY");
            trustKeyStore.load(null, null);
        }
        String alias = trustKeyStore.getCertificateAlias(certificado);
        if (alias == null) {
            trustKeyStore.deleteEntry("temporal");
            trustKeyStore.setCertificateEntry("temporal", certificado);
            trustKeyStore.load(null, null);
            alias = "temporal";
        }
        Certificate[] cadenaCertificacion = trustKeyStore.getCertificateChain(alias);
        trustKeyStore.deleteEntry("temporal");
        return consultarOCSP(cadenaCertificacion, url);
    }
}
