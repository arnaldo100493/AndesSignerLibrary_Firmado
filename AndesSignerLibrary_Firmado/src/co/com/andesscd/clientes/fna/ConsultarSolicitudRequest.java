// Decompiled by DJ v3.12.12.98 Copyright 2014 Atanas Neshkov  Date: 17/11/2017 7:06:55 p. m.
// Home Page: http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   ConsultarSolicitudRequest.java
package co.com.andesscd.clientes.fna;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {"rasolicitud", "tipoDoc", "documento"})
@XmlRootElement(name = "ConsultarSolicitudRequest")
public class ConsultarSolicitudRequest {

    protected int rasolicitud;

    protected int tipoDoc;

    @XmlElement(required = true)
    protected String documento;
    
    
    public ConsultarSolicitudRequest() {
      this.rasolicitud = 0;
      this.tipoDoc = 0;
      this.documento = "";
    }

    public ConsultarSolicitudRequest(int rasolicitud, int tipoDoc, String documento) {
        this.rasolicitud = rasolicitud;
        this.tipoDoc = tipoDoc;
        this.documento = documento;
    }

    public int getRasolicitud() {
        return this.rasolicitud;
    }

    public void setRasolicitud(int value) {
        this.rasolicitud = value;
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
        return "ConsultarSolicitudRequest{" + "rasolicitud=" + rasolicitud + ", tipoDoc=" + tipoDoc + ", documento=" + documento + '}';
    }
    
}
