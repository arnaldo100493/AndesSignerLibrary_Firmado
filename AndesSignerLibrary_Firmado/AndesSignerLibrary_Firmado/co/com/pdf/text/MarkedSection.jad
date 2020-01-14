// Decompiled by DJ v3.12.12.98 Copyright 2014 Atanas Neshkov  Date: 17/11/2017 7:07:03 p. m.
// Home Page: http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   MarkedSection.java

package co.com.pdf.text;

import co.com.pdf.text.api.Indentable;
import java.util.Collection;
import java.util.Iterator;

// Referenced classes of package co.com.pdf.text:
//            MarkedObject, Section, Element, DocumentException, 
//            Paragraph, ElementListener

public class MarkedSection extends MarkedObject
    implements Indentable
{

    public MarkedSection(Section section)
    {
        title = null;
        if(section.title != null)
        {
            title = new MarkedObject(section.title);
            section.setTitle(null);
        }
        element = section;
    }

    public void add(int index, Element o)
    {
        ((Section)element).add(index, o);
    }

    public boolean add(Element o)
    {
        return ((Section)element).add(o);
    }

    public boolean process(ElementListener listener)
    {
        try
        {
            Element element;
            for(Iterator i = ((Section)this.element).iterator(); i.hasNext(); listener.add(element))
                element = (Element)i.next();

            return true;
        }
        catch(DocumentException de)
        {
            return false;
        }
    }

    public boolean addAll(Collection collection)
    {
        return ((Section)element).addAll(collection);
    }

    public MarkedSection addSection(float indentation, int numberDepth)
    {
        MarkedSection section = ((Section)element).addMarkedSection();
        section.setIndentation(indentation);
        section.setNumberDepth(numberDepth);
        return section;
    }

    public MarkedSection addSection(float indentation)
    {
        MarkedSection section = ((Section)element).addMarkedSection();
        section.setIndentation(indentation);
        return section;
    }

    public MarkedSection addSection(int numberDepth)
    {
        MarkedSection section = ((Section)element).addMarkedSection();
        section.setNumberDepth(numberDepth);
        return section;
    }

    public MarkedSection addSection()
    {
        return ((Section)element).addMarkedSection();
    }

    public void setTitle(MarkedObject title)
    {
        if(title.element instanceof Paragraph)
            this.title = title;
    }

    public MarkedObject getTitle()
    {
        Paragraph result = Section.constructTitle((Paragraph)title.element, ((Section)element).numbers, ((Section)element).numberDepth, ((Section)element).numberStyle);
        MarkedObject mo = new MarkedObject(result);
        mo.markupAttributes = title.markupAttributes;
        return mo;
    }

    public void setNumberDepth(int numberDepth)
    {
        ((Section)element).setNumberDepth(numberDepth);
    }

    public void setIndentationLeft(float indentation)
    {
        ((Section)element).setIndentationLeft(indentation);
    }

    public void setIndentationRight(float indentation)
    {
        ((Section)element).setIndentationRight(indentation);
    }

    public void setIndentation(float indentation)
    {
        ((Section)element).setIndentation(indentation);
    }

    public void setBookmarkOpen(boolean bookmarkOpen)
    {
        ((Section)element).setBookmarkOpen(bookmarkOpen);
    }

    public void setTriggerNewPage(boolean triggerNewPage)
    {
        ((Section)element).setTriggerNewPage(triggerNewPage);
    }

    public void setBookmarkTitle(String bookmarkTitle)
    {
        ((Section)element).setBookmarkTitle(bookmarkTitle);
    }

    public void newPage()
    {
        ((Section)element).newPage();
    }

    public float getIndentationLeft()
    {
        return ((Section)element).getIndentationLeft();
    }

    public float getIndentationRight()
    {
        return ((Section)element).getIndentationRight();
    }

    protected MarkedObject title;
}