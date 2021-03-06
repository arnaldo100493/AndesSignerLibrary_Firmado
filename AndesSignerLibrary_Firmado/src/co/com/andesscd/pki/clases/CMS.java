// Decompiled by DJ v3.12.12.98 Copyright 2014 Atanas Neshkov  Date: 17/11/2017 7:06:57 p. m.
// Home Page: http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   CMS.java
package co.com.andesscd.pki.clases;

import co.com.andesscd.Auxiliar;
import co.com.andesscd.Base64Coder;
import co.com.andesscd.OSValidador;
import co.com.pdf.text.DocumentException;
import co.com.pdf.text.Rectangle;
import co.com.pdf.text.pdf.AcroFields;
import co.com.pdf.text.pdf.PdfDate;
import co.com.pdf.text.pdf.PdfDictionary;
import co.com.pdf.text.pdf.PdfName;
import co.com.pdf.text.pdf.PdfObject;
import co.com.pdf.text.pdf.PdfReader;
import co.com.pdf.text.pdf.PdfSignature;
import co.com.pdf.text.pdf.PdfSignatureAppearance;
import co.com.pdf.text.pdf.PdfStamper;
import co.com.pdf.text.pdf.PdfString;
import co.com.pdf.text.pdf.security.CertificateInfo;
import co.org.bouncy.asn1.ASN1Encodable;
import co.org.bouncy.asn1.ASN1EncodableVector;
import co.org.bouncy.asn1.ASN1InputStream;
import co.org.bouncy.asn1.ASN1ObjectIdentifier;
import co.org.bouncy.asn1.ASN1Primitive;
import co.org.bouncy.asn1.ASN1Sequence;
import co.org.bouncy.asn1.ASN1Set;
import co.org.bouncy.asn1.ASN1UTCTime;
import co.org.bouncy.asn1.DERSet;
import co.org.bouncy.asn1.DERUTCTime;
import co.org.bouncy.asn1.cms.Attribute;
import co.org.bouncy.asn1.cms.AttributeTable;
import co.org.bouncy.asn1.cms.CMSAttributes;
import co.org.bouncy.cert.jcajce.JcaCertStore;
import co.org.bouncy.cert.jcajce.JcaCertStoreBuilder;
import co.org.bouncy.cert.selector.X509CertificateHolderSelector;
import co.org.bouncy.cert.selector.jcajce.JcaX509CertSelectorConverter;
import co.org.bouncy.cms.CMSAttributeTableGenerator;
import co.org.bouncy.cms.CMSException;
import co.org.bouncy.cms.CMSProcessableByteArray;
import co.org.bouncy.cms.CMSSignedData;
import co.org.bouncy.cms.CMSSignedDataGenerator;
import co.org.bouncy.cms.CMSTypedData;
import co.org.bouncy.cms.DefaultSignedAttributeTableGenerator;
import co.org.bouncy.cms.SignerId;
import co.org.bouncy.cms.SignerInfoGenerator;
import co.org.bouncy.cms.SignerInformation;
import co.org.bouncy.cms.SignerInformationStore;
import co.org.bouncy.cms.SimpleAttributeTableGenerator;
import co.org.bouncy.cms.jcajce.JcaSignerInfoGeneratorBuilder;
import co.org.bouncy.cms.jcajce.JcaSimpleSignerInfoVerifierBuilder;
import co.org.bouncy.jce.provider.BouncyCastleProvider;
import co.org.bouncy.operator.ContentSigner;
import co.org.bouncy.operator.OperatorCreationException;
import co.org.bouncy.operator.jcajce.JcaContentSignerBuilder;
import co.org.bouncy.operator.jcajce.JcaDigestCalculatorProviderBuilder;
import co.org.bouncy.tsp.TSPAlgorithms;
import co.org.bouncy.tsp.TSPException;
import co.org.bouncy.tsp.TimeStampToken;
import co.org.bouncy.util.Store;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.PublicKey;
import java.security.Security;
import java.security.SignatureException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertPathBuilder;
import java.security.cert.CertPathBuilderException;
import java.security.cert.CertPathBuilderResult;
import java.security.cert.CertStore;
import java.security.cert.CertStoreException;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateExpiredException;
import java.security.cert.CertificateNotYetValidException;
import java.security.cert.CollectionCertStoreParameters;
import java.security.cert.PKIXBuilderParameters;
import java.security.cert.TrustAnchor;
import java.security.cert.X509CertSelector;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.SimpleTimeZone;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import sun.security.pkcs11.SunPKCS11;

public class CMS {

    private static int toleranciaTemporal = 0;

    private static final int PDF_CONTENTS_SIZE = 12000;

    public enum FuenteHoraria {
        LOCAL, SNTP, TSA;
    }

    private enum TipoDocumento {
        NO_ANALIZADO, DESCONOCIDO, PKCS7, PDF, PDF_FIRMADO;
    }

    private static String ID_TIME_STAMP_TOKEN = "1.2.840.113549.1.9.16.2.14";

    private static String ID_TIME = "1.2.840.113549.1.9.5";

    private static FuenteHoraria fuenteHoraria = FuenteHoraria.LOCAL;

    private static String urlFuenteHoraria = "";

    private static String loginFuenteHoraria = "";

    private static String passwordFuenteHoraria = "";

    private static boolean bcAgregado = false;

    private static boolean secureBlackboxIniciado = false;

    private static Proxy miProxy = null;

    private static boolean firmaPdfVisible = false;

    private byte[] contenido;

    private String nombreDocumento;

    private String descripcion;

    private TipoDocumento tipoDocumento;

    private CMSSignedData signedData;

    private SignerInformationStore firmantes;

    private CertStore certificados;

    private PdfReader pdfReader;

    private ArrayList<CMS> firmasPdf;

    private byte[] salida;

    private HashMap<String, byte[]> hashParaTimeStamp;

    private HashMap<String, String> pdfMetaData;

    protected String urlOCSP;

    protected String urlCRL;

    public CMS() throws FileNotFoundException, Exception {
        this.contenido = null;
        this.nombreDocumento = "";
        this.descripcion = "";
        this.tipoDocumento = TipoDocumento.NO_ANALIZADO;
        this.signedData = null;
        this.pdfReader = null;
        this.firmasPdf = null;
        this.urlOCSP = null;
        this.urlCRL = null;
        this.nombreDocumento = "";
        this.descripcion = "";
        iniciarCMS(null);

    }

    public CMS(String archivoEntrada) throws FileNotFoundException, Exception {
        this.contenido = null;
        this.nombreDocumento = "";
        this.descripcion = "";
        this.tipoDocumento = TipoDocumento.NO_ANALIZADO;
        this.signedData = null;
        this.pdfReader = null;
        this.firmasPdf = null;
        this.urlOCSP = null;
        this.urlCRL = null;
        File archivo = new File(archivoEntrada);
        this.nombreDocumento = archivo.getName();
        this.descripcion = "";
        iniciarCMS(new FileInputStream(archivoEntrada));
    }

    public CMS(InputStream streamEntrada) throws Exception {
        this.contenido = null;
        this.nombreDocumento = "";
        this.descripcion = "";
        this.tipoDocumento = TipoDocumento.NO_ANALIZADO;
        this.signedData = null;
        this.pdfReader = null;
        this.firmasPdf = null;
        this.urlOCSP = null;
        this.urlCRL = null;
        this.nombreDocumento = "";
        this.descripcion = "";
        iniciarCMS(streamEntrada);
    }

