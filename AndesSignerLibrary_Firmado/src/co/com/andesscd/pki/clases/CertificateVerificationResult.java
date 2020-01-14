// Decompiled by DJ v3.12.12.98 Copyright 2014 Atanas Neshkov  Date: 17/11/2017 7:06:57 p. m.
// Home Page: http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   CertificateVerificationResult.java
package co.com.andesscd.pki.clases;

import java.security.cert.PKIXCertPathBuilderResult;

public class CertificateVerificationResult {

    private boolean valid;

    private PKIXCertPathBuilderResult result;

    private Throwable exception;

    public CertificateVerificationResult() {
        this.valid = false;
        this.result = null;
    }

    public CertificateVerificationResult(PKIXCertPathBuilderResult result) {
        this.valid = true;
        this.result = result;
    }

    public CertificateVerificationResult(Throwable exception) {
        this.valid = false;
        this.exception = exception;
    }

    public boolean isValid() {
        return this.valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public PKIXCertPathBuilderResult getResult() {
        return this.result;
    }

    public void setResult(PKIXCertPathBuilderResult result) {
        this.result = result;
    }

    public Throwable getException() {
        return this.exception;
    }

    public void setException(Throwable exception) {
        this.exception = exception;
    }

    @Override
    public String toString() {
        return "CertificateVerificationResult{" + "valid=" + valid + ", result=" + result + ", exception=" + exception + '}';
    }

}
