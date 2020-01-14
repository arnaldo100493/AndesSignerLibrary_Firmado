// Decompiled by DJ v3.12.12.98 Copyright 2014 Atanas Neshkov  Date: 17/11/2017 7:07:01 p. m.
// Home Page: http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   ChapterAutoNumber.java

package co.com.pdf.text;

import co.com.pdf.text.error_messages.MessageLocalization;

// Referenced classes of package co.com.pdf.text:
//            Chapter, Paragraph, Section

public class ChapterAutoNumber extends Chapter
{

    public ChapterAutoNumber(Paragraph para)
    {
        super(para, 0);
        numberSet = false;
    }

    public ChapterAutoNumber(String title)
    {
        super(title, 0);
        numberSet = false;
    }

    public Section addSection(String title)
    {
        if(isAddedCompletely())
            throw new IllegalStateException(MessageLocalization.getComposedMessage("this.largeelement.has.already.been.added.to.the.document", new Object[0]));
        else
            return addSection(title, 2);
    }

    public Section addSection(Paragraph title)
    {
        if(isAddedCompletely())
            throw new IllegalStateException(MessageLocalization.getComposedMessage("this.largeelement.has.already.been.added.to.the.document", new Object[0]));
        else
            return addSection(title, 2);
    }

    public int setAutomaticNumber(int number)
    {
        if(!numberSet)
        {
            number++;
            super.setChapterNumber(number);
            numberSet = true;
        }
        return number;
    }

    private static final long serialVersionUID = 0x8015031d4b85d8a9L;
    protected boolean numberSet;
}