// Decompiled by DJ v3.12.12.98 Copyright 2014 Atanas Neshkov  Date: 17/11/2017 7:06:58 p. m.
// Home Page: http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   AsianFontMapper.java
package co.com.pdf.awt;

import co.com.pdf.text.pdf.BaseFont;
import java.awt.Font;

// Referenced classes of package co.com.pdf.awt:
//            DefaultFontMapper

public class AsianFontMapper extends DefaultFontMapper {

    public static final String ChineseSimplifiedFont = "STSong-Light";

    public static final String ChineseSimplifiedEncoding_H = "UniGB-UCS2-H";

    public static final String ChineseSimplifiedEncoding_V = "UniGB-UCS2-V";

    public static final String ChineseTraditionalFont_MHei = "MHei-Medium";

    public static final String ChineseTraditionalFont_MSung = "MSung-Light";

    public static final String ChineseTraditionalEncoding_H = "UniCNS-UCS2-H";

    public static final String ChineseTraditionalEncoding_V = "UniCNS-UCS2-V";

    public static final String JapaneseFont_Go = "HeiseiKakuGo-W5";

    public static final String JapaneseFont_Min = "HeiseiMin-W3";

    public static final String JapaneseEncoding_H = "UniJIS-UCS2-H";

    public static final String JapaneseEncoding_V = "UniJIS-UCS2-V";

    public static final String JapaneseEncoding_HW_H = "UniJIS-UCS2-HW-H";

    public static final String JapaneseEncoding_HW_V = "UniJIS-UCS2-HW-V";

    public static final String KoreanFont_GoThic = "HYGoThic-Medium";

    public static final String KoreanFont_SMyeongJo = "HYSMyeongJo-Medium";

    public static final String KoreanEncoding_H = "UniKS-UCS2-H";

    public static final String KoreanEncoding_V = "UniKS-UCS2-V";

    private final String defaultFont;

    private final String encoding;

    public AsianFontMapper() {
        this.defaultFont = "";
        this.encoding = "";
    }

    public AsianFontMapper(String font, String encoding) {
        this.defaultFont = font;
        this.encoding = encoding;
    }

    @Override
    public BaseFont awtToPdf(Font font) {
        try {
            DefaultFontMapper.BaseFontParameters p = getBaseFontParameters(font.getFontName());
            if (p != null) {
                return BaseFont.createFont(p.fontName, p.encoding, p.embedded, p.cached, p.ttfAfm, p.pfb);
            }
            return BaseFont.createFont(this.defaultFont, this.encoding, true);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
