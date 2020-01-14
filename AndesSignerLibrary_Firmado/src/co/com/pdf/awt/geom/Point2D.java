// Decompiled by DJ v3.12.12.98 Copyright 2014 Atanas Neshkov  Date: 17/11/2017 7:07:00 p. m.
// Home Page: http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   Point2D.java
package co.com.pdf.awt.geom;

import co.com.pdf.awt.geom.misc.HashCode;

public abstract class Point2D implements Cloneable {

    public Point2D() {

    }

    public abstract double getX();

    public abstract double getY();

    public abstract void setLocation(double paramDouble1, double paramDouble2);

    public static class Float extends Point2D {

        public float x;

        public float y;

        public Float() {
        }

        public Float(float x, float y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public double getX() {
            return this.x;
        }

        @Override
        public double getY() {
            return this.y;
        }

        public void setLocation(float x, float y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public void setLocation(double x, double y) {
            this.x = (float) x;
            this.y = (float) y;
        }

        @Override
        public String toString() {
            return getClass().getName() + "[x=" + this.x + ",y=" + this.y + "]";
        }
    }

    public static class Double extends Point2D {

        public double x;

        public double y;

        public Double() {
        }

        public Double(double x, double y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public double getX() {
            return this.x;
        }

        @Override
        public double getY() {
            return this.y;
        }

        @Override
        public void setLocation(double x, double y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return getClass().getName() + "[x=" + this.x + ",y=" + this.y + "]";
        }
    }

    public void setLocation(Point2D p) {
        setLocation(p.getX(), p.getY());
    }

    public static double distanceSq(double x1, double y1, double x2, double y2) {
        x2 -= x1;
        y2 -= y1;
        return x2 * x2 + y2 * y2;
    }

    public double distanceSq(double px, double py) {
        return distanceSq(getX(), getY(), px, py);
    }

    public double distanceSq(Point2D p) {
        return distanceSq(getX(), getY(), p.getX(), p.getY());
    }

    public static double distance(double x1, double y1, double x2, double y2) {
        return Math.sqrt(distanceSq(x1, y1, x2, y2));
    }

    public double distance(double px, double py) {
        return Math.sqrt(distanceSq(px, py));
    }

    public double distance(Point2D p) {
        return Math.sqrt(distanceSq(p));
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            throw new InternalError();
        }
    }

    @Override
    public int hashCode() {
        HashCode hash = new HashCode();
        hash.append(getX());
        hash.append(getY());
        return hash.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof Point2D) {
            Point2D p = (Point2D) obj;
            return (getX() == p.getX() && getY() == p.getY());
        }
        return false;
    }
}
