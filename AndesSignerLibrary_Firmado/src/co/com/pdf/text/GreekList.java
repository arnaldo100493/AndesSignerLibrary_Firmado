// Decompiled by DJ v3.12.12.98 Copyright 2014 Atanas Neshkov  Date: 17/11/2017 7:07:02 p. m.
// Home Page: http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   GreekList.java

package co.com.pdf.text;

import co.com.pdf.text.factories.GreekAlphabetFactory;
import java.util.ArrayList;

// Referenced classes of package co.com.pdf.text:
//            List, ListItem, Chunk, Font, 
//            FontFactory, Element

public class GreekList extends List
{

    public GreekList()
    {
        super(true);
        setGreekFont();
    }

    public GreekList(int symbolIndent)
    {
        super(true, symbolIndent);
        setGreekFont();
    }

    public GreekList(boolean greeklower, int symbolIndent)
    {
        super(true, symbolIndent);
        lowercase = greeklower;
        setGreekFont();
    }

    protected void setGreekFont()
    {
        float fontsize = symbol.getFont().getSize();
        symbol.setFont(FontFactory.getFont("Symbol", fontsize, 0));
    }

    public boolean add(Element o)
    {
        if(o instanceof ListItem)
        {
            ListItem item = (ListItem)o;
            Chunk chunk = new Chunk(preSymbol, symbol.getFont());
            chunk.setAttributes(symbol.getAttributes());
            chunk.append(GreekAlphabetFactory.getString(first + list.size(), lowercase));
            chunk.append(postSymbol);
            item.setListSymbol(chunk);
            item.setIndentationLeft(symbolIndent, autoindent);
            item.setIndentationRight(0.0F);
            list.add(item);
        } else
        if(o instanceof List)
        {
            List nested = (List)o;
            nested.setIndentationLeft(nested.getIndentationLeft() + symbolIndent);
            first--;
            return list.add(nested);
        }
        return false;
    }
}