// Decompiled by DJ v3.12.12.98 Copyright 2014 Atanas Neshkov  Date: 17/11/2017 7:06:56 p. m.
// Home Page: http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   VerificarFirmaRequest.java
package co.com.andesscd.clientes.fna;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {"archivo", "tipoArchivo"})
@XmlRootElement(name = "VerificarFirmaRequest")
public class VerificarFirmaRequest {

    @XmlElement(required = true)
    protected String archivo;

    @XmlElement(required = true)
    protected String tipoArchivo;

    public VerificarFirmaRequest() {
        this.archivo = "";
        this.tipoArchivo = "";
    }

    public VerificarFirmaRequest(String archivo, String tipoArchivo) {
        this.archivo = archivo;
        this.tipoArchivo = tipoArchivo;
    }

    public String getArchivo() {
        return this.archivo;
    }

    public void setArchivo(String value) {
        this.archivo = value;
    }

    public String getTipoArchivo() {
        return this.tipoArchivo;
    }

    public void setTipoArchivo(String value) {
        this.tipoArchivo = value;
    }

    @Override
    public String toString() {
        return "VerificarFirmaRequest{" + "archivo=" + archivo + ", tipoArchivo=" + tipoArchivo + '}';
    }

}
