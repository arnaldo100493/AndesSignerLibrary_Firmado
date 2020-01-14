// Decompiled by DJ v3.12.12.98 Copyright 2014 Atanas Neshkov  Date: 17/11/2017 7:06:56 p. m.
// Home Page: http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   RespuestaFirmar.java
package co.com.andesscd.clientes.fna;

import co.com.andesscd.Base64Coder;
import co.com.andesscd.pki.clases.CMSException;

import co.com.andesscd.Base64Coder;
import co.com.andesscd.pki.clases.CMSException;

public class RespuestaFirmar {

    protected int estado;

    protected String mensaje;

    public RespuestaFirmar() {
        this.estado = 0;
        this.mensaje = "";
    }

    public RespuestaFirmar(int estado, String mensaje) throws CMSException {
        this.estado = estado;
        this.mensaje = mensaje;
        if (this.estado != 0) {
            throw new CMSException(this.mensaje);
        }
    }

    public int getEstado() {
        return this.estado;
    }

    public String getMensaje() {
        return this.mensaje;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public byte[] getFirma() {
        return Base64Coder.decode(this.mensaje);
    }

    @Override
    public String toString() {
        return "RespuestaFirmar{" + "estado=" + estado + ", mensaje=" + mensaje + '}';
    }

}
