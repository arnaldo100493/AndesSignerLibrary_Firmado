// Decompiled by DJ v3.12.12.98 Copyright 2014 Atanas Neshkov  Date: 17/11/2017 7:07:03 p. m.
// Home Page: http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   List.java

package co.com.pdf.text;

import co.com.pdf.text.api.Indentable;
import co.com.pdf.text.factories.RomanAlphabetFactory;
import co.com.pdf.text.pdf.PdfName;
import co.com.pdf.text.pdf.PdfObject;
import co.com.pdf.text.pdf.interfaces.IAccessibleElement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

// Referenced classes of package co.com.pdf.text:
//            Chunk, Element, DocumentException, ListItem, 
//            AccessibleElementId, TextElementArray, ElementListener

public class List
    implements TextElementArray, Indentable, IAccessibleElement
{

    public List()
    {
        this(false, false);
    }

    public List(float symbolIndent)
    {
        list = new ArrayList();
        numbered = false;
        lettered = false;
        lowercase = false;
        autoindent = false;
        alignindent = false;
        first = 1;
        symbol = new Chunk("- ");
        preSymbol = "";
        postSymbol = ". ";
        indentationLeft = 0.0F;
        indentationRight = 0.0F;
        this.symbolIndent = 0.0F;
        role = PdfName.L;
        accessibleAttributes = null;
        id = null;
        this.symbolIndent = symbolIndent;
    }

    public List(boolean numbered)
    {
        this(numbered, false);
    }

    public List(boolean numbered, boolean lettered)
    {
        list = new ArrayList();
        this.numbered = false;
        this.lettered = false;
        lowercase = false;
        autoindent = false;
        alignindent = false;
        first = 1;
        symbol = new Chunk("- ");
        preSymbol = "";
        postSymbol = ". ";
        indentationLeft = 0.0F;
        indentationRight = 0.0F;
        symbolIndent = 0.0F;
        role = PdfName.L;
        accessibleAttributes = null;
        id = null;
        this.numbered = numbered;
        this.lettered = lettered;
        autoindent = true;
        alignindent = true;
    }

    public List(boolean numbered, float symbolIndent)
    {
        this(numbered, false, symbolIndent);
    }

    public List(boolean numbered, boolean lettered, float symbolIndent)
    {
        list = new ArrayList();
        this.numbered = false;
        this.lettered = false;
        lowercase = false;
        autoindent = false;
        alignindent = false;
        first = 1;
        symbol = new Chunk("- ");
        preSymbol = "";
        postSymbol = ". ";
        indentationLeft = 0.0F;
        indentationRight = 0.0F;
        this.symbolIndent = 0.0F;
        role = PdfName.L;
        accessibleAttributes = null;
        id = null;
        this.numbered = numbered;
        this.lettered = lettered;
        this.symbolIndent = symbolIndent;
    }

    public boolean process(ElementListener listener)
    {
        try
        {
            Element element;
            for(Iterator i$ = list.iterator(); i$.hasNext(); listener.add(element))
                element = (Element)i$.next();

            return true;
        }
        catch(DocumentException de)
        {
            return false;
        }
    }

    public int type()
    {
        return 14;
    }

    public java.util.List getChunks()
    {
        java.util.List tmp = new ArrayList();
        Element element;
        for(Iterator i$ = list.iterator(); i$.hasNext(); tmp.addAll(element.getChunks()))
            element = (Element)i$.next();

        return tmp;
    }

    public boolean add(String s)
    {
        if(s != null)
            return add(((Element) (new ListItem(s))));
        else
            return false;
    }

    public boolean add(Element o)
    {
        if(o instanceof ListItem)
        {
            ListItem item = (ListItem)o;
            if(numbered || lettered)
            {
                Chunk chunk = new Chunk(preSymbol, symbol.getFont());
                chunk.setAttributes(symbol.getAttributes());
                int index = first + list.size();
                if(lettered)
                    chunk.append(RomanAlphabetFactory.getString(index, lowercase));
                else
                    chunk.append(String.valueOf(index));
                chunk.append(postSymbol);
                item.setListSymbol(chunk);
            } else
            {
                item.setListSymbol(symbol);
            }
            item.setIndentationLeft(symbolIndent, autoindent);
            item.setIndentationRight(0.0F);
            return list.add(item);
        }
        if(o instanceof List)
        {
            List nested = (List)o;
            nested.setIndentationLeft(nested.getIndentationLeft() + symbolIndent);
            first--;
            return list.add(nested);
        } else
        {
            return false;
        }
    }

    public void normalizeIndentation()
    {
        float max = 0.0F;
        Iterator i$ = list.iterator();
        do
        {
            if(!i$.hasNext())
                break;
            Element o = (Element)i$.next();
            if(o instanceof ListItem)
                max = Math.max(max, ((ListItem)o).getIndentationLeft());
        } while(true);
        i$ = list.iterator();
        do
        {
            if(!i$.hasNext())
                break;
            Element o = (Element)i$.next();
            if(o instanceof ListItem)
                ((ListItem)o).setIndentationLeft(max);
        } while(true);
    }

    public void setNumbered(boolean numbered)
    {
        this.numbered = numbered;
    }

    public void setLettered(boolean lettered)
    {
        this.lettered = lettered;
    }

    public void setLowercase(boolean uppercase)
    {
        lowercase = uppercase;
    }

    public void setAutoindent(boolean autoindent)
    {
        this.autoindent = autoindent;
    }

    public void setAlignindent(boolean alignindent)
    {
        this.alignindent = alignindent;
    }

    public void setFirst(int first)
    {
        this.first = first;
    }

    public void setListSymbol(Chunk symbol)
    {
        this.symbol = symbol;
    }

    public void setListSymbol(String symbol)
    {
        this.symbol = new Chunk(symbol);
    }

    public void setIndentationLeft(float indentation)
    {
        indentationLeft = indentation;
    }

    public void setIndentationRight(float indentation)
    {
        indentationRight = indentation;
    }

    public void setSymbolIndent(float symbolIndent)
    {
        this.symbolIndent = symbolIndent;
    }

    public ArrayList getItems()
    {
        return list;
    }

    public int size()
    {
        return list.size();
    }

    public boolean isEmpty()
    {
        return list.isEmpty();
    }

    public float getTotalLeading()
    {
        if(list.size() < 1)
        {
            return -1F;
        } else
        {
            ListItem item = (ListItem)list.get(0);
            return item.getTotalLeading();
        }
    }

    public boolean isNumbered()
    {
        return numbered;
    }

    public boolean isLettered()
    {
        return lettered;
    }

    public boolean isLowercase()
    {
        return lowercase;
    }

    public boolean isAutoindent()
    {
        return autoindent;
    }

    public boolean isAlignindent()
    {
        return alignindent;
    }

    public int getFirst()
    {
        return first;
    }

    public Chunk getSymbol()
    {
        return symbol;
    }

    public float getIndentationLeft()
    {
        return indentationLeft;
    }

    public float getIndentationRight()
    {
        return indentationRight;
    }

    public float getSymbolIndent()
    {
        return symbolIndent;
    }

    public boolean isContent()
    {
        return true;
    }

    public boolean isNestable()
    {
        return true;
    }

    public String getPostSymbol()
    {
        return postSymbol;
    }

    public void setPostSymbol(String postSymbol)
    {
        this.postSymbol = postSymbol;
    }

    public String getPreSymbol()
    {
        return preSymbol;
    }

    public void setPreSymbol(String preSymbol)
    {
        this.preSymbol = preSymbol;
    }

    public ListItem getFirstItem()
    {
        Element lastElement = list.size() <= 0 ? null : (Element)list.get(0);
        if(lastElement != null)
        {
            if(lastElement instanceof ListItem)
                return (ListItem)lastElement;
            if(lastElement instanceof List)
                return ((List)lastElement).getFirstItem();
        }
        return null;
    }

    public ListItem getLastItem()
    {
        Element lastElement = list.size() <= 0 ? null : (Element)list.get(list.size() - 1);
        if(lastElement != null)
        {
            if(lastElement instanceof ListItem)
                return (ListItem)lastElement;
            if(lastElement instanceof List)
                return ((List)lastElement).getLastItem();
        }
        return null;
    }

    public PdfObject getAccessibleAttribute(PdfName key)
    {
        if(accessibleAttributes != null)
            return (PdfObject)accessibleAttributes.get(key);
        else
            return null;
    }

    public void setAccessibleAttribute(PdfName key, PdfObject value)
    {
        if(accessibleAttributes == null)
            accessibleAttributes = new HashMap();
        accessibleAttributes.put(key, value);
    }

    public HashMap getAccessibleAttributes()
    {
        return accessibleAttributes;
    }

    public PdfName getRole()
    {
        return role;
    }

    public void setRole(PdfName role)
    {
        this.role = role;
    }

    public AccessibleElementId getId()
    {
        if(id == null)
            id = new AccessibleElementId();
        return id;
    }

    public void setId(AccessibleElementId id)
    {
        this.id = id;
    }

    public static final boolean ORDERED = true;
    public static final boolean UNORDERED = false;
    public static final boolean NUMERICAL = false;
    public static final boolean ALPHABETICAL = true;
    public static final boolean UPPERCASE = false;
    public static final boolean LOWERCASE = true;
    protected ArrayList list;
    protected boolean numbered;
    protected boolean lettered;
    protected boolean lowercase;
    protected boolean autoindent;
    protected boolean alignindent;
    protected int first;
    protected Chunk symbol;
    protected String preSymbol;
    protected String postSymbol;
    protected float indentationLeft;
    protected float indentationRight;
    protected float symbolIndent;
    protected PdfName role;
    protected HashMap accessibleAttributes;
    private AccessibleElementId id;
}