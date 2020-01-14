// Decompiled by DJ v3.12.12.98 Copyright 2014 Atanas Neshkov  Date: 17/11/2017 7:06:54 p. m.
// Home Page: http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   CertificadosRequest.java
package co.com.andesscd.clientes.fna;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {"tipoCert", "tipoDoc", "documento", "nombres", "apellidos", "municipio", "direccion", "email", "telefono", "celular", "ocupacion", "fechaCert", "vigenciaCert", "formato", "testigo", "foto", "soporte", "pin", "pkcs10", "verificDoc", "plantillaHuella"})
@XmlRootElement(name = "CertificadosRequest")
public class CertificadosRequest {

    protected int tipoCert;

    protected int tipoDoc;

    @XmlElement(required = true)
    protected String documento;

    @XmlElement(required = true)
    protected String nombres;

    @XmlElement(required = true)
    protected String apellidos;

    protected int municipio;

    @XmlElement(required = true)
    protected String direccion;

    @XmlElement(required = true)
    protected String email;

    protected String telefono;

    protected String celular;

    protected String ocupacion;

    @XmlElement(required = true)
    protected String fechaCert;

    protected int vigenciaCert;

    protected int formato;

    @XmlElement(required = true)
    protected String testigo;

    protected String foto;

    protected String soporte;

    protected String pin;

    protected String pkcs10;

    @XmlElement(name = "verific_doc")
    protected String verificDoc;

    protected String plantillaHuella;

    public CertificadosRequest() {
        this.tipoCert = 0;
        this.tipoDoc = 0;
        this.documento = "";
        this.nombres = "";
        this.apellidos = "";
        this.municipio = 0;
        this.direccion = "";
        this.email = "";
        this.telefono = "";
        this.celular = "";
        this.ocupacion = "";
        this.fechaCert = "";
        this.vigenciaCert = 0;
        this.formato = 0;
        this.testigo = "";
        this.foto = "";
        this.soporte = "";
        this.pin = "";
        this.pkcs10 = "";
        this.verificDoc = "";
        this.plantillaHuella = "";
    }

    public CertificadosRequest(int tipoCert, int tipoDoc, String documento, String nombres, String apellidos, int municipio, String direccion, String email, String telefono, String celular, String ocupacion, String fechaCert, int vigenciaCert, int formato, String testigo, String foto, String soporte, String pin, String pkcs10, String verificDoc, String plantillaHuella) {
        this.tipoCert = tipoCert;
        this.tipoDoc = tipoDoc;
        this.documento = documento;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.municipio = municipio;
        this.direccion = direccion;
        this.email = email;
        this.telefono = telefono;
        this.celular = celular;
        this.ocupacion = ocupacion;
        this.fechaCert = fechaCert;
        this.vigenciaCert = vigenciaCert;
        this.formato = formato;
        this.testigo = testigo;
        this.foto = foto;
        this.soporte = soporte;
        this.pin = pin;
        this.pkcs10 = pkcs10;
        this.verificDoc = verificDoc;
        this.plantillaHuella = plantillaHuella;
    }

    public int getTipoCert() {
        return this.tipoCert;
    }

    public void setTipoCert(int value) {
        this.tipoCert = value;
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

    public String getNombres() {
        return this.nombres;
    }

    public void setNombres(String value) {
        this.nombres = value;
    }

    public String getApellidos() {
        return this.apellidos;
    }

    public void setApellidos(String value) {
        this.apellidos = value;
    }

    public int getMunicipio() {
        return this.municipio;
    }

    public void setMunicipio(int value) {
        this.municipio = value;
    }

    public String getDireccion() {
        return this.direccion;
    }

    public void setDireccion(String value) {
        this.direccion = value;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String value) {
        this.email = value;
    }

    public String getTelefono() {
        return this.telefono;
    }

    public void setTelefono(String value) {
        this.telefono = value;
    }

    public String getCelular() {
        return this.celular;
    }

    public void setCelular(String value) {
        this.celular = value;
    }

    public String getOcupacion() {
        return this.ocupacion;
    }

    public void setOcupacion(String value) {
        this.ocupacion = value;
    }

    public String getFechaCert() {
        return this.fechaCert;
    }

    public void setFechaCert(String value) {
        this.fechaCert = value;
    }

    public int getVigenciaCert() {
        return this.vigenciaCert;
    }

    public void setVigenciaCert(int value) {
        this.vigenciaCert = value;
    }

    public int getFormato() {
        return this.formato;
    }

    public void setFormato(int value) {
        this.formato = value;
    }

    public String getTestigo() {
        return this.testigo;
    }

    public void setTestigo(String value) {
        this.testigo = value;
    }

    public String getFoto() {
        return this.foto;
    }

    public void setFoto(String value) {
        this.foto = value;
    }

    public String getSoporte() {
        return this.soporte;
    }

    public void setSoporte(String value) {
        this.soporte = value;
    }

    public String getPin() {
        return this.pin;
    }

    public void setPin(String value) {
        this.pin = value;
    }

    public String getPkcs10() {
        return this.pkcs10;
    }

    public void setPkcs10(String value) {
        this.pkcs10 = value;
    }

    public String getVerificDoc() {
        return this.verificDoc;
    }

    public void setVerificDoc(String value) {
        this.verificDoc = value;
    }

    public String getPlantillaHuella() {
        return this.plantillaHuella;
    }

    public void setPlantillaHuella(String value) {
        this.plantillaHuella = value;
    }

    @Override
    public String toString() {
        return "CertificadosRequest{" + "tipoCert=" + tipoCert + ", tipoDoc=" + tipoDoc + ", documento=" + documento + ", nombres=" + nombres + ", apellidos=" + apellidos + ", municipio=" + municipio + ", direccion=" + direccion + ", email=" + email + ", telefono=" + telefono + ", celular=" + celular + ", ocupacion=" + ocupacion + ", fechaCert=" + fechaCert + ", vigenciaCert=" + vigenciaCert + ", formato=" + formato + ", testigo=" + testigo + ", foto=" + foto + ", soporte=" + soporte + ", pin=" + pin + ", pkcs10=" + pkcs10 + ", verificDoc=" + verificDoc + ", plantillaHuella=" + plantillaHuella + '}';
    }

}
