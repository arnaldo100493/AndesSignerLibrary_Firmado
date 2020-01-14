// Decompiled by DJ v3.12.12.98 Copyright 2014 Atanas Neshkov  Date: 17/11/2017 7:06:53 p. m.
// Home Page: http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   CMSApplet.java
package co.com.andesscd.applets;

import co.com.andesscd.Base64Coder;
import co.com.andesscd.pki.clases.CMS;
import co.com.andesscd.pki.clases.RESULTADO_VERIFICACION;
import co.org.bouncy.jce.provider.BouncyCastleProvider;
import java.applet.Applet;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringWriter;
import java.security.AccessController;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivilegedAction;
import java.security.Provider;
import java.security.Security;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.json.JsonWriter;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.commons.io.FilenameUtils;

public class CMSApplet extends Applet {

    public void init() {
        AccessController.doPrivileged(new PrivilegedAction() {
            public Object run() {
                try {
                    System.out.print("Agregando provedor de seguridad por defecto...");
                    Security.addProvider((Provider) new BouncyCastleProvider());
                    System.out.println("...OK");
                } catch (Exception ex) {
                    System.out.println("...Error: " + ex.getMessage());
                }
                return null;
            }
        });
    }

    public static void setFuenteHorariaTSA(String urlFuenteHoraria, String loginFuenteHoraria, String passwordFuenteHoraria) {
        class PrivilegedActionSetTSA implements PrivilegedAction<String> {

            String urlFuenteHoraria = null;

            String loginFuenteHoraria = null;

            String passwordFuenteHoraria = null;

            public PrivilegedActionSetTSA(String urlFuenteHoraria, String loginFuenteHoraria, String passwordFuenteHoraria) {
                this.urlFuenteHoraria = urlFuenteHoraria;
                this.loginFuenteHoraria = loginFuenteHoraria;
                this.passwordFuenteHoraria = passwordFuenteHoraria;
            }

            public String run() {
                String retorno = "TSA ok";
                CMS.setFuenteHorariaTSA(this.urlFuenteHoraria, this.loginFuenteHoraria, this.passwordFuenteHoraria);
                return retorno;
            }
        };
        AccessController.doPrivileged(new PrivilegedActionSetTSA(urlFuenteHoraria, loginFuenteHoraria, passwordFuenteHoraria));
    }

    public String getFilePath(final String descripcionExtenciones, String... extenciones) {
        return AccessController.<String>doPrivileged(new PrivilegedAction<String>() {
            public String run() {
                String archivo = "";
                System.out.print("Abriendo dialogo y seleccionando archivo...");
                JFileChooser fc = new JFileChooser();
                if (descripcionExtenciones != null && !descripcionExtenciones.isEmpty()) {
                    fc.removeChoosableFileFilter(fc.getFileFilter());
                    fc.setFileFilter(new FileNameExtensionFilter(descripcionExtenciones, extenciones));
                }
                if (fc.showOpenDialog(null) == 0) {
                    File file = fc.getSelectedFile();
                    archivo = file.getAbsolutePath();
                }
                System.out.println("...OK");
                return archivo;
            }
        });
    }

    protected KeyStore abrirStore(String pathAlStore, String passwordDelStore) throws FileNotFoundException, KeyStoreException, IOException, NoSuchAlgorithmException, CertificateException {
        KeyStore keyStore = null;
        if (pathAlStore != null && !pathAlStore.trim().isEmpty()) {
            System.out.print("Determinando exitencia del store...");
            File archivo = new File(pathAlStore);
            if (!archivo.exists()) {
                throw new FileNotFoundException("No existe el archivo " + pathAlStore);
            }
            System.out.println("...OK");
            String ext = FilenameUtils.getExtension(pathAlStore).toLowerCase();
            if (ext.compareToIgnoreCase("p12") == 0 || ext.compareToIgnoreCase("pfx") == 0) {
                System.out.print("El estore posee extension pkcs12, intentando abrir...");
                keyStore = KeyStore.getInstance("pkcs12");
                keyStore.load(new FileInputStream(archivo), passwordDelStore.toCharArray());
                System.out.println("...OK");
            }
        }
        return keyStore;
    }

