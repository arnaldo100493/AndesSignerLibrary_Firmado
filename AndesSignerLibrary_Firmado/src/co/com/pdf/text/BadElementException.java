// Decompiled by DJ v3.12.12.98 Copyright 2014 Atanas Neshkov  Date: 17/11/2017 7:07:01 p. m.
// Home Page: http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   BadElementException.java

package co.com.pdf.text;


// Referenced classes of package co.com.pdf.text:
//            DocumentException

public class BadElementException extends DocumentException
{

    public BadElementException(Exception ex)
    {
        super(ex);
    }

    BadElementException()
    {
    }

    public BadElementException(String message)
    {
        super(message);
    }

    private static final long serialVersionUID = 0xf4e95c3f954fcd52L;
}