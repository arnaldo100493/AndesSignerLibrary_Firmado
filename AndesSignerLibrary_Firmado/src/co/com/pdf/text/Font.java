// Decompiled by DJ v3.12.12.98 Copyright 2014 Atanas Neshkov  Date: 17/11/2017 7:07:02 p. m.
// Home Page: http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   Font.java

package co.com.pdf.text;

import co.com.pdf.text.pdf.BaseFont;

// Referenced classes of package co.com.pdf.text:
//            BaseColor, ExceptionConverter, FontFactory

public class Font
    implements Comparable
{
    public static final class FontStyle extends Enum
    {

        public static FontStyle[] values()
        {
            return (FontStyle[])$VALUES.clone();
        }

        public static FontStyle valueOf(String name)
        {
            return (FontStyle)Enum.valueOf(co/com/pdf/text/Font$FontStyle, name);
        }

        public String getValue()
        {
            return code;
        }

        public static final FontStyle NORMAL;
        public static final FontStyle BOLD;
        public static final FontStyle ITALIC;
        public static final FontStyle OBLIQUE;
        public static final FontStyle UNDERLINE;
        public static final FontStyle LINETHROUGH;
        private String code;
        private static final FontStyle $VALUES[];

        static 
        {
            NORMAL = new FontStyle("NORMAL", 0, "normal");
            BOLD = new FontStyle("BOLD", 1, "bold");
            ITALIC = new FontStyle("ITALIC", 2, "italic");
            OBLIQUE = new FontStyle("OBLIQUE", 3, "oblique");
            UNDERLINE = new FontStyle("UNDERLINE", 4, "underline");
            LINETHROUGH = new FontStyle("LINETHROUGH", 5, "line-through");
            $VALUES = (new FontStyle[] {
                NORMAL, BOLD, ITALIC, OBLIQUE, UNDERLINE, LINETHROUGH
            });
        }

        private FontStyle(String s, int i, String code)
        {
            super(s, i);
            this.code = code;
        }
    }

    public static final class FontFamily extends Enum
    {

        public static FontFamily[] values()
        {
            return (FontFamily[])$VALUES.clone();
        }

        public static FontFamily valueOf(String name)
        {
            return (FontFamily)Enum.valueOf(co/com/pdf/text/Font$FontFamily, name);
        }

        public static final FontFamily COURIER;
        public static final FontFamily HELVETICA;
        public static final FontFamily TIMES_ROMAN;
        public static final FontFamily SYMBOL;
        public static final FontFamily ZAPFDINGBATS;
        public static final FontFamily UNDEFINED;
        private static final FontFamily $VALUES[];

        static 
        {
            COURIER = new FontFamily("COURIER", 0);
            HELVETICA = new FontFamily("HELVETICA", 1);
            TIMES_ROMAN = new FontFamily("TIMES_ROMAN", 2);
            SYMBOL = new FontFamily("SYMBOL", 3);
            ZAPFDINGBATS = new FontFamily("ZAPFDINGBATS", 4);
            UNDEFINED = new FontFamily("UNDEFINED", 5);
            $VALUES = (new FontFamily[] {
                COURIER, HELVETICA, TIMES_ROMAN, SYMBOL, ZAPFDINGBATS, UNDEFINED
            });
        }

        private FontFamily(String s, int i)
        {
            super(s, i);
        }
    }


    public Font(Font other)
    {
        family = FontFamily.UNDEFINED;
        size = -1F;
        style = -1;
        color = null;
        baseFont = null;
        family = other.family;
        size = other.size;
        style = other.style;
        color = other.color;
        baseFont = other.baseFont;
    }

    public Font(FontFamily family, float size, int style, BaseColor color)
    {
        this.family = FontFamily.UNDEFINED;
        this.size = -1F;
        this.style = -1;
        this.color = null;
        baseFont = null;
        this.family = family;
        this.size = size;
        this.style = style;
        this.color = color;
    }

    public Font(BaseFont bf, float size, int style, BaseColor color)
    {
        family = FontFamily.UNDEFINED;
        this.size = -1F;
        this.style = -1;
        this.color = null;
        baseFont = null;
        baseFont = bf;
        this.size = size;
        this.style = style;
        this.color = color;
    }

    public Font(BaseFont bf, float size, int style)
    {
        this(bf, size, style, null);
    }

    public Font(BaseFont bf, float size)
    {
        this(bf, size, -1, null);
    }

    public Font(BaseFont bf)
    {
        this(bf, -1F, -1, null);
    }

    public Font(FontFamily family, float size, int style)
    {
        this(family, size, style, null);
    }

    public Font(FontFamily family, float size)
    {
        this(family, size, -1, null);
    }

    public Font(FontFamily family)
    {
        this(family, -1F, -1, null);
    }

    public Font()
    {
        this(FontFamily.UNDEFINED, -1F, -1, null);
    }

    public int compareTo(Font font)
    {
        if(font == null)
            return -1;
        try
        {
            if(baseFont != null && !baseFont.equals(font.getBaseFont()))
                return -2;
        }
        catch(ClassCastException cce)
        {
            return -3;
        }
        if(family != font.getFamily())
            return 1;
        if(size != font.getSize())
            return 2;
        if(style != font.getStyle())
            return 3;
        if(color != null)
            break MISSING_BLOCK_LABEL_88;
        if(font.color == null)
            return 0;
        return 4;
        if(font.color == null)
            return 4;
        if(color.equals(font.getColor()))
            return 0;
        return 4;
    }

    public FontFamily getFamily()
    {
        return family;
    }

    public String getFamilyname()
    {
        String tmp = "unknown";
        static class _cls1
        {

            static final int $SwitchMap$co$com$pdf$text$Font$FontFamily[];

            static 
            {
                $SwitchMap$co$com$pdf$text$Font$FontFamily = new int[FontFamily.values().length];
                try
                {
                    $SwitchMap$co$com$pdf$text$Font$FontFamily[FontFamily.COURIER.ordinal()] = 1;
                }
                catch(NoSuchFieldError ex) { }
                try
                {
                    $SwitchMap$co$com$pdf$text$Font$FontFamily[FontFamily.HELVETICA.ordinal()] = 2;
                }
                catch(NoSuchFieldError ex) { }
                try
                {
                    $SwitchMap$co$com$pdf$text$Font$FontFamily[FontFamily.TIMES_ROMAN.ordinal()] = 3;
                }
                catch(NoSuchFieldError ex) { }
                try
                {
                    $SwitchMap$co$com$pdf$text$Font$FontFamily[FontFamily.SYMBOL.ordinal()] = 4;
                }
                catch(NoSuchFieldError ex) { }
                try
                {
                    $SwitchMap$co$com$pdf$text$Font$FontFamily[FontFamily.ZAPFDINGBATS.ordinal()] = 5;
                }
                catch(NoSuchFieldError ex) { }
            }
        }

        switch(_cls1..SwitchMap.co.com.pdf.text.Font.FontFamily[getFamily().ordinal()])
        {
        case 1: // '\001'
            return "Courier";

        case 2: // '\002'
            return "Helvetica";

        case 3: // '\003'
            return "Times-Roman";

        case 4: // '\004'
            return "Symbol";

        case 5: // '\005'
            return "ZapfDingbats";
        }
        if(baseFont != null)
        {
            String names[][] = baseFont.getFamilyFontName();
            String arr$[][] = names;
            int len$ = arr$.length;
            for(int i$ = 0; i$ < len$; i$++)
            {
                String name[] = arr$[i$];
                if("0".equals(name[2]))
                    return name[3];
                if("1033".equals(name[2]))
                    tmp = name[3];
                if("".equals(name[2]))
                    tmp = name[3];
            }

        }
        return tmp;
    }

    public void setFamily(String family)
    {
        this.family = getFamily(family);
    }

    public static FontFamily getFamily(String family)
    {
        if(family.equalsIgnoreCase("Courier"))
            return FontFamily.COURIER;
        if(family.equalsIgnoreCase("Helvetica"))
            return FontFamily.HELVETICA;
        if(family.equalsIgnoreCase("Times-Roman"))
            return FontFamily.TIMES_ROMAN;
        if(family.equalsIgnoreCase("Symbol"))
            return FontFamily.SYMBOL;
        if(family.equalsIgnoreCase("ZapfDingbats"))
            return FontFamily.ZAPFDINGBATS;
        else
            return FontFamily.UNDEFINED;
    }

    public float getSize()
    {
        return size;
    }

    public float getCalculatedSize()
    {
        float s = size;
        if(s == -1F)
            s = 12F;
        return s;
    }

    public float getCalculatedLeading(float linespacing)
    {
        return linespacing * getCalculatedSize();
    }

    public void setSize(float size)
    {
        this.size = size;
    }

    public int getStyle()
    {
        return style;
    }

    public int getCalculatedStyle()
    {
        int style = this.style;
        if(style == -1)
            style = 0;
        if(baseFont != null)
            return style;
        if(family == FontFamily.SYMBOL || family == FontFamily.ZAPFDINGBATS)
            return style;
        else
            return style & -4;
    }

    public boolean isBold()
    {
        if(style == -1)
            return false;
        else
            return (style & 1) == 1;
    }

    public boolean isItalic()
    {
        if(style == -1)
            return false;
        else
            return (style & 2) == 2;
    }

    public boolean isUnderlined()
    {
        if(style == -1)
            return false;
        else
            return (style & 4) == 4;
    }

    public boolean isStrikethru()
    {
        if(style == -1)
            return false;
        else
            return (style & 8) == 8;
    }

    public void setStyle(int style)
    {
        this.style = style;
    }

    public void setStyle(String style)
    {
        if(this.style == -1)
            this.style = 0;
        this.style |= getStyleValue(style);
    }

    public static int getStyleValue(String style)
    {
        int s = 0;
        if(style.indexOf(FontStyle.NORMAL.getValue()) != -1)
            s |= 0;
        if(style.indexOf(FontStyle.BOLD.getValue()) != -1)
            s |= 1;
        if(style.indexOf(FontStyle.ITALIC.getValue()) != -1)
            s |= 2;
        if(style.indexOf(FontStyle.OBLIQUE.getValue()) != -1)
            s |= 2;
        if(style.indexOf(FontStyle.UNDERLINE.getValue()) != -1)
            s |= 4;
        if(style.indexOf(FontStyle.LINETHROUGH.getValue()) != -1)
            s |= 8;
        return s;
    }

    public BaseColor getColor()
    {
        return color;
    }

    public void setColor(BaseColor color)
    {
        this.color = color;
    }

    public void setColor(int red, int green, int blue)
    {
        color = new BaseColor(red, green, blue);
    }

    public BaseFont getBaseFont()
    {
        return baseFont;
    }

    public BaseFont getCalculatedBaseFont(boolean specialEncoding)
    {
        if(baseFont != null)
            return baseFont;
        int style = this.style;
        if(style == -1)
            style = 0;
        String fontName = "Helvetica";
        String encoding = "Cp1252";
        BaseFont cfont = null;
        switch(_cls1..SwitchMap.co.com.pdf.text.Font.FontFamily[family.ordinal()])
        {
        case 1: // '\001'
            switch(style & 3)
            {
            case 1: // '\001'
                fontName = "Courier-Bold";
                break;

            case 2: // '\002'
                fontName = "Courier-Oblique";
                break;

            case 3: // '\003'
                fontName = "Courier-BoldOblique";
                break;

            default:
                fontName = "Courier";
                break;
            }
            break;

        case 3: // '\003'
            switch(style & 3)
            {
            case 1: // '\001'
                fontName = "Times-Bold";
                break;

            case 2: // '\002'
                fontName = "Times-Italic";
                break;

            case 3: // '\003'
                fontName = "Times-BoldItalic";
                break;

            case 0: // '\0'
            default:
                fontName = "Times-Roman";
                break;
            }
            break;

        case 4: // '\004'
            fontName = "Symbol";
            if(specialEncoding)
                encoding = "Symbol";
            break;

        case 5: // '\005'
            fontName = "ZapfDingbats";
            if(specialEncoding)
                encoding = "ZapfDingbats";
            break;

        case 2: // '\002'
        default:
            switch(style & 3)
            {
            case 1: // '\001'
                fontName = "Helvetica-Bold";
                break;

            case 2: // '\002'
                fontName = "Helvetica-Oblique";
                break;

            case 3: // '\003'
                fontName = "Helvetica-BoldOblique";
                break;

            case 0: // '\0'
            default:
                fontName = "Helvetica";
                break;
            }
            break;
        }
        try
        {
            cfont = BaseFont.createFont(fontName, encoding, false);
        }
        catch(Exception ee)
        {
            throw new ExceptionConverter(ee);
        }
        return cfont;
    }

    public boolean isStandardFont()
    {
        return family == FontFamily.UNDEFINED && size == -1F && style == -1 && color == null && baseFont == null;
    }

    public Font difference(Font font)
    {
        if(font == null)
            return this;
        float dSize = font.size;
        if(dSize == -1F)
            dSize = size;
        int dStyle = -1;
        int style1 = style;
        int style2 = font.getStyle();
        if(style1 != -1 || style2 != -1)
        {
            if(style1 == -1)
                style1 = 0;
            if(style2 == -1)
                style2 = 0;
            dStyle = style1 | style2;
        }
        BaseColor dColor = font.color;
        if(dColor == null)
            dColor = color;
        if(font.baseFont != null)
            return new Font(font.baseFont, dSize, dStyle, dColor);
        if(font.getFamily() != FontFamily.UNDEFINED)
            return new Font(font.family, dSize, dStyle, dColor);
        if(baseFont != null)
        {
            if(dStyle == style1)
                return new Font(baseFont, dSize, dStyle, dColor);
            else
                return FontFactory.getFont(getFamilyname(), dSize, dStyle, dColor);
        } else
        {
            return new Font(family, dSize, dStyle, dColor);
        }
    }

    public volatile int compareTo(Object x0)
    {
        return compareTo((Font)x0);
    }

    public static final int NORMAL = 0;
    public static final int BOLD = 1;
    public static final int ITALIC = 2;
    public static final int UNDERLINE = 4;
    public static final int STRIKETHRU = 8;
    public static final int BOLDITALIC = 3;
    public static final int UNDEFINED = -1;
    public static final int DEFAULTSIZE = 12;
    private FontFamily family;
    private float size;
    private int style;
    private BaseColor color;
    private BaseFont baseFont;
}