    public CMS(URL url) throws IOException, Exception {
        HttpURLConnection conexion;
        this.contenido = null;
        this.nombreDocumento = "";
        this.descripcion = "";
        this.tipoDocumento = TipoDocumento.NO_ANALIZADO;
        this.signedData = null;
        this.pdfReader = null;
        this.firmasPdf = null;
        this.urlOCSP = null;
        this.urlCRL = null;
        if (getProxy() != null) {
            conexion = (HttpURLConnection) url.openConnection(getProxy());
        } else {
            conexion = (HttpURLConnection) url.openConnection();
        }
        this.nombreDocumento = "";
        this.descripcion = "";
        iniciarCMS(conexion.getInputStream());
    }

    public static void iniciarComponente() {
        Provider provider = Security.getProvider("BC");
        if (provider == null) {
            Security.addProvider((Provider) new BouncyCastleProvider());
            bcAgregado = true;
        }
    }

    private static void iniciarComponente(String xmlLicencia) {
        iniciarComponente();
        if (!secureBlackboxIniciado);
    }

    private static void iniciarComponente(String xmlLicencia, String JNIPath) {
        iniciarComponente(xmlLicencia);
    }

    public static void setToleranciaTemporal(int toleranciaTemporal) {
        CMS.toleranciaTemporal = toleranciaTemporal;
    }

    public void setUrlOCSP(String url) {
        this.urlOCSP = url;
    }

    public void setUrlCRL(String url) {
        this.urlCRL = url;
    }

    public static int getToleranciaTemporal() {
        return toleranciaTemporal;
    }

    public static void setFirmaPdfVisible(boolean visible) {
        firmaPdfVisible = visible;
    }

    public static void setProxy(Proxy miProxy) {
        CMS.miProxy = miProxy;
    }

    public static void setProxy(String proxyUrl, int proxyPort, Proxy.Type type) {
        if (type == null) {
            type = Proxy.Type.HTTP;
        }
        if (proxyUrl != null && !"".equals(proxyUrl.trim())) {
            miProxy = new Proxy(type, new InetSocketAddress(proxyUrl, proxyPort));
        }
    }

    public static Proxy getProxy() {
        return miProxy;
    }

    public void setNombreDocumento(String nombreDocumento) {
        this.nombreDocumento = nombreDocumento;
    }

