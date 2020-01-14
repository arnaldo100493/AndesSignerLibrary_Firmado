// Decompiled by DJ v3.12.12.98 Copyright 2014 Atanas Neshkov  Date: 17/11/2017 7:07:04 p. m.
// Home Page: http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   RomanList.java

package co.com.pdf.text;

import co.com.pdf.text.factories.RomanNumberFactory;
import java.util.ArrayList;

// Referenced classes of package co.com.pdf.text:
//            List, ListItem, Chunk, Element

public class RomanList extends List
{

    public RomanList()
    {
        super(true);
    }

    public RomanList(int symbolIndent)
    {
        super(true, symbolIndent);
    }

    public RomanList(boolean lowercase, int symbolIndent)
    {
        super(true, symbolIndent);
        this.lowercase = lowercase;
    }

    public boolean add(Element o)
    {
        if(o instanceof ListItem)
        {
            ListItem item = (ListItem)o;
            Chunk chunk = new Chunk(preSymbol, symbol.getFont());
            chunk.setAttributes(symbol.getAttributes());
            chunk.append(RomanNumberFactory.getString(first + list.size(), lowercase));
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