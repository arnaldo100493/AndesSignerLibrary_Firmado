// Decompiled by DJ v3.12.12.98 Copyright 2014 Atanas Neshkov  Date: 17/11/2017 7:07:03 p. m.
// Home Page: http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   ImgWMF.java

package co.com.pdf.text;

import co.com.pdf.text.error_messages.MessageLocalization;
import co.com.pdf.text.pdf.PdfTemplate;
import co.com.pdf.text.pdf.codec.wmf.InputMeta;
import co.com.pdf.text.pdf.codec.wmf.MetaDo;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

// Referenced classes of package co.com.pdf.text:
//            Image, BadElementException, DocumentException, Utilities

public class ImgWMF extends Image
{

    ImgWMF(Image image)
    {
        super(image);
    }

    public ImgWMF(URL url)
        throws BadElementException, IOException
    {
        super(url);
        processParameters();
    }

    public ImgWMF(String filename)
        throws BadElementException, MalformedURLException, IOException
    {
        this(Utilities.toURL(filename));
    }

    public ImgWMF(byte img[])
        throws BadElementException, IOException
    {
        super((URL)null);
        rawData = img;
        originalData = img;
        processParameters();
    }

    private void processParameters()
        throws BadElementException, IOException
    {
        InputStream is;
        type = 35;
        originalType = 6;
        is = null;
        String errorID;
        if(rawData == null)
        {
            is = url.openStream();
            errorID = url.toString();
        } else
        {
            is = new ByteArrayInputStream(rawData);
            errorID = "Byte array";
        }
        InputMeta in = new InputMeta(is);
        if(in.readInt() != 0x9ac6cdd7)
            throw new BadElementException(MessageLocalization.getComposedMessage("1.is.not.a.valid.placeable.windows.metafile", new Object[] {
                errorID
            }));
        in.readWord();
        int left = in.readShort();
        int top = in.readShort();
        int right = in.readShort();
        int bottom = in.readShort();
        int inch = in.readWord();
        dpiX = 72;
        dpiY = 72;
        scaledHeight = ((float)(bottom - top) / (float)inch) * 72F;
        setTop(scaledHeight);
        scaledWidth = ((float)(right - left) / (float)inch) * 72F;
        setRight(scaledWidth);
        if(is != null)
            is.close();
        plainWidth = getWidth();
        plainHeight = getHeight();
        break MISSING_BLOCK_LABEL_247;
        Exception exception;
        exception;
        if(is != null)
            is.close();
        plainWidth = getWidth();
        plainHeight = getHeight();
        throw exception;
    }

    public void readWMF(PdfTemplate template)
        throws IOException, DocumentException
    {
        InputStream is;
        setTemplateData(template);
        template.setWidth(getWidth());
        template.setHeight(getHeight());
        is = null;
        if(rawData == null)
            is = url.openStream();
        else
            is = new ByteArrayInputStream(rawData);
        MetaDo meta = new MetaDo(is, template);
        meta.readAll();
        if(is != null)
            is.close();
        break MISSING_BLOCK_LABEL_91;
        Exception exception;
        exception;
        if(is != null)
            is.close();
        throw exception;
    }
}