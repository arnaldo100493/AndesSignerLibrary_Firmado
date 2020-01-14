// Decompiled by DJ v3.12.12.98 Copyright 2014 Atanas Neshkov  Date: 17/11/2017 7:07:04 p. m.
// Home Page: http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   Rectangle.java

package co.com.pdf.text;

import co.com.pdf.text.pdf.GrayColor;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package co.com.pdf.text:
//            DocumentException, Element, ElementListener, BaseColor

public class Rectangle
    implements Element
{

    public Rectangle(float llx, float lly, float urx, float ury)
    {
        rotation = 0;
        backgroundColor = null;
        border = -1;
        useVariableBorders = false;
        borderWidth = -1F;
        borderWidthLeft = -1F;
        borderWidthRight = -1F;
        borderWidthTop = -1F;
        borderWidthBottom = -1F;
        borderColor = null;
        borderColorLeft = null;
        borderColorRight = null;
        borderColorTop = null;
        borderColorBottom = null;
        this.llx = llx;
        this.lly = lly;
        this.urx = urx;
        this.ury = ury;
    }

    public Rectangle(float llx, float lly, float urx, float ury, int rotation)
    {
        this(llx, lly, urx, ury);
        setRotation(rotation);
    }

    public Rectangle(float urx, float ury)
    {
        this(0.0F, 0.0F, urx, ury);
    }

    public Rectangle(float urx, float ury, int rotation)
    {
        this(0.0F, 0.0F, urx, ury, rotation);
    }

    public Rectangle(Rectangle rect)
    {
        this(rect.llx, rect.lly, rect.urx, rect.ury);
        cloneNonPositionParameters(rect);
    }

    public Rectangle(co.com.pdf.awt.geom.Rectangle rect)
    {
        this((float)rect.getX(), (float)rect.getY(), (float)(rect.getX() + rect.getWidth()), (float)(rect.getY() + rect.getHeight()));
    }

    public boolean process(ElementListener listener)
    {
        try
        {
            return listener.add(this);
        }
        catch(DocumentException de)
        {
            return false;
        }
    }

    public int type()
    {
        return 30;
    }

    public List getChunks()
    {
        return new ArrayList();
    }

    public boolean isContent()
    {
        return true;
    }

    public boolean isNestable()
    {
        return false;
    }

    public void setLeft(float llx)
    {
        this.llx = llx;
    }

    public float getLeft()
    {
        return llx;
    }

    public float getLeft(float margin)
    {
        return llx + margin;
    }

    public void setRight(float urx)
    {
        this.urx = urx;
    }

    public float getRight()
    {
        return urx;
    }

    public float getRight(float margin)
    {
        return urx - margin;
    }

    public float getWidth()
    {
        return urx - llx;
    }

    public void setTop(float ury)
    {
        this.ury = ury;
    }

    public float getTop()
    {
        return ury;
    }

    public float getTop(float margin)
    {
        return ury - margin;
    }

    public void setBottom(float lly)
    {
        this.lly = lly;
    }

    public float getBottom()
    {
        return lly;
    }

    public float getBottom(float margin)
    {
        return lly + margin;
    }

    public float getHeight()
    {
        return ury - lly;
    }

    public void normalize()
    {
        if(llx > urx)
        {
            float a = llx;
            llx = urx;
            urx = a;
        }
        if(lly > ury)
        {
            float a = lly;
            lly = ury;
            ury = a;
        }
    }

    public int getRotation()
    {
        return rotation;
    }

    public void setRotation(int rotation)
    {
        this.rotation = rotation % 360;
        switch(this.rotation)
        {
        default:
            this.rotation = 0;
            // fall through

        case 90: // 'Z'
        case 180: 
        case 270: 
            return;
        }
    }

    public Rectangle rotate()
    {
        Rectangle rect = new Rectangle(lly, llx, ury, urx);
        rect.setRotation(rotation + 90);
        return rect;
    }

    public BaseColor getBackgroundColor()
    {
        return backgroundColor;
    }

    public void setBackgroundColor(BaseColor backgroundColor)
    {
        this.backgroundColor = backgroundColor;
    }

    public float getGrayFill()
    {
        if(backgroundColor instanceof GrayColor)
            return ((GrayColor)backgroundColor).getGray();
        else
            return 0.0F;
    }

    public void setGrayFill(float value)
    {
        backgroundColor = new GrayColor(value);
    }

    public int getBorder()
    {
        return border;
    }

    public boolean hasBorders()
    {
        switch(border)
        {
        case -1: 
        case 0: // '\0'
            return false;
        }
        return borderWidth > 0.0F || borderWidthLeft > 0.0F || borderWidthRight > 0.0F || borderWidthTop > 0.0F || borderWidthBottom > 0.0F;
    }

    public boolean hasBorder(int type)
    {
        if(border == -1)
            return false;
        else
            return (border & type) == type;
    }

    public void setBorder(int border)
    {
        this.border = border;
    }

    public boolean isUseVariableBorders()
    {
        return useVariableBorders;
    }

    public void setUseVariableBorders(boolean useVariableBorders)
    {
        this.useVariableBorders = useVariableBorders;
    }

    public void enableBorderSide(int side)
    {
        if(border == -1)
            border = 0;
        border |= side;
    }

    public void disableBorderSide(int side)
    {
        if(border == -1)
            border = 0;
        border &= ~side;
    }

    public float getBorderWidth()
    {
        return borderWidth;
    }

    public void setBorderWidth(float borderWidth)
    {
        this.borderWidth = borderWidth;
    }

    private float getVariableBorderWidth(float variableWidthValue, int side)
    {
        if((border & side) != 0)
            return variableWidthValue == -1F ? borderWidth : variableWidthValue;
        else
            return 0.0F;
    }

    private void updateBorderBasedOnWidth(float width, int side)
    {
        useVariableBorders = true;
        if(width > 0.0F)
            enableBorderSide(side);
        else
            disableBorderSide(side);
    }

    public float getBorderWidthLeft()
    {
        return getVariableBorderWidth(borderWidthLeft, 4);
    }

    public void setBorderWidthLeft(float borderWidthLeft)
    {
        this.borderWidthLeft = borderWidthLeft;
        updateBorderBasedOnWidth(borderWidthLeft, 4);
    }

    public float getBorderWidthRight()
    {
        return getVariableBorderWidth(borderWidthRight, 8);
    }

    public void setBorderWidthRight(float borderWidthRight)
    {
        this.borderWidthRight = borderWidthRight;
        updateBorderBasedOnWidth(borderWidthRight, 8);
    }

    public float getBorderWidthTop()
    {
        return getVariableBorderWidth(borderWidthTop, 1);
    }

    public void setBorderWidthTop(float borderWidthTop)
    {
        this.borderWidthTop = borderWidthTop;
        updateBorderBasedOnWidth(borderWidthTop, 1);
    }

    public float getBorderWidthBottom()
    {
        return getVariableBorderWidth(borderWidthBottom, 2);
    }

    public void setBorderWidthBottom(float borderWidthBottom)
    {
        this.borderWidthBottom = borderWidthBottom;
        updateBorderBasedOnWidth(borderWidthBottom, 2);
    }

    public BaseColor getBorderColor()
    {
        return borderColor;
    }

    public void setBorderColor(BaseColor borderColor)
    {
        this.borderColor = borderColor;
    }

    public BaseColor getBorderColorLeft()
    {
        if(borderColorLeft == null)
            return borderColor;
        else
            return borderColorLeft;
    }

    public void setBorderColorLeft(BaseColor borderColorLeft)
    {
        this.borderColorLeft = borderColorLeft;
    }

    public BaseColor getBorderColorRight()
    {
        if(borderColorRight == null)
            return borderColor;
        else
            return borderColorRight;
    }

    public void setBorderColorRight(BaseColor borderColorRight)
    {
        this.borderColorRight = borderColorRight;
    }

    public BaseColor getBorderColorTop()
    {
        if(borderColorTop == null)
            return borderColor;
        else
            return borderColorTop;
    }

    public void setBorderColorTop(BaseColor borderColorTop)
    {
        this.borderColorTop = borderColorTop;
    }

    public BaseColor getBorderColorBottom()
    {
        if(borderColorBottom == null)
            return borderColor;
        else
            return borderColorBottom;
    }

    public void setBorderColorBottom(BaseColor borderColorBottom)
    {
        this.borderColorBottom = borderColorBottom;
    }

    public Rectangle rectangle(float top, float bottom)
    {
        Rectangle tmp = new Rectangle(this);
        if(getTop() > top)
        {
            tmp.setTop(top);
            tmp.disableBorderSide(1);
        }
        if(getBottom() < bottom)
        {
            tmp.setBottom(bottom);
            tmp.disableBorderSide(2);
        }
        return tmp;
    }

    public void cloneNonPositionParameters(Rectangle rect)
    {
        rotation = rect.rotation;
        backgroundColor = rect.backgroundColor;
        border = rect.border;
        useVariableBorders = rect.useVariableBorders;
        borderWidth = rect.borderWidth;
        borderWidthLeft = rect.borderWidthLeft;
        borderWidthRight = rect.borderWidthRight;
        borderWidthTop = rect.borderWidthTop;
        borderWidthBottom = rect.borderWidthBottom;
        borderColor = rect.borderColor;
        borderColorLeft = rect.borderColorLeft;
        borderColorRight = rect.borderColorRight;
        borderColorTop = rect.borderColorTop;
        borderColorBottom = rect.borderColorBottom;
    }

    public void softCloneNonPositionParameters(Rectangle rect)
    {
        if(rect.rotation != 0)
            rotation = rect.rotation;
        if(rect.backgroundColor != null)
            backgroundColor = rect.backgroundColor;
        if(rect.border != -1)
            border = rect.border;
        if(useVariableBorders)
            useVariableBorders = rect.useVariableBorders;
        if(rect.borderWidth != -1F)
            borderWidth = rect.borderWidth;
        if(rect.borderWidthLeft != -1F)
            borderWidthLeft = rect.borderWidthLeft;
        if(rect.borderWidthRight != -1F)
            borderWidthRight = rect.borderWidthRight;
        if(rect.borderWidthTop != -1F)
            borderWidthTop = rect.borderWidthTop;
        if(rect.borderWidthBottom != -1F)
            borderWidthBottom = rect.borderWidthBottom;
        if(rect.borderColor != null)
            borderColor = rect.borderColor;
        if(rect.borderColorLeft != null)
            borderColorLeft = rect.borderColorLeft;
        if(rect.borderColorRight != null)
            borderColorRight = rect.borderColorRight;
        if(rect.borderColorTop != null)
            borderColorTop = rect.borderColorTop;
        if(rect.borderColorBottom != null)
            borderColorBottom = rect.borderColorBottom;
    }

    public String toString()
    {
        StringBuffer buf = new StringBuffer("Rectangle: ");
        buf.append(getWidth());
        buf.append('x');
        buf.append(getHeight());
        buf.append(" (rot: ");
        buf.append(rotation);
        buf.append(" degrees)");
        return buf.toString();
    }

    public boolean equals(Object obj)
    {
        if(obj instanceof Rectangle)
        {
            Rectangle other = (Rectangle)obj;
            return other.llx == llx && other.lly == lly && other.urx == urx && other.ury == ury && other.rotation == rotation;
        } else
        {
            return false;
        }
    }

    public static final int UNDEFINED = -1;
    public static final int TOP = 1;
    public static final int BOTTOM = 2;
    public static final int LEFT = 4;
    public static final int RIGHT = 8;
    public static final int NO_BORDER = 0;
    public static final int BOX = 15;
    protected float llx;
    protected float lly;
    protected float urx;
    protected float ury;
    protected int rotation;
    protected BaseColor backgroundColor;
    protected int border;
    protected boolean useVariableBorders;
    protected float borderWidth;
    protected float borderWidthLeft;
    protected float borderWidthRight;
    protected float borderWidthTop;
    protected float borderWidthBottom;
    protected BaseColor borderColor;
    protected BaseColor borderColorLeft;
    protected BaseColor borderColorRight;
    protected BaseColor borderColorTop;
    protected BaseColor borderColorBottom;
}