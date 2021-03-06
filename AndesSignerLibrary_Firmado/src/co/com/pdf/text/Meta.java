// Decompiled by DJ v3.12.12.98 Copyright 2014 Atanas Neshkov  Date: 17/11/2017 7:07:03 p. m.
// Home Page: http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   Meta.java

package co.com.pdf.text;

import java.util.ArrayList;
import java.util.List;

// Referenced classes of package co.com.pdf.text:
//            DocumentException, Element, ElementListener

public class Meta
    implements Element
{

    Meta(int type, String content)
    {
        this.type = type;
        this.content = new StringBuffer(content);
    }

    public Meta(String tag, String content)
    {
        type = getType(tag);
        this.content = new StringBuffer(content);
    }

    public boolean process(ElementListener listener)
    {
        try
        {
            return listener.add(this);
        }
        catch(DocumentException de)
        {
            return false;
        }
    }

    public int type()
    {
        return type;
    }

    public List getChunks()
    {
        return new ArrayList();
    }

    public boolean isContent()
    {
        return false;
    }

    public boolean isNestable()
    {
        return false;
    }

    public StringBuffer append(String string)
    {
        return content.append(string);
    }

    public String getContent()
    {
        return content.toString();
    }

    public String getName()
    {
        switch(type)
        {
        case 2: // '\002'
            return "subject";

        case 3: // '\003'
            return "keywords";

        case 4: // '\004'
            return "author";

        case 1: // '\001'
            return "title";

        case 5: // '\005'
            return "producer";

        case 6: // '\006'
            return "creationdate";
        }
        return "unknown";
    }

    public static int getType(String tag)
    {
        if("subject".equals(tag))
            return 2;
        if("keywords".equals(tag))
            return 3;
        if("author".equals(tag))
            return 4;
        if("title".equals(tag))
            return 1;
        if("producer".equals(tag))
            return 5;
        return !"creationdate".equals(tag) ? 0 : 6;
    }

    private final int type;
    private final StringBuffer content;
    public static final String UNKNOWN = "unknown";
    public static final String PRODUCER = "producer";
    public static final String CREATIONDATE = "creationdate";
    public static final String AUTHOR = "author";
    public static final String KEYWORDS = "keywords";
    public static final String SUBJECT = "subject";
    public static final String TITLE = "title";
}