    public String listarStore(String pathAlStore, String passwordDelStore) {
        class PrivilegedActionListarStore implements PrivilegedAction<String> {

            String pathAlStore = null;

            String passwordDelStore = null;

            public PrivilegedActionListarStore(String pathAlStoreLocal, String passwordDelStoreLocal) {
                this.pathAlStore = pathAlStoreLocal;
                this.passwordDelStore = passwordDelStoreLocal;
            }

            public String run() {
                String retorno = "";
                KeyStore keyStore = null;
                try {
                    keyStore = CMSApplet.this.abrirStore(this.pathAlStore, this.passwordDelStore);
                    System.out.print("Obteniendo lista de certificados...");
                    HashMap<String, X509Certificate> certificados = CMS.listarStore(keyStore, this.passwordDelStore);
                    System.out.println("...OK");
                    System.out.print("Contruyendo JSON...");
                    JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
                    for (Map.Entry<String, X509Certificate> entrada : certificados.entrySet()) {
                        JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
                        objectBuilder.add("alias", entrada.getKey());
                        objectBuilder.add("subject", ((X509Certificate) entrada.getValue()).getSubjectX500Principal().toString());
                        objectBuilder.add("issuer", ((X509Certificate) entrada.getValue()).getIssuerX500Principal().toString());
                        arrayBuilder.add(objectBuilder);
                    }
                    StringWriter stringWriter = new StringWriter();
                    JsonWriter jsonWriter = Json.createWriter(stringWriter);
                    jsonWriter.writeArray(arrayBuilder.build());
                    jsonWriter.close();
                    System.out.println("...OK");
                    retorno = stringWriter.toString();
                } catch (Exception ex) {
                    System.out.println("...Error: " + ex.getMessage());
                }
                return retorno;
            }
        };
        return AccessController.<String>doPrivileged(new PrivilegedActionListarStore(pathAlStore, passwordDelStore));
    }

    protected String firmarStream(InputStream streamAFirmar, String pathAlStore, String passwordDelStore, String alias, boolean firmarComoPdf, OutputStream streamSalida) {
        class PrivilegedActionListarStore implements PrivilegedAction<String> {

            InputStream streamAFirmar = null;

            String pathAlStore = null;

            String passwordDelStore = null;

            String alias = null;

            OutputStream streamSalida = null;

            boolean firmarComoPdf = false;

            public PrivilegedActionListarStore(InputStream streamAFirmarLocal, String pathAlStoreLocal, String passwordDelStoreLocal, String aliasLocal, boolean firmarComoPdfLocal, OutputStream streamSalidaLocal) {
                this.pathAlStore = pathAlStoreLocal;
                this.passwordDelStore = passwordDelStoreLocal;
                this.streamAFirmar = streamAFirmarLocal;
                this.streamSalida = streamSalidaLocal;
                this.alias = aliasLocal;
                this.firmarComoPdf = firmarComoPdfLocal;
            }

            public String run() {
                String firmaBase64 = "";
                KeyStore keyStore = null;
                try {
                    if (this.alias == null || this.alias.isEmpty()) {
                        throw new Exception("No se proporciono alias del store");
                    }
                    keyStore = CMSApplet.this.abrirStore(this.pathAlStore, this.passwordDelStore);
                    ByteArrayOutputStream firmaBinaria = new ByteArrayOutputStream();
                    CMS miCMS = new CMS(this.streamAFirmar);
                    if (this.firmarComoPdf) {
                        System.out.print("Firmando en formato PDF...");
                        miCMS.firmarPdf(keyStore, this.alias, this.passwordDelStore, firmaBinaria);
                    } else {
                        System.out.print("Firmando en formato PKCS7...");
                        miCMS.firmar(keyStore, this.alias, this.passwordDelStore, firmaBinaria);
                    }
                    System.out.println("...OK");
                    System.out.print("Obteniendo la base 64 de la firma...");
                    byte[] arrayBytesFirma = firmaBinaria.toByteArray();
                    firmaBase64 = new String(Base64Coder.encode(arrayBytesFirma));
                    System.out.println("...OK");
                    if (this.streamSalida != null) {
                        System.out.print("Guardando firma en stream...");
                        this.streamSalida.write(arrayBytesFirma);
                        System.out.println("...OK");
                    }
                } catch (Exception ex) {
                    System.out.println("...Error: " + ex.getMessage());
                }
                return firmaBase64;
            }
        };
        return AccessController.<String>doPrivileged(new PrivilegedActionListarStore(streamAFirmar, pathAlStore, passwordDelStore, alias, firmarComoPdf, streamSalida));
    }

