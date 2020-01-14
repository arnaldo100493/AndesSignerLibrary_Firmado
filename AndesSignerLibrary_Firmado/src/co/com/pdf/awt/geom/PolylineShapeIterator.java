// Decompiled by DJ v3.12.12.98 Copyright 2014 Atanas Neshkov  Date: 17/11/2017 7:07:00 p. m.
// Home Page: http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   PolylineShapeIterator.java
package co.com.pdf.awt.geom;

import co.com.pdf.text.error_messages.MessageLocalization;
import java.awt.geom.AffineTransform;
import java.awt.geom.PathIterator;
import java.util.NoSuchElementException;

// Referenced classes of package co.com.pdf.awt.geom:
//            PolylineShape
public class PolylineShapeIterator implements PathIterator {

    protected PolylineShape poly;

    protected AffineTransform affine;

    protected int index;

    PolylineShapeIterator() {

    }

    PolylineShapeIterator(PolylineShape l, AffineTransform at) {
        this.poly = l;
        this.affine = at;
    }

    @Override
    public int currentSegment(double[] coords) {
        if (isDone()) {
            throw new NoSuchElementException(MessageLocalization.getComposedMessage("line.iterator.out.of.bounds", new Object[0]));
        }
        int type = (this.index == 0) ? 0 : 1;
        coords[0] = this.poly.x[this.index];
        coords[1] = this.poly.y[this.index];
        if (this.affine != null) {
            this.affine.transform(coords, 0, coords, 0, 1);
        }
        return type;
    }

    @Override
    public int currentSegment(float[] coords) {
        if (isDone()) {
            throw new NoSuchElementException(MessageLocalization.getComposedMessage("line.iterator.out.of.bounds", new Object[0]));
        }
        int type = (this.index == 0) ? 0 : 1;
        coords[0] = this.poly.x[this.index];
        coords[1] = this.poly.y[this.index];
        if (this.affine != null) {
            this.affine.transform(coords, 0, coords, 0, 1);
        }
        return type;
    }

    @Override
    public int getWindingRule() {
        return 1;
    }

    @Override
    public boolean isDone() {
        return (this.index >= this.poly.np);
    }

    @Override
    public void next() {
        this.index++;
    }
}
