// Decompiled by DJ v3.12.12.98 Copyright 2014 Atanas Neshkov  Date: 17/11/2017 7:06:54 p. m.
// Home Page: http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   ConsultarCertRequest.java
package co.com.andesscd.clientes.fna;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {"tipoDoc", "documento"})
@XmlRootElement(name = "ConsultarCertRequest")
public class ConsultarCertRequest {

    protected int tipoDoc;

    @XmlElement(required = true)
    protected String documento;

    public ConsultarCertRequest() {
      this.tipoDoc = 0;
      this.documento = "";
    }

    public ConsultarCertRequest(int tipoDoc, String documento) {
        this.tipoDoc = tipoDoc;
        this.documento = documento;
    }

    public int getTipoDoc() {
        return this.tipoDoc;
    }

    public void setTipoDoc(int value) {
        this.tipoDoc = value;
    }

    public String getDocumento() {
        return this.documento;
    }

    public void setDocumento(String value) {
        this.documento = value;
    }

    @Override
    public String toString() {
        return "ConsultarCertRequest{" + "tipoDoc=" + tipoDoc + ", documento=" + documento + '}';
    }
    
}
