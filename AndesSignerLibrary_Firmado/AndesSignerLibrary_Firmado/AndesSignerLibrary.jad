// Decompiled by DJ v3.12.12.98 Copyright 2014 Atanas Neshkov  Date: 17/11/2017 7:06:52 p. m.
// Home Page: http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   AndesSignerLibrary.java

import co.com.andesscd.pki.clases.CMS;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.security.KeyStore;
import java.util.*;

public class AndesSignerLibrary
{

    public AndesSignerLibrary()
    {
    }

    public static void main(String args[])
    {
        try
        {
            System.out.println("El programa esta por iniciar...");
            CMS.iniciarComponente();
            int opt = Integer.parseInt(args[0]);
            String libPath = args[1];
            String pass = args[2];
            int slot = Integer.parseInt(args[3]);
            KeyStore keyStore = CMS.crearPKCS11Store(libPath, pass, slot);
            switch(opt)
            {
            case 1: // '\001'
                System.out.println("Listando");
                HashMap lista = CMS.listarStore(keyStore, pass);
                String key;
                for(Iterator i$ = lista.entrySet().iterator(); i$.hasNext(); System.out.println(key))
                {
                    java.util.Map.Entry entry = (java.util.Map.Entry)i$.next();
                    key = (String)entry.getKey();
                }

                break;

            default:
                System.out.println("Firmando");
                String docpath = args[4];
                String alias = args[5];
                String outpath = args[6];
                CMS cms = new CMS(docpath);
                FileOutputStream out = new FileOutputStream(outpath);
                cms.firmar(keyStore, alias, pass, out);
                out.close();
                break;
            }
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
            ex.printStackTrace(System.out);
        }
    }
}