    public String getNombreDocumento() {
        return this.nombreDocumento;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public static FuenteHoraria getFuenteHoraria() {
        return fuenteHoraria;
    }

    public static void setFuenteHorariaLocal() {
        urlFuenteHoraria = "";
        loginFuenteHoraria = "";
        passwordFuenteHoraria = "";
        fuenteHoraria = FuenteHoraria.LOCAL;
    }

    public static void setFuenteHorariaSNTP(String urlFuenteHoraria) {
        CMS.urlFuenteHoraria = urlFuenteHoraria;
        loginFuenteHoraria = "";
        passwordFuenteHoraria = "";
        fuenteHoraria = FuenteHoraria.SNTP;
    }

    public static void setFuenteHorariaTSA(String urlFuenteHoraria, String loginFuenteHoraria, String passwordFuenteHoraria) {
        CMS.urlFuenteHoraria = urlFuenteHoraria;
        CMS.loginFuenteHoraria = loginFuenteHoraria;
        CMS.passwordFuenteHoraria = passwordFuenteHoraria;
        fuenteHoraria = FuenteHoraria.TSA;
    }

    public static GregorianCalendar getFechaActual() throws IOException, NoSuchAlgorithmException, TSPException, CertificateException, Exception {
        SNTPClient sntpClient;
        Random r;
        byte[] datosAleatorios;
        ByteArrayOutputStream tokenStream;
        TimeStamped timeStamped;
        GregorianCalendar fechaActual = null;
        switch (fuenteHoraria) {
            case LOCAL:
                fechaActual = new GregorianCalendar(new SimpleTimeZone(0, "America/Bogota"));
                fechaActual.setTime(new Date());
                break;
            case SNTP:
                sntpClient = new SNTPClient();
                sntpClient.requestTime(urlFuenteHoraria, 10000);
                fechaActual = sntpClient.getFecha();
                break;
            case TSA:
                r = new Random();
                datosAleatorios = new byte[8];
                r.nextBytes(datosAleatorios);
                tokenStream = new ByteArrayOutputStream();
                TSAClient.getTimestampToken(new ByteArrayInputStream(datosAleatorios), urlFuenteHoraria, loginFuenteHoraria, passwordFuenteHoraria, tokenStream);
                timeStamped = new TimeStamped(tokenStream.toByteArray());
                fechaActual = timeStamped.getFechaEstampado();
                break;
        }
        return fechaActual;
    }

    private void iniciarCMS(InputStream streamEntrada) throws Exception {
        iniciarComponente();
        if (streamEntrada == null) {
            throw new Exception("No se proporcionaron datos de entrada");
        }
        this.contenido = Auxiliar.inputStream2ByteArray(streamEntrada);
        this.hashParaTimeStamp = (HashMap) new HashMap<>();
    }

    public ArrayList<CMS> getFirmasPdf() throws IllegalArgumentException, IOException, Exception {
        if (this.firmasPdf != null) {
            return this.firmasPdf;
        }
        this.firmasPdf = new ArrayList<>();
        try {
            if (this.pdfReader == null) {
                this.pdfReader = new PdfReader(this.contenido);
            }
        } catch (IOException ex) {
            throw new IllegalArgumentException("Los datos de entrada no describen un documento pdf valido");
        }
        AcroFields af = this.pdfReader.getAcroFields();
        for (Object obj : af.getSignatureNames()) {
            ASN1Primitive pkcs;
            String name = (String) obj;
            af.getSignatureDictionary(name);
            PdfDictionary dict = af.getSignatureDictionary(name);
            PdfString contents = dict.getAsString(PdfName.CONTENTS);
            ASN1InputStream din = new ASN1InputStream(new ByteArrayInputStream(contents.getOriginalBytes()));
            try {
                pkcs = din.readObject();
            } catch (IOException e) {
                throw new IllegalArgumentException("No se puede decodificar la seccion del documento pdf que contiene la firma");
            }
            if (!(pkcs instanceof ASN1Sequence)) {
                throw new IllegalArgumentException("El formato de la firma en el documento pdf parace no ser valido");
            }
            this.firmasPdf.add(new CMS(new ByteArrayInputStream(((ASN1Sequence) pkcs).getEncoded())));
        }
        return this.firmasPdf;
    }

    public TipoDocumento getTipoDocumento() {
        if (this.tipoDocumento == TipoDocumento.NO_ANALIZADO) {
            try {
                if (this.signedData == null) {
                    decode(null);
                }
                return this.tipoDocumento = TipoDocumento.PKCS7;
            } catch (Exception ex) {
                try {
                    if (this.pdfReader == null) {
                        this.pdfReader = new PdfReader(this.contenido);
                    }
                    this.tipoDocumento = TipoDocumento.PDF;
                    if (!getFirmasPdf().isEmpty()) {
                        this.tipoDocumento = TipoDocumento.PDF_FIRMADO;
                    }
                } catch (Exception exception) {
                }
                if (this.tipoDocumento == TipoDocumento.NO_ANALIZADO) {
                    this.tipoDocumento = TipoDocumento.DESCONOCIDO;
                }
            }
        }
        return this.tipoDocumento;
    }

    public void decode(OutputStream streamSalida) throws IOException, CMSException, NoSuchAlgorithmException, NoSuchProviderException, GeneralSecurityException {
        try {
            if (this.signedData == null) {
                this.signedData = new CMSSignedData(this.contenido);
                this.firmantes = this.signedData.getSignerInfos();
                JcaCertStoreBuilder sb = new JcaCertStoreBuilder();
                sb.addCertificates(this.signedData.getCertificates());
                this.certificados = sb.build();
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                this.signedData.getSignedContent().write(byteArrayOutputStream);
                this.salida = byteArrayOutputStream.toByteArray();
            }
            if (streamSalida != null) {
                streamSalida.write(this.salida);
            }
        } finally {
            if (streamSalida != null) {
                streamSalida.close();
            }
        }
    }

    private Collection<? extends Certificate> getCertificados() throws CertStoreException {
        return this.certificados.getCertificates(null);
    }

    public void firmar(X509Certificate certificado, PrivateKey llavePrivada, Provider provedor, OutputStream streamSalida) throws NoSuchAlgorithmException, IOException, CMSException, CertStoreException, InvalidAlgorithmParameterException, CertificateEncodingException, OperatorCreationException, TSPException, Exception {
        CMSSignedDataGenerator generadorDeFirma = new CMSSignedDataGenerator();
        ArrayList<X509Certificate> listaDeCertificados = new ArrayList();
        try {
            CMSProcessableByteArray cMSProcessableByteArray;
            listaDeCertificados.add(certificado);
            JcaCertStore jcaCertStore = new JcaCertStore(listaDeCertificados);
            generadorDeFirma.addCertificates((Store) jcaCertStore);
            JcaSignerInfoGeneratorBuilder jcaSignerInfoGeneratorBuilder = new JcaSignerInfoGeneratorBuilder((new JcaDigestCalculatorProviderBuilder()).setProvider("BC").build());
            ASN1EncodableVector signedAttributes = new ASN1EncodableVector();
            Date fechaActual = null;
            if (fuenteHoraria == FuenteHoraria.LOCAL || fuenteHoraria == FuenteHoraria.SNTP) {
                fechaActual = getFechaActual().getTime();
            } else {
                MessageDigest sha = MessageDigest.getInstance("SHA1");
                byte[] timeStampToken = TSAClient.getTimestampToken(sha.digest(this.contenido), urlFuenteHoraria, loginFuenteHoraria, passwordFuenteHoraria);
                fechaActual = (new TimeStamped(timeStampToken)).getFechaEstampado().getTime();
                ASN1EncodableVector unSignedAttributes = new ASN1EncodableVector();
                unSignedAttributes.add((ASN1Encodable) new Attribute(new ASN1ObjectIdentifier(ID_TIME_STAMP_TOKEN), (ASN1Set) new DERSet((ASN1Encodable) (new ASN1InputStream(timeStampToken)).readObject())));
                AttributeTable unSignedAttributesTable = new AttributeTable(unSignedAttributes);
                SimpleAttributeTableGenerator unSignedAttributeGenerator = new SimpleAttributeTableGenerator(unSignedAttributesTable);
                jcaSignerInfoGeneratorBuilder.setUnsignedAttributeGenerator((CMSAttributeTableGenerator) unSignedAttributeGenerator);
            }
            signedAttributes.add((ASN1Encodable) new Attribute(CMSAttributes.signingTime, (ASN1Set) new DERSet((ASN1Encodable) new DERUTCTime(fechaActual))));
            AttributeTable signedAttributesTable = new AttributeTable(signedAttributes);
            DefaultSignedAttributeTableGenerator signedAttributeGenerator = new DefaultSignedAttributeTableGenerator(signedAttributesTable);
            jcaSignerInfoGeneratorBuilder.setSignedAttributeGenerator((CMSAttributeTableGenerator) signedAttributeGenerator);
            ContentSigner firmante = (new JcaContentSignerBuilder("SHA1withRSA")).setProvider(provedor).build(llavePrivada);
            SignerInfoGenerator signerInfoGenerator = jcaSignerInfoGeneratorBuilder.build(firmante, certificado);
            generadorDeFirma.addSignerInfoGenerator(signerInfoGenerator);
            ArrayList<SignerInformation> nuevosFirmantes = new ArrayList();
            if (getTipoDocumento() == TipoDocumento.PKCS7) {
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                decode(out);
                cMSProcessableByteArray = new CMSProcessableByteArray(out.toByteArray());
                Collection<SignerInformation> collection = this.firmantes.getSigners();
                for (SignerInformation si : collection) {
                    nuevosFirmantes.add(si);
                }
            } else {
                cMSProcessableByteArray = new CMSProcessableByteArray(this.contenido);
            }
            CMSSignedData datosFirmados = generadorDeFirma.generate((CMSTypedData) cMSProcessableByteArray, true);
            Collection<SignerInformation> firmantesLocal = datosFirmados.getSignerInfos().getSigners();
            for (SignerInformation si : firmantesLocal) {
                nuevosFirmantes.add(si);
            }
            SignerInformationStore newSignerInformationStore = new SignerInformationStore(nuevosFirmantes);
            CMSSignedData newSd = CMSSignedData.replaceSigners(datosFirmados, newSignerInformationStore);
            this.salida = newSd.getEncoded();
            streamSalida.write(this.salida);
        } finally {
            if (streamSalida != null) {
                streamSalida.close();
            }
        }
    }

    public void firmar(KeyStore keyStore, String alias, String contrase, Provider provedor, OutputStream streamSalida) throws KeyStoreException, NoSuchAlgorithmException, UnrecoverableKeyException, Exception {
        if (!keyStore.containsAlias(alias)) {
            throw new Exception("El almacen no contiene el alias: " + alias);
        }
        if (!keyStore.isKeyEntry(alias)) {
            throw new Exception("El almacen no contiene una llave con alias: " + alias);
        }
        X509Certificate certificado = (X509Certificate) keyStore.getCertificate(alias);
        if (certificado == null) {
            throw new Exception("El almacen no contiene un certificado con alias: " + alias);
        }
        PrivateKey llavePrivada = (PrivateKey) keyStore.getKey(alias, (contrase != null) ? contrase.toCharArray() : null);
        if (llavePrivada == null) {
            throw new Exception("No se pudo recuperar la llave con alias: " + alias);
        }
        firmar(certificado, llavePrivada, provedor, streamSalida);
    }

    public void firmar(KeyStore keyStore, String alias, String contrase, OutputStream streamSalida) throws NoSuchAlgorithmException, IOException, CMSException, CertStoreException, InvalidAlgorithmParameterException, CertificateEncodingException, OperatorCreationException, KeyStoreException, Exception {
        if (keyStore == null) {
            if (OSValidador.getOS() == OSValidador.OSTYPE.WINDOWS) {
                keyStore = KeyStore.getInstance("Windows-MY", "SunMSCAPI");
                keyStore.load(null, null);
            } else {
                keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
                keyStore.load(new FileInputStream(System.getProperty("java.home") + "/lib/security/cacerts".replace('/', File.separatorChar)), "changeit".toCharArray());
            }
        }
        firmar(keyStore, alias, contrase, keyStore.getProvider(), streamSalida);
    }

    public void firmar(KeyStore keyStore, String contrase, OutputStream streamSalida) throws KeyStoreException, NoSuchAlgorithmException, UnrecoverableKeyException, Exception {
        String alias = null;
        Enumeration<String> aliases = keyStore.aliases();
        while (aliases.hasMoreElements()) {
            alias = aliases.nextElement();
            if (keyStore.isKeyEntry(alias)) {
                break;
            }
        }
        if (alias == null || alias.isEmpty()) {
            throw new Exception("No se enocntro una llave util para firma dentro del contenedor");
        }
        firmar(keyStore, alias, contrase, streamSalida);
    }

    public static String firmar(String datos, String alias, String password) throws KeyStoreException, NoSuchProviderException, NoSuchAlgorithmException, CertificateException, IOException, Exception {
        KeyStore keystore;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        if (OSValidador.getOS() == OSValidador.OSTYPE.WINDOWS) {
            keystore = KeyStore.getInstance("Windows-MY", "SunMSCAPI");
            keystore.load(null, null);
        } else {
            keystore = KeyStore.getInstance(KeyStore.getDefaultType());
            keystore.load(new FileInputStream(System.getProperty("java.home") + "/lib/security/cacerts".replace('/', File.separatorChar)), "changeit".toCharArray());
        }
        if (!keystore.containsAlias(alias)) {
            throw new Exception("El almacen de windows no contiene el alias " + alias);
        }
        Certificate certificado = keystore.getCertificate(alias);
        Key privateKey = keystore.getKey(alias, password.toCharArray());
        CMS cms = new CMS(new ByteArrayInputStream(datos.getBytes("UTF-8")));
        cms.firmar((X509Certificate) certificado, (PrivateKey) privateKey, keystore.getProvider(), out);
        return new String(Base64Coder.encode(out.toByteArray()));
    }

    public static byte[] getSha1(InputStream s) throws NoSuchAlgorithmException, IOException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA1");
        int read = 0;
        byte[] buff = new byte[1048576];
        while ((read = s.read(buff, 0, 1048576)) > 0) {
            messageDigest.update(buff, 0, read);
        }
        return messageDigest.digest();
    }

