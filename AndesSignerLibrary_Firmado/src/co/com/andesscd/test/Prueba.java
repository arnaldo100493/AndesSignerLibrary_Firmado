// Decompiled by DJ v3.12.12.98 Copyright 2014 Atanas Neshkov  Date: 17/11/2017 7:06:58 p. m.
// Home Page: http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   Prueba.java
package co.com.andesscd.test;

import co.com.andesscd.pki.clases.CMS;
import co.com.andesscd.pki.clases.RESULTADO_VERIFICACION;
import co.org.bouncy.tsp.TSPException;
import java.io.FileNotFoundException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Prueba {

    public Prueba() {

    }

    public static void main(String[] args) throws FileNotFoundException, NoSuchAlgorithmException, TSPException, Exception {
        int contador = 0;
        int i = 1;
        do {
            if (i % 2 != 0 && i % 3 == 0) {
                contador++;
            }
            i++;
        } while (contador < 5);
        CMS.iniciarComponente();
        try {
            RESULTADO_VERIFICACION resultado = CMS.validarCertificado(null, "Usuario de Pruebas", null, null, null, null);
            System.out.println(resultado);
            System.out.println("Fin de la prueba");
        } catch (Exception ex) {
            Logger.getLogger(Prueba.class.getName()).log(Level.SEVERE, (String) null, ex);
        }
    }
}
