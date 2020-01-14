// Decompiled by DJ v3.12.12.98 Copyright 2014 Atanas Neshkov  Date: 17/11/2017 7:06:57 p. m.
// Home Page: http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   WSandesscd.java
package co.com.andesscd.clientes.fna;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;
// Referenced classes of package co.com.andesscd.clientes.fna:
//            CertificadosPortType, CertificadosPersonaNaturalRUTPortType, CertificateVinculacionEmpresaPortType, FirmarPortType, 
//            VerificarFirmaPortType, ConsultarSolicitudPortType, FirmarOTPPortType, TiempoUsoOTPPortType, 
//            InvalidarOTPPortType, LoginPortType, DepartamentoPortType, MunicipioPortType, 
//            ConsultarCertPortType, RevocacionPortType, CambiarPinPortType

@WebServiceClient(name = "WSandesscd", targetNamespace = "http://www.andesscd.com.co/", wsdlLocation = "https://ra.andesscd.com.co/test/WebService/wsdl.php?WSDL")
public class WSandesscd extends Service {

    private static final URL WSANDESSCD_WSDL_LOCATION;

    private static final WebServiceException WSANDESSCD_EXCEPTION;

    private static final QName WSANDESSCD_QNAME = new QName("http://www.andesscd.com.co/", "WSandesscd");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("https://ra.andesscd.com.co/test/WebService/wsdl.php?WSDL");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        WSANDESSCD_WSDL_LOCATION = url;
        WSANDESSCD_EXCEPTION = e;
    }

    public WSandesscd() {
        super(__getWsdlLocation(), WSANDESSCD_QNAME);
    }

    public WSandesscd(WebServiceFeature... features) {
        super(__getWsdlLocation(), WSANDESSCD_QNAME, features);
    }

    public WSandesscd(URL wsdlLocation) {
        super(wsdlLocation, WSANDESSCD_QNAME);
    }

    public WSandesscd(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, WSANDESSCD_QNAME, features);
    }

    public WSandesscd(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public WSandesscd(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    @WebEndpoint(name = "CertificadosPort")
    public CertificadosPortType getCertificadosPort() {
        return getPort(new QName("http://www.andesscd.com.co/", "CertificadosPort"), CertificadosPortType.class);
    }

    @WebEndpoint(name = "CertificadosPort")
    public CertificadosPortType getCertificadosPort(WebServiceFeature... features) {
        return getPort(new QName("http://www.andesscd.com.co/", "CertificadosPort"), CertificadosPortType.class, features);
    }

    @WebEndpoint(name = "CertificadosPersonaNaturalRUTPort")
    public CertificadosPersonaNaturalRUTPortType getCertificadosPersonaNaturalRUTPort() {
        return getPort(new QName("http://www.andesscd.com.co/", "CertificadosPersonaNaturalRUTPort"), CertificadosPersonaNaturalRUTPortType.class);
    }

    @WebEndpoint(name = "CertificadosPersonaNaturalRUTPort")
    public CertificadosPersonaNaturalRUTPortType getCertificadosPersonaNaturalRUTPort(WebServiceFeature... features) {
        return getPort(new QName("http://www.andesscd.com.co/", "CertificadosPersonaNaturalRUTPort"), CertificadosPersonaNaturalRUTPortType.class, features);
    }

    @WebEndpoint(name = "CertificateVinculacionEmpresaPort")
    public CertificateVinculacionEmpresaPortType getCertificateVinculacionEmpresaPort() {
        return getPort(new QName("http://www.andesscd.com.co/", "CertificateVinculacionEmpresaPort"), CertificateVinculacionEmpresaPortType.class);
    }

    @WebEndpoint(name = "CertificateVinculacionEmpresaPort")
    public CertificateVinculacionEmpresaPortType getCertificateVinculacionEmpresaPort(WebServiceFeature... features) {
        return getPort(new QName("http://www.andesscd.com.co/", "CertificateVinculacionEmpresaPort"), CertificateVinculacionEmpresaPortType.class, features);
    }

    @WebEndpoint(name = "FirmarPort")
    public FirmarPortType getFirmarPort() {
        return getPort(new QName("http://www.andesscd.com.co/", "FirmarPort"), FirmarPortType.class);
    }

    @WebEndpoint(name = "FirmarPort")
    public FirmarPortType getFirmarPort(WebServiceFeature... features) {
        return getPort(new QName("http://www.andesscd.com.co/", "FirmarPort"), FirmarPortType.class, features);
    }

    @WebEndpoint(name = "VerificarFirmaPort")
    public VerificarFirmaPortType getVerificarFirmaPort() {
        return getPort(new QName("http://www.andesscd.com.co/", "VerificarFirmaPort"), VerificarFirmaPortType.class);
    }

    @WebEndpoint(name = "VerificarFirmaPort")
    public VerificarFirmaPortType getVerificarFirmaPort(WebServiceFeature... features) {
        return getPort(new QName("http://www.andesscd.com.co/", "VerificarFirmaPort"), VerificarFirmaPortType.class, features);
    }

    @WebEndpoint(name = "ConsultarSolicitudPort")
    public ConsultarSolicitudPortType getConsultarSolicitudPort() {
        return getPort(new QName("http://www.andesscd.com.co/", "ConsultarSolicitudPort"), ConsultarSolicitudPortType.class);
    }

    @WebEndpoint(name = "ConsultarSolicitudPort")
    public ConsultarSolicitudPortType getConsultarSolicitudPort(WebServiceFeature... features) {
        return getPort(new QName("http://www.andesscd.com.co/", "ConsultarSolicitudPort"), ConsultarSolicitudPortType.class, features);
    }

    @WebEndpoint(name = "FirmarOTPPort")
    public FirmarOTPPortType getFirmarOTPPort() {
        return getPort(new QName("http://www.andesscd.com.co/", "FirmarOTPPort"), FirmarOTPPortType.class);
    }

    @WebEndpoint(name = "FirmarOTPPort")
    public FirmarOTPPortType getFirmarOTPPort(WebServiceFeature... features) {
        return getPort(new QName("http://www.andesscd.com.co/", "FirmarOTPPort"), FirmarOTPPortType.class, features);
    }

    @WebEndpoint(name = "TiempoUsoOTPPort")
    public TiempoUsoOTPPortType getTiempoUsoOTPPort() {
        return getPort(new QName("http://www.andesscd.com.co/", "TiempoUsoOTPPort"), TiempoUsoOTPPortType.class);
    }

    @WebEndpoint(name = "TiempoUsoOTPPort")
    public TiempoUsoOTPPortType getTiempoUsoOTPPort(WebServiceFeature... features) {
        return getPort(new QName("http://www.andesscd.com.co/", "TiempoUsoOTPPort"), TiempoUsoOTPPortType.class, features);
    }

    @WebEndpoint(name = "InvalidarOTPPort")
    public InvalidarOTPPortType getInvalidarOTPPort() {
        return getPort(new QName("http://www.andesscd.com.co/", "InvalidarOTPPort"), InvalidarOTPPortType.class);
    }

    @WebEndpoint(name = "InvalidarOTPPort")
    public InvalidarOTPPortType getInvalidarOTPPort(WebServiceFeature... features) {
        return getPort(new QName("http://www.andesscd.com.co/", "InvalidarOTPPort"), InvalidarOTPPortType.class, features);
    }

    @WebEndpoint(name = "LoginPort")
    public LoginPortType getLoginPort() {
        return getPort(new QName("http://www.andesscd.com.co/", "LoginPort"), LoginPortType.class);
    }

    @WebEndpoint(name = "LoginPort")
    public LoginPortType getLoginPort(WebServiceFeature... features) {
        return getPort(new QName("http://www.andesscd.com.co/", "LoginPort"), LoginPortType.class, features);
    }

    @WebEndpoint(name = "DepartamentoPort")
    public DepartamentoPortType getDepartamentoPort() {
        return getPort(new QName("http://www.andesscd.com.co/", "DepartamentoPort"), DepartamentoPortType.class);
    }

    @WebEndpoint(name = "DepartamentoPort")
    public DepartamentoPortType getDepartamentoPort(WebServiceFeature... features) {
        return getPort(new QName("http://www.andesscd.com.co/", "DepartamentoPort"), DepartamentoPortType.class, features);
    }

    @WebEndpoint(name = "MunicipioPort")
    public MunicipioPortType getMunicipioPort() {
        return getPort(new QName("http://www.andesscd.com.co/", "MunicipioPort"), MunicipioPortType.class);
    }

    @WebEndpoint(name = "MunicipioPort")
    public MunicipioPortType getMunicipioPort(WebServiceFeature... features) {
        return getPort(new QName("http://www.andesscd.com.co/", "MunicipioPort"), MunicipioPortType.class, features);
    }

    @WebEndpoint(name = "ConsultarCertPort")
    public ConsultarCertPortType getConsultarCertPort() {
        return getPort(new QName("http://www.andesscd.com.co/", "ConsultarCertPort"), ConsultarCertPortType.class);
    }

    @WebEndpoint(name = "ConsultarCertPort")
    public ConsultarCertPortType getConsultarCertPort(WebServiceFeature... features) {
        return getPort(new QName("http://www.andesscd.com.co/", "ConsultarCertPort"), ConsultarCertPortType.class, features);
    }

    @WebEndpoint(name = "RevocacionPort")
    public RevocacionPortType getRevocacionPort() {
        return getPort(new QName("http://www.andesscd.com.co/", "RevocacionPort"), RevocacionPortType.class);
    }

    @WebEndpoint(name = "RevocacionPort")
    public RevocacionPortType getRevocacionPort(WebServiceFeature... features) {
        return getPort(new QName("http://www.andesscd.com.co/", "RevocacionPort"), RevocacionPortType.class, features);
    }

    @WebEndpoint(name = "CambiarPinPort")
    public CambiarPinPortType getCambiarPinPort() {
        return getPort(new QName("http://www.andesscd.com.co/", "CambiarPinPort"), CambiarPinPortType.class);
    }

    @WebEndpoint(name = "CambiarPinPort")
    public CambiarPinPortType getCambiarPinPort(WebServiceFeature... features) {
        return getPort(new QName("http://www.andesscd.com.co/", "CambiarPinPort"), CambiarPinPortType.class, features);
    }

    private static URL __getWsdlLocation() {
        if (WSANDESSCD_EXCEPTION != null) {
            throw WSANDESSCD_EXCEPTION;
        }
        return WSANDESSCD_WSDL_LOCATION;
    }
}