    public static String getVersion() {
        return "2.4.1.1";
    }

    public void addPdfMetaData(HashMap<String, String> metaData) {
        this.pdfMetaData = metaData;
    }

    public void addPdfMetaData(String nombre, String valor) {
        if (this.pdfMetaData == null) {
            this.pdfMetaData = new HashMap<>();
        }
        this.pdfMetaData.put(nombre, valor);
    }

    public void setPdfMetaDataTitle(String titulo) {
        addPdfMetaData("Title", titulo);
    }

    public void setPdfMetaDataSubject(String asunto) {
        addPdfMetaData("Title", asunto);
    }

    public void setPdfMetaDataKeywords(String palabrasClave) {
        addPdfMetaData("Keywords", palabrasClave);
    }

    public void setPdfMetaDataCreator(String creador) {
        addPdfMetaData("Creator", creador);
    }

    public void setPdfMetaDataAuthor(String autor) {
        addPdfMetaData("Author", autor);
    }

    public void setPdfMetaDataCustom(String nombre, String valor) {
        addPdfMetaData(nombre, valor);
    }

    public PdfSignatureAppearance iniciarFirmaPdf(X509Certificate x509Certificate, OutputStream streamSalida) throws NoSuchAlgorithmException, TSPException, CertificateException, IOException, DocumentException, Exception {
        Calendar signDate = getFechaActual();
        PdfReader reader = new PdfReader(this.contenido);
        PdfStamper stamper = PdfStamper.createSignature(reader, streamSalida, false, null, true);
        if (this.pdfMetaData != null) {
            HashMap<String, String> hMap = reader.getInfo();
            hMap.putAll(this.pdfMetaData);
            stamper.setMoreInfo(hMap);
        }
        PdfSignature pdfSignature = new PdfSignature(PdfName.ADOBE_PPKMS, PdfName.ADBE_PKCS7_SHA1);
        pdfSignature.setDate(new PdfDate(signDate));
        if (x509Certificate != null) {
            pdfSignature.setName(CertificateInfo.getSubjectFields(x509Certificate).getField("CN"));
        }
        PdfSignatureAppearance signatureAppearance = stamper.getSignatureAppearance();
        if (firmaPdfVisible) {
            signatureAppearance.setVisibleSignature(new Rectangle(0.0F, 0.0F, 300.0F, 300.0F), reader.getNumberOfPages(), null);
        }
        signatureAppearance.setSignDate(signDate);
        if (x509Certificate != null) {
            signatureAppearance.setCertificate(x509Certificate);
        }
        signatureAppearance.setCryptoDictionary((PdfDictionary) pdfSignature);
        HashMap<Object, Object> exc = new HashMap<>();
        exc.put(PdfName.CONTENTS, Integer.valueOf(24002));
        if (x509Certificate == null);
        signatureAppearance.preClose(exc);
        return signatureAppearance;
    }

    public void finalizarFirmaPdf(X509Certificate x509Certificate, PdfSignatureAppearance signatureAppearance, byte[] firmaPlana, OutputStream streamSalida) throws IOException, DocumentException, CMSException, CertStoreException, NoSuchProviderException, GeneralSecurityException, Exception {
        PdfDictionary pdfDictionary = new PdfDictionary();
        if (x509Certificate == null) {
            CMS cmdHashFirmado = new CMS(new ByteArrayInputStream(firmaPlana));
            cmdHashFirmado.decode(null);
            Collection<? extends Certificate> certificadosUsados = cmdHashFirmado.certificados.getCertificates(null);
            for (Certificate certificado : certificadosUsados) {
                signatureAppearance.setCertificate(certificado);
            }
        }
        byte[] outc = new byte[12000];
        System.arraycopy(firmaPlana, 0, outc, 0, firmaPlana.length);
        pdfDictionary.put(PdfName.CONTENTS, (PdfObject) (new PdfString(outc)).setHexWriting(true));
        signatureAppearance.close(pdfDictionary);
        streamSalida.flush();
        streamSalida.close();
    }

    public void firmarPdf(X509Certificate x509Certificate, PrivateKey llavePrivada, Provider provedor, OutputStream streamSalida) throws InvalidAlgorithmParameterException, NoSuchAlgorithmException, IOException, DocumentException, CertStoreException, CMSException, Exception {
        PdfSignatureAppearance signatureAppearance = iniciarFirmaPdf(x509Certificate, streamSalida);
        CMS cmdHash = new CMS(new ByteArrayInputStream(getSha1(signatureAppearance.getRangeStream())));
        ByteArrayOutputStream streamHashFirmado = new ByteArrayOutputStream();
        cmdHash.firmar(x509Certificate, llavePrivada, provedor, streamHashFirmado);
        finalizarFirmaPdf(x509Certificate, signatureAppearance, streamHashFirmado.toByteArray(), streamSalida);
    }

    public void firmarEnPDFConPKCS12WebServiceParcial(ISignerWebService webService, String PKCS12Data, String PKCS12Password, String alias, String trustStoreData, String trustStorePassword, OutputStream streamSalida) throws TSPException, CertificateException, IOException, DocumentException, NoSuchAlgorithmException, NoSuchProviderException, GeneralSecurityException, CertStoreException, Exception {
        PdfSignatureAppearance signatureAppearance = iniciarFirmaPdf(null, streamSalida);
        String firmaBase64 = webService.firmarEnPKCS7ConPKCS12(PKCS12Data, PKCS12Password, alias, trustStoreData, trustStorePassword, 0L, new String(Base64Coder.encode(getSha1(signatureAppearance.getRangeStream()))));
        finalizarFirmaPdf(null, signatureAppearance, Base64Coder.decode(firmaBase64), streamSalida);
    }

