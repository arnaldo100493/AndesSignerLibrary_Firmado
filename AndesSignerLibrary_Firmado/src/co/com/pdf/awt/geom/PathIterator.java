// Decompiled by DJ v3.12.12.98 Copyright 2014 Atanas Neshkov  Date: 17/11/2017 7:06:59 p. m.
// Home Page: http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   PathIterator.java
package co.com.pdf.awt.geom;

public interface PathIterator {

    public static final int WIND_EVEN_ODD = 0;

    public static final int WIND_NON_ZERO = 1;

    public static final int SEG_MOVETO = 0;

    public static final int SEG_LINETO = 1;

    public static final int SEG_QUADTO = 2;

    public static final int SEG_CUBICTO = 3;

    public static final int SEG_CLOSE = 4;

    public int getWindingRule();

    public boolean isDone();

    public void next();

    public int currentSegment(float[] paramArrayOffloat);

    public int currentSegment(double[] paramArrayOfdouble);
}
