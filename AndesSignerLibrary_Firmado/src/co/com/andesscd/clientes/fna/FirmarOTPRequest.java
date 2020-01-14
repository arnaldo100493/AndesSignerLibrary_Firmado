// Decompiled by DJ v3.12.12.98 Copyright 2014 Atanas Neshkov  Date: 17/11/2017 7:06:55 p. m.
// Home Page: http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   FirmarOTPRequest.java
package co.com.andesscd.clientes.fna;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {"tipoDoc", "documento", "pin", "otp", "archivo", "firmaVisible", "firmarEstampa", "loginTSA", "passswordTSA"})
@XmlRootElement(name = "FirmarOTPRequest")
public class FirmarOTPRequest {

    protected int tipoDoc;

    @XmlElement(required = true)
    protected String documento;

    @XmlElement(required = true)
    protected String pin;

    protected int otp;

    @XmlElement(required = true)
    protected String archivo;

    protected String firmaVisible;

    protected String firmarEstampa;

    protected String loginTSA;

    protected String passswordTSA;

    public FirmarOTPRequest() {
        this.tipoDoc = 0;
        this.documento = "";
        this.pin = "";
        this.otp = 0;
        this.archivo = "";
        this.firmaVisible = "";
        this.firmarEstampa = "";
        this.loginTSA = "";
        this.loginTSA = "";
        this.passswordTSA = "";
    }

    public FirmarOTPRequest(int tipoDoc, String documento, String pin, int otp, String archivo, String firmaVisible, String firmarEstampa, String loginTSA, String passswordTSA) {
        this.tipoDoc = tipoDoc;
        this.documento = documento;
        this.pin = pin;
        this.otp = otp;
        this.archivo = archivo;
        this.firmaVisible = firmaVisible;
        this.firmarEstampa = firmarEstampa;
        this.loginTSA = loginTSA;
        this.passswordTSA = passswordTSA;
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

    public String getPin() {
        return this.pin;
    }

    public void setPin(String value) {
        this.pin = value;
    }

    public int getOtp() {
        return this.otp;
    }

    public void setOtp(int value) {
        this.otp = value;
    }

    public String getArchivo() {
        return this.archivo;
    }

    public void setArchivo(String value) {
        this.archivo = value;
    }

    public String getFirmaVisible() {
        return this.firmaVisible;
    }

    public void setFirmaVisible(String value) {
        this.firmaVisible = value;
    }

    public String getFirmarEstampa() {
        return this.firmarEstampa;
    }

    public void setFirmarEstampa(String value) {
        this.firmarEstampa = value;
    }

    public String getLoginTSA() {
        return this.loginTSA;
    }

    public void setLoginTSA(String value) {
        this.loginTSA = value;
    }

    public String getPassswordTSA() {
        return this.passswordTSA;
    }

    public void setPassswordTSA(String value) {
        this.passswordTSA = value;
    }

    @Override
    public String toString() {
        return "FirmarOTPRequest{" + "tipoDoc=" + tipoDoc + ", documento=" + documento + ", pin=" + pin + ", otp=" + otp + ", archivo=" + archivo + ", firmaVisible=" + firmaVisible + ", firmarEstampa=" + firmarEstampa + ", loginTSA=" + loginTSA + ", passswordTSA=" + passswordTSA + '}';
    }    
    
}
