// Decompiled by DJ v3.12.12.98 Copyright 2014 Atanas Neshkov  Date: 17/11/2017 7:07:02 p. m.
// Home Page: http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   Header.java

package co.com.pdf.text;


// Referenced classes of package co.com.pdf.text:
//            Meta

public class Header extends Meta
{

    public Header(String name, String content)
    {
        super(0, content);
        this.name = new StringBuffer(name);
    }

    public String getName()
    {
        return name.toString();
    }

    private StringBuffer name;
}