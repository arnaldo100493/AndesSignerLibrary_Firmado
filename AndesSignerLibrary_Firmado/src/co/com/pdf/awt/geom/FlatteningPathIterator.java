// Decompiled by DJ v3.12.12.98 Copyright 2014 Atanas Neshkov  Date: 17/11/2017 7:06:59 p. m.
// Home Page: http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   FlatteningPathIterator.java
package co.com.pdf.awt.geom;

import co.com.pdf.awt.geom.misc.Messages;
import java.util.NoSuchElementException;

// Referenced classes of package co.com.pdf.awt.geom:
//            PathIterator, QuadCurve2D, CubicCurve2D
public class FlatteningPathIterator implements PathIterator {

    private static final int BUFFER_SIZE = 16;

    private static final int BUFFER_LIMIT = 16;

    private static final int BUFFER_CAPACITY = 16;

    int bufType;

    int bufLimit;

    int bufSize;

    int bufIndex;

    int bufSubdiv;

    double[] buf;

    boolean bufEmpty = true;

    PathIterator p;

    double flatness;

    double flatness2;

    double px;

    double py;

    double[] coords = new double[6];

    public FlatteningPathIterator() {

    }

    public FlatteningPathIterator(PathIterator path, double flatness) {
        this(path, flatness, 16);
    }

    public FlatteningPathIterator(PathIterator path, double flatness, int limit) {
        if (flatness < 0.0D) {
            throw new IllegalArgumentException(Messages.getString("awt.206"));
        }
        if (limit < 0) {
            throw new IllegalArgumentException(Messages.getString("awt.207"));
        }
        if (path == null) {
            throw new NullPointerException(Messages.getString("awt.208"));
        }
        this.p = path;
        this.flatness = flatness;
        this.flatness2 = flatness * flatness;
        this.bufLimit = limit;
        this.bufSize = Math.min(this.bufLimit, 16);
        this.buf = new double[this.bufSize];
        this.bufIndex = this.bufSize;
    }

    public double getFlatness() {
        return this.flatness;
    }

    public int getRecursionLimit() {
        return this.bufLimit;
    }

    @Override
    public int getWindingRule() {
        return this.p.getWindingRule();
    }

    @Override
    public boolean isDone() {
        return (this.bufEmpty && this.p.isDone());
    }

    void evaluate() {
        if (this.bufEmpty) {
            this.bufType = this.p.currentSegment(this.coords);
        }
        switch (this.bufType) {
            case 0:
            case 1:
                this.px = this.coords[0];
                this.py = this.coords[1];
                break;
            case 2:
                if (this.bufEmpty) {
                    this.bufIndex -= 6;
                    this.buf[this.bufIndex + 0] = this.px;
                    this.buf[this.bufIndex + 1] = this.py;
                    System.arraycopy(this.coords, 0, this.buf, this.bufIndex + 2, 4);
                    this.bufSubdiv = 0;
                }
                while (this.bufSubdiv < this.bufLimit
                        && QuadCurve2D.getFlatnessSq(this.buf, this.bufIndex) >= this.flatness2) {
                    if (this.bufIndex <= 4) {
                        double[] tmp = new double[this.bufSize + 16];
                        System.arraycopy(this.buf, this.bufIndex, tmp, this.bufIndex + 16, this.bufSize - this.bufIndex);
                        this.buf = tmp;
                        this.bufSize += 16;
                        this.bufIndex += 16;
                    }
                    QuadCurve2D.subdivide(this.buf, this.bufIndex, this.buf, this.bufIndex - 4, this.buf, this.bufIndex);
                    this.bufIndex -= 4;
                    this.bufSubdiv++;
                }
                this.bufIndex += 4;
                this.px = this.buf[this.bufIndex];
                this.py = this.buf[this.bufIndex + 1];
                this.bufEmpty = (this.bufIndex == this.bufSize - 2);
                if (this.bufEmpty) {
                    this.bufIndex = this.bufSize;
                    this.bufType = 1;
                }
                break;
            case 3:
                if (this.bufEmpty) {
                    this.bufIndex -= 8;
                    this.buf[this.bufIndex + 0] = this.px;
                    this.buf[this.bufIndex + 1] = this.py;
                    System.arraycopy(this.coords, 0, this.buf, this.bufIndex + 2, 6);
                    this.bufSubdiv = 0;
                }
                while (this.bufSubdiv < this.bufLimit
                        && CubicCurve2D.getFlatnessSq(this.buf, this.bufIndex) >= this.flatness2) {
                    if (this.bufIndex <= 6) {
                        double[] tmp = new double[this.bufSize + 16];
                        System.arraycopy(this.buf, this.bufIndex, tmp, this.bufIndex + 16, this.bufSize - this.bufIndex);
                        this.buf = tmp;
                        this.bufSize += 16;
                        this.bufIndex += 16;
                    }
                    CubicCurve2D.subdivide(this.buf, this.bufIndex, this.buf, this.bufIndex - 6, this.buf, this.bufIndex);
                    this.bufIndex -= 6;
                    this.bufSubdiv++;
                }
                this.bufIndex += 6;
                this.px = this.buf[this.bufIndex];
                this.py = this.buf[this.bufIndex + 1];
                this.bufEmpty = (this.bufIndex == this.bufSize - 2);
                if (this.bufEmpty) {
                    this.bufIndex = this.bufSize;
                    this.bufType = 1;
                }
                break;
        }
    }

    @Override
    public void next() {
        if (this.bufEmpty) {
            this.p.next();
        }
    }

    @Override
    public int currentSegment(float[] coords) {
        if (isDone()) {
            throw new NoSuchElementException(Messages.getString("awt.4Bx"));
        }
        evaluate();
        int type = this.bufType;
        if (type != 4) {
            coords[0] = (float) this.px;
            coords[1] = (float) this.py;
            if (type != 0) {
                type = 1;
            }
        }
        return type;
    }

    @Override
    public int currentSegment(double[] coords) {
        if (isDone()) {
            throw new NoSuchElementException(Messages.getString("awt.4B"));
        }
        evaluate();
        int type = this.bufType;
        if (type != 4) {
            coords[0] = this.px;
            coords[1] = this.py;
            if (type != 0) {
                type = 1;
            }
        }
        return type;
    }
}
