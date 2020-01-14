// Decompiled by DJ v3.12.12.98 Copyright 2014 Atanas Neshkov  Date: 17/11/2017 7:06:58 p. m.
// Home Page: http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   Test2.java
package co.com.andesscd.test;

import co.com.andesscd.clientes.fna.FNAWebClient;
import co.com.andesscd.pki.clases.CMS;
import co.com.andesscd.pki.clases.ISignerWebService;
import java.io.FileOutputStream;
import java.security.KeyStore;

public class Test2 {

    public Test2() {

    }

    public static void main(String[] args) {
        try {
            CMS.iniciarComponente();
            KeyStore ks = null;
            CMS cms = new CMS("I:\\tmp\\1484410092804_x_firmar.pdf");
            String wsdlURL = "https://ra.andesscd.com.co/test/WebService/wsdl.php?WSDL";
            String wssUserName = "JEPR";
            String wssUserToken = "ZnthRDbYXN";
            FNAWebClient fnaWebClient = new FNAWebClient(wsdlURL, wssUserName, wssUserToken);
            String identificacion = "1032357357";
            String pin = "11231275";
            String userTsa = "sealmail";
            String passTsa = "sm_A9oF7";
            cms.firmarPdf((ISignerWebService) fnaWebClient, identificacion, pin, new FileOutputStream("I:\\tmp\\prueba_firmada.pdf"));
            System.out.println("***************HA SIDO FIRMADO***************");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }
}
