// Decompiled by DJ v3.12.12.98 Copyright 2014 Atanas Neshkov  Date: 17/11/2017 7:07:01 p. m.
// Home Page: http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   BaseColor.java

package co.com.pdf.text;

import co.com.pdf.text.error_messages.MessageLocalization;

public class BaseColor
{

    public BaseColor(int red, int green, int blue, int alpha)
    {
        validate(red);
        validate(green);
        validate(blue);
        validate(alpha);
        value = (alpha & 0xff) << 24 | (red & 0xff) << 16 | (green & 0xff) << 8 | (blue & 0xff) << 0;
    }

    public BaseColor(int red, int green, int blue)
    {
        this(red, green, blue, 255);
    }

    public BaseColor(float red, float green, float blue, float alpha)
    {
        this((int)((double)(red * 255F) + 0.5D), (int)((double)(green * 255F) + 0.5D), (int)((double)(blue * 255F) + 0.5D), (int)((double)(alpha * 255F) + 0.5D));
    }

    public BaseColor(float red, float green, float blue)
    {
        this(red, green, blue, 1.0F);
    }

    public BaseColor(int argb)
    {
        value = argb;
    }

    public int getRGB()
    {
        return value;
    }

    public int getRed()
    {
        return getRGB() >> 16 & 0xff;
    }

    public int getGreen()
    {
        return getRGB() >> 8 & 0xff;
    }

    public int getBlue()
    {
        return getRGB() >> 0 & 0xff;
    }

    public int getAlpha()
    {
        return getRGB() >> 24 & 0xff;
    }

    public BaseColor brighter()
    {
        int r = getRed();
        int g = getGreen();
        int b = getBlue();
        int i = 3;
        if(r == 0 && g == 0 && b == 0)
            return new BaseColor(i, i, i);
        if(r > 0 && r < i)
            r = i;
        if(g > 0 && g < i)
            g = i;
        if(b > 0 && b < i)
            b = i;
        return new BaseColor(Math.min((int)((double)r / 0.69999999999999996D), 255), Math.min((int)((double)g / 0.69999999999999996D), 255), Math.min((int)((double)b / 0.69999999999999996D), 255));
    }

    public BaseColor darker()
    {
        return new BaseColor(Math.max((int)((double)getRed() * 0.69999999999999996D), 0), Math.max((int)((double)getGreen() * 0.69999999999999996D), 0), Math.max((int)((double)getBlue() * 0.69999999999999996D), 0));
    }

    public boolean equals(Object obj)
    {
        return (obj instanceof BaseColor) && ((BaseColor)obj).value == value;
    }

    public int hashCode()
    {
        return value;
    }

    private static void validate(int value)
    {
        if(value < 0 || value > 255)
            throw new IllegalArgumentException(MessageLocalization.getComposedMessage("color.value.outside.range.0.255", new Object[0]));
        else
            return;
    }

    public String toString()
    {
        return (new StringBuilder()).append("Color value[").append(Integer.toString(value, 16)).append("]").toString();
    }

    public static final BaseColor WHITE = new BaseColor(255, 255, 255);
    public static final BaseColor LIGHT_GRAY = new BaseColor(192, 192, 192);
    public static final BaseColor GRAY = new BaseColor(128, 128, 128);
    public static final BaseColor DARK_GRAY = new BaseColor(64, 64, 64);
    public static final BaseColor BLACK = new BaseColor(0, 0, 0);
    public static final BaseColor RED = new BaseColor(255, 0, 0);
    public static final BaseColor PINK = new BaseColor(255, 175, 175);
    public static final BaseColor ORANGE = new BaseColor(255, 200, 0);
    public static final BaseColor YELLOW = new BaseColor(255, 255, 0);
    public static final BaseColor GREEN = new BaseColor(0, 255, 0);
    public static final BaseColor MAGENTA = new BaseColor(255, 0, 255);
    public static final BaseColor CYAN = new BaseColor(0, 255, 255);
    public static final BaseColor BLUE = new BaseColor(0, 0, 255);
    private static final double FACTOR = 0.69999999999999996D;
    private final int value;

}