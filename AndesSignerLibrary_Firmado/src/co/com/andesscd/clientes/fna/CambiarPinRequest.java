// Decompiled by DJ v3.12.12.98 Copyright 2014 Atanas Neshkov  Date: 17/11/2017 7:06:54 p. m.
// Home Page: http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   CambiarPinRequest.java
package co.com.andesscd.clientes.fna;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {"tipoDoc", "documento", "pinactual", "pinnuevo"})
@XmlRootElement(name = "CambiarPinRequest")
public class CambiarPinRequest {

    protected int tipoDoc;

    @XmlElement(required = true)
    protected String documento;

    @XmlElement(required = true)
    protected String pinActual;

    @XmlElement(required = true)
    protected String pinNuevo;

    public CambiarPinRequest() {
        this.tipoDoc = 0;
        this.documento = "";
        this.pinActual = "";
        this.pinNuevo = "";
    }

    public CambiarPinRequest(int tipoDoc, String documento, String pinactual, String pinNuevo) {
        this.tipoDoc = tipoDoc;
        this.documento = documento;
        this.pinActual = pinactual;
        this.pinNuevo = pinNuevo;
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

    public String getPinActual() {
        return this.pinActual;
    }

    public void setPinActual(String value) {
        this.pinActual = value;
    }

    public String getPinNuevo() {
        return this.pinNuevo;
    }

    public void setPinNuevo(String value) {
        this.pinNuevo = value;
    }

    @Override
    public String toString() {
        return "CambiarPinRequest{" + "tipoDoc=" + tipoDoc + ", documento=" + documento + ", pinActual=" + pinActual + ", pinNuevo=" + pinNuevo + '}';
    }
    
}
