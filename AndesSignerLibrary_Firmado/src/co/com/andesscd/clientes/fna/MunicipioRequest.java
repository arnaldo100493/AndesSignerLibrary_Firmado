// Decompiled by DJ v3.12.12.98 Copyright 2014 Atanas Neshkov  Date: 17/11/2017 7:06:56 p. m.
// Home Page: http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   MunicipioRequest.java
package co.com.andesscd.clientes.fna;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {"idDepartamento"})
@XmlRootElement(name = "MunicipioRequest")
public class MunicipioRequest {

    @XmlElement(name = "id_departamento")
    protected int idDepartamento;

    public MunicipioRequest() {
        this.idDepartamento = 0;
    }

    public MunicipioRequest(int idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public int getIdDepartamento() {
        return this.idDepartamento;
    }

    public void setIdDepartamento(int value) {
        this.idDepartamento = value;
    }

    @Override
    public String toString() {
        return "MunicipioRequest{" + "idDepartamento=" + idDepartamento + '}';
    }

}
