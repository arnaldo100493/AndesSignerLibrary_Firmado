// Decompiled by DJ v3.12.12.98 Copyright 2014 Atanas Neshkov  Date: 17/11/2017 7:07:04 p. m.
// Home Page: http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   TabStop.java

package co.com.pdf.text;

import co.com.pdf.text.pdf.draw.DrawInterface;

public class TabStop
{
    public static final class Alignment extends Enum
    {

        public static Alignment[] values()
        {
            return (Alignment[])$VALUES.clone();
        }

        public static Alignment valueOf(String name)
        {
            return (Alignment)Enum.valueOf(co/com/pdf/text/TabStop$Alignment, name);
        }

        public static final Alignment LEFT;
        public static final Alignment RIGHT;
        public static final Alignment CENTER;
        public static final Alignment ANCHOR;
        private static final Alignment $VALUES[];

        static 
        {
            LEFT = new Alignment("LEFT", 0);
            RIGHT = new Alignment("RIGHT", 1);
            CENTER = new Alignment("CENTER", 2);
            ANCHOR = new Alignment("ANCHOR", 3);
            $VALUES = (new Alignment[] {
                LEFT, RIGHT, CENTER, ANCHOR
            });
        }

        private Alignment(String s, int i)
        {
            super(s, i);
        }
    }


    public static TabStop newInstance(float currentPosition, float tabInterval)
    {
        currentPosition = (float)Math.round(currentPosition * 1000F) / 1000F;
        tabInterval = (float)Math.round(tabInterval * 1000F) / 1000F;
        TabStop tabStop = new TabStop((currentPosition + tabInterval) - currentPosition % tabInterval);
        return tabStop;
    }

    public TabStop(float position)
    {
        this(position, Alignment.LEFT);
    }

    public TabStop(float position, DrawInterface leader)
    {
        this(position, leader, Alignment.LEFT);
    }

    public TabStop(float position, Alignment alignment)
    {
        this(position, ((DrawInterface) (null)), alignment);
    }

    public TabStop(float position, Alignment alignment, char anchorChar)
    {
        this(position, null, alignment, anchorChar);
    }

    public TabStop(float position, DrawInterface leader, Alignment alignment)
    {
        this(position, leader, alignment, '.');
    }

    public TabStop(float position, DrawInterface leader, Alignment alignment, char anchorChar)
    {
        this.alignment = Alignment.LEFT;
        this.anchorChar = '.';
        this.position = position;
        this.leader = leader;
        this.alignment = alignment;
        this.anchorChar = anchorChar;
    }

    public TabStop(TabStop tabStop)
    {
        this(tabStop.getPosition(), tabStop.getLeader(), tabStop.getAlignment(), tabStop.getAnchorChar());
    }

    public float getPosition()
    {
        return position;
    }

    public void setPosition(float position)
    {
        this.position = position;
    }

    public Alignment getAlignment()
    {
        return alignment;
    }

    public void setAlignment(Alignment alignment)
    {
        this.alignment = alignment;
    }

    public DrawInterface getLeader()
    {
        return leader;
    }

    public void setLeader(DrawInterface leader)
    {
        this.leader = leader;
    }

    public char getAnchorChar()
    {
        return anchorChar;
    }

    public void setAnchorChar(char anchorChar)
    {
        this.anchorChar = anchorChar;
    }

    public float getPosition(float tabPosition, float currentPosition, float anchorPosition)
    {
        float newPosition = position;
        float textWidth = currentPosition - tabPosition;
        static class _cls1
        {

            static final int $SwitchMap$co$com$pdf$text$TabStop$Alignment[];

            static 
            {
                $SwitchMap$co$com$pdf$text$TabStop$Alignment = new int[Alignment.values().length];
                try
                {
                    $SwitchMap$co$com$pdf$text$TabStop$Alignment[Alignment.RIGHT.ordinal()] = 1;
                }
                catch(NoSuchFieldError ex) { }
                try
                {
                    $SwitchMap$co$com$pdf$text$TabStop$Alignment[Alignment.CENTER.ordinal()] = 2;
                }
                catch(NoSuchFieldError ex) { }
                try
                {
                    $SwitchMap$co$com$pdf$text$TabStop$Alignment[Alignment.ANCHOR.ordinal()] = 3;
                }
                catch(NoSuchFieldError ex) { }
            }
        }

        switch(_cls1..SwitchMap.co.com.pdf.text.TabStop.Alignment[alignment.ordinal()])
        {
        default:
            break;

        case 1: // '\001'
            if(tabPosition + textWidth < position)
                newPosition = position - textWidth;
            else
                newPosition = tabPosition;
            break;

        case 2: // '\002'
            if(tabPosition + textWidth / 2.0F < position)
                newPosition = position - textWidth / 2.0F;
            else
                newPosition = tabPosition;
            break;

        case 3: // '\003'
            if(!Float.isNaN(anchorPosition))
            {
                if(anchorPosition < position)
                    newPosition = position - (anchorPosition - tabPosition);
                else
                    newPosition = tabPosition;
                break;
            }
            if(tabPosition + textWidth < position)
                newPosition = position - textWidth;
            else
                newPosition = tabPosition;
            break;
        }
        return newPosition;
    }

    protected float position;
    protected Alignment alignment;
    protected DrawInterface leader;
    protected char anchorChar;
}