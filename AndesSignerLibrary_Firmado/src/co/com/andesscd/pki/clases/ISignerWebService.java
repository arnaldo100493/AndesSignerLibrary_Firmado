// Decompiled by DJ v3.12.12.98 Copyright 2014 Atanas Neshkov  Date: 17/11/2017 7:06:57 p. m.
// Home Page: http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   ISignerWebService.java
package co.com.andesscd.pki.clases;

public interface ISignerWebService {

    public String firmarConCertificadoCustodiado(String paramString1, String paramString2, String paramString3) throws Exception;

    public String firmarConCertificadoCustodiado(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5) throws Exception;

    public String firmarConCertificadoCustodiado(String paramString1, String paramString2, int paramInt, String paramString3) throws Exception;

    public String firmarConCertificadoCustodiado(String paramString1, String paramString2, int paramInt, String paramString3, String paramString4, String paramString5) throws Exception;

    public String firmarEnPKCS7ConPKCS12(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, long paramLong, String paramString6) throws Exception;

    public String firmarEnPKCS7ConPKCS11(String paramString1, String paramString2, int paramInt, String paramString3, String paramString4, String paramString5, String paramString6, long paramLong, String paramString7) throws Exception;

    public String firmarEnPKCS7ConPKCS11(int paramInt, String paramString) throws Exception;

    public String firmarEnPDFConPKCS12(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, long paramLong, String paramString6) throws Exception;

    public String firmarEnPDFConPKCS11(String paramString1, String paramString2, int paramInt, String paramString3, String paramString4, String paramString5, String paramString6, long paramLong, String paramString7) throws Exception;
}
