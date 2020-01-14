// Decompiled by DJ v3.12.12.98 Copyright 2014 Atanas Neshkov  Date: 17/11/2017 7:06:56 p. m.
// Home Page: http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   RevocacionRequest.java

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {"tipoDoc", "documento", "serialcertificado", "idcausalrevocacion", "observacion"})
@XmlRootElement(name = "RevocacionRequest")
public class RevocacionRequest {

    protected int tipoDoc;

    @XmlElement(required = true)
    protected String documento;

    @XmlElement(required = true)
    protected String serialCertificado;

    protected int idCausalRevocacion;

    @XmlElement(required = true)
    protected String observacion;

    public RevocacionRequest() {
        this.tipoDoc = 0;
        this.documento = "";
        this.serialCertificado = "";
        this.idCausalRevocacion = 0;
        this.observacion = "";
    }

    public RevocacionRequest(int tipoDoc, String documento, String serialCertificado, int idCausalRevocacion, String observacion) {
        this.tipoDoc = tipoDoc;
        this.documento = documento;
        this.serialCertificado = serialCertificado;
        this.idCausalRevocacion = idCausalRevocacion;
        this.observacion = observacion;
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

    public String getSerialCertificado() {
        return this.serialCertificado;
    }

    public void setSerialCertificado(String value) {
        this.serialCertificado = value;
    }

    public int getIdCausalRevocacion() {
        return this.idCausalRevocacion;
    }

    public void setIdCausalRevocacion(int value) {
        this.idCausalRevocacion = value;
    }

    public String getObservacion() {
        return this.observacion;
    }

    public void setObservacion(String value) {
        this.observacion = value;
    }

    @Override
    public String toString() {
        return "RevocacionRequest{" + "tipoDoc=" + tipoDoc + ", documento=" + documento + ", serialCertificado=" + serialCertificado + ", idCausalRevocacion=" + idCausalRevocacion + ", observacion=" + observacion + '}';
    }

}
