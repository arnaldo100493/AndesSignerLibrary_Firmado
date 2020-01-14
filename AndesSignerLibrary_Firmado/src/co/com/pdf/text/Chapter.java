// Decompiled by DJ v3.12.12.98 Copyright 2014 Atanas Neshkov  Date: 17/11/2017 7:07:01 p. m.
// Home Page: http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   Chapter.java

package co.com.pdf.text;

import java.util.ArrayList;

// Referenced classes of package co.com.pdf.text:
//            Section, Paragraph

public class Chapter extends Section
{

    public Chapter(int number)
    {
        super(null, 1);
        numbers = new ArrayList();
        numbers.add(Integer.valueOf(number));
        triggerNewPage = true;
    }

    public Chapter(Paragraph title, int number)
    {
        super(title, 1);
        numbers = new ArrayList();
        numbers.add(Integer.valueOf(number));
        triggerNewPage = true;
    }

    public Chapter(String title, int number)
    {
        this(new Paragraph(title), number);
    }

    public int type()
    {
        return 16;
    }

    public boolean isNestable()
    {
        return false;
    }

    private static final long serialVersionUID = 0x18dae9a4c867f2b1L;
}