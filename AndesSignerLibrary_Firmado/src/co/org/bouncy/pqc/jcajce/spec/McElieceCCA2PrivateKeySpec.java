// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   McElieceCCA2PrivateKeySpec.java

package co.org.bouncy.pqc.jcajce.spec;

import co.org.bouncy.pqc.math.linearalgebra.*;
import java.security.spec.KeySpec;

public class McElieceCCA2PrivateKeySpec
    implements KeySpec
{

    public McElieceCCA2PrivateKeySpec(String oid, int n, int k, GF2mField field, PolynomialGF2mSmallM gp, Permutation p, GF2Matrix h, 
            PolynomialGF2mSmallM qInv[])
    {
        this.oid = oid;
        this.n = n;
        this.k = k;
        this.field = field;
        goppaPoly = gp;
        this.p = p;
        this.h = h;
        this.qInv = qInv;
    }

    public McElieceCCA2PrivateKeySpec(String oid, int n, int k, byte encFieldPoly[], byte encGoppaPoly[], byte encP[], byte encH[], 
            byte encQInv[][])
    {
        this.oid = oid;
        this.n = n;
        this.k = k;
        field = new GF2mField(encFieldPoly);
        goppaPoly = new PolynomialGF2mSmallM(field, encGoppaPoly);
        p = new Permutation(encP);
        h = new GF2Matrix(encH);
        qInv = new PolynomialGF2mSmallM[encQInv.length];
        for(int i = 0; i < encQInv.length; i++)
            qInv[i] = new PolynomialGF2mSmallM(field, encQInv[i]);

    }

    public int getN()
    {
        return n;
    }

    public int getK()
    {
        return k;
    }

    public GF2mField getField()
    {
        return field;
    }

    public PolynomialGF2mSmallM getGoppaPoly()
    {
        return goppaPoly;
    }

    public Permutation getP()
    {
        return p;
    }

    public GF2Matrix getH()
    {
        return h;
    }

    public PolynomialGF2mSmallM[] getQInv()
    {
        return qInv;
    }

    public String getOIDString()
    {
        return oid;
    }

    private String oid;
    private int n;
    private int k;
    private GF2mField field;
    private PolynomialGF2mSmallM goppaPoly;
    private Permutation p;
    private GF2Matrix h;
    private PolynomialGF2mSmallM qInv[];
}
