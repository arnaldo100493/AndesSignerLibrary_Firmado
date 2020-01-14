// Decompiled by DJ v3.12.12.98 Copyright 2014 Atanas Neshkov  Date: 17/11/2017 7:06:55 p. m.
// Home Page: http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   DepartamentoRequest.java
package co.com.andesscd.clientes.fna;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {"cadena"})
@XmlRootElement(name = "DepartamentoRequest")
public class DepartamentoRequest {

    @XmlElement(required = true)
    protected String cadena;

    public DepartamentoRequest() {
        this.cadena = "";
    }

    public DepartamentoRequest(String cadena) {
        this.cadena = cadena;
    }
    
    public String getCadena() {
        return this.cadena;
    }

    public void setCadena(String value) {
        this.cadena = value;
    }

    @Override
    public String toString() {
        return "DepartamentoRequest{" + "cadena=" + cadena + '}';
    }
    
}
