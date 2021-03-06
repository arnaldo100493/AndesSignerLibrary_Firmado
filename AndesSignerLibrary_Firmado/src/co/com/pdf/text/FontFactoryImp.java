// Decompiled by DJ v3.12.12.98 Copyright 2014 Atanas Neshkov  Date: 17/11/2017 7:07:02 p. m.
// Home Page: http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   FontFactoryImp.java

package co.com.pdf.text;

import co.com.pdf.text.log.Level;
import co.com.pdf.text.log.Logger;
import co.com.pdf.text.log.LoggerFactory;
import co.com.pdf.text.pdf.BaseFont;
import java.io.File;
import java.io.IOException;
import java.util.*;

// Referenced classes of package co.com.pdf.text:
//            Font, DocumentException, ExceptionConverter, FontProvider, 
//            BaseColor

public class FontFactoryImp
    implements FontProvider
{

    public FontFactoryImp()
    {
        defaultEncoding = "Cp1252";
        defaultEmbedding = false;
        trueTypeFonts.put("Courier".toLowerCase(), "Courier");
        trueTypeFonts.put("Courier-Bold".toLowerCase(), "Courier-Bold");
        trueTypeFonts.put("Courier-Oblique".toLowerCase(), "Courier-Oblique");
        trueTypeFonts.put("Courier-BoldOblique".toLowerCase(), "Courier-BoldOblique");
        trueTypeFonts.put("Helvetica".toLowerCase(), "Helvetica");
        trueTypeFonts.put("Helvetica-Bold".toLowerCase(), "Helvetica-Bold");
        trueTypeFonts.put("Helvetica-Oblique".toLowerCase(), "Helvetica-Oblique");
        trueTypeFonts.put("Helvetica-BoldOblique".toLowerCase(), "Helvetica-BoldOblique");
        trueTypeFonts.put("Symbol".toLowerCase(), "Symbol");
        trueTypeFonts.put("Times-Roman".toLowerCase(), "Times-Roman");
        trueTypeFonts.put("Times-Bold".toLowerCase(), "Times-Bold");
        trueTypeFonts.put("Times-Italic".toLowerCase(), "Times-Italic");
        trueTypeFonts.put("Times-BoldItalic".toLowerCase(), "Times-BoldItalic");
        trueTypeFonts.put("ZapfDingbats".toLowerCase(), "ZapfDingbats");
        ArrayList tmp = new ArrayList();
        tmp.add("Courier");
        tmp.add("Courier-Bold");
        tmp.add("Courier-Oblique");
        tmp.add("Courier-BoldOblique");
        fontFamilies.put("Courier".toLowerCase(), tmp);
        tmp = new ArrayList();
        tmp.add("Helvetica");
        tmp.add("Helvetica-Bold");
        tmp.add("Helvetica-Oblique");
        tmp.add("Helvetica-BoldOblique");
        fontFamilies.put("Helvetica".toLowerCase(), tmp);
        tmp = new ArrayList();
        tmp.add("Symbol");
        fontFamilies.put("Symbol".toLowerCase(), tmp);
        tmp = new ArrayList();
        tmp.add("Times-Roman");
        tmp.add("Times-Bold");
        tmp.add("Times-Italic");
        tmp.add("Times-BoldItalic");
        fontFamilies.put("Times".toLowerCase(), tmp);
        fontFamilies.put("Times-Roman".toLowerCase(), tmp);
        tmp = new ArrayList();
        tmp.add("ZapfDingbats");
        fontFamilies.put("ZapfDingbats".toLowerCase(), tmp);
    }

    public Font getFont(String fontname, String encoding, boolean embedded, float size, int style, BaseColor color)
    {
        return getFont(fontname, encoding, embedded, size, style, color, true);
    }

    public Font getFont(String fontname, String encoding, boolean embedded, float size, int style, BaseColor color, boolean cached)
    {
        BaseFont basefont;
        if(fontname == null)
            return new Font(Font.FontFamily.UNDEFINED, size, style, color);
        String lowercasefontname = fontname.toLowerCase();
        ArrayList tmp = (ArrayList)fontFamilies.get(lowercasefontname);
        if(tmp != null)
            synchronized(tmp)
            {
                int s = style != -1 ? style : 0;
                int fs = 0;
                boolean found = false;
                Iterator i$ = tmp.iterator();
                do
                {
                    if(!i$.hasNext())
                        break;
                    String f = (String)i$.next();
                    String lcf = f.toLowerCase();
                    fs = 0;
                    if(lcf.indexOf("bold") != -1)
                        fs |= 1;
                    if(lcf.indexOf("italic") != -1 || lcf.indexOf("oblique") != -1)
                        fs |= 2;
                    if((s & 3) != fs)
                        continue;
                    fontname = f;
                    found = true;
                    break;
                } while(true);
                if(style != -1 && found)
                    style &= ~fs;
            }
        basefont = null;
        try
        {
            basefont = BaseFont.createFont(fontname, encoding, embedded, cached, null, null, true);
        }
        catch(DocumentException de) { }
        if(basefont != null)
            break MISSING_BLOCK_LABEL_338;
        fontname = (String)trueTypeFonts.get(fontname.toLowerCase());
        if(fontname == null)
            return new Font(Font.FontFamily.UNDEFINED, size, style, color);
        try
        {
            basefont = BaseFont.createFont(fontname, encoding, embedded, cached, null, null);
        }
        catch(DocumentException de)
        {
            throw new ExceptionConverter(de);
        }
        catch(IOException ioe)
        {
            return new Font(Font.FontFamily.UNDEFINED, size, style, color);
        }
        catch(NullPointerException npe)
        {
            return new Font(Font.FontFamily.UNDEFINED, size, style, color);
        }
        return new Font(basefont, size, style, color);
    }

    public Font getFont(String fontname, String encoding, boolean embedded, float size, int style)
    {
        return getFont(fontname, encoding, embedded, size, style, null);
    }

    public Font getFont(String fontname, String encoding, boolean embedded, float size)
    {
        return getFont(fontname, encoding, embedded, size, -1, null);
    }

    public Font getFont(String fontname, String encoding, boolean embedded)
    {
        return getFont(fontname, encoding, embedded, -1F, -1, null);
    }

    public Font getFont(String fontname, String encoding, float size, int style, BaseColor color)
    {
        return getFont(fontname, encoding, defaultEmbedding, size, style, color);
    }

    public Font getFont(String fontname, String encoding, float size, int style)
    {
        return getFont(fontname, encoding, defaultEmbedding, size, style, null);
    }

    public Font getFont(String fontname, String encoding, float size)
    {
        return getFont(fontname, encoding, defaultEmbedding, size, -1, null);
    }

    public Font getFont(String fontname, float size, BaseColor color)
    {
        return getFont(fontname, defaultEncoding, defaultEmbedding, size, -1, color);
    }

    public Font getFont(String fontname, String encoding)
    {
        return getFont(fontname, encoding, defaultEmbedding, -1F, -1, null);
    }

    public Font getFont(String fontname, float size, int style, BaseColor color)
    {
        return getFont(fontname, defaultEncoding, defaultEmbedding, size, style, color);
    }

    public Font getFont(String fontname, float size, int style)
    {
        return getFont(fontname, defaultEncoding, defaultEmbedding, size, style, null);
    }

    public Font getFont(String fontname, float size)
    {
        return getFont(fontname, defaultEncoding, defaultEmbedding, size, -1, null);
    }

    public Font getFont(String fontname)
    {
        return getFont(fontname, defaultEncoding, defaultEmbedding, -1F, -1, null);
    }

    public void registerFamily(String familyName, String fullName, String path)
    {
        if(path != null)
            trueTypeFonts.put(fullName, path);
        ArrayList tmp;
        synchronized(fontFamilies)
        {
            tmp = (ArrayList)fontFamilies.get(familyName);
            if(tmp == null)
            {
                tmp = new ArrayList();
                fontFamilies.put(familyName, tmp);
            }
        }
        synchronized(tmp)
        {
            if(!tmp.contains(fullName))
            {
                int fullNameLength = fullName.length();
                boolean inserted = false;
                int j = 0;
                do
                {
                    if(j >= tmp.size())
                        break;
                    if(((String)tmp.get(j)).length() >= fullNameLength)
                    {
                        tmp.add(j, fullName);
                        inserted = true;
                        break;
                    }
                    j++;
                } while(true);
                if(!inserted)
                    tmp.add(fullName);
            }
        }
    }

    public void register(String path)
    {
        register(path, null);
    }

    public void register(String path, String alias)
    {
        try
        {
            if(path.toLowerCase().endsWith(".ttf") || path.toLowerCase().endsWith(".otf") || path.toLowerCase().indexOf(".ttc,") > 0)
            {
                Object allNames[] = BaseFont.getAllFontNames(path, "Cp1252", null);
                trueTypeFonts.put(((String)allNames[0]).toLowerCase(), path);
                if(alias != null)
                    trueTypeFonts.put(alias.toLowerCase(), path);
                String names[][] = (String[][])(String[][])allNames[2];
                String arr$[][] = names;
                int len$ = arr$.length;
                for(int i$ = 0; i$ < len$; i$++)
                {
                    String name[] = arr$[i$];
                    trueTypeFonts.put(name[3].toLowerCase(), path);
                }

                String fullName = null;
                String familyName = null;
                names = (String[][])(String[][])allNames[1];
label0:
                for(int k = 0; k < TTFamilyOrder.length; k += 3)
                {
                    String arr$[][] = names;
                    int len$ = arr$.length;
                    int i$ = 0;
                    do
                    {
                        if(i$ >= len$)
                            continue label0;
                        String name[] = arr$[i$];
                        if(TTFamilyOrder[k].equals(name[0]) && TTFamilyOrder[k + 1].equals(name[1]) && TTFamilyOrder[k + 2].equals(name[2]))
                        {
                            familyName = name[3].toLowerCase();
                            k = TTFamilyOrder.length;
                            continue label0;
                        }
                        i$++;
                    } while(true);
                }

                if(familyName != null)
                {
                    String lastName = "";
                    names = (String[][])(String[][])allNames[2];
                    String arr$[][] = names;
                    int len$ = arr$.length;
                    for(int i$ = 0; i$ < len$; i$++)
                    {
                        String name[] = arr$[i$];
                        for(int k = 0; k < TTFamilyOrder.length; k += 3)
                        {
                            if(!TTFamilyOrder[k].equals(name[0]) || !TTFamilyOrder[k + 1].equals(name[1]) || !TTFamilyOrder[k + 2].equals(name[2]))
                                continue;
                            fullName = name[3];
                            if(fullName.equals(lastName))
                                continue;
                            lastName = fullName;
                            registerFamily(familyName, fullName, null);
                            break;
                        }

                    }

                }
            } else
            if(path.toLowerCase().endsWith(".ttc"))
            {
                if(alias != null)
                    LOGGER.error("You can't define an alias for a true type collection.");
                String names[] = BaseFont.enumerateTTCNames(path);
                for(int i = 0; i < names.length; i++)
                    register((new StringBuilder()).append(path).append(",").append(i).toString());

            } else
            if(path.toLowerCase().endsWith(".afm") || path.toLowerCase().endsWith(".pfm"))
            {
                BaseFont bf = BaseFont.createFont(path, "Cp1252", false);
                String fullName = bf.getFullFontName()[0][3].toLowerCase();
                String familyName = bf.getFamilyFontName()[0][3].toLowerCase();
                String psName = bf.getPostscriptFontName().toLowerCase();
                registerFamily(familyName, fullName, null);
                trueTypeFonts.put(psName, path);
                trueTypeFonts.put(fullName, path);
            }
            if(LOGGER.isLogging(Level.TRACE))
                LOGGER.trace(String.format("Registered %s", new Object[] {
                    path
                }));
        }
        catch(DocumentException de)
        {
            throw new ExceptionConverter(de);
        }
        catch(IOException ioe)
        {
            throw new ExceptionConverter(ioe);
        }
    }

    public int registerDirectory(String dir)
    {
        return registerDirectory(dir, false);
    }

    public int registerDirectory(String dir, boolean scanSubdirectories)
    {
        int count;
        if(LOGGER.isLogging(Level.DEBUG))
            LOGGER.debug(String.format("Registering directory %s, looking for fonts", new Object[] {
                dir
            }));
        count = 0;
        File file;
        file = new File(dir);
        if(!file.exists() || !file.isDirectory())
            return 0;
        String files[];
        files = file.list();
        if(files == null)
            return 0;
        try
        {
            for(int k = 0; k < files.length; k++)
                try
                {
                    file = new File(dir, files[k]);
                    if(file.isDirectory())
                    {
                        if(scanSubdirectories)
                            count += registerDirectory(file.getAbsolutePath(), true);
                    } else
                    {
                        String name = file.getPath();
                        String suffix = name.length() >= 4 ? name.substring(name.length() - 4).toLowerCase() : null;
                        if(".afm".equals(suffix) || ".pfm".equals(suffix))
                        {
                            File pfb = new File((new StringBuilder()).append(name.substring(0, name.length() - 4)).append(".pfb").toString());
                            if(pfb.exists())
                            {
                                register(name, null);
                                count++;
                            }
                        } else
                        if(".ttf".equals(suffix) || ".otf".equals(suffix) || ".ttc".equals(suffix))
                        {
                            register(name, null);
                            count++;
                        }
                    }
                }
                catch(Exception e) { }

        }
        catch(Exception e) { }
        return count;
    }

    public int registerDirectories()
    {
        int count = 0;
        String windir = System.getenv("windir");
        String fileseparator = System.getProperty("file.separator");
        if(windir != null && fileseparator != null)
            count += registerDirectory((new StringBuilder()).append(windir).append(fileseparator).append("fonts").toString());
        count += registerDirectory("/usr/share/X11/fonts", true);
        count += registerDirectory("/usr/X/lib/X11/fonts", true);
        count += registerDirectory("/usr/openwin/lib/X11/fonts", true);
        count += registerDirectory("/usr/share/fonts", true);
        count += registerDirectory("/usr/X11R6/lib/X11/fonts", true);
        count += registerDirectory("/Library/Fonts");
        count += registerDirectory("/System/Library/Fonts");
        return count;
    }

    public Set getRegisteredFonts()
    {
        return trueTypeFonts.keySet();
    }

    public Set getRegisteredFamilies()
    {
        return fontFamilies.keySet();
    }

    public boolean isRegistered(String fontname)
    {
        return trueTypeFonts.containsKey(fontname.toLowerCase());
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(co/com/pdf/text/FontFactoryImp);
    private final Hashtable trueTypeFonts = new Hashtable();
    private static String TTFamilyOrder[] = {
        "3", "1", "1033", "3", "0", "1033", "1", "0", "0", "0", 
        "3", "0"
    };
    private final Hashtable fontFamilies = new Hashtable();
    public String defaultEncoding;
    public boolean defaultEmbedding;

}