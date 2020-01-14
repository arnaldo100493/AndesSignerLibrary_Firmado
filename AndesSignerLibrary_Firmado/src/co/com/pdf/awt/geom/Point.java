// Decompiled by DJ v3.12.12.98 Copyright 2014 Atanas Neshkov  Date: 17/11/2017 7:06:59 p. m.
// Home Page: http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   Point.java
package co.com.pdf.awt.geom;

import java.io.Serializable;

// Referenced classes of package co.com.pdf.awt.geom:
//            Point2D
public class Point extends Point2D implements Serializable {

    private static final long serialVersionUID = -5276940640259749850L;

    public double x;

    public double y;

    public Point() {
        setLocation(0, 0);
    }

    public Point(int x, int y) {
        setLocation(x, y);
    }

    public Point(double x, double y) {
        setLocation(x, y);
    }

    public Point(Point p) {
        setLocation(p.x, p.y);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof Point) {
            Point p = (Point) obj;
            return (this.x == p.x && this.y == p.y);
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + (int) (Double.doubleToLongBits(this.x) ^ (Double.doubleToLongBits(this.x) >>> 32));
        hash = 89 * hash + (int) (Double.doubleToLongBits(this.y) ^ (Double.doubleToLongBits(this.y) >>> 32));
        return hash;
    }

    @Override
    public String toString() {
        return getClass().getName() + "[x=" + this.x + ",y=" + this.y + "]";
    }

    @Override
    public double getX() {
        return this.x;
    }

    @Override
    public double getY() {
        return this.y;
    }

    public Point getLocation() {
        return new Point(this.x, this.y);
    }

    public void setLocation(Point p) {
        setLocation(p.x, p.y);
    }

    public void setLocation(int x, int y) {
        setLocation(x, y);
    }

    @Override
    public void setLocation(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void move(int x, int y) {
        move(x, y);
    }

    public void move(double x, double y) {
        setLocation(x, y);
    }

    public void translate(int dx, int dy) {
        translate(this.x, this.y);
    }

    public void translate(double dx, double dy) {
        this.x += dx;
        this.y += dy;
    }
}
