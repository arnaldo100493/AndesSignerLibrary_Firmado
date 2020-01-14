// Decompiled by DJ v3.12.12.98 Copyright 2014 Atanas Neshkov  Date: 17/11/2017 7:07:03 p. m.
// Home Page: http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   ListLabel.java

package co.com.pdf.text;

import co.com.pdf.text.pdf.PdfName;

// Referenced classes of package co.com.pdf.text:
//            ListBody, ListItem

public class ListLabel extends ListBody
{

    protected ListLabel(ListItem parentItem)
    {
        super(parentItem);
        role = PdfName.LBL;
        indentation = 0.0F;
        tagLabelContent = true;
    }

    public PdfName getRole()
    {
        return role;
    }

    public void setRole(PdfName role)
    {
        this.role = role;
    }

    public float getIndentation()
    {
        return indentation;
    }

    public void setIndentation(float indentation)
    {
        this.indentation = indentation;
    }

    public boolean getTagLabelContent()
    {
        return tagLabelContent;
    }

    public void setTagLabelContent(boolean tagLabelContent)
    {
        this.tagLabelContent = tagLabelContent;
    }

    protected PdfName role;
    protected float indentation;
    protected boolean tagLabelContent;
}