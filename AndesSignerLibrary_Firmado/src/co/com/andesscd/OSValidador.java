// Decompiled by DJ v3.12.12.98 Copyright 2014 Atanas Neshkov  Date: 17/11/2017 7:06:53 p. m.
// Home Page: http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   OSValidador.java
package co.com.andesscd;

public class OSValidador {

    public OSValidador() {

    }

    public enum OSTYPE {
        WINDOWS, MAC, UNIX, SOLARIS, NO_DEFINIDO;
    }

    private static final String OS = System.getProperty("os.name").toLowerCase();

    public static OSTYPE getOS() {
        OSTYPE os;
        if (isWindows()) {
            os = OSTYPE.WINDOWS;
        } else if (isMac()) {
            os = OSTYPE.MAC;
        } else if (isUnix()) {
            os = OSTYPE.UNIX;
        } else if (isSolaris()) {
            os = OSTYPE.SOLARIS;
        } else {
            os = OSTYPE.NO_DEFINIDO;
        }
        return os;
    }

    public static boolean isWindows() {
        return (OS.indexOf("win") >= 0);
    }

    public static boolean isMac() {
        return (OS.indexOf("mac") >= 0);
    }

    public static boolean isUnix() {
        return (OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS.indexOf("aix") > 0);
    }

    public static boolean isSolaris() {
        return (OS.indexOf("sunos") >= 0);
    }
}
