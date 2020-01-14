// Decompiled by DJ v3.12.12.98 Copyright 2014 Atanas Neshkov  Date: 17/11/2017 7:06:58 p. m.
// Home Page: http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   TimeStamped.java
package co.com.andesscd.pki.clases;

import co.org.bouncy.cert.X509CertificateHolder;
import co.org.bouncy.cms.CMSException;
import co.org.bouncy.cms.CMSSignedData;
import co.org.bouncy.cms.SignerInformation;
import co.org.bouncy.tsp.TSPException;
import co.org.bouncy.tsp.TimeStampToken;
import co.org.bouncy.util.Selector;
import co.org.bouncy.util.Store;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.SimpleTimeZone;

public class TimeStamped {

    private GregorianCalendar fechaEstampado;

    private byte[] hash;

    private String hashAlgoritmoOid;

    private X509Certificate firmante;

    private byte[] encoded;

    public TimeStamped(byte[] encodedTimeStamped) throws CMSException, TSPException, IOException, CertificateException, Exception {
        TimeStampToken timeStampToken = new TimeStampToken(new CMSSignedData(encodedTimeStamped));
        CMSSignedData cmsSignedData = new CMSSignedData(encodedTimeStamped);
        SimpleTimeZone simpleTimeZone = new SimpleTimeZone(0, "America/Bogota");
        this.encoded = encodedTimeStamped;
        this.fechaEstampado = new GregorianCalendar(simpleTimeZone);
        this.fechaEstampado.setTime(timeStampToken.getTimeStampInfo().getGenTime());
        this.hash = timeStampToken.getTimeStampInfo().getMessageImprintDigest();
        this.hashAlgoritmoOid = timeStampToken.getTimeStampInfo().getMessageImprintAlgOID().getId();
        Store certificateStore = cmsSignedData.getCertificates();
        Iterator<SignerInformation> signerIterator = cmsSignedData.getSignerInfos().getSigners().iterator();
        if (signerIterator.hasNext()) {
            SignerInformation signerInformation = signerIterator.next();
            Iterator<X509CertificateHolder> certificateIterator = certificateStore.getMatches((Selector) signerInformation.getSID()).iterator();
            X509CertificateHolder certificateHolder = certificateIterator.next();
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
            this.firmante = (X509Certificate) certificateFactory.generateCertificate(new ByteArrayInputStream(certificateHolder.getEncoded()));
        }
    }

    public GregorianCalendar getFechaEstampado() {
        return this.fechaEstampado;
    }

    public byte[] getHash() {
        return this.hash;
    }

    public String getHashAlgorithmOid() {
        return this.hashAlgoritmoOid;
    }

    public X509Certificate getCertificadoFirmante() {
        return this.firmante;
    }

    public byte[] getEncoded() {
        return this.encoded;
    }

    public boolean verificarDatos(InputStream stream) throws NoSuchAlgorithmException, IOException, Exception {
        int leidos;
        byte[] buffer = new byte[1048576];
        MessageDigest sha1 = MessageDigest.getInstance("SHA");
        do {
            leidos = stream.read(buffer);
            if (leidos <= 0) {
                continue;
            }
            sha1.update(buffer, 0, leidos);
        } while (leidos > 0);
        byte[] hashCalculado = sha1.digest();
        if (this.hash == null) {
            throw new Exception("El hash en el token es nulo");
        }
        if (this.hash.length != hashCalculado.length) {
            return false;
        }
        for (int i = 0; i < this.hash.length; i++) {
            if (this.hash[i] != hashCalculado[i]) {
                return false;
            }
        }
        return true;
    }

    public boolean verificarDatos(String rutaArchivo) throws NoSuchAlgorithmException, IOException, Exception {
        return verificarDatos(new FileInputStream(rutaArchivo));
    }
}
