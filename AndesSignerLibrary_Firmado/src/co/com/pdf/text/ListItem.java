// Decompiled by DJ v3.12.12.98 Copyright 2014 Atanas Neshkov  Date: 17/11/2017 7:07:03 p. m.
// Home Page: http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   ListItem.java

package co.com.pdf.text;

import co.com.pdf.text.pdf.PdfName;
import java.util.List;

// Referenced classes of package co.com.pdf.text:
//            Paragraph, Chunk, ListBody, ListLabel, 
//            Font, Phrase

public class ListItem extends Paragraph
{

    public ListItem()
    {
        listBody = null;
        listLabel = null;
        setRole(PdfName.LI);
    }

    public ListItem(float leading)
    {
        super(leading);
        listBody = null;
        listLabel = null;
        setRole(PdfName.LI);
    }

    public ListItem(Chunk chunk)
    {
        super(chunk);
        listBody = null;
        listLabel = null;
        setRole(PdfName.LI);
    }

    public ListItem(String string)
    {
        super(string);
        listBody = null;
        listLabel = null;
        setRole(PdfName.LI);
    }

    public ListItem(String string, Font font)
    {
        super(string, font);
        listBody = null;
        listLabel = null;
        setRole(PdfName.LI);
    }

    public ListItem(float leading, Chunk chunk)
    {
        super(leading, chunk);
        listBody = null;
        listLabel = null;
        setRole(PdfName.LI);
    }

    public ListItem(float leading, String string)
    {
        super(leading, string);
        listBody = null;
        listLabel = null;
        setRole(PdfName.LI);
    }

    public ListItem(float leading, String string, Font font)
    {
        super(leading, string, font);
        listBody = null;
        listLabel = null;
        setRole(PdfName.LI);
    }

    public ListItem(Phrase phrase)
    {
        super(phrase);
        listBody = null;
        listLabel = null;
        setRole(PdfName.LI);
    }

    public int type()
    {
        return 15;
    }

    public void setListSymbol(Chunk symbol)
    {
        if(this.symbol == null)
        {
            this.symbol = symbol;
            if(this.symbol.getFont().isStandardFont())
                this.symbol.setFont(font);
        }
    }

    public void setIndentationLeft(float indentation, boolean autoindent)
    {
        if(autoindent)
            setIndentationLeft(getListSymbol().getWidthPoint());
        else
            setIndentationLeft(indentation);
    }

    public void adjustListSymbolFont()
    {
        List cks = getChunks();
        if(!cks.isEmpty() && symbol != null)
            symbol.setFont(((Chunk)cks.get(0)).getFont());
    }

    public Chunk getListSymbol()
    {
        return symbol;
    }

    public ListBody getListBody()
    {
        if(listBody == null)
            listBody = new ListBody(this);
        return listBody;
    }

    public ListLabel getListLabel()
    {
        if(listLabel == null)
            listLabel = new ListLabel(this);
        return listLabel;
    }

    private static final long serialVersionUID = 0x1b593aa3ce779f6eL;
    protected Chunk symbol;
    private ListBody listBody;
    private ListLabel listLabel;
}