    protected byte[] firmarStreamEnBinario(InputStream streamAFirmar, String pathAlStore, String passwordDelStore, String alias, boolean firmarComoPdf, OutputStream streamSalida) {
        class PrivilegedActionListarStore implements PrivilegedAction<byte[]> {

            InputStream streamAFirmar = null;

            String pathAlStore = null;

            String passwordDelStore = null;

            String alias = null;

            OutputStream streamSalida = null;

            boolean firmarComoPdf = false;

            public PrivilegedActionListarStore(InputStream streamAFirmarLocal, String pathAlStoreLocal, String passwordDelStoreLocal, String aliasLocal, boolean firmarComoPdfLocal, OutputStream streamSalidaLocal) {
                this.pathAlStore = pathAlStoreLocal;
                this.passwordDelStore = passwordDelStoreLocal;
                this.streamAFirmar = streamAFirmarLocal;
                this.streamSalida = streamSalidaLocal;
                this.alias = aliasLocal;
                this.firmarComoPdf = firmarComoPdfLocal;
            }

            public byte[] run() {
                byte[] firma = null;
                KeyStore keyStore = null;
                try {
                    if (this.alias == null || this.alias.isEmpty()) {
                        throw new Exception("No se proporciono alias del store");
                    }
                    keyStore = CMSApplet.this.abrirStore(this.pathAlStore, this.passwordDelStore);
                    ByteArrayOutputStream firmaBinaria = new ByteArrayOutputStream();
                    CMS miCMS = new CMS(this.streamAFirmar);
                    if (this.firmarComoPdf) {
                        System.out.print("Firmando en formato PDF...");
                        miCMS.firmarPdf(keyStore, this.alias, this.passwordDelStore, firmaBinaria);
                    } else {
                        System.out.print("Firmando en formato PKCS7...");
                        miCMS.firmar(keyStore, this.alias, this.passwordDelStore, firmaBinaria);
                    }
                    System.out.println("...OK");
                    System.out.print("Obteniendo la firma en array de byte...");
                    firma = firmaBinaria.toByteArray();
                    System.out.println("...OK");
                    if (this.streamSalida != null) {
                        System.out.print("Guardando firma en stream...");
                        this.streamSalida.write(firma);
                        System.out.println("...OK");
                    }
                } catch (Exception ex) {
                    System.out.println("...Error: " + ex.getMessage());
                }
                return firma;
            }
        };
        return AccessController.<byte[]>doPrivileged(new PrivilegedActionListarStore(streamAFirmar, pathAlStore, passwordDelStore, alias, firmarComoPdf, streamSalida));
    }

    public String firmarArchivo(String pathAlArchivo, String pathAlStore, String passwordDelStore, String alias) {
        class PrivilegedActionListarStore implements PrivilegedAction<String> {

            String pathAlArchivo = null;

            String pathAlStore = null;

            String passwordDelStore = null;

            String alias = null;

            public PrivilegedActionListarStore(String pathAlArchivoLocal, String pathAlStoreLocal, String passwordDelStoreLocal, String aliasLocal) {
                this.pathAlStore = pathAlStoreLocal;
                this.passwordDelStore = passwordDelStoreLocal;
                this.pathAlArchivo = pathAlArchivoLocal;
                this.alias = aliasLocal;
            }

            public String run() {
                String firmaBase64 = "";
                try {
                    ByteArrayOutputStream firmaBinaria;
                    String nombreArchivoSalida;
                    System.out.print("Determinando exitencia del archivo a firmar...");
                    File archivo = new File(this.pathAlArchivo);
                    if (!archivo.exists()) {
                        throw new FileNotFoundException("No existe el archivo " + this.pathAlArchivo);
                    }
                    System.out.println("...OK");
                    try (FileInputStream archivoAFirmar = new FileInputStream(archivo)) {
                        boolean firmarComoPdf;
                        firmaBinaria = new ByteArrayOutputStream();
                        if (FilenameUtils.getExtension(this.pathAlArchivo).compareToIgnoreCase("pdf") == 0) {
                            System.out.print("Firmando en formato PDF...");
                            firmarComoPdf = true;
                        } else {
                            System.out.print("Firmando en formato PKCS7...");
                            firmarComoPdf = false;
                        }
                        firmaBase64 = CMSApplet.this.firmarStream(archivoAFirmar, this.pathAlStore, this.passwordDelStore, this.alias, firmarComoPdf, firmaBinaria);
                        System.out.println("...OK");
                    }
                    if (FilenameUtils.getExtension(this.pathAlArchivo).compareToIgnoreCase("pdf") == 0) {
                        System.out.print("Guardando firma PDF a disco...");
                        nombreArchivoSalida = this.pathAlArchivo.replace(FilenameUtils.getBaseName(this.pathAlArchivo), FilenameUtils.getBaseName(this.pathAlArchivo) + "_firmado");
                    } else {
                        System.out.print("Guardando firma PKCS7 a disco...");
                        nombreArchivoSalida = this.pathAlArchivo + ".p7s";
                    }
                    try (FileOutputStream archivoSalida = new FileOutputStream(nombreArchivoSalida)) {
                        archivoSalida.write(firmaBinaria.toByteArray());
                        System.out.println("...OK");
                    }
                } catch (Exception ex) {
                    System.out.println("...Error: " + ex.getMessage());
                }
                return firmaBase64;
            }
        };
        return AccessController.<String>doPrivileged(new PrivilegedActionListarStore(pathAlArchivo, pathAlStore, passwordDelStore, alias));
    }

