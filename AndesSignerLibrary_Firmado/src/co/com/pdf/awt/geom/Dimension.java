// Decompiled by DJ v3.12.12.98 Copyright 2014 Atanas Neshkov  Date: 17/11/2017 7:06:59 p. m.
// Home Page: http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   Dimension.java
package co.com.pdf.awt.geom;

import co.com.pdf.awt.geom.misc.HashCode;
import java.io.Serializable;

// Referenced classes of package co.com.pdf.awt.geom:
//            Dimension2D
public class Dimension extends Dimension2D implements Serializable {

    private static final long serialVersionUID = 4723952579491349524L;

    public double width;

    public double height;

    public Dimension(Dimension d) {
        this(d.width, d.height);
    }

    public Dimension() {
        this(0, 0);
    }

    public Dimension(double width, double height) {
        setSize(width, height);
    }

    public Dimension(int width, int height) {
        setSize(width, height);
    }

    @Override
    public int hashCode() {
        HashCode hash = new HashCode();
        hash.append(this.width);
        hash.append(this.height);
        return hash.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof Dimension) {
            Dimension d = (Dimension) obj;
            return (d.width == this.width && d.height == this.height);
        }
        return false;
    }

    @Override
    public String toString() {
        return getClass().getName() + "[width=" + this.width + ",height=" + this.height + "]";
    }

    public void setSize(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void setSize(Dimension d) {
        setSize(d.width, d.height);
    }

    @Override
    public void setSize(double width, double height) {
        setSize((int) Math.ceil(width), (int) Math.ceil(height));
    }

    public Dimension getSize() {
        return new Dimension(this.width, this.height);
    }

    @Override
    public double getHeight() {
        return this.height;
    }

    @Override
    public double getWidth() {
        return this.width;
    }
}
