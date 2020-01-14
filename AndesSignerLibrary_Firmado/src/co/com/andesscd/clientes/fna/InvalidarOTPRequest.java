// Decompiled by DJ v3.12.12.98 Copyright 2014 Atanas Neshkov  Date: 17/11/2017 7:06:55 p. m.
// Home Page: http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   InvalidarOTPRequest.java
package co.com.andesscd.clientes.fna;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {"otp"})
@XmlRootElement(name = "InvalidarOTPRequest")
public class InvalidarOTPRequest {

    @XmlElement(required = true)
    protected String otp;

    public InvalidarOTPRequest() {
        this.otp = "";
    }

    public InvalidarOTPRequest(String otp) {
        this.otp = otp;
    }

    public String getOtp() {
        return this.otp;
    }

    public void setOtp(String value) {
        this.otp = value;
    }

    @Override
    public String toString() {
        return "InvalidarOTPRequest{" + "otp=" + otp + '}';
    }
     
}
