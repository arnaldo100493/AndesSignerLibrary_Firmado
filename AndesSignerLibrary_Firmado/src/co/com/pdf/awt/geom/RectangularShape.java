// Decompiled by DJ v3.12.12.98 Copyright 2014 Atanas Neshkov  Date: 17/11/2017 7:07:00 p. m.
// Home Page: http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   RectangularShape.java
package co.com.pdf.awt.geom;

// Referenced classes of package co.com.pdf.awt.geom:
//            Rectangle, FlatteningPathIterator, Shape, Rectangle2D, 
//            Point2D, Dimension2D, AffineTransform, PathIterator
public abstract class RectangularShape implements Shape, Cloneable {

    public RectangularShape() {

    }

    public abstract double getX();

    public abstract double getY();

    public abstract double getWidth();

    public abstract double getHeight();

    public abstract boolean isEmpty();

    public abstract void setFrame(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4);

    public double getMinX() {
        return getX();
    }

    public double getMinY() {
        return getY();
    }

    public double getMaxX() {
        return getX() + getWidth();
    }

    public double getMaxY() {
        return getY() + getHeight();
    }

    public double getCenterX() {
        return getX() + getWidth() / 2.0D;
    }

    public double getCenterY() {
        return getY() + getHeight() / 2.0D;
    }

    public Rectangle2D getFrame() {
        return new Rectangle2D.Double(getX(), getY(), getWidth(), getHeight());
    }

    public void setFrame(Point2D loc, Dimension2D size) {
        setFrame(loc.getX(), loc.getY(), size.getWidth(), size.getHeight());
    }

    public void setFrame(Rectangle2D r) {
        setFrame(r.getX(), r.getY(), r.getWidth(), r.getHeight());
    }

    public void setFrameFromDiagonal(double x1, double y1, double x2, double y2) {
        double rx, ry, rw, rh;
        if (x1 < x2) {
            rx = x1;
            rw = x2 - x1;
        } else {
            rx = x2;
            rw = x1 - x2;
        }
        if (y1 < y2) {
            ry = y1;
            rh = y2 - y1;
        } else {
            ry = y2;
            rh = y1 - y2;
        }
        setFrame(rx, ry, rw, rh);
    }

    public void setFrameFromDiagonal(Point2D p1, Point2D p2) {
        setFrameFromDiagonal(p1.getX(), p1.getY(), p2.getX(), p2.getY());
    }

    public void setFrameFromCenter(double centerX, double centerY, double cornerX, double cornerY) {
        double width = Math.abs(cornerX - centerX);
        double height = Math.abs(cornerY - centerY);
        setFrame(centerX - width, centerY - height, width * 2.0D, height * 2.0D);
    }

    public void setFrameFromCenter(Point2D center, Point2D corner) {
        setFrameFromCenter(center.getX(), center.getY(), corner.getX(), corner.getY());
    }

    @Override
    public boolean contains(Point2D point) {
        return contains(point.getX(), point.getY());
    }

    @Override
    public boolean intersects(Rectangle2D rect) {
        return intersects(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());
    }

    @Override
    public boolean contains(Rectangle2D rect) {
        return contains(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());
    }

    @Override
    public Rectangle getBounds() {
        int x1 = (int) Math.floor(getMinX());
        int y1 = (int) Math.floor(getMinY());
        int x2 = (int) Math.ceil(getMaxX());
        int y2 = (int) Math.ceil(getMaxY());
        return new Rectangle(x1, y1, (x2 - x1), (y2 - y1));
    }

    @Override
    public PathIterator getPathIterator(AffineTransform t, double flatness) {
        return new FlatteningPathIterator(getPathIterator(t), flatness);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            throw new InternalError();
        }
    }
}
