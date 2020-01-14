// Decompiled by DJ v3.12.12.98 Copyright 2014 Atanas Neshkov  Date: 17/11/2017 7:07:00 p. m.
// Home Page: http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   Shape.java
package co.com.pdf.awt.geom;

// Referenced classes of package co.com.pdf.awt.geom:
//            Point2D, Rectangle2D, Rectangle, AffineTransform, 
//            PathIterator
public interface Shape {

    public boolean contains(double paramDouble1, double paramDouble2);

    public boolean contains(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4);

    public boolean contains(Point2D paramPoint2D);

    public boolean contains(Rectangle2D paramRectangle2D);

    public Rectangle getBounds();

    public Rectangle2D getBounds2D();

    public PathIterator getPathIterator(AffineTransform paramAffineTransform);

    public PathIterator getPathIterator(AffineTransform paramAffineTransform, double paramDouble);

    boolean intersects(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4);

    boolean intersects(Rectangle2D paramRectangle2D);
}
