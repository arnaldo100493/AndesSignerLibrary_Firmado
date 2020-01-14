// Decompiled by DJ v3.12.12.98 Copyright 2014 Atanas Neshkov  Date: 17/11/2017 7:07:03 p. m.
// Home Page: http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   ListBody.java

package co.com.pdf.text;

import co.com.pdf.text.pdf.PdfName;
import co.com.pdf.text.pdf.PdfObject;
import co.com.pdf.text.pdf.interfaces.IAccessibleElement;
import java.util.HashMap;

// Referenced classes of package co.com.pdf.text:
//            AccessibleElementId, ListItem

public class ListBody
    implements IAccessibleElement
{

    protected ListBody(ListItem parentItem)
    {
        role = PdfName.LBODY;
        id = null;
        accessibleAttributes = null;
        this.parentItem = null;
        this.parentItem = parentItem;
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

    protected PdfName role;
    private AccessibleElementId id;
    protected HashMap accessibleAttributes;
    protected ListItem parentItem;
}