// Decompiled by DJ v3.12.12.98 Copyright 2014 Atanas Neshkov  Date: 17/11/2017 7:06:55 p. m.
// Home Page: http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   FirmarOTPResponse.java
package co.com.andesscd.clientes.fna;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {"estado", "mensaje"})
@XmlRootElement(name = "FirmarOTPResponse")
public class FirmarOTPResponse {

    protected int estado;

    @XmlElement(required = true)
    protected String mensaje;
    
    public FirmarOTPResponse(){
        this.estado = 0;
        this.mensaje = "";
    }

    public FirmarOTPResponse(int estado, String mensaje) {
        this.estado = estado;
        this.mensaje = mensaje;
    }

    public int getEstado() {
        return this.estado;
    }

    public void setEstado(int value) {
        this.estado = value;
    }

    public String getMensaje() {
        return this.mensaje;
    }

    public void setMensaje(String value) {
        this.mensaje = value;
    }

    @Override
    public String toString() {
        return "FirmarOTPResponse{" + "estado=" + estado + ", mensaje=" + mensaje + '}';
    }

}