    public String firmarArchivo(String datosBase64, String pathAlStore, String passwordDelStore, String alias, boolean firmarComoPdf) {
        class PrivilegedActionListarStore implements PrivilegedAction<String> {

            String datosBase64 = null;

            String pathAlStore = null;

            String passwordDelStore = null;

            String alias = null;

            boolean firmarComoPdf = false;

            public PrivilegedActionListarStore(String datosBase64Local, String pathAlStoreLocal, String passwordDelStoreLocal, String aliasLocal, boolean firmarComoPdfLocal) {
                this.pathAlStore = pathAlStoreLocal;
                this.passwordDelStore = passwordDelStoreLocal;
                this.datosBase64 = datosBase64Local;
                this.alias = aliasLocal;
                this.firmarComoPdf = firmarComoPdfLocal;
            }

            public String run() {
                String firmaBase64 = "";
                try (ByteArrayInputStream archivoAFirmar = new ByteArrayInputStream(Base64Coder.decode(this.datosBase64))) {
                    ByteArrayOutputStream firmaBinaria = new ByteArrayOutputStream();
                    firmaBase64 = CMSApplet.this.firmarStream(archivoAFirmar, this.pathAlStore, this.passwordDelStore, this.alias, this.firmarComoPdf, firmaBinaria);
                    System.out.println("...OK");
                } catch (Exception ex) {
                    System.out.println("...Error: " + ex.getMessage());
                }
                return firmaBase64;
            }
        };
        return AccessController.<String>doPrivileged(new PrivilegedActionListarStore(datosBase64, pathAlStore, passwordDelStore, alias, firmarComoPdf));
    }

    public String firmarZip(String datosBase64, String pathAlStore, String passwordDelStore, String alias, boolean firmarComoPdf) {
        class PrivilegedActionListarStore implements PrivilegedAction<String> {

            String zipBase64 = null;

            String pathAlStore = null;

            String passwordDelStore = null;

            String alias = null;

            boolean firmarComoPdf = false;

            public PrivilegedActionListarStore(String zipBase64Local, String pathAlStoreLocal, String passwordDelStoreLocal, String aliasLocal, boolean firmarComoPdfLocal) {
                this.pathAlStore = pathAlStoreLocal;
                this.passwordDelStore = passwordDelStoreLocal;
                this.zipBase64 = zipBase64Local;
                this.alias = aliasLocal;
                this.firmarComoPdf = firmarComoPdfLocal;
            }

            public String run() {
                String zipSalidaBase64 = "";
                try {
                    int BUFFER = 1024;
                    byte[] data = new byte[BUFFER];
                    ByteArrayInputStream fis = new ByteArrayInputStream(Base64Coder.decode(this.zipBase64));
                    ZipInputStream zipEntrada = new ZipInputStream(new BufferedInputStream(fis));
                    ByteArrayOutputStream zipEnBytes = new ByteArrayOutputStream();
                    ZipOutputStream zipSalida = new ZipOutputStream(zipEnBytes);
                    ZipEntry entryZipEntrada;
                    while ((entryZipEntrada = zipEntrada.getNextEntry()) != null) {
                        ByteArrayOutputStream archivoEnBytes = new ByteArrayOutputStream();
                        int count;
                        while ((count = zipEntrada.read(data, 0, BUFFER)) != -1) {
                            archivoEnBytes.write(data, 0, count);
                        }
                        ByteArrayOutputStream firmaBinaria = null;
                        byte[] firmaEnBytes = CMSApplet.this.firmarStreamEnBinario(new ByteArrayInputStream(archivoEnBytes.toByteArray()), this.pathAlStore, this.passwordDelStore, this.alias, this.firmarComoPdf, firmaBinaria);
                        archivoEnBytes.close();
                        ZipEntry entryZipSalida = new ZipEntry(entryZipEntrada.getName());
                        zipSalida.putNextEntry(entryZipSalida);
                        zipSalida.write(firmaEnBytes);
                        System.out.println("...OK");
                    }
                    zipEntrada.close();
                    zipSalida.close();
                    zipEnBytes.flush();
                    System.out.print("Creando base 64 del zip");
                    zipSalidaBase64 = new String(Base64Coder.encode(zipEnBytes.toByteArray()));
                    System.out.print("... OK");
                    zipEnBytes.close();
                } catch (Exception ex) {
                    System.out.println("...Error: " + ex.getMessage());
                }
                return zipSalidaBase64;
            }
        };
        return AccessController.<String>doPrivileged(new PrivilegedActionListarStore(datosBase64, pathAlStore, passwordDelStore, alias, firmarComoPdf));
    }

