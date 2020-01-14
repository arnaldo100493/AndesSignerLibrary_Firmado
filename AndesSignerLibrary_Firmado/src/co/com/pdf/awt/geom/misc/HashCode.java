// Decompiled by DJ v3.12.12.98 Copyright 2014 Atanas Neshkov  Date: 17/11/2017 7:07:01 p. m.
// Home Page: http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   HashCode.java
package co.com.pdf.awt.geom.misc;

public final class HashCode {

    public static final int EMPTY_HASH_CODE = 1;

    private int hashCode = 1;

    public HashCode() {

    }

    @Override
    public final int hashCode() {
        return this.hashCode;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final HashCode other = (HashCode) obj;
        if (this.hashCode != other.hashCode) {
            return false;
        }
        return true;
    }

    public static int combine(int hashCode, boolean value) {
        int v = value ? 1231 : 1237;
        return combine(hashCode, v);
    }

    public static int combine(int hashCode, long value) {
        int v = (int) (value ^ value >>> 32L);
        return combine(hashCode, v);
    }

    public static int combine(int hashCode, float value) {
        int v = Float.floatToIntBits(value);
        return combine(hashCode, v);
    }

    public static int combine(int hashCode, double value) {
        long v = Double.doubleToLongBits(value);
        return combine(hashCode, v);
    }

    public static int combine(int hashCode, Object value) {
        return combine(hashCode, value.hashCode());
    }

    public static int combine(int hashCode, int value) {
        return 31 * hashCode + value;
    }

    public final HashCode append(int value) {
        this.hashCode = combine(this.hashCode, value);
        return this;
    }

    public final HashCode append(long value) {
        this.hashCode = combine(this.hashCode, value);
        return this;
    }

    public final HashCode append(float value) {
        this.hashCode = combine(this.hashCode, value);
        return this;
    }

    public final HashCode append(double value) {
        this.hashCode = combine(this.hashCode, value);
        return this;
    }

    public final HashCode append(boolean value) {
        this.hashCode = combine(this.hashCode, value);
        return this;
    }

    public final HashCode append(Object value) {
        this.hashCode = combine(this.hashCode, value);
        return this;
    }
}
