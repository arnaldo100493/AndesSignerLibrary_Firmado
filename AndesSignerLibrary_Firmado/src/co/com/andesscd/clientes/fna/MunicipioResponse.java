// Decompiled by DJ v3.12.12.98 Copyright 2014 Atanas Neshkov  Date: 17/11/2017 7:06:56 p. m.
// Home Page: http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   MunicipioResponse.java
package co.com.andesscd.clientes.fna;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {"estado", "mensaje"})
@XmlRootElement(name = "MunicipioResponse")
public class MunicipioResponse {

    protected int estado;

    @XmlElement(required = true)
    protected String mensaje;

    public MunicipioResponse() {
        this.estado = 0;
        this.mensaje = "";
    }

    public MunicipioResponse(int estado, String mensaje) {
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
        return "MunicipioResponse{" + "estado=" + estado + ", mensaje=" + mensaje + '}';
    }

}