    public String firmarTexto(String textoAFirmar, String pathAlStore, String passwordDelStore, String alias) {
        class PrivilegedActionListarStore implements PrivilegedAction<String> {

            String textoAFirmar = null;

            String pathAlStore = null;

            String passwordDelStore = null;

            String alias = null;

            public PrivilegedActionListarStore(String textoAFirmarLocal, String pathAlStoreLocal, String passwordDelStoreLocal, String aliasLocal) {
                this.textoAFirmar = textoAFirmarLocal;
                this.pathAlStore = pathAlStoreLocal;
                this.passwordDelStore = passwordDelStoreLocal;
                this.alias = aliasLocal;
            }

            public String run() {
                String firmaBase64 = "";
                try (ByteArrayInputStream streamAFirmar = new ByteArrayInputStream(this.textoAFirmar.getBytes("UTF-8"))) {
                    System.out.print("Firmando texto en formato PKCS7...");
                    firmaBase64 = CMSApplet.this.firmarStream(streamAFirmar, this.pathAlStore, this.passwordDelStore, this.alias, false, null);
                    System.out.println("...OK");
                } catch (Exception ex) {
                    System.out.println("...Error: " + ex.getMessage());
                }
                return firmaBase64;
            }
        };
        return AccessController.<String>doPrivileged(new PrivilegedActionListarStore(textoAFirmar, pathAlStore, passwordDelStore, alias));
    }

    public RESULTADO_VERIFICACION verificarFirma(String pathAlArchivo) {
        class PrivilegedActionListarStore implements PrivilegedAction<RESULTADO_VERIFICACION> {

            String pathAlArchivo = null;

            public PrivilegedActionListarStore(String pathAlArchivoLocal) {
                this.pathAlArchivo = pathAlArchivoLocal;
            }

            public RESULTADO_VERIFICACION run() {
                FileInputStream archivoAVerificar = null;
                RESULTADO_VERIFICACION resultadoVerificacion = RESULTADO_VERIFICACION.ERROR_DESCONOCIDO;
                try {
                    System.out.print("Determinando exitencia del archivo a verificar...");
                    File archivo = new File(this.pathAlArchivo);
                    if (!archivo.exists()) {
                        throw new FileNotFoundException("No existe el archivo " + this.pathAlArchivo);
                    }
                    System.out.println("...OK");
                    archivoAVerificar = new FileInputStream(archivo);
                    CMS miCMS = new CMS(archivoAVerificar);
                    if (FilenameUtils.getExtension(this.pathAlArchivo).compareToIgnoreCase("pdf") == 0) {
                        System.out.print("Verificando en formato PDF...");
                        resultadoVerificacion = miCMS.verificarPdf(null);
                        System.out.println("...OK");
                    } else if (FilenameUtils.getExtension(this.pathAlArchivo).compareToIgnoreCase("p7s") == 0) {
                        System.out.print("Verificando en formato PKCS7...");
                        miCMS.decode(new FileOutputStream(FilenameUtils.removeExtension(this.pathAlArchivo)));
                        resultadoVerificacion = miCMS.verificar(null, false);
                        System.out.println("...OK");
                    } else {
                        System.out.println("El archivo firmado debe tener extencion pdf o p7s para poder ser verificado.");
                    }
                } catch (Exception ex) {
                    System.out.println("...Error: " + ex.getMessage());
                } finally {
                    if (archivoAVerificar != null) {
                        try {
                            archivoAVerificar.close();
                        } catch (IOException ex) {
                            Logger.getLogger(CMSApplet.class.getName()).log(Level.SEVERE, (String) null, ex);
                        }
                    }
                }
                return resultadoVerificacion;
            }
        };
        return AccessController.<RESULTADO_VERIFICACION>doPrivileged(new PrivilegedActionListarStore(pathAlArchivo));
    }
}