    public void firmarEnPDFConPKCS12WebServiceTotal(ISignerWebService webService, String PKCS12Data, String PKCS12Password, String alias, String trustStoreData, String trustStorePassword, OutputStream streamSalida) throws TSPException, CertificateException, IOException, DocumentException, NoSuchAlgorithmException, NoSuchProviderException, GeneralSecurityException, CertStoreException, Exception {
        String firmaBase64 = webService.firmarEnPDFConPKCS12(PKCS12Data, PKCS12Password, alias, trustStoreData, trustStorePassword, 0L, new String(Base64Coder.encode(this.contenido)));
        streamSalida.write(Base64Coder.decode(firmaBase64));
    }

    public void firmarMasivoEnPDFConPKCS11WebServiceParcial(ISignerWebService webService, int user, OutputStream streamSalida) throws TSPException, CertificateException, IOException, DocumentException, NoSuchAlgorithmException, NoSuchProviderException, GeneralSecurityException, CertStoreException, Exception {
        PdfSignatureAppearance signatureAppearance = iniciarFirmaPdf(null, streamSalida);
        String firmaBase64 = webService.firmarEnPKCS7ConPKCS11(user, new String(Base64Coder.encode(getSha1(signatureAppearance.getRangeStream()))));
        finalizarFirmaPdf(null, signatureAppearance, Base64Coder.decode(firmaBase64), streamSalida);
    }

    public void firmarEnPDFConPKCS11WebServiceParcial(ISignerWebService webService, String pkcs11LibPath, String pkca11Password, int slot, String alias, String aliasPassword, String trustStoreData, String trustStorePassword, OutputStream streamSalida) throws TSPException, CertificateException, IOException, DocumentException, NoSuchAlgorithmException, NoSuchProviderException, GeneralSecurityException, CertStoreException, Exception {
        PdfSignatureAppearance signatureAppearance = iniciarFirmaPdf(null, streamSalida);
        String firmaBase64 = webService.firmarEnPKCS7ConPKCS11(pkcs11LibPath, pkca11Password, slot, alias, aliasPassword, trustStoreData, trustStorePassword, 0L, new String(Base64Coder.encode(getSha1(signatureAppearance.getRangeStream()))));
        finalizarFirmaPdf(null, signatureAppearance, Base64Coder.decode(firmaBase64), streamSalida);
    }

    public void firmarEnPDFConPKCS11WebServiceTotal(ISignerWebService webService, String pkcs11LibPath, String pkca11Password, int slot, String alias, String aliasPassword, String trustStoreData, String trustStorePassword, OutputStream streamSalida) throws TSPException, CertificateException, IOException, DocumentException, NoSuchAlgorithmException, NoSuchProviderException, GeneralSecurityException, CertStoreException, Exception {
        String firmaBase64 = webService.firmarEnPDFConPKCS11(pkcs11LibPath, pkca11Password, slot, alias, aliasPassword, trustStoreData, trustStorePassword, 0L, new String(Base64Coder.encode(this.contenido)));
        streamSalida.write(Base64Coder.decode(firmaBase64));
    }

    public void firmarPdf(KeyStore keyStore, String alias, String contrase, Provider provedor, OutputStream streamSalida) throws InvalidAlgorithmParameterException, NoSuchAlgorithmException, IOException, DocumentException, CertStoreException, CMSException, Exception {
        if (!keyStore.containsAlias(alias)) {
            throw new Exception("El almacen no contiene el alias: " + alias);
        }
        if (!keyStore.isKeyEntry(alias)) {
            throw new Exception("El almacen no contiene una llave con alias: " + alias);
        }
        X509Certificate certificado = (X509Certificate) keyStore.getCertificate(alias);
        if (certificado == null) {
            throw new Exception("El almacen no contiene un certificado con alias: " + alias);
        }
        PrivateKey llavePrivada = (PrivateKey) keyStore.getKey(alias, (contrase != null) ? contrase.toCharArray() : null);
        if (llavePrivada == null) {
            throw new Exception("No se pudo recuperar la llave con alias: " + alias);
        }
        firmarPdf(certificado, llavePrivada, provedor, streamSalida);
    }

    public void firmarPdf(KeyStore keyStore, String alias, String contrase, OutputStream streamSalida) throws InvalidAlgorithmParameterException, NoSuchAlgorithmException, IOException, DocumentException, CertStoreException, CMSException, Exception {
        if (keyStore == null) {
            if (OSValidador.getOS() == OSValidador.OSTYPE.WINDOWS) {
                keyStore = KeyStore.getInstance("Windows-MY", "SunMSCAPI");
                keyStore.load(null, null);
            } else {
                if (contrase == null || contrase.isEmpty()) {
                    contrase = "changeit";
                }
                keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
                keyStore.load(new FileInputStream(System.getProperty("java.home") + "/lib/security/cacerts".replace('/', File.separatorChar)), contrase.toCharArray());
            }
        }
        firmarPdf(keyStore, alias, contrase, keyStore.getProvider(), streamSalida);
    }

    public void firmarPdf(KeyStore keyStore, String contrase, OutputStream streamSalida) throws InvalidAlgorithmParameterException, NoSuchAlgorithmException, IOException, DocumentException, CertStoreException, CMSException, Exception {
        String alias = null;
        Enumeration<String> aliases = keyStore.aliases();
        while (aliases.hasMoreElements()) {
            alias = aliases.nextElement();
            if (keyStore.isKeyEntry(alias)) {
                break;
            }
        }
        if (alias == null || alias.isEmpty()) {
            throw new Exception("No se enocntro una llave util para firma dentro del contenedor");
        }
        firmarPdf(keyStore, alias, contrase, streamSalida);
    }

    public void firmarPdf(ISignerWebService webService, String usuario, String password, OutputStream streamSalida) throws TSPException, CertificateException, IOException, DocumentException, NoSuchAlgorithmException, NoSuchProviderException, GeneralSecurityException, CertStoreException, Exception {
        PdfSignatureAppearance signatureAppearance = iniciarFirmaPdf(null, streamSalida);
        String firmaBase64 = webService.firmarConCertificadoCustodiado(usuario, password, new String(Base64Coder.encode(getSha1(signatureAppearance.getRangeStream()))));
        finalizarFirmaPdf(null, signatureAppearance, Base64Coder.decodeLines(firmaBase64), streamSalida);
    }

    public void firmarPdf(ISignerWebService webService, String usuario, String password, int otp, OutputStream streamSalida) throws TSPException, CertificateException, IOException, DocumentException, NoSuchAlgorithmException, NoSuchProviderException, GeneralSecurityException, CertStoreException, Exception {
        PdfSignatureAppearance signatureAppearance = iniciarFirmaPdf(null, streamSalida);
        String firmaBase64 = webService.firmarConCertificadoCustodiado(usuario, password, otp, new String(Base64Coder.encode(getSha1(signatureAppearance.getRangeStream()))));
        finalizarFirmaPdf(null, signatureAppearance, Base64Coder.decodeLines(firmaBase64), streamSalida);
    }

    public void firmarPdf(ISignerWebService webService, String usuario, String password, String tsaUser, String tsaPass, OutputStream streamSalida) throws TSPException, CertificateException, IOException, DocumentException, NoSuchAlgorithmException, NoSuchProviderException, GeneralSecurityException, CertStoreException, Exception {
        PdfSignatureAppearance signatureAppearance = iniciarFirmaPdf(null, streamSalida);
        String firmaBase64 = webService.firmarConCertificadoCustodiado(usuario, password, tsaUser, tsaPass, new String(Base64Coder.encode(getSha1(signatureAppearance.getRangeStream()))));
        finalizarFirmaPdf(null, signatureAppearance, Base64Coder.decodeLines(firmaBase64), streamSalida);
    }

