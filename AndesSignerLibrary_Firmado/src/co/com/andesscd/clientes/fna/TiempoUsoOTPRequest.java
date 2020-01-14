// Decompiled by DJ v3.12.12.98 Copyright 2014 Atanas Neshkov  Date: 17/11/2017 7:06:56 p. m.
// Home Page: http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   TiempoUsoOTPRequest.java
package co.com.andesscd.clientes.fna;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {"tiempootp"})
@XmlRootElement(name = "TiempoUsoOTPRequest")
public class TiempoUsoOTPRequest {

    protected int tiempooTp;

    public TiempoUsoOTPRequest() {
        this.tiempooTp = 0;
    }

    public TiempoUsoOTPRequest(int tiempooTp) {
        this.tiempooTp = tiempooTp;
    }

    public int getTiempooTp() {
        return this.tiempooTp;
    }

    public void setTiempooTp(int value) {
        this.tiempooTp = value;
    }

    @Override
    public String toString() {
        return "TiempoUsoOTPRequest{" + "tiempooTp=" + tiempooTp + '}';
    }

}
