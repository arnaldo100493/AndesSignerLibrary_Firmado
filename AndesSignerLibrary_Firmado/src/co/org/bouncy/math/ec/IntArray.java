// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   IntArray.java

package co.org.bouncy.math.ec;

import co.org.bouncy.util.Arrays;
import java.math.BigInteger;

// Referenced classes of package co.org.bouncy.math.ec:
//            ECConstants

class IntArray
{

    public IntArray(int intLen)
    {
        m_ints = new int[intLen];
    }

    public IntArray(int ints[])
    {
        m_ints = ints;
    }

    public IntArray(BigInteger bigInt)
    {
        this(bigInt, 0);
    }

    public IntArray(BigInteger bigInt, int minIntLen)
    {
        if(bigInt.signum() == -1)
            throw new IllegalArgumentException("Only positive Integers allowed");
        if(bigInt.equals(ECConstants.ZERO))
        {
            m_ints = (new int[] {
                0
            });
            return;
        }
        byte barr[] = bigInt.toByteArray();
        int barrLen = barr.length;
        int barrStart = 0;
        if(barr[0] == 0)
        {
            barrLen--;
            barrStart = 1;
        }
        int intLen = (barrLen + 3) / 4;
        if(intLen < minIntLen)
            m_ints = new int[minIntLen];
        else
            m_ints = new int[intLen];
        int iarrJ = intLen - 1;
        int rem = barrLen % 4 + barrStart;
        int temp = 0;
        int barrI = barrStart;
        if(barrStart < rem)
        {
            for(; barrI < rem; barrI++)
            {
                temp <<= 8;
                int barrBarrI = barr[barrI];
                if(barrBarrI < 0)
                    barrBarrI += 256;
                temp |= barrBarrI;
            }

            m_ints[iarrJ--] = temp;
        }
        for(; iarrJ >= 0; iarrJ--)
        {
            temp = 0;
            for(int i = 0; i < 4; i++)
            {
                temp <<= 8;
                int barrBarrI = barr[barrI++];
                if(barrBarrI < 0)
                    barrBarrI += 256;
                temp |= barrBarrI;
            }

            m_ints[iarrJ] = temp;
        }

    }

    public boolean isZero()
    {
        return m_ints.length == 0 || m_ints[0] == 0 && getUsedLength() == 0;
    }

    public int getUsedLength()
    {
        int highestIntPos = m_ints.length;
        if(highestIntPos < 1)
            return 0;
        if(m_ints[0] != 0)
        {
            while(m_ints[--highestIntPos] == 0) ;
            return highestIntPos + 1;
        }
        do
            if(m_ints[--highestIntPos] != 0)
                return highestIntPos + 1;
        while(highestIntPos > 0);
        return 0;
    }

    public int bitLength()
    {
        int intLen = getUsedLength();
        if(intLen == 0)
            return 0;
        int last = intLen - 1;
        int highest = m_ints[last];
        int bits = (last << 5) + 1;
        if((highest & 0xffff0000) != 0)
        {
            if((highest & 0xff000000) != 0)
            {
                bits += 24;
                highest >>>= 24;
            } else
            {
                bits += 16;
                highest >>>= 16;
            }
        } else
        if(highest > 255)
        {
            bits += 8;
            highest >>>= 8;
        }
        for(; highest != 1; highest >>>= 1)
            bits++;

        return bits;
    }

    private int[] resizedInts(int newLen)
    {
        int newInts[] = new int[newLen];
        int oldLen = m_ints.length;
        int copyLen = oldLen >= newLen ? newLen : oldLen;
        System.arraycopy(m_ints, 0, newInts, 0, copyLen);
        return newInts;
    }

    public BigInteger toBigInteger()
    {
        int usedLen = getUsedLength();
        if(usedLen == 0)
            return ECConstants.ZERO;
        int highestInt = m_ints[usedLen - 1];
        byte temp[] = new byte[4];
        int barrI = 0;
        boolean trailingZeroBytesDone = false;
        for(int j = 3; j >= 0; j--)
        {
            byte thisByte = (byte)(highestInt >>> 8 * j);
            if(trailingZeroBytesDone || thisByte != 0)
            {
                trailingZeroBytesDone = true;
                temp[barrI++] = thisByte;
            }
        }

        int barrLen = 4 * (usedLen - 1) + barrI;
        byte barr[] = new byte[barrLen];
        for(int j = 0; j < barrI; j++)
            barr[j] = temp[j];

        for(int iarrJ = usedLen - 2; iarrJ >= 0; iarrJ--)
        {
            for(int j = 3; j >= 0; j--)
                barr[barrI++] = (byte)(m_ints[iarrJ] >>> 8 * j);

        }

        return new BigInteger(1, barr);
    }

    public void shiftLeft()
    {
        int usedLen = getUsedLength();
        if(usedLen == 0)
            return;
        if(m_ints[usedLen - 1] < 0 && ++usedLen > m_ints.length)
            m_ints = resizedInts(m_ints.length + 1);
        boolean carry = false;
        for(int i = 0; i < usedLen; i++)
        {
            boolean nextCarry = m_ints[i] < 0;
            m_ints[i] <<= 1;
            if(carry)
                m_ints[i] |= 1;
            carry = nextCarry;
        }

    }

