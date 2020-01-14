// Decompiled by DJ v3.12.12.98 Copyright 2014 Atanas Neshkov  Date: 17/11/2017 7:06:58 p. m.
// Home Page: http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   FontMapper.java
package co.com.pdf.awt;

import co.com.pdf.text.pdf.BaseFont;
import java.awt.Font;

public interface FontMapper {

    public BaseFont awtToPdf(Font paramFont);

    public Font pdfToAwt(BaseFont paramBaseFont, int paramInt);
}
