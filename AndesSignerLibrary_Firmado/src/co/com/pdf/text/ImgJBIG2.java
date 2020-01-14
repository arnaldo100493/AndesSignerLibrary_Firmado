// Decompiled by DJ v3.12.12.98 Copyright 2014 Atanas Neshkov  Date: 17/11/2017 7:07:03 p. m.
// Home Page: http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   ImgJBIG2.java

package co.com.pdf.text;

import java.net.URL;
import java.security.MessageDigest;

// Referenced classes of package co.com.pdf.text:
//            Image

public class ImgJBIG2 extends Image
{

    ImgJBIG2(Image image)
    {
        super(image);
    }

    public ImgJBIG2()
    {
        super((Image)null);
    }

    public ImgJBIG2(int width, int height, byte data[], byte globals[])
    {
        super((URL)null);
        type = 36;
        originalType = 9;
        scaledHeight = height;
        setTop(scaledHeight);
        scaledWidth = width;
        setRight(scaledWidth);
        bpc = 1;
        colorspace = 1;
        rawData = data;
        plainWidth = getWidth();
        plainHeight = getHeight();
        if(globals != null)
        {
            global = globals;
            try
            {
                MessageDigest md = MessageDigest.getInstance("MD5");
                md.update(global);
                globalHash = md.digest();
            }
            catch(Exception e) { }
        }
    }

    public byte[] getGlobalBytes()
    {
        return global;
    }

    public byte[] getGlobalHash()
    {
        return globalHash;
    }

    private byte global[];
    private byte globalHash[];
}