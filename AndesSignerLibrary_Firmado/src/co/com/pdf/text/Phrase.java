// Decompiled by DJ v3.12.12.98 Copyright 2014 Atanas Neshkov  Date: 17/11/2017 7:07:04 p. m.
// Home Page: http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   Phrase.java

package co.com.pdf.text;

import co.com.pdf.text.error_messages.MessageLocalization;
import co.com.pdf.text.pdf.HyphenationEvent;
import java.util.*;

// Referenced classes of package co.com.pdf.text:
//            Font, Chunk, Element, DocumentException, 
//            TextElementArray, ElementListener, SpecialSymbol, TabSettings

public class Phrase extends ArrayList
    implements TextElementArray
{

    public Phrase()
    {
        this(16F);
    }

    public Phrase(Phrase phrase)
    {
        leading = (0.0F / 0.0F);
        hyphenation = null;
        tabSettings = null;
        addAll(phrase);
        leading = phrase.getLeading();
        font = phrase.getFont();
        tabSettings = phrase.getTabSettings();
        setHyphenation(phrase.getHyphenation());
    }

    public Phrase(float leading)
    {
        this.leading = (0.0F / 0.0F);
        hyphenation = null;
        tabSettings = null;
        this.leading = leading;
        font = new Font();
    }

    public Phrase(Chunk chunk)
    {
        leading = (0.0F / 0.0F);
        hyphenation = null;
        tabSettings = null;
        super.add(chunk);
        font = chunk.getFont();
        setHyphenation(chunk.getHyphenation());
    }

    public Phrase(float leading, Chunk chunk)
    {
        this.leading = (0.0F / 0.0F);
        hyphenation = null;
        tabSettings = null;
        this.leading = leading;
        super.add(chunk);
        font = chunk.getFont();
        setHyphenation(chunk.getHyphenation());
    }

    public Phrase(String string)
    {
        this((0.0F / 0.0F), string, new Font());
    }

    public Phrase(String string, Font font)
    {
        this((0.0F / 0.0F), string, font);
    }

    public Phrase(float leading, String string)
    {
        this(leading, string, new Font());
    }

    public Phrase(float leading, String string, Font font)
    {
        this.leading = (0.0F / 0.0F);
        hyphenation = null;
        tabSettings = null;
        this.leading = leading;
        this.font = font;
        if(string != null && string.length() != 0)
            super.add(new Chunk(string, font));
    }

    public boolean process(ElementListener listener)
    {
        try
        {
            Object element;
            for(Iterator i$ = iterator(); i$.hasNext(); listener.add((Element)element))
                element = i$.next();

            return true;
        }
        catch(DocumentException de)
        {
            return false;
        }
    }

    public int type()
    {
        return 11;
    }

    public List getChunks()
    {
        List tmp = new ArrayList();
        Element element;
        for(Iterator i$ = iterator(); i$.hasNext(); tmp.addAll(element.getChunks()))
            element = (Element)i$.next();

        return tmp;
    }

    public boolean isContent()
    {
        return true;
    }

    public boolean isNestable()
    {
        return true;
    }

    public void add(int index, Element element)
    {
        if(element == null)
            return;
        switch(element.type())
        {
        case 10: // '\n'
            Chunk chunk = (Chunk)element;
            if(!font.isStandardFont())
                chunk.setFont(font.difference(chunk.getFont()));
            if(hyphenation != null && chunk.getHyphenation() == null && !chunk.isEmpty())
                chunk.setHyphenation(hyphenation);
            super.add(index, chunk);
            return;

        case 11: // '\013'
        case 12: // '\f'
        case 14: // '\016'
        case 17: // '\021'
        case 23: // '\027'
        case 29: // '\035'
        case 37: // '%'
        case 50: // '2'
        case 55: // '7'
        case 666: 
            super.add(index, element);
            return;
        }
        throw new ClassCastException(MessageLocalization.getComposedMessage("insertion.of.illegal.element.1", new Object[] {
            element.getClass().getName()
        }));
    }

    public boolean add(String s)
    {
        if(s == null)
            return false;
        else
            return super.add(new Chunk(s, font));
    }

    public boolean add(Element element)
    {
        if(element == null)
            return false;
        element.type();
        JVM INSTR lookupswitch 11: default 203
    //                   10: 112
    //                   11: 121
    //                   12: 121
    //                   14: 197
    //                   17: 197
    //                   23: 197
    //                   29: 197
    //                   37: 197
    //                   50: 197
    //                   55: 197
    //                   666: 197;
           goto _L1 _L2 _L3 _L3 _L4 _L4 _L4 _L4 _L4 _L4 _L4 _L4
_L2:
        return addChunk((Chunk)element);
_L3:
        try
        {
            Phrase phrase = (Phrase)element;
            boolean success = true;
            for(Iterator i$ = phrase.iterator(); i$.hasNext();)
            {
                Object element2 = i$.next();
                Element e = (Element)element2;
                if(e instanceof Chunk)
                    success &= addChunk((Chunk)e);
                else
                    success &= add(e);
            }

            return success;
        }
        catch(ClassCastException cce)
        {
            throw new ClassCastException(MessageLocalization.getComposedMessage("insertion.of.illegal.element.1", new Object[] {
                cce.getMessage()
            }));
        }
_L4:
        return super.add(element);
_L1:
        throw new ClassCastException(String.valueOf(element.type()));
    }

    public boolean addAll(Collection collection)
    {
        Element e;
        for(Iterator i$ = collection.iterator(); i$.hasNext(); add(e))
            e = (Element)i$.next();

        return true;
    }

    protected boolean addChunk(Chunk chunk)
    {
        Font f = chunk.getFont();
        String c = chunk.getContent();
        if(font != null && !font.isStandardFont())
            f = font.difference(chunk.getFont());
        if(size() > 0 && !chunk.hasAttributes())
            try
            {
                Chunk previous = (Chunk)get(size() - 1);
                if(!previous.hasAttributes() && (f == null || f.compareTo(previous.getFont()) == 0) && !"".equals(previous.getContent().trim()) && !"".equals(c.trim()))
                {
                    previous.append(c);
                    return true;
                }
            }
            catch(ClassCastException cce) { }
        Chunk newChunk = new Chunk(c, f);
        newChunk.setAttributes(chunk.getAttributes());
        newChunk.role = chunk.getRole();
        newChunk.accessibleAttributes = chunk.getAccessibleAttributes();
        if(hyphenation != null && newChunk.getHyphenation() == null && !newChunk.isEmpty())
            newChunk.setHyphenation(hyphenation);
        return super.add(newChunk);
    }

    protected void addSpecial(Element object)
    {
        super.add(object);
    }

    public void setLeading(float leading)
    {
        this.leading = leading;
    }

    public void setFont(Font font)
    {
        this.font = font;
    }

    public float getLeading()
    {
        if(Float.isNaN(leading) && font != null)
            return font.getCalculatedLeading(1.5F);
        else
            return leading;
    }

    public float getTotalLeading()
    {
        return getLeading();
    }

    public boolean hasLeading()
    {
        return !Float.isNaN(leading);
    }

    public Font getFont()
    {
        return font;
    }

    public String getContent()
    {
        StringBuffer buf = new StringBuffer();
        Chunk c;
        for(Iterator i$ = getChunks().iterator(); i$.hasNext(); buf.append(c.toString()))
            c = (Chunk)i$.next();

        return buf.toString();
    }

    public boolean isEmpty()
    {
        switch(size())
        {
        case 0: // '\0'
            return true;

        case 1: // '\001'
            Element element = (Element)get(0);
            return element.type() == 10 && ((Chunk)element).isEmpty();
        }
        return false;
    }

    public HyphenationEvent getHyphenation()
    {
        return hyphenation;
    }

    public void setHyphenation(HyphenationEvent hyphenation)
    {
        this.hyphenation = hyphenation;
    }

    public TabSettings getTabSettings()
    {
        return tabSettings;
    }

    public void setTabSettings(TabSettings tabSettings)
    {
        this.tabSettings = tabSettings;
    }

    private Phrase(boolean dummy)
    {
        leading = (0.0F / 0.0F);
        hyphenation = null;
        tabSettings = null;
    }

    public static final Phrase getInstance(String string)
    {
        return getInstance(16, string, new Font());
    }

    public static final Phrase getInstance(int leading, String string)
    {
        return getInstance(leading, string, new Font());
    }

    public static final Phrase getInstance(int leading, String string, Font font)
    {
        Phrase p = new Phrase(true);
        p.setLeading(leading);
        p.font = font;
        int index;
        if(font.getFamily() != Font.FontFamily.SYMBOL && font.getFamily() != Font.FontFamily.ZAPFDINGBATS && font.getBaseFont() == null)
            while((index = SpecialSymbol.index(string)) > -1) 
            {
                if(index > 0)
                {
                    String firstPart = string.substring(0, index);
                    p.add(new Chunk(firstPart, font));
                    string = string.substring(index);
                }
                Font symbol = new Font(Font.FontFamily.SYMBOL, font.getSize(), font.getStyle(), font.getColor());
                StringBuffer buf = new StringBuffer();
                buf.append(SpecialSymbol.getCorrespondingSymbol(string.charAt(0)));
                for(string = string.substring(1); SpecialSymbol.index(string) == 0; string = string.substring(1))
                    buf.append(SpecialSymbol.getCorrespondingSymbol(string.charAt(0)));

                p.add(new Chunk(buf.toString(), symbol));
            }
        if(string != null && string.length() != 0)
            p.add(new Chunk(string, font));
        return p;
    }

    public boolean trim()
    {
        do
        {
            if(size() <= 0)
                break;
            Element firstChunk = (Element)get(0);
            if(!(firstChunk instanceof Chunk) || !((Chunk)firstChunk).isWhitespace())
                break;
            remove(firstChunk);
        } while(true);
        do
        {
            if(size() <= 0)
                break;
            Element lastChunk = (Element)get(size() - 1);
            if(!(lastChunk instanceof Chunk) || !((Chunk)lastChunk).isWhitespace())
                break;
            remove(lastChunk);
        } while(true);
        return size() > 0;
    }

    public volatile void add(int x0, Object x1)
    {
        add(x0, (Element)x1);
    }

    public volatile boolean add(Object x0)
    {
        return add((Element)x0);
    }

    private static final long serialVersionUID = 0x24afef48d9796e47L;
    protected float leading;
    protected Font font;
    protected HyphenationEvent hyphenation;
    protected TabSettings tabSettings;
}