// Decompiled by DJ v3.12.12.98 Copyright 2014 Atanas Neshkov  Date: 17/11/2017 7:06:54 p. m.
// Home Page: http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   CertificateVinculacionEmpresaRequest.java
package co.com.andesscd.clientes.fna;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {"tipoCert", "tipoDoc", "documento", "nombres", "apellidos", "municipio", "direccion", "email", "emailEnt", "telefono", "celular", "ocupacion", "tipoDocEnt", "documentoEnt", "razonsocial", "municipioEnt", "direccionEnt", "cargo", "unidadOrganizacional", "fechaCert", "formato", "vigenciaCert", "testigo", "foto", "pin", "pkcs10", "soporte", "verificDoc", "plantillaHuella"})
@XmlRootElement(name = "CertificateVinculacionEmpresaRequest")
public class CertificateVinculacionEmpresaRequest {

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

    @XmlElement(required = true)
    protected String emailEnt;

    protected String telefono;

    @XmlElement(required = true)
    protected String celular;

    protected String ocupacion;

    protected int tipoDocEnt;

    @XmlElement(required = true)
    protected String documentoEnt;

    @XmlElement(required = true)
    protected String razonsocial;

    protected int municipioEnt;

    @XmlElement(required = true)
    protected String direccionEnt;

    @XmlElement(required = true)
    protected String cargo;

    @XmlElement(required = true)
    protected String unidadOrganizacional;

    @XmlElement(required = true)
    protected String fechaCert;

    protected int formato;

    protected int vigenciaCert;

    protected String testigo;

    protected String foto;

    protected String pin;

    protected String pkcs10;

    @XmlElement(required = true)
    protected String soporte;

    @XmlElement(name = "verific_doc")
    protected String verificDoc;

    protected String plantillaHuella;

    public CertificateVinculacionEmpresaRequest() {
        this.tipoCert = 0;
        this.tipoDoc = 0;
        this.documento = "";
        this.nombres = "";
        this.apellidos = "";
        this.municipio = 0;
        this.direccion = "";
        this.email = "";
        this.emailEnt = "";
        this.telefono = "";
        this.celular = "";
        this.ocupacion = "";
        this.tipoDocEnt = 0;
        this.documentoEnt = "";
        this.cargo = "";
        this.unidadOrganizacional = "";
        this.fechaCert = "";
        this.formato = 0;
        this.vigenciaCert = 0;
        this.testigo = "";
        this.foto = "";
        this.pin = "";
        this.pkcs10 = "";
        this.soporte = "";
        this.verificDoc = "";
        this.plantillaHuella = "";
    }

    public CertificateVinculacionEmpresaRequest(int tipoCert, int tipoDoc, String documento, String nombres, String apellidos, int municipio, String direccion, String email, String emailEnt, String telefono, String celular, String ocupacion, int tipoDocEnt, String documentoEnt, String razonsocial, int municipioEnt, String direccionEnt, String cargo, String unidadOrganizacional, String fechaCert, int formato, int vigenciaCert, String testigo, String foto, String pin, String pkcs10, String soporte, String verificDoc, String plantillaHuella) {
        this.tipoCert = tipoCert;
        this.tipoDoc = tipoDoc;
        this.documento = documento;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.municipio = municipio;
        this.direccion = direccion;
        this.email = email;
        this.emailEnt = emailEnt;
        this.telefono = telefono;
        this.celular = celular;
        this.ocupacion = ocupacion;
        this.tipoDocEnt = tipoDocEnt;
        this.documentoEnt = documentoEnt;
        this.razonsocial = razonsocial;
        this.municipioEnt = municipioEnt;
        this.direccionEnt = direccionEnt;
        this.cargo = cargo;
        this.unidadOrganizacional = unidadOrganizacional;
        this.fechaCert = fechaCert;
        this.formato = formato;
        this.vigenciaCert = vigenciaCert;
        this.testigo = testigo;
        this.foto = foto;
        this.pin = pin;
        this.pkcs10 = pkcs10;
        this.soporte = soporte;
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

    public String getEmailEnt() {
        return this.emailEnt;
    }

    public void setEmailEnt(String value) {
        this.emailEnt = value;
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

    public int getTipoDocEnt() {
        return this.tipoDocEnt;
    }

    public void setTipoDocEnt(int value) {
        this.tipoDocEnt = value;
    }

    public String getDocumentoEnt() {
        return this.documentoEnt;
    }

    public void setDocumentoEnt(String value) {
        this.documentoEnt = value;
    }

    public String getRazonsocial() {
        return this.razonsocial;
    }

    public void setRazonsocial(String value) {
        this.razonsocial = value;
    }

    public int getMunicipioEnt() {
        return this.municipioEnt;
    }

    public void setMunicipioEnt(int value) {
        this.municipioEnt = value;
    }

    public String getDireccionEnt() {
        return this.direccionEnt;
    }

    public void setDireccionEnt(String value) {
        this.direccionEnt = value;
    }

    public String getCargo() {
        return this.cargo;
    }

    public void setCargo(String value) {
        this.cargo = value;
    }

    public String getUnidadOrganizacional() {
        return this.unidadOrganizacional;
    }

    public void setUnidadOrganizacional(String value) {
        this.unidadOrganizacional = value;
    }

    public String getFechaCert() {
        return this.fechaCert;
    }

    public void setFechaCert(String value) {
        this.fechaCert = value;
    }

    public int getFormato() {
        return this.formato;
    }

    public void setFormato(int value) {
        this.formato = value;
    }

    public int getVigenciaCert() {
        return this.vigenciaCert;
    }

    public void setVigenciaCert(int value) {
        this.vigenciaCert = value;
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

    public String getSoporte() {
        return this.soporte;
    }

    public void setSoporte(String value) {
        this.soporte = value;
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
        return "CertificateVinculacionEmpresaRequest{" + "tipoCert=" + tipoCert + ", tipoDoc=" + tipoDoc + ", documento=" + documento + ", nombres=" + nombres + ", apellidos=" + apellidos + ", municipio=" + municipio + ", direccion=" + direccion + ", email=" + email + ", emailEnt=" + emailEnt + ", telefono=" + telefono + ", celular=" + celular + ", ocupacion=" + ocupacion + ", tipoDocEnt=" + tipoDocEnt + ", documentoEnt=" + documentoEnt + ", razonsocial=" + razonsocial + ", municipioEnt=" + municipioEnt + ", direccionEnt=" + direccionEnt + ", cargo=" + cargo + ", unidadOrganizacional=" + unidadOrganizacional + ", fechaCert=" + fechaCert + ", formato=" + formato + ", vigenciaCert=" + vigenciaCert + ", testigo=" + testigo + ", foto=" + foto + ", pin=" + pin + ", pkcs10=" + pkcs10 + ", soporte=" + soporte + ", verificDoc=" + verificDoc + ", plantillaHuella=" + plantillaHuella + '}';
    }
    
}
