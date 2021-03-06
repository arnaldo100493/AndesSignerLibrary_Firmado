// Decompiled by DJ v3.12.12.98 Copyright 2014 Atanas Neshkov  Date: 17/11/2017 7:07:02 p. m.
// Home Page: http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   DocWriter.java

package co.com.pdf.text;

import co.com.pdf.text.pdf.OutputStreamCounter;
import java.io.*;
import java.util.*;

// Referenced classes of package co.com.pdf.text:
//            ExceptionConverter, DocListener, DocumentException, Rectangle, 
//            Document, Element

public abstract class DocWriter
    implements DocListener
{

    protected DocWriter()
    {
        open = false;
        pause = false;
        closeStream = true;
    }

    protected DocWriter(Document document, OutputStream os)
    {
        open = false;
        pause = false;
        closeStream = true;
        this.document = document;
        this.os = new OutputStreamCounter(new BufferedOutputStream(os));
    }

    public boolean add(Element element)
        throws DocumentException
    {
        return false;
    }

    public void open()
    {
        open = true;
    }

    public boolean setPageSize(Rectangle pageSize)
    {
        this.pageSize = pageSize;
        return true;
    }

    public boolean setMargins(float marginLeft, float marginRight, float marginTop, float f)
    {
        return false;
    }

    public boolean newPage()
    {
        return open;
    }

    public void resetPageCount()
    {
    }

    public void setPageCount(int i)
    {
    }

    public void close()
    {
        open = false;
        try
        {
            os.flush();
            if(closeStream)
                os.close();
        }
        catch(IOException ioe)
        {
            throw new ExceptionConverter(ioe);
        }
    }

    public static final byte[] getISOBytes(String text)
    {
        if(text == null)
            return null;
        int len = text.length();
        byte b[] = new byte[len];
        for(int k = 0; k < len; k++)
            b[k] = (byte)text.charAt(k);

        return b;
    }

    public void pause()
    {
        pause = true;
    }

    public boolean isPaused()
    {
        return pause;
    }

    public void resume()
    {
        pause = false;
    }

    public void flush()
    {
        try
        {
            os.flush();
        }
        catch(IOException ioe)
        {
            throw new ExceptionConverter(ioe);
        }
    }

    protected void write(String string)
        throws IOException
    {
        os.write(getISOBytes(string));
    }

    protected void addTabs(int indent)
        throws IOException
    {
        os.write(10);
        for(int i = 0; i < indent; i++)
            os.write(9);

    }

    protected void write(String key, String value)
        throws IOException
    {
        os.write(32);
        write(key);
        os.write(61);
        os.write(34);
        write(value);
        os.write(34);
    }

    protected void writeStart(String tag)
        throws IOException
    {
        os.write(60);
        write(tag);
    }

    protected void writeEnd(String tag)
        throws IOException
    {
        os.write(60);
        os.write(47);
        write(tag);
        os.write(62);
    }

    protected void writeEnd()
        throws IOException
    {
        os.write(32);
        os.write(47);
        os.write(62);
    }

    protected boolean writeMarkupAttributes(Properties markup)
        throws IOException
    {
        if(markup == null)
            return false;
        String name;
        for(Iterator attributeIterator = markup.keySet().iterator(); attributeIterator.hasNext(); write(name, markup.getProperty(name)))
            name = String.valueOf(attributeIterator.next());

        markup.clear();
        return true;
    }

    public boolean isCloseStream()
    {
        return closeStream;
    }

    public void setCloseStream(boolean closeStream)
    {
        this.closeStream = closeStream;
    }

    public boolean setMarginMirroring(boolean MarginMirroring)
    {
        return false;
    }

    public boolean setMarginMirroringTopBottom(boolean MarginMirroring)
    {
        return false;
    }

    public static final byte NEWLINE = 10;
    public static final byte TAB = 9;
    public static final byte LT = 60;
    public static final byte SPACE = 32;
    public static final byte EQUALS = 61;
    public static final byte QUOTE = 34;
    public static final byte GT = 62;
    public static final byte FORWARD = 47;
    protected Rectangle pageSize;
    protected Document document;
    protected OutputStreamCounter os;
    protected boolean open;
    protected boolean pause;
    protected boolean closeStream;
}