    public void firmarPdf(ISignerWebService webService, String usuario, String password, int otp, String tsaUser, String tsaPass, OutputStream streamSalida) throws TSPException, CertificateException, IOException, DocumentException, NoSuchAlgorithmException, NoSuchProviderException, GeneralSecurityException, CertStoreException, Exception {
        PdfSignatureAppearance signatureAppearance = iniciarFirmaPdf(null, streamSalida);
        String firmaBase64 = webService.firmarConCertificadoCustodiado(usuario, password, otp, tsaUser, tsaPass, new String(Base64Coder.encode(getSha1(signatureAppearance.getRangeStream()))));
        finalizarFirmaPdf(null, signatureAppearance, Base64Coder.decodeLines(firmaBase64), streamSalida);
    }

    public static HashMap<String, X509Certificate> listarStore(KeyStore keyStore, String password) throws NoSuchProviderException, KeyStoreException, IOException, NoSuchAlgorithmException, CertificateException, CMSException {
        HashMap<String, X509Certificate> certificados = new HashMap<>();
        if (keyStore == null) {
            if (OSValidador.getOS() == OSValidador.OSTYPE.WINDOWS) {
                keyStore = crearWindowsStore();
            } else {
                keyStore = crearJKSStore(System.getProperty("java.home") + "/lib/security/cacerts".replace('/', File.separatorChar), password);
            }
        }
        Enumeration<String> aliases = keyStore.aliases();
        while (aliases.hasMoreElements()) {
            String alias = aliases.nextElement();
            System.out.println(alias + "->" + keyStore.isKeyEntry(alias) + ":" + keyStore.isCertificateEntry(alias));
            if (!keyStore.isKeyEntry(alias) && !keyStore.isCertificateEntry(alias)) {
                continue;
            }
            X509Certificate cert = (X509Certificate) keyStore.getCertificate(alias);
            certificados.put(alias, cert);
        }
        return certificados;
    }

    public static HashMap<String, X509Certificate> listarStore(KeyStore keyStore) throws NoSuchProviderException, KeyStoreException, IOException, NoSuchAlgorithmException, CertificateException, CMSException {
        return listarStore(keyStore, "changeit");
    }

