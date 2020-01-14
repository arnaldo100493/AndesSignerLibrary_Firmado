// Decompiled by DJ v3.12.12.98 Copyright 2014 Atanas Neshkov  Date: 17/11/2017 7:06:59 p. m.
// Home Page: http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   Dimension2D.java
package co.com.pdf.awt.geom;

public abstract class Dimension2D implements Cloneable {

    public Dimension2D() {

    }

    public abstract double getWidth();

    public abstract double getHeight();

    public abstract void setSize(double paramDouble1, double paramDouble2);

    public void setSize(Dimension2D d) {
        setSize(d.getWidth(), d.getHeight());
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
