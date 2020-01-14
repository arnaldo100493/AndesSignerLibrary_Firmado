// Decompiled by DJ v3.12.12.98 Copyright 2014 Atanas Neshkov  Date: 17/11/2017 7:06:57 p. m.
// Home Page: http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   CMSException.java
package co.com.andesscd.pki.clases;

public class CMSException extends Exception {

    public CMSException() {
        super();
    }

    public CMSException(String message) {
        super(message);
    }

    public CMSException(String message, Throwable cause) {
        super(message, cause);
    }

    public CMSException(Throwable cause) {
        super(cause);
    }

    public CMSException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
