// Decompiled by DJ v3.12.12.98 Copyright 2014 Atanas Neshkov  Date: 17/11/2017 7:07:01 p. m.
// Home Page: http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   Document.java

package co.com.pdf.text;

import co.com.pdf.text.error_messages.MessageLocalization;
import co.com.pdf.text.pdf.PdfName;
import co.com.pdf.text.pdf.PdfObject;
import co.com.pdf.text.pdf.interfaces.IAccessibleElement;
import java.text.SimpleDateFormat;
import java.util.*;

// Referenced classes of package co.com.pdf.text:
//            AccessibleElementId, DocumentException, ChapterAutoNumber, DocListener, 
//            LargeElement, Header, ExceptionConverter, Meta, 
//            PageSize, Element, Version, Rectangle

public class Document
    implements DocListener, IAccessibleElement
{

    public Document()
    {
        this(PageSize.A4);
    }

    public Document(Rectangle pageSize)
    {
        this(pageSize, 36F, 36F, 36F, 36F);
    }

    public Document(Rectangle pageSize, float marginLeft, float marginRight, float marginTop, float marginBottom)
    {
        listeners = new ArrayList();
        this.marginLeft = 0.0F;
        this.marginRight = 0.0F;
        this.marginTop = 0.0F;
        this.marginBottom = 0.0F;
        marginMirroring = false;
        marginMirroringTopBottom = false;
        javaScript_onLoad = null;
        javaScript_onUnLoad = null;
        htmlStyleClass = null;
        pageN = 0;
        chapternumber = 0;
        role = PdfName.DOCUMENT;
        accessibleAttributes = null;
        id = new AccessibleElementId();
        this.pageSize = pageSize;
        this.marginLeft = marginLeft;
        this.marginRight = marginRight;
        this.marginTop = marginTop;
        this.marginBottom = marginBottom;
    }

    public void addDocListener(DocListener listener)
    {
        listeners.add(listener);
        if(listener instanceof IAccessibleElement)
        {
            IAccessibleElement ae = (IAccessibleElement)listener;
            ae.setRole(role);
            ae.setId(id);
            if(accessibleAttributes != null)
            {
                PdfName key;
                for(Iterator i$ = accessibleAttributes.keySet().iterator(); i$.hasNext(); ae.setAccessibleAttribute(key, (PdfObject)accessibleAttributes.get(key)))
                    key = (PdfName)i$.next();

            }
        }
    }

    public void removeDocListener(DocListener listener)
    {
        listeners.remove(listener);
    }

    public boolean add(Element element)
        throws DocumentException
    {
        if(close)
            throw new DocumentException(MessageLocalization.getComposedMessage("the.document.has.been.closed.you.can.t.add.any.elements", new Object[0]));
        if(!open && element.isContent())
            throw new DocumentException(MessageLocalization.getComposedMessage("the.document.is.not.open.yet.you.can.only.add.meta.information", new Object[0]));
        boolean success = false;
        if(element instanceof ChapterAutoNumber)
            chapternumber = ((ChapterAutoNumber)element).setAutomaticNumber(chapternumber);
        for(Iterator i$ = listeners.iterator(); i$.hasNext();)
        {
            DocListener listener = (DocListener)i$.next();
            success |= listener.add(element);
        }

        if(element instanceof LargeElement)
        {
            LargeElement e = (LargeElement)element;
            if(!e.isComplete())
                e.flushContent();
        }
        return success;
    }

    public void open()
    {
        if(!close)
            open = true;
        DocListener listener;
        for(Iterator i$ = listeners.iterator(); i$.hasNext(); listener.open())
        {
            listener = (DocListener)i$.next();
            listener.setPageSize(pageSize);
            listener.setMargins(marginLeft, marginRight, marginTop, marginBottom);
        }

    }

    public boolean setPageSize(Rectangle pageSize)
    {
        this.pageSize = pageSize;
        DocListener listener;
        for(Iterator i$ = listeners.iterator(); i$.hasNext(); listener.setPageSize(pageSize))
            listener = (DocListener)i$.next();

        return true;
    }

    public boolean setMargins(float marginLeft, float marginRight, float marginTop, float marginBottom)
    {
        this.marginLeft = marginLeft;
        this.marginRight = marginRight;
        this.marginTop = marginTop;
        this.marginBottom = marginBottom;
        DocListener listener;
        for(Iterator i$ = listeners.iterator(); i$.hasNext(); listener.setMargins(marginLeft, marginRight, marginTop, marginBottom))
            listener = (DocListener)i$.next();

        return true;
    }

    public boolean newPage()
    {
        if(!open || close)
            return false;
        DocListener listener;
        for(Iterator i$ = listeners.iterator(); i$.hasNext(); listener.newPage())
            listener = (DocListener)i$.next();

        return true;
    }

    public void resetPageCount()
    {
        pageN = 0;
        DocListener listener;
        for(Iterator i$ = listeners.iterator(); i$.hasNext(); listener.resetPageCount())
            listener = (DocListener)i$.next();

    }

    public void setPageCount(int pageN)
    {
        this.pageN = pageN;
        DocListener listener;
        for(Iterator i$ = listeners.iterator(); i$.hasNext(); listener.setPageCount(pageN))
            listener = (DocListener)i$.next();

    }

    public int getPageNumber()
    {
        return pageN;
    }

    public void close()
    {
        if(!close)
        {
            open = false;
            close = true;
        }
        DocListener listener;
        for(Iterator i$ = listeners.iterator(); i$.hasNext(); listener.close())
            listener = (DocListener)i$.next();

    }

    public boolean addHeader(String name, String content)
    {
        try
        {
            return add(new Header(name, content));
        }
        catch(DocumentException de)
        {
            throw new ExceptionConverter(de);
        }
    }

    public boolean addTitle(String title)
    {
        try
        {
            return add(new Meta(1, title));
        }
        catch(DocumentException de)
        {
            throw new ExceptionConverter(de);
        }
    }

    public boolean addSubject(String subject)
    {
        try
        {
            return add(new Meta(2, subject));
        }
        catch(DocumentException de)
        {
            throw new ExceptionConverter(de);
        }
    }

    public boolean addKeywords(String keywords)
    {
        try
        {
            return add(new Meta(3, keywords));
        }
        catch(DocumentException de)
        {
            throw new ExceptionConverter(de);
        }
    }

    public boolean addAuthor(String author)
    {
        try
        {
            return add(new Meta(4, author));
        }
        catch(DocumentException de)
        {
            throw new ExceptionConverter(de);
        }
    }

    public boolean addCreator(String creator)
    {
        try
        {
            return add(new Meta(7, creator));
        }
        catch(DocumentException de)
        {
            throw new ExceptionConverter(de);
        }
    }

    public boolean addProducer()
    {
        try
        {
            return add(new Meta(5, Version.getInstance().getVersion()));
        }
        catch(DocumentException de)
        {
            throw new ExceptionConverter(de);
        }
    }

    public boolean addLanguage(String language)
    {
        try
        {
            return add(new Meta(8, language));
        }
        catch(DocumentException de)
        {
            throw new ExceptionConverter(de);
        }
    }

    public boolean addCreationDate()
    {
        try
        {
            SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
            return add(new Meta(6, sdf.format(new Date())));
        }
        catch(DocumentException de)
        {
            throw new ExceptionConverter(de);
        }
    }

    public float leftMargin()
    {
        return marginLeft;
    }

    public float rightMargin()
    {
        return marginRight;
    }

    public float topMargin()
    {
        return marginTop;
    }

    public float bottomMargin()
    {
        return marginBottom;
    }

    public float left()
    {
        return pageSize.getLeft(marginLeft);
    }

    public float right()
    {
        return pageSize.getRight(marginRight);
    }

    public float top()
    {
        return pageSize.getTop(marginTop);
    }

    public float bottom()
    {
        return pageSize.getBottom(marginBottom);
    }

    public float left(float margin)
    {
        return pageSize.getLeft(marginLeft + margin);
    }

    public float right(float margin)
    {
        return pageSize.getRight(marginRight + margin);
    }

    public float top(float margin)
    {
        return pageSize.getTop(marginTop + margin);
    }

    public float bottom(float margin)
    {
        return pageSize.getBottom(marginBottom + margin);
    }

    public Rectangle getPageSize()
    {
        return pageSize;
    }

    public boolean isOpen()
    {
        return open;
    }

    public void setJavaScript_onLoad(String code)
    {
        javaScript_onLoad = code;
    }

    public String getJavaScript_onLoad()
    {
        return javaScript_onLoad;
    }

    public void setJavaScript_onUnLoad(String code)
    {
        javaScript_onUnLoad = code;
    }

    public String getJavaScript_onUnLoad()
    {
        return javaScript_onUnLoad;
    }

    public void setHtmlStyleClass(String htmlStyleClass)
    {
        this.htmlStyleClass = htmlStyleClass;
    }

    public String getHtmlStyleClass()
    {
        return htmlStyleClass;
    }

    public boolean setMarginMirroring(boolean marginMirroring)
    {
        this.marginMirroring = marginMirroring;
        DocListener listener;
        for(Iterator i$ = listeners.iterator(); i$.hasNext(); listener.setMarginMirroring(marginMirroring))
        {
            Object element = i$.next();
            listener = (DocListener)element;
        }

        return true;
    }

    public boolean setMarginMirroringTopBottom(boolean marginMirroringTopBottom)
    {
        this.marginMirroringTopBottom = marginMirroringTopBottom;
        DocListener listener;
        for(Iterator i$ = listeners.iterator(); i$.hasNext(); listener.setMarginMirroringTopBottom(marginMirroringTopBottom))
        {
            Object element = i$.next();
            listener = (DocListener)element;
        }

        return true;
    }

    public boolean isMarginMirroring()
    {
        return marginMirroring;
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
        return id;
    }

    public void setId(AccessibleElementId id)
    {
        this.id = id;
    }

    public static boolean compress = true;
    public static boolean plainRandomAccess = false;
    public static float wmfFontCorrection = 0.86F;
    protected ArrayList listeners;
    protected boolean open;
    protected boolean close;
    protected Rectangle pageSize;
    protected float marginLeft;
    protected float marginRight;
    protected float marginTop;
    protected float marginBottom;
    protected boolean marginMirroring;
    protected boolean marginMirroringTopBottom;
    protected String javaScript_onLoad;
    protected String javaScript_onUnLoad;
    protected String htmlStyleClass;
    protected int pageN;
    protected int chapternumber;
    protected PdfName role;
    protected HashMap accessibleAttributes;
    protected AccessibleElementId id;

}