    private static boolean esAutoFirmado(X509Certificate cert) throws CertificateException, NoSuchAlgorithmException, NoSuchProviderException {
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

    public static RESULTADO_VERIFICACION validarCertificado(X509Certificate cert, Set<X509Certificate> trustedRootCerts, Set<X509Certificate> intermediateCerts, Date fechaDeInteres, String urlOCSP, String urlCRL) throws InvalidAlgorithmParameterException, NoSuchAlgorithmException, NoSuchProviderException, CertificateException, TSPException, Exception {
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
        pkixParams.setDate(fechaDeInteres);
        CertStore intermediateCertStore = CertStore.getInstance("Collection", new CollectionCertStoreParameters(intermediateCerts), "BC");
        pkixParams.addCertStore(intermediateCertStore);
        CertPathBuilder builder = CertPathBuilder.getInstance("PKIX", "BC");
        System.out.println("Esta es la version de Mauricio");
        try {
            result = builder.build(pkixParams);
        } catch (CertPathBuilderException ex) {
            RESULTADO_VERIFICACION res = validarFecha(cert, fechaDeInteres);
            if (res != RESULTADO_VERIFICACION.VALIDO) {
                return res;
            }
            return RESULTADO_VERIFICACION.CADENA_INCOMPLETA;
        }
        List<X509Certificate> certificados = (List) result.getCertPath().getCertificates();
        X509Certificate[] cadenaCertificacion = new X509Certificate[certificados.size()];
        for (int i = 0; i < certificados.size(); i++) {
            cadenaCertificacion[i] = certificados.get(i);
        }
        RESULTADO_VERIFICACION resultado = OCSPClient.consultarOCSP((Certificate[]) cadenaCertificacion, urlOCSP);
        if (resultado == RESULTADO_VERIFICACION.CERTIFICADO_REVOCADO) {
            return resultado;
        }
        if (resultado != RESULTADO_VERIFICACION.VALIDO) {
            resultado = CRLClient.consultarCRL(cert, urlCRL);
            if (resultado != RESULTADO_VERIFICACION.VALIDO) {
                return resultado;
            }
        }
        return validarFecha(cert, fechaDeInteres);
    }

    protected static RESULTADO_VERIFICACION validarFecha(X509Certificate cert, Date fechaDeInteres) throws CertificateException, TSPException, NoSuchAlgorithmException, Exception {
        try {
            if (fechaDeInteres == null) {
                fechaDeInteres = getFechaActual().getTime();
            }
            cert.checkValidity(fechaDeInteres);
        } catch (CertificateExpiredException ex) {
            return RESULTADO_VERIFICACION.CERTIFICADO_EXPIRADO;
        } catch (CertificateNotYetValidException ex) {
            return RESULTADO_VERIFICACION.CERTIFICADO_AUN_NO_VALIDO;
        }
        return RESULTADO_VERIFICACION.VALIDO;
    }

    public static RESULTADO_VERIFICACION validarCertificado(X509Certificate cert, Set<X509Certificate> additionalCerts, Date fechaDeInteres, String urlOCSP, String urlCRL) throws InvalidAlgorithmParameterException, NoSuchAlgorithmException, NoSuchProviderException, CertificateException, Exception {
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
        return validarCertificado(cert, trustedRootCerts, intermediateCerts, fechaDeInteres, urlOCSP, urlCRL);
    }

    public static RESULTADO_VERIFICACION validarCertificado(X509Certificate cert, KeyStore additionalCertsStore, Date fechaDeInteres, String urlOCSP, String urlCRL) throws KeyStoreException, IOException, NoSuchAlgorithmException, CertificateException, InvalidAlgorithmParameterException, NoSuchProviderException, Exception {
        HashSet<X509Certificate> additionalCerts = new HashSet<>();
        if (additionalCertsStore == null) {
            if (OSValidador.getOS() == OSValidador.OSTYPE.WINDOWS) {
                additionalCertsStore = KeyStore.getInstance("Windows-MY", "SunMSCAPI");
                additionalCertsStore.load(null, null);
                String alias = additionalCertsStore.getCertificateAlias(cert);
                if (alias == null) {
                    additionalCertsStore.deleteEntry("temporal");
                    additionalCertsStore.setCertificateEntry("temporal", cert);
                    additionalCertsStore.load(null, null);
                    alias = "temporal";
                }
                Certificate[] cadenaCertificacion = additionalCertsStore.getCertificateChain(alias);
                additionalCertsStore.deleteEntry("temporal");
                for (Certificate c : cadenaCertificacion) {
                    additionalCerts.add((X509Certificate) c);
                }
            } else {
                additionalCertsStore = KeyStore.getInstance(KeyStore.getDefaultType());
                additionalCertsStore.load(new FileInputStream(System.getProperty("java.home") + "/lib/security/cacerts".replace('/', File.separatorChar)), "changeit".toCharArray());
                Enumeration<String> aliases = additionalCertsStore.aliases();
                while (aliases.hasMoreElements()) {
                    String alias = aliases.nextElement();
                    if (!additionalCertsStore.isKeyEntry(alias) && !additionalCertsStore.isCertificateEntry(alias)) {
                        continue;
                    }
                    additionalCerts.add((X509Certificate) additionalCertsStore.getCertificate(alias));
                }
                additionalCerts.add(cert);
            }
        } else {
            Enumeration<String> aliases = additionalCertsStore.aliases();
            while (aliases.hasMoreElements()) {
                String alias = aliases.nextElement();
                if (!additionalCertsStore.isKeyEntry(alias) && !additionalCertsStore.isCertificateEntry(alias)) {
                    continue;
                }
                additionalCerts.add((X509Certificate) additionalCertsStore.getCertificate(alias));
            }
            additionalCerts.add(cert);
        }
        return validarCertificado(cert, additionalCerts, fechaDeInteres, urlOCSP, urlCRL);
    }

    public static RESULTADO_VERIFICACION validarCertificado(KeyStore keyStore, String alias, KeyStore additionalCertsStore, Date fechaDeInteres, String urlOCSP, String urlCRL) throws KeyStoreException, IOException, NoSuchAlgorithmException, CertificateException, InvalidAlgorithmParameterException, NoSuchProviderException, Exception {
        if (keyStore == null) {
            if (OSValidador.getOS() == OSValidador.OSTYPE.WINDOWS) {
                keyStore = KeyStore.getInstance("Windows-MY", "SunMSCAPI");
                keyStore.load(null, null);
            } else {
                keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
                keyStore.load(new FileInputStream(System.getProperty("java.home") + "/lib/security/cacerts".replace('/', File.separatorChar)), "changeit".toCharArray());
            }
        }
        if (alias == null) {
            Enumeration<String> aliases = keyStore.aliases();
            while (aliases.hasMoreElements()) {
                alias = aliases.nextElement();
                if (!keyStore.isKeyEntry(alias) && !keyStore.isCertificateEntry(alias));
            }
        }
        X509Certificate cert = (X509Certificate) keyStore.getCertificate(alias);
        if (cert == null) {
            throw new Exception("No existe el alias " + alias + " en el store");
        }
        return validarCertificado(cert, additionalCertsStore, fechaDeInteres, urlOCSP, urlCRL);
    }

    private static boolean compararHash(byte[] hash1, byte[] hash2) throws Exception {
        if (hash1 == null || hash2 == null) {
            throw new Exception("Uno de los hash a comprar es nulo");
        }
        if (hash1.length == 0 || hash2.length == 0) {
            throw new Exception("Uno de los hash a comprar esta vacio");
        }
        if (hash1.length != hash2.length) {
            return false;
        }
        for (int i = 0; i < hash1.length; i++) {
            if (hash1[i] != hash2[i]) {
                return false;
            }
        }
        return true;
    }

    public RESULTADO_VERIFICACION verificar(KeyStore additionalCertsStore, SignerInformation firmante, X509Certificate certificado, boolean verificarSoloIntegridad) throws NoSuchAlgorithmException, NoSuchProviderException, CMSException, KeyStoreException, IOException, CertificateException, OperatorCreationException, Exception {
        decode(null);
        JcaSimpleSignerInfoVerifierBuilder signerInfoVerifierBuilder = new JcaSimpleSignerInfoVerifierBuilder();
        if (!firmante.verify(signerInfoVerifierBuilder.build(certificado.getPublicKey()))) {
            return RESULTADO_VERIFICACION.DOCUMENTO_ORIGINAL_MODIFICADO;
        }
        Date fechaFirma = null;
        Date fechaActual = null;
        Attribute atributos = null;
        try {
            atributos = firmante.getSignedAttributes().get(new ASN1ObjectIdentifier(ID_TIME));
        } catch (Exception ex) {
        }
        if (atributos != null) {
            Enumeration en = atributos.getAttrValues().getObjects();
            while (en.hasMoreElements()) {
                Object obj = en.nextElement();
                if (obj instanceof ASN1UTCTime) {
                    ASN1UTCTime asn1Time = (ASN1UTCTime) obj;
                    fechaFirma = asn1Time.getDate();
                    continue;
                }
                if (obj instanceof DERUTCTime) {
                    DERUTCTime derTime = (DERUTCTime) obj;
                    fechaFirma = derTime.getDate();
                }
            }
        }
        atributos = null;
        try {
            atributos = firmante.getUnsignedAttributes().get(new ASN1ObjectIdentifier(ID_TIME_STAMP_TOKEN));
        } catch (Exception ex) {
        }
        if (atributos != null) {
            ASN1Encodable[] arr$ = atributos.getAttributeValues();
            int len$ = arr$.length, i$ = 0;
            if (i$ < len$) {
                String hashAlgoritmoNombre;
                ASN1Encodable encodeddata = arr$[i$];
                CMSSignedData cmsSignedData = new CMSSignedData(encodeddata.toASN1Primitive().getEncoded());
                TimeStampToken timeStampToken = new TimeStampToken(cmsSignedData);
                fechaFirma = timeStampToken.getTimeStampInfo().getGenTime();
                try {
                    CMS cmsToken = new CMS(new ByteArrayInputStream(encodeddata.toASN1Primitive().getEncoded()));
                    RESULTADO_VERIFICACION resultadoValidacionToken = cmsToken.verificar(additionalCertsStore, true);
                    if (resultadoValidacionToken != RESULTADO_VERIFICACION.VALIDO) {
                        return RESULTADO_VERIFICACION.TIMESTAMP_NO_CONFIABLE;
                    }
                } catch (Exception ex) {
                    return RESULTADO_VERIFICACION.TIMESTAMP_ERROR_DE_VALIDACION;
                }
                String hashAlgoritmoOID = timeStampToken.getTimeStampInfo().toASN1Structure().getMessageImprint().getHashAlgorithm().getAlgorithm().getId();
                if (hashAlgoritmoOID.compareToIgnoreCase(TSPAlgorithms.SHA1.getId()) == 0) {
                    hashAlgoritmoNombre = "SHA1";
                } else if (hashAlgoritmoOID.compareToIgnoreCase(TSPAlgorithms.MD5.getId()) == 0) {
                    hashAlgoritmoNombre = "MD5";
                } else if (hashAlgoritmoOID.compareToIgnoreCase(TSPAlgorithms.SHA256.getId()) == 0) {
                    hashAlgoritmoNombre = "SHA256";
                } else if (hashAlgoritmoOID.compareToIgnoreCase(TSPAlgorithms.SHA384.getId()) == 0) {
                    hashAlgoritmoNombre = "SHA384";
                } else if (hashAlgoritmoOID.compareToIgnoreCase(TSPAlgorithms.SHA512.getId()) == 0) {
                    hashAlgoritmoNombre = "SHA512";
                } else {
                    throw new Exception("Algoritmo timestamp no implementado");
                }
                if (!this.hashParaTimeStamp.containsKey(hashAlgoritmoNombre)) {
                    MessageDigest hashAlgoritmo = MessageDigest.getInstance(hashAlgoritmoNombre);
                    this.hashParaTimeStamp.put(hashAlgoritmoNombre, hashAlgoritmo.digest(this.salida));
                }
                if (!compararHash(this.hashParaTimeStamp.get(hashAlgoritmoNombre), timeStampToken.getTimeStampInfo().toASN1Structure().getMessageImprint().getHashedMessage())) {
                    return RESULTADO_VERIFICACION.TIMESTAMP_NO_CORRESPONDE;
                }
            }
        }
        if (fechaFirma == null) {
            return RESULTADO_VERIFICACION.FECHA_FIRMA_DESCONOCIDA;
        }
        fechaActual = getFechaActual().getTime();
        long segundos = TimeUnit.MILLISECONDS.toSeconds(fechaActual.getTime() - fechaFirma.getTime());
        if (segundos < 0L && Math.abs(segundos) > Math.abs(toleranciaTemporal)) {
            return RESULTADO_VERIFICACION.FIRMADO_EN_EL_FUTURO;
        }
        if (!verificarSoloIntegridad) {
            return validarCertificado(certificado, additionalCertsStore, fechaFirma, this.urlOCSP, this.urlCRL);
        }
        return RESULTADO_VERIFICACION.VALIDO;
    }

    public RESULTADO_VERIFICACION verificar(KeyStore additionalCertsStore, SignerInformationStore firmates, CertStore certificados, boolean verificarSoloIntegridad) throws CertStoreException, NoSuchAlgorithmException, NoSuchProviderException, CMSException, KeyStoreException, IOException, CertificateException, Exception {
        Iterator<SignerInformation> iterador = firmates.getSigners().iterator();
        while (iterador.hasNext()) {
            SignerInformation firmante = iterador.next();
            SignerId signerid = firmante.getSID();
            JcaX509CertSelectorConverter certSelectorConverter = new JcaX509CertSelectorConverter();
            X509CertificateHolderSelector certificateHolderSelector = new X509CertificateHolderSelector(signerid.getIssuer(), signerid.getSerialNumber());
            X509CertSelector signerConstraints = certSelectorConverter.getCertSelector(certificateHolderSelector);
            Collection<? extends Certificate> certificadosDelFirmante = certificados.getCertificates(signerConstraints);
            if (!certificadosDelFirmante.isEmpty()) {
                X509Certificate certificado = certificadosDelFirmante.iterator().next();
                RESULTADO_VERIFICACION resultado = verificar(additionalCertsStore, firmante, certificado, verificarSoloIntegridad);
                if (resultado != RESULTADO_VERIFICACION.VALIDO) {
                    return resultado;
                }
                continue;
            }
            return RESULTADO_VERIFICACION.CERTIFICADO_NO_ENCONTRADO;
        }
        return RESULTADO_VERIFICACION.VALIDO;
    }

    public RESULTADO_VERIFICACION verificar(KeyStore additionalCertsStore, boolean verificarSoloIntegridad) throws IOException, CMSException, CertStoreException, NoSuchAlgorithmException, NoSuchProviderException, KeyStoreException, CertificateException, GeneralSecurityException, Exception {
        decode(null);
        return verificar(additionalCertsStore, this.firmantes, this.certificados, verificarSoloIntegridad);
    }

    public RESULTADO_VERIFICACION verificarPdf(KeyStore additionalCertsStore) throws IOException, CMSException, CertStoreException, NoSuchAlgorithmException, NoSuchProviderException, KeyStoreException, CertificateException, Exception, NoSuchProviderException {
        RESULTADO_VERIFICACION resultado = RESULTADO_VERIFICACION.ERROR_DESCONOCIDO;
        ArrayList<CMS> firmas = getFirmasPdf();
        for (int i = 0; i < firmas.size(); i++) {
            resultado = ((CMS) firmas.get(i)).verificar(additionalCertsStore, false);
            if (resultado != RESULTADO_VERIFICACION.VALIDO) {
                return resultado;
            }
        }
        return resultado;
    }

    public static KeyStore crearPKCS11Store(String pkcs11LibPath, String pkcs11Password, int slot) throws CMSException, KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException {
        if (pkcs11LibPath == null || pkcs11LibPath.isEmpty()) {
            throw new CMSException("No se recibio ruta a la libreria PKCS11");
        }
        File file = new File(pkcs11LibPath);
        if (!file.exists()) {
            throw new CMSException("No existe la libreria PKCS11 en la ruta proporcionada");
        }
        Random random = new Random();
        String pkcs11LibConfig = "name=PKCS11_" + random.nextInt(2000000000) + "\nlibrary=" + pkcs11LibPath + "\nslot=" + slot + "\n";
        ByteArrayInputStream pkcs11LibConfigStream = new ByteArrayInputStream(pkcs11LibConfig.getBytes());
        KeyStore keyStore = KeyStore.getInstance("PKCS11", new SunPKCS11(pkcs11LibConfigStream));
        keyStore.load(pkcs11LibConfigStream, pkcs11Password.toCharArray());
        return keyStore;
    }

    public static KeyStore crearPKCS11Store(String pkcs11ConfigPath, String pkcs11Password) throws CMSException, KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException {
        if (pkcs11ConfigPath == null || pkcs11ConfigPath.isEmpty()) {
            throw new CMSException("No se recibio ruta al archivo de configuracion PKCS11");
        }
        File file = new File(pkcs11ConfigPath);
        if (!file.exists()) {
            throw new CMSException("No existe el archivo de configuracion PKCS11 en la ruta proporcionada");
        }
        ByteArrayInputStream pkcs11LibConfigStream = new ByteArrayInputStream(FileUtils.readFileToByteArray(file));
        KeyStore keyStore = KeyStore.getInstance("PKCS11", new SunPKCS11(pkcs11LibConfigStream));
        keyStore.load(pkcs11LibConfigStream, pkcs11Password.toCharArray());
        return keyStore;
    }

    public static KeyStore crearWindowsStore() throws KeyStoreException, IOException, NoSuchAlgorithmException, NoSuchProviderException, CertificateException {
        KeyStore keyStore = KeyStore.getInstance("Windows-MY", "SunMSCAPI");
        keyStore.load(null, null);
        return keyStore;
    }

    public static KeyStore crearJKSStore(InputStream jksStream, String jksPassword) throws CMSException, KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException {
        KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
        keyStore.load(jksStream, jksPassword.toCharArray());
        return keyStore;
    }

    public static KeyStore crearJKSStore(String jksPath, String jksPassword) throws CMSException, KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException {
        if (jksPath == null || jksPath.isEmpty()) {
            throw new CMSException("No se recibio ruta al archivo JKS");
        }
        File file = new File(jksPath);
        if (!file.exists()) {
            throw new CMSException("No existe el archivo JKS en la ruta proporcionada");
        }
        return crearJKSStore(new FileInputStream(jksPath), jksPassword);
    }

    public static KeyStore crearPKCS12Store(InputStream PCKS12Stream, String PCKS12Password) throws CMSException, KeyStoreException, FileNotFoundException, IOException, NoSuchAlgorithmException, CertificateException {
        KeyStore keyStore = KeyStore.getInstance("pkcs12");
        keyStore.load(PCKS12Stream, PCKS12Password.toCharArray());
        return keyStore;
    }

    public static KeyStore crearPKCS12Store(String PCKS12Path, String PCKS12Password) throws CMSException, KeyStoreException, FileNotFoundException, IOException, NoSuchAlgorithmException, CertificateException {
        if (PCKS12Path == null || PCKS12Path.isEmpty()) {
            throw new CMSException("No se recibio ruta a al archivo PKCS12");
        }
        File file = new File(PCKS12Path);
        if (!file.exists()) {
            throw new CMSException("No existe el archivo PKCS12 en la ruta proporcionada");
        }
        return crearPKCS12Store(new FileInputStream(PCKS12Path), PCKS12Password);
    }

    public SignerInformationStore getFirmantes() {
        return this.firmantes;
    }

    public CertStore getCertStore() {
        return this.certificados;
    }
}
