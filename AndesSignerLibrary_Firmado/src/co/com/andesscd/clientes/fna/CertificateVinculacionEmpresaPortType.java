// Decompiled by DJ v3.12.12.98 Copyright 2014 Atanas Neshkov  Date: 17/11/2017 7:06:54 p. m.
// Home Page: http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   CertificateVinculacionEmpresaPortType.java
package co.com.andesscd.clientes.fna;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

// Referenced classes of package co.com.andesscd.clientes.fna:
//            CertificateVinculacionEmpresaRequest, CertificateVinculacionEmpresaResponse
@WebService(name = "CertificateVinculacionEmpresaPortType", targetNamespace = "http://www.andesscd.com.co/")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
@XmlSeeAlso({ObjectFactory.class})
public interface CertificateVinculacionEmpresaPortType {

    @WebMethod(operationName = "CertificateVinculacionEmpresa", action = "https://ra.andesscd.com.co/test/WebService/soap-server_new.php#CertificateVinculacionEmpresa")
    @WebResult(name = "CertificateVinculacionEmpresaResponse", targetNamespace = "http://www.andesscd.com.co/", partName = "parameters")
    public CertificateVinculacionEmpresaResponse certificateVinculacionEmpresa(@WebParam(name = "CertificateVinculacionEmpresaRequest", targetNamespace = "http://www.andesscd.com.co/", partName = "parameters") CertificateVinculacionEmpresaRequest paramCertificateVinculacionEmpresaRequest);
}
