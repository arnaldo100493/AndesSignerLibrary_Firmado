// Decompiled by DJ v3.12.12.98 Copyright 2014 Atanas Neshkov  Date: 17/11/2017 7:06:55 p. m.
// Home Page: http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   FNAWebClient.java
package co.com.andesscd.clientes.fna;

import co.com.andesscd.Base64Coder;
import co.com.andesscd.pki.clases.ISignerWebService;
import java.net.URL;

// Referenced classes of package co.com.andesscd.clientes.fna:
//            ContextSSL, WSandesscd, HeaderHandlerResolver, FirmarRequest, 
//            RespuestaFirmar, FirmarOTPRequest, FirmarPortType, FirmarResponse, 
//            FirmarOTPPortType, FirmarOTPResponse
public class FNAWebClient implements ISignerWebService {

    protected WSandesscd service;

    protected FirmarPortType firmarPortType;

    protected FirmarOTPPortType firmarOTPPortType;

    public FNAWebClient() {

    }

    public FNAWebClient(String wsdlAddress, String userName, String userToken) throws Exception {
        wsdlAddress = (wsdlAddress == null || wsdlAddress.isEmpty()) ? "https://desarrollo.avance.org.co/~angela.gomez/WebService_Cert/wsdl.php?WSDL" : wsdlAddress;
        URL url = new URL(wsdlAddress);
        ContextSSL contexto = new ContextSSL();
        contexto.setContext();
        this.service = new WSandesscd(url);
        this.service.setHandlerResolver(new HeaderHandlerResolver(userName, userToken));
        this.firmarPortType = this.service.getFirmarPort();
        this.firmarOTPPortType = this.service.getFirmarOTPPort();
    }

    public static void main(String[] args) throws Exception {
        FNAWebClient fnaWebClient = new FNAWebClient("https://desarrollo.avance.org.co/~angela.gomez/WebService_Cert/wsdl.php?WSDL", "ango", "E9*#Npz8W@");
        RespuestaFirmar respuestaFirmar = fnaWebClient.firmar(1, "11100134", "80324634", new String(Base64Coder.encode("Estos son los datos a firmar".getBytes())));
        System.out.println(respuestaFirmar.getEstado());
        System.out.println(respuestaFirmar.getMensaje());
    }

    public RespuestaFirmar firmar(int tipoDocumento, String identificacion, String pin, String documentoBase64) throws Exception {
        FirmarRequest request = new FirmarRequest();
        request.setTipoDoc(tipoDocumento);
        request.setDocumento(identificacion);
        request.setPin(pin);
        request.setArchivo(documentoBase64);
        FirmarResponse response = this.firmarPortType.firmar(request);
        return new RespuestaFirmar(response.getEstado(), response.getMensaje());
    }

    public RespuestaFirmar firmar(int tipoDocumento, String identificacion, String pin, int otp, String documentoBase64) throws Exception {
        FirmarOTPRequest request = new FirmarOTPRequest();
        request.setTipoDoc(tipoDocumento);
        request.setDocumento(identificacion);
        request.setPin(pin);
        request.setArchivo(documentoBase64);
        request.setOtp(otp);
        FirmarOTPResponse response = this.firmarOTPPortType.firmarOTP(request);
        return new RespuestaFirmar(response.getEstado(), response.getMensaje());
    }

    public RespuestaFirmar firmar(int tipoDocumento, String identificacion, String pin, String tsaUser, String tsaPass, String documentoBase64) throws Exception {
        FirmarRequest request = new FirmarRequest();
        request.setTipoDoc(tipoDocumento);
        request.setDocumento(identificacion);
        request.setPin(pin);
        request.setArchivo(documentoBase64);
        request.setLoginTSA(tsaUser);
        request.setPassswordTSA(tsaPass);
        request.setFirmarEstampa("true");
        FirmarResponse response = this.firmarPortType.firmar(request);
        return new RespuestaFirmar(response.getEstado(), response.getMensaje());
    }

    public RespuestaFirmar firmar(int tipoDocumento, String identificacion, String pin, int otp, String tsaUser, String tsaPass, String documentoBase64) throws Exception {
        FirmarOTPRequest request = new FirmarOTPRequest();
        request.setTipoDoc(tipoDocumento);
        request.setDocumento(identificacion);
        request.setPin(pin);
        request.setArchivo(documentoBase64);
        request.setOtp(otp);
        request.setLoginTSA(tsaUser);
        request.setPassswordTSA(tsaPass);
        request.setFirmarEstampa("true");
        FirmarOTPResponse response = this.firmarOTPPortType.firmarOTP(request);
        return new RespuestaFirmar(response.getEstado(), response.getMensaje());
    }

    @Override
    public String firmarConCertificadoCustodiado(String usuario, String password, String datosAFirmarbase64) throws Exception {
        RespuestaFirmar respuesta = firmar(1, usuario, password, datosAFirmarbase64);
        return respuesta.getMensaje();
    }

    @Override
    public String firmarConCertificadoCustodiado(String usuario, String password, int otp, String datosAFirmarbase64) throws Exception {
        RespuestaFirmar respuesta = firmar(1, usuario, password, otp, datosAFirmarbase64);
        return respuesta.getMensaje();
    }

    @Override
    public String firmarConCertificadoCustodiado(String usuario, String password, String tsaUser, String tsaPass, String datosAFirmarbase64) throws Exception {
        RespuestaFirmar respuesta = firmar(1, usuario, password, tsaUser, tsaPass, datosAFirmarbase64);
        return respuesta.getMensaje();
    }

    @Override
    public String firmarConCertificadoCustodiado(String usuario, String password, int otp, String tsaUser, String tsaPass, String datosAFirmarbase64) throws Exception {
        RespuestaFirmar respuesta = firmar(1, usuario, password, otp, tsaUser, tsaPass, datosAFirmarbase64);
        return respuesta.getMensaje();
    }

    @Override
    public String firmarEnPKCS7ConPKCS12(String PKCS12Data, String PKCS12Password, String alias, String trustStoreData, String trustStorePassword, long fechaHoraVerificacion, String datosAFirmarbase64) throws Exception {
        throw new UnsupportedOperationException("Not supported yet 1.");
    }

    @Override
    public String firmarEnPKCS7ConPKCS11(String pkcs11LibPath, String pkca11Password, int slot, String alias, String aliasPassword, String trustStoreData, String trustStorePassword, long fechaHoraVerificacion, String datosAFirmarbase64) throws Exception {
        throw new UnsupportedOperationException("Not supported yet 2.");
    }

    @Override
    public String firmarEnPKCS7ConPKCS11(int user, String datosAFirmarbase64) throws Exception {
        throw new UnsupportedOperationException("Not supported yet 3.");
    }

    @Override
    public String firmarEnPDFConPKCS12(String PKCS12Data, String PKCS12Password, String alias, String trustStoreData, String trustStorePassword, long fechaHoraVerificacion, String datosAFirmarbase64) throws Exception {
        throw new UnsupportedOperationException("Not supported yet 4.");
    }

    @Override
    public String firmarEnPDFConPKCS11(String pkcs11LibPath, String pkca11Password, int slot, String alias, String aliasPassword, String trustStoreData, String trustStorePassword, long fechaHoraVerificacion, String datosAFirmarbase64) throws Exception {
        throw new UnsupportedOperationException("Not supported yet 5.");
    }
}
