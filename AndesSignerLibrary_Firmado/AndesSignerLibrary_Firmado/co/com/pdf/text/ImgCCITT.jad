// Decompiled by DJ v3.12.12.98 Copyright 2014 Atanas Neshkov  Date: 17/11/2017 7:07:02 p. m.
// Home Page: http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   ImgCCITT.java

package co.com.pdf.text;

import co.com.pdf.text.error_messages.MessageLocalization;
import co.com.pdf.text.pdf.codec.TIFFFaxDecoder;
import java.net.URL;

// Referenced classes of package co.com.pdf.text:
//            Image, BadElementException

public class ImgCCITT extends Image
{

    ImgCCITT(Image image)
    {
        super(image);
    }

    public ImgCCITT(int width, int height, boolean reverseBits, int typeCCITT, int parameters, byte data[])
        throws BadElementException
    {
        super((URL)null);
        if(typeCCITT != 256 && typeCCITT != 257 && typeCCITT != 258)
            throw new BadElementException(MessageLocalization.getComposedMessage("the.ccitt.compression.type.must.be.ccittg4.ccittg3.1d.or.ccittg3.2d", new Object[0]));
        if(reverseBits)
            TIFFFaxDecoder.reverseBits(data);
        type = 34;
        scaledHeight = height;
        setTop(scaledHeight);
        scaledWidth = width;
        setRight(scaledWidth);
        colorspace = parameters;
        bpc = typeCCITT;
        rawData = data;
        plainWidth = getWidth();
        plainHeight = getHeight();
    }
}