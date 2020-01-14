// Decompiled by DJ v3.12.12.98 Copyright 2014 Atanas Neshkov  Date: 17/11/2017 7:06:57 p. m.
// Home Page: http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   OCSPManager.java
package co.com.andesscd.pki.clases;

import co.org.bouncy.asn1.ASN1OctetString;
import co.org.bouncy.asn1.DEROctetString;
import co.org.bouncy.asn1.ocsp.OCSPObjectIdentifiers;
import co.org.bouncy.asn1.x509.Extension;
import co.org.bouncy.asn1.x509.Extensions;
import co.org.bouncy.asn1.x509.SubjectPublicKeyInfo;
import co.org.bouncy.cert.X509CRLHolder;
import co.org.bouncy.cert.X509CertificateHolder;
import co.org.bouncy.cert.ocsp.BasicOCSPResp;
import co.org.bouncy.cert.ocsp.BasicOCSPRespBuilder;
import co.org.bouncy.cert.ocsp.CertificateID;
import co.org.bouncy.cert.ocsp.CertificateStatus;
import co.org.bouncy.cert.ocsp.OCSPException;
import co.org.bouncy.cert.ocsp.OCSPReq;
import co.org.bouncy.cert.ocsp.OCSPReqBuilder;
import co.org.bouncy.cert.ocsp.OCSPResp;
import co.org.bouncy.cert.ocsp.OCSPRespBuilder;
import co.org.bouncy.cert.ocsp.Req;
import co.org.bouncy.cert.ocsp.RevokedStatus;
import co.org.bouncy.cert.ocsp.SingleResp;
import co.org.bouncy.ocsp.OCSPException;
import co.org.bouncy.operator.ContentSigner;
import co.org.bouncy.operator.OperatorCreationException;
import co.org.bouncy.operator.jcajce.JcaContentSignerBuilder;
import co.org.bouncy.operator.jcajce.JcaContentVerifierProviderBuilder;
import co.org.bouncy.operator.jcajce.JcaDigestCalculatorProviderBuilder;
import java.io.IOException;
import java.math.BigInteger;
import java.security.PrivateKey;
import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.Date;

// Referenced classes of package co.com.andesscd.pki.clases:
//            CRLManager
public class OCSPManager {

    public OCSPManager() {

    }

    public static OCSPReq generateOCSPRequest(X509Certificate issuerCert, BigInteger serialNumber) throws OCSPException, OCSPException, CertificateEncodingException, OperatorCreationException, OCSPException, IOException {
        CertificateID id = new CertificateID((new JcaDigestCalculatorProviderBuilder()).setProvider("BC").build().get(CertificateID.HASH_SHA1), new X509CertificateHolder(issuerCert.getEncoded()), serialNumber);
        OCSPReqBuilder ocspGen = new OCSPReqBuilder();
        ocspGen.addRequest(id);
        BigInteger nonce = BigInteger.valueOf(System.currentTimeMillis());
        Extension ext = new Extension(OCSPObjectIdentifiers.id_pkix_ocsp_nonce, true, (ASN1OctetString) new DEROctetString(nonce.toByteArray()));
        ocspGen.setRequestExtensions(new Extensions(new Extension[]{ext}));
        return ocspGen.build();
    }

    public static void listRequest(OCSPReq req) {
        Req[] requests = req.getRequestList();
        for (int i = 0; i != requests.length; i++) {
            CertificateID certID = requests[i].getCertID();
            System.out.println("OCSP Request to check certificate number " + certID.getSerialNumber());
        }
    }

    public static OCSPResp generateOCSPResponse(OCSPReq request, X509Certificate caCert, PrivateKey privKey, X509CRLHolder crl) {
        BasicOCSPRespBuilder respGen;
        int response = 2;
        SubjectPublicKeyInfo keyinfo = SubjectPublicKeyInfo.getInstance(caCert.getPublicKey().getEncoded());
        try {
            respGen = new BasicOCSPRespBuilder(keyinfo, (new JcaDigestCalculatorProviderBuilder()).setProvider("BC").build().get(CertificateID.HASH_SHA1));
        } catch (OperatorCreationException | OCSPException e) {
            return null;
        }
        Extension ext = request.getExtension(OCSPObjectIdentifiers.id_pkix_ocsp_nonce);
        if (ext != null) {
            respGen.setResponseExtensions(new Extensions(new Extension[]{ext}));
        }
        Req[] requests = request.getRequestList();
        for (int i = 0; i != requests.length; i++) {
            CertificateID certID = requests[i].getCertID();
            BigInteger serial = certID.getSerialNumber();
            if (CRLManager.serialNotInCRL(crl, serial)) {
                respGen.addResponse(certID, CertificateStatus.GOOD);
            } else {
                respGen.addResponse(certID, (CertificateStatus) new RevokedStatus(new Date(), 9));
            }
        }
        try {
            ContentSigner contentSigner = (new JcaContentSignerBuilder("SHA1withRSA")).setProvider("BC").build(privKey);
            BasicOCSPResp basicResp = respGen.build(contentSigner, new X509CertificateHolder[]{new X509CertificateHolder(caCert.getEncoded())}, new Date());
            response = 0;
            return (new OCSPRespBuilder()).build(response, basicResp);
        } catch (OperatorCreationException | CertificateEncodingException | IOException | OCSPException e) {
            return null;
        }
    }

    public static String analyseResponse(OCSPResp response, OCSPReq request, X509Certificate caCert) throws Exception {
        BasicOCSPResp basicResponse = (BasicOCSPResp) response.getResponseObject();
        if (basicResponse.isSignatureValid((new JcaContentVerifierProviderBuilder()).setProvider("BC").build(caCert.getPublicKey()))) {
            SingleResp[] responses = basicResponse.getResponses();
            byte[] reqNonce = request.getExtension(OCSPObjectIdentifiers.id_pkix_ocsp_nonce).getExtnId().getEncoded();
            byte[] respNonce = basicResponse.getExtension(OCSPObjectIdentifiers.id_pkix_ocsp_nonce).getExtnId().getEncoded();
            if (reqNonce == null || Arrays.equals(reqNonce, respNonce)) {
                String message = "";
                int i = 0;
                if (i != responses.length) {
                    message = message + " certificate number " + responses[i].getCertID().getSerialNumber();
                    if (responses[i].getCertStatus() == CertificateStatus.GOOD) {
                        return message + " status: good";
                    }
                    return message + " status: revoked";
                }
                return message;
            }
            return "response nonce failed to validate";
        }
        return "response failed to verify OCSP signature";
    }
}
