// Decompiled by DJ v3.12.12.98 Copyright 2014 Atanas Neshkov  Date: 17/11/2017 7:07:03 p. m.
// Home Page: http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   ImgTemplate.java

package co.com.pdf.text;

import co.com.pdf.text.error_messages.MessageLocalization;
import co.com.pdf.text.pdf.PdfTemplate;
import java.net.URL;

// Referenced classes of package co.com.pdf.text:
//            Image, BadElementException

public class ImgTemplate extends Image
{

    ImgTemplate(Image image)
    {
        super(image);
    }

    public ImgTemplate(PdfTemplate template)
        throws BadElementException
    {
        super((URL)null);
        if(template == null)
            throw new BadElementException(MessageLocalization.getComposedMessage("the.template.can.not.be.null", new Object[0]));
        if(template.getType() == 3)
        {
            throw new BadElementException(MessageLocalization.getComposedMessage("a.pattern.can.not.be.used.as.a.template.to.create.an.image", new Object[0]));
        } else
        {
            type = 35;
            scaledHeight = template.getHeight();
            setTop(scaledHeight);
            scaledWidth = template.getWidth();
            setRight(scaledWidth);
            setTemplateData(template);
            plainWidth = getWidth();
            plainHeight = getHeight();
            return;
        }
    }
}