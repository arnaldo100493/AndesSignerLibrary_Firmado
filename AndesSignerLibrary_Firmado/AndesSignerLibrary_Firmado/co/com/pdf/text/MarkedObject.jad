// Decompiled by DJ v3.12.12.98 Copyright 2014 Atanas Neshkov  Date: 17/11/2017 7:07:03 p. m.
// Home Page: http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   MarkedObject.java

package co.com.pdf.text;

import java.util.List;
import java.util.Properties;

// Referenced classes of package co.com.pdf.text:
//            DocumentException, Element, ElementListener

public class MarkedObject
    implements Element
{

    protected MarkedObject()
    {
        markupAttributes = new Properties();
        element = null;
    }

    public MarkedObject(Element element)
    {
        markupAttributes = new Properties();
        this.element = element;
    }

    public List getChunks()
    {
        return element.getChunks();
    }

    public boolean process(ElementListener listener)
    {
        try
        {
            return listener.add(element);
        }
        catch(DocumentException de)
        {
            return false;
        }
    }

    public int type()
    {
        return 50;
    }

    public boolean isContent()
    {
        return true;
    }

    public boolean isNestable()
    {
        return true;
    }

    public Properties getMarkupAttributes()
    {
        return markupAttributes;
    }

    public void setMarkupAttribute(String key, String value)
    {
        markupAttributes.setProperty(key, value);
    }

    protected Element element;
    protected Properties markupAttributes;
}