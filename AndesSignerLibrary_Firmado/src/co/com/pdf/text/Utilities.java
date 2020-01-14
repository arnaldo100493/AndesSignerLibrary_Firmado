// Decompiled by DJ v3.12.12.98 Copyright 2014 Atanas Neshkov  Date: 17/11/2017 7:07:04 p. m.
// Home Page: http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   Utilities.java

package co.com.pdf.text;

import co.com.pdf.text.pdf.ByteBuffer;
import co.com.pdf.text.pdf.PRTokeniser;
import co.com.pdf.text.pdf.PdfEncodings;
import java.io.*;
import java.net.*;
import java.util.*;

public class Utilities
{

    public Utilities()
    {
    }

    /**
     * @deprecated Method getKeySet is deprecated
     */

    public static Set getKeySet(Hashtable table)
    {
        return table != null ? table.keySet() : Collections.emptySet();
    }

    public static Object[][] addToArray(Object original[][], Object item[])
    {
        if(original == null)
        {
            original = new Object[1][];
            original[0] = item;
            return original;
        } else
        {
            Object original2[][] = new Object[original.length + 1][];
            System.arraycopy(((Object) (original)), 0, ((Object) (original2)), 0, original.length);
            original2[original.length] = item;
            return original2;
        }
    }

    public static boolean checkTrueOrFalse(Properties attributes, String key)
    {
        return "true".equalsIgnoreCase(attributes.getProperty(key));
    }

    public static String unEscapeURL(String src)
    {
        StringBuffer bf = new StringBuffer();
        char s[] = src.toCharArray();
        for(int k = 0; k < s.length; k++)
        {
            char c = s[k];
            if(c == '%')
            {
                if(k + 2 >= s.length)
                {
                    bf.append(c);
                    continue;
                }
                int a0 = PRTokeniser.getHex(s[k + 1]);
                int a1 = PRTokeniser.getHex(s[k + 2]);
                if(a0 < 0 || a1 < 0)
                {
                    bf.append(c);
                } else
                {
                    bf.append((char)(a0 * 16 + a1));
                    k += 2;
                }
            } else
            {
                bf.append(c);
            }
        }

        return bf.toString();
    }

    public static URL toURL(String filename)
        throws MalformedURLException
    {
        try
        {
            return new URL(filename);
        }
        catch(Exception e)
        {
            return (new File(filename)).toURI().toURL();
        }
    }

    public static void skip(InputStream is, int size)
        throws IOException
    {
        do
        {
            if(size <= 0)
                break;
            long n = is.skip(size);
            if(n <= 0L)
                break;
            size = (int)((long)size - n);
        } while(true);
    }

    public static final float millimetersToPoints(float value)
    {
        return inchesToPoints(millimetersToInches(value));
    }

    public static final float millimetersToInches(float value)
    {
        return value / 25.4F;
    }

    public static final float pointsToMillimeters(float value)
    {
        return inchesToMillimeters(pointsToInches(value));
    }

    public static final float pointsToInches(float value)
    {
        return value / 72F;
    }

    public static final float inchesToMillimeters(float value)
    {
        return value * 25.4F;
    }

    public static final float inchesToPoints(float value)
    {
        return value * 72F;
    }

    public static boolean isSurrogateHigh(char c)
    {
        return c >= '\uD800' && c <= '\uDBFF';
    }

    public static boolean isSurrogateLow(char c)
    {
        return c >= '\uDC00' && c <= '\uDFFF';
    }

    public static boolean isSurrogatePair(String text, int idx)
    {
        if(idx < 0 || idx > text.length() - 2)
            return false;
        else
            return isSurrogateHigh(text.charAt(idx)) && isSurrogateLow(text.charAt(idx + 1));
    }

    public static boolean isSurrogatePair(char text[], int idx)
    {
        if(idx < 0 || idx > text.length - 2)
            return false;
        else
            return isSurrogateHigh(text[idx]) && isSurrogateLow(text[idx + 1]);
    }

    public static int convertToUtf32(char highSurrogate, char lowSurrogate)
    {
        return (((highSurrogate - 55296) * 1024 + lowSurrogate) - 56320) + 0x10000;
    }

    public static int convertToUtf32(char text[], int idx)
    {
        return (((text[idx] - 55296) * 1024 + text[idx + 1]) - 56320) + 0x10000;
    }

    public static int convertToUtf32(String text, int idx)
    {
        return (((text.charAt(idx) - 55296) * 1024 + text.charAt(idx + 1)) - 56320) + 0x10000;
    }

    public static String convertFromUtf32(int codePoint)
    {
        if(codePoint < 0x10000)
        {
            return Character.toString((char)codePoint);
        } else
        {
            codePoint -= 0x10000;
            return new String(new char[] {
                (char)(codePoint / 1024 + 55296), (char)(codePoint % 1024 + 56320)
            });
        }
    }

    public static String readFileToString(String path)
        throws IOException
    {
        return readFileToString(new File(path));
    }

    public static String readFileToString(File file)
        throws IOException
    {
        byte jsBytes[] = new byte[(int)file.length()];
        FileInputStream f = new FileInputStream(file);
        f.read(jsBytes);
        return new String(jsBytes);
    }

    public static String convertToHex(byte bytes[])
    {
        ByteBuffer buf = new ByteBuffer();
        byte arr$[] = bytes;
        int len$ = arr$.length;
        for(int i$ = 0; i$ < len$; i$++)
        {
            byte b = arr$[i$];
            buf.appendHex(b);
        }

        return PdfEncodings.convertToString(buf.toByteArray(), null).toUpperCase();
    }
}