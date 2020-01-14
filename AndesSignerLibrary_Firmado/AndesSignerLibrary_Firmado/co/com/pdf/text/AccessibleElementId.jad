// Decompiled by DJ v3.12.12.98 Copyright 2014 Atanas Neshkov  Date: 17/11/2017 7:07:01 p. m.
// Home Page: http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   AccessibleElementId.java

package co.com.pdf.text;


public class AccessibleElementId
    implements Comparable
{

    public AccessibleElementId()
    {
        id = 0;
        id = ++id_counter;
    }

    public String toString()
    {
        return Integer.toString(id);
    }

    public int hashCode()
    {
        return id;
    }

    public boolean equals(Object o)
    {
        return (o instanceof AccessibleElementId) && id == ((AccessibleElementId)o).id;
    }

    public int compareTo(AccessibleElementId elementId)
    {
        if(id < elementId.id)
            return -1;
        return id <= elementId.id ? 0 : 1;
    }

    public volatile int compareTo(Object x0)
    {
        return compareTo((AccessibleElementId)x0);
    }

    private static int id_counter = 0;
    private int id;

}