    public IntArray shiftLeft(int n)
    {
        int usedLen = getUsedLength();
        if(usedLen == 0)
            return this;
        if(n == 0)
            return this;
        if(n > 31)
            throw new IllegalArgumentException((new StringBuilder()).append("shiftLeft() for max 31 bits , ").append(n).append("bit shift is not possible").toString());
        int newInts[] = new int[usedLen + 1];
        int nm32 = 32 - n;
        newInts[0] = m_ints[0] << n;
        for(int i = 1; i < usedLen; i++)
            newInts[i] = m_ints[i] << n | m_ints[i - 1] >>> nm32;

        newInts[usedLen] = m_ints[usedLen - 1] >>> nm32;
        return new IntArray(newInts);
    }

    public void addShifted(IntArray other, int shift)
    {
        int usedLenOther = other.getUsedLength();
        int newMinUsedLen = usedLenOther + shift;
        if(newMinUsedLen > m_ints.length)
            m_ints = resizedInts(newMinUsedLen);
        for(int i = 0; i < usedLenOther; i++)
            m_ints[i + shift] ^= other.m_ints[i];

    }

    public int getLength()
    {
        return m_ints.length;
    }

    public boolean testBit(int n)
    {
        int theInt = n >> 5;
        int theBit = n & 0x1f;
        int tester = 1 << theBit;
        return (m_ints[theInt] & tester) != 0;
    }

    public void flipBit(int n)
    {
        int theInt = n >> 5;
        int theBit = n & 0x1f;
        int flipper = 1 << theBit;
        m_ints[theInt] ^= flipper;
    }

    public void setBit(int n)
    {
        int theInt = n >> 5;
        int theBit = n & 0x1f;
        int setter = 1 << theBit;
        m_ints[theInt] |= setter;
    }

    public IntArray multiply(IntArray other, int m)
    {
        int t = m + 31 >> 5;
        if(m_ints.length < t)
            m_ints = resizedInts(t);
        IntArray b = new IntArray(other.resizedInts(other.getLength() + 1));
        IntArray c = new IntArray(m + m + 31 >> 5);
        int testBit = 1;
        for(int k = 0; k < 32; k++)
        {
            for(int j = 0; j < t; j++)
                if((m_ints[j] & testBit) != 0)
                    c.addShifted(b, j);

            testBit <<= 1;
            b.shiftLeft();
        }

        return c;
    }

    public void reduce(int m, int redPol[])
    {
        for(int i = (m + m) - 2; i >= m; i--)
        {
            if(!testBit(i))
                continue;
            int bit = i - m;
            flipBit(bit);
            flipBit(i);
            for(int l = redPol.length; --l >= 0;)
                flipBit(redPol[l] + bit);

        }

        m_ints = resizedInts(m + 31 >> 5);
    }

    public IntArray square(int m)
    {
        int table[] = {
            0, 1, 4, 5, 16, 17, 20, 21, 64, 65, 
            68, 69, 80, 81, 84, 85
        };
        int t = m + 31 >> 5;
        if(m_ints.length < t)
            m_ints = resizedInts(t);
        IntArray c = new IntArray(t + t);
        for(int i = 0; i < t; i++)
        {
            int v0 = 0;
            for(int j = 0; j < 4; j++)
            {
                v0 >>>= 8;
                int u = m_ints[i] >>> j * 4 & 0xf;
                int w = table[u] << 24;
                v0 |= w;
            }

            c.m_ints[i + i] = v0;
            v0 = 0;
            int upper = m_ints[i] >>> 16;
            for(int j = 0; j < 4; j++)
            {
                v0 >>>= 8;
                int u = upper >>> j * 4 & 0xf;
                int w = table[u] << 24;
                v0 |= w;
            }

            c.m_ints[i + i + 1] = v0;
        }

        return c;
    }

    public boolean equals(Object o)
    {
        if(!(o instanceof IntArray))
            return false;
        IntArray other = (IntArray)o;
        int usedLen = getUsedLength();
        if(other.getUsedLength() != usedLen)
            return false;
        for(int i = 0; i < usedLen; i++)
            if(m_ints[i] != other.m_ints[i])
                return false;

        return true;
    }

    public int hashCode()
    {
        int usedLen = getUsedLength();
        int hash = 1;
        for(int i = 0; i < usedLen; i++)
            hash = hash * 31 + m_ints[i];

        return hash;
    }

    public Object clone()
    {
        return new IntArray(Arrays.clone(m_ints));
    }

    public String toString()
    {
        int usedLen = getUsedLength();
        if(usedLen == 0)
            return "0";
        StringBuffer sb = new StringBuffer(Integer.toBinaryString(m_ints[usedLen - 1]));
        for(int iarrJ = usedLen - 2; iarrJ >= 0; iarrJ--)
        {
            String hexString = Integer.toBinaryString(m_ints[iarrJ]);
            for(int i = hexString.length(); i < 8; i++)
                hexString = (new StringBuilder()).append("0").append(hexString).toString();

            sb.append(hexString);
        }

        return sb.toString();
    }

    private int m_ints[];
}
