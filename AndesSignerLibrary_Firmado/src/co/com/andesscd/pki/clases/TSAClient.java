// Decompiled by DJ v3.12.12.98 Copyright 2014 Atanas Neshkov  Date: 17/11/2017 7:06:58 p. m.
// Home Page: http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   TSAClient.java
package co.com.andesscd.pki.clases;

import co.com.andesscd.Auxiliar;
import co.com.andesscd.Base64Coder;
import co.org.bouncy.asn1.ASN1ObjectIdentifier;
import co.org.bouncy.asn1.cmp.PKIFailureInfo;
import co.org.bouncy.asn1.x509.X509ObjectIdentifiers;
import co.org.bouncy.tsp.TSPException;
import co.org.bouncy.tsp.TimeStampRequest;
import co.org.bouncy.tsp.TimeStampRequestGenerator;
import co.org.bouncy.tsp.TimeStampResponse;
import co.org.bouncy.tsp.TimeStampToken;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;

public class TSAClient {

    public TSAClient() {

    }

    private static byte[] GetTSAResponse(String urlTSA, String usuario, String contrase, byte[] requestBytes) throws MalformedURLException, IOException, TSPException, Exception {
        HttpURLConnection conexionTSA;
        URL url = new URL(urlTSA);
        if (CMS.getProxy() != null) {
            conexionTSA = (HttpURLConnection) url.openConnection(CMS.getProxy());
        } else {
            conexionTSA = (HttpURLConnection) url.openConnection();
        }
        conexionTSA.setDoOutput(true);
        conexionTSA.setDoInput(true);
        conexionTSA.setRequestMethod("POST");
        conexionTSA.setRequestProperty("Content-type", "application/timestamp-query");
        conexionTSA.setRequestProperty("Content-length", String.valueOf(requestBytes.length));
        if (usuario != null && !usuario.isEmpty()) {
            String userpassword = usuario + ":" + contrase;
            String encodedAuthorization = new String(Base64Coder.encode(userpassword.getBytes()));
            conexionTSA.setRequestProperty("Authorization", "Basic " + encodedAuthorization);
        }
        OutputStream outputStream = conexionTSA.getOutputStream();
        outputStream.write(requestBytes);
        outputStream.flush();
        if (conexionTSA.getResponseCode() != 200) {
            throw new IOException("Received HTTP error: " + conexionTSA.getResponseCode() + " - " + conexionTSA.getResponseMessage());
        }
        return Auxiliar.inputStream2ByteArray(conexionTSA.getInputStream());
    }

    public static byte[] getTimestampToken(byte[] hash, String tsaURL, String tsaUserName, String tsaPassword) throws IOException, TSPException, Exception {
        TimeStampRequestGenerator generadorDePeticion = new TimeStampRequestGenerator();
        generadorDePeticion.setCertReq(true);
        generadorDePeticion.setReqPolicy(new ASN1ObjectIdentifier("1.3.6.1.4.1.601.10.3.1"));
        BigInteger nonce = BigInteger.valueOf(Calendar.getInstance().getTimeInMillis());
        TimeStampRequest peticionTsa = generadorDePeticion.generate(new ASN1ObjectIdentifier(X509ObjectIdentifiers.id_SHA1.getId()), hash, nonce);
        byte[] bytesPeticion = peticionTsa.getEncoded();
        byte[] bytesRespuesta = GetTSAResponse(tsaURL, tsaUserName, tsaPassword, bytesPeticion);
        TimeStampResponse respuestaTsa = new TimeStampResponse(bytesRespuesta);
        respuestaTsa.validate(peticionTsa);
        PKIFailureInfo fallaTsa = respuestaTsa.getFailInfo();
        if (fallaTsa != null) {
            throw new Exception("No se puede conectar a la URL");
        }
        TimeStampToken tokenTsa = respuestaTsa.getTimeStampToken();
        if (tokenTsa == null) {
            throw new Exception("No se obtuvo respuesta esperada");
        }
        return tokenTsa.getEncoded();
    }

    public static void getTimestampToken(InputStream streamEntrada, String url, String usuario, String contrase, OutputStream streamSalida) throws IOException, NoSuchAlgorithmException, TSPException, Exception {
        try {
            int leidos;
            byte[] buffer = new byte[1048576];
            MessageDigest sha1 = MessageDigest.getInstance("SHA");
            do {
                leidos = streamEntrada.read(buffer);
                if (leidos <= 0) {
                    continue;
                }
                sha1.update(buffer, 0, leidos);
            } while (leidos > 0);
            byte[] hash = sha1.digest();
            byte[] tokenBytes = getTimestampToken(hash, url, usuario, contrase);
            streamSalida.write(tokenBytes, 0, tokenBytes.length);
        } finally {
            if (streamSalida != null) {
                streamSalida.close();
            }
        }
    }
}
