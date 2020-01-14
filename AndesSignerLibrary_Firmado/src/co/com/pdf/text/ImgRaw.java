// Decompiled by DJ v3.12.12.98 Copyright 2014 Atanas Neshkov  Date: 17/11/2017 7:07:03 p. m.
// Home Page: http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   ImgRaw.java

package co.com.pdf.text;

import co.com.pdf.text.error_messages.MessageLocalization;
import java.net.URL;

// Referenced classes of package co.com.pdf.text:
//            Image, BadElementException

public class ImgRaw extends Image
{

    ImgRaw(Image image)
    {
        super(image);
    }

    public ImgRaw(int width, int height, int components, int bpc, byte data[])
        throws BadElementException
    {
        super((URL)null);
        type = 34;
        scaledHeight = height;
        setTop(scaledHeight);
        scaledWidth = width;
        setRight(scaledWidth);
        if(components != 1 && components != 3 && components != 4)
            throw new BadElementException(MessageLocalization.getComposedMessage("components.must.be.1.3.or.4", new Object[0]));
        if(bpc != 1 && bpc != 2 && bpc != 4 && bpc != 8)
        {
            throw new BadElementException(MessageLocalization.getComposedMessage("bits.per.component.must.be.1.2.4.or.8", new Object[0]));
        } else
        {
            colorspace = components;
            this.bpc = bpc;
            rawData = data;
            plainWidth = getWidth();
            plainHeight = getHeight();
            return;
        }
    }
}