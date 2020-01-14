// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SHA224Digest.java

package co.org.bouncy.crypto.digests;

import co.org.bouncy.crypto.util.Pack;
import co.org.bouncy.util.Memoable;

// Referenced classes of package co.org.bouncy.crypto.digests:
//            GeneralDigest

public class SHA224Digest extends GeneralDigest
{

    public SHA224Digest()
    {
        X = new int[64];
        reset();
    }

    public SHA224Digest(SHA224Digest t)
    {
        super(t);
        X = new int[64];
        doCopy(t);
    }

    private void doCopy(SHA224Digest t)
    {
        super.copyIn(t);
        H1 = t.H1;
        H2 = t.H2;
        H3 = t.H3;
        H4 = t.H4;
        H5 = t.H5;
        H6 = t.H6;
        H7 = t.H7;
        H8 = t.H8;
        System.arraycopy(t.X, 0, X, 0, t.X.length);
        xOff = t.xOff;
    }

    public String getAlgorithmName()
    {
        return "SHA-224";
    }

    public int getDigestSize()
    {
        return 28;
    }

    protected void processWord(byte in[], int inOff)
    {
        int n = in[inOff] << 24;
        n |= (in[++inOff] & 0xff) << 16;
        n |= (in[++inOff] & 0xff) << 8;
        n |= in[++inOff] & 0xff;
        X[xOff] = n;
        if(++xOff == 16)
            processBlock();
    }

    protected void processLength(long bitLength)
    {
        if(xOff > 14)
            processBlock();
        X[14] = (int)(bitLength >>> 32);
        X[15] = (int)(bitLength & -1L);
    }

    public int doFinal(byte out[], int outOff)
    {
        finish();
        Pack.intToBigEndian(H1, out, outOff);
        Pack.intToBigEndian(H2, out, outOff + 4);
        Pack.intToBigEndian(H3, out, outOff + 8);
        Pack.intToBigEndian(H4, out, outOff + 12);
        Pack.intToBigEndian(H5, out, outOff + 16);
        Pack.intToBigEndian(H6, out, outOff + 20);
        Pack.intToBigEndian(H7, out, outOff + 24);
        reset();
        return 28;
    }

    public void reset()
    {
        super.reset();
        H1 = 0xc1059ed8;
        H2 = 0x367cd507;
        H3 = 0x3070dd17;
        H4 = 0xf70e5939;
        H5 = 0xffc00b31;
        H6 = 0x68581511;
        H7 = 0x64f98fa7;
        H8 = 0xbefa4fa4;
        xOff = 0;
        for(int i = 0; i != X.length; i++)
            X[i] = 0;

    }

    protected void processBlock()
    {
        for(int t = 16; t <= 63; t++)
            X[t] = Theta1(X[t - 2]) + X[t - 7] + Theta0(X[t - 15]) + X[t - 16];

        int a = H1;
        int b = H2;
        int c = H3;
        int d = H4;
        int e = H5;
        int f = H6;
        int g = H7;
        int h = H8;
        int t = 0;
        for(int i = 0; i < 8; i++)
        {
            h += Sum1(e) + Ch(e, f, g) + K[t] + X[t];
            d += h;
            h += Sum0(a) + Maj(a, b, c);
            t++;
            g += Sum1(d) + Ch(d, e, f) + K[t] + X[t];
            c += g;
            g += Sum0(h) + Maj(h, a, b);
            t++;
            f += Sum1(c) + Ch(c, d, e) + K[t] + X[t];
            b += f;
            f += Sum0(g) + Maj(g, h, a);
            t++;
            e += Sum1(b) + Ch(b, c, d) + K[t] + X[t];
            a += e;
            e += Sum0(f) + Maj(f, g, h);
            t++;
            d += Sum1(a) + Ch(a, b, c) + K[t] + X[t];
            h += d;
            d += Sum0(e) + Maj(e, f, g);
            t++;
            c += Sum1(h) + Ch(h, a, b) + K[t] + X[t];
            g += c;
            c += Sum0(d) + Maj(d, e, f);
            t++;
            b += Sum1(g) + Ch(g, h, a) + K[t] + X[t];
            f += b;
            b += Sum0(c) + Maj(c, d, e);
            t++;
            a += Sum1(f) + Ch(f, g, h) + K[t] + X[t];
            e += a;
            a += Sum0(b) + Maj(b, c, d);
            t++;
        }

        H1 += a;
        H2 += b;
        H3 += c;
        H4 += d;
        H5 += e;
        H6 += f;
        H7 += g;
        H8 += h;
        xOff = 0;
        for(int i = 0; i < 16; i++)
            X[i] = 0;

    }

    private int Ch(int x, int y, int z)
    {
        return x & y ^ ~x & z;
    }

    private int Maj(int x, int y, int z)
    {
        return x & y ^ x & z ^ y & z;
    }

    private int Sum0(int x)
    {
        return (x >>> 2 | x << 30) ^ (x >>> 13 | x << 19) ^ (x >>> 22 | x << 10);
    }

    private int Sum1(int x)
    {
        return (x >>> 6 | x << 26) ^ (x >>> 11 | x << 21) ^ (x >>> 25 | x << 7);
    }

    private int Theta0(int x)
    {
        return (x >>> 7 | x << 25) ^ (x >>> 18 | x << 14) ^ x >>> 3;
    }

    private int Theta1(int x)
    {
        return (x >>> 17 | x << 15) ^ (x >>> 19 | x << 13) ^ x >>> 10;
    }

    public Memoable copy()
    {
        return new SHA224Digest(this);
    }

    public void reset(Memoable other)
    {
        SHA224Digest d = (SHA224Digest)other;
        doCopy(d);
    }

    private static final int DIGEST_LENGTH = 28;
    private int H1;
    private int H2;
    private int H3;
    private int H4;
    private int H5;
    private int H6;
    private int H7;
    private int H8;
    private int X[];
    private int xOff;
    static final int K[] = {
        0x428a2f98, 0x71374491, 0xb5c0fbcf, 0xe9b5dba5, 0x3956c25b, 0x59f111f1, 0x923f82a4, 0xab1c5ed5, 0xd807aa98, 0x12835b01, 
        0x243185be, 0x550c7dc3, 0x72be5d74, 0x80deb1fe, 0x9bdc06a7, 0xc19bf174, 0xe49b69c1, 0xefbe4786, 0xfc19dc6, 0x240ca1cc, 
        0x2de92c6f, 0x4a7484aa, 0x5cb0a9dc, 0x76f988da, 0x983e5152, 0xa831c66d, 0xb00327c8, 0xbf597fc7, 0xc6e00bf3, 0xd5a79147, 
        0x6ca6351, 0x14292967, 0x27b70a85, 0x2e1b2138, 0x4d2c6dfc, 0x53380d13, 0x650a7354, 0x766a0abb, 0x81c2c92e, 0x92722c85, 
        0xa2bfe8a1, 0xa81a664b, 0xc24b8b70, 0xc76c51a3, 0xd192e819, 0xd6990624, 0xf40e3585, 0x106aa070, 0x19a4c116, 0x1e376c08, 
        0x2748774c, 0x34b0bcb5, 0x391c0cb3, 0x4ed8aa4a, 0x5b9cca4f, 0x682e6ff3, 0x748f82ee, 0x78a5636f, 0x84c87814, 0x8cc70208, 
        0x90befffa, 0xa4506ceb, 0xbef9a3f7, 0xc67178f2
    };

}
