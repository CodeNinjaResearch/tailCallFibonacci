package src.util

import java.math.BigInteger

/**
 * Created by vicboma on 02/11/15.
 * Utility class for multiplying BigIntegers quickly, using the Karatsuba multiplication algorithm.
 */
object Karatsuba {
    // Requirement: CUTOFF >= 64, or else there will be infinite recursion.
    private val CUTOFF = 1536
    /**
     * Returns {@code x * y}, the product of the specified integers. This gives the same result as {@code x.multiply(y)} but should be faster.
     * @param x a multiplicand
     * @param y a multiplicand
     * @return {@code x} times {@code} y
     * @throws NullPointerException if {@code x} or {@code y} is {@code null}
     */
    fun multiply(x: BigInteger, y: BigInteger): BigInteger {
        if (x.bitLength() <= CUTOFF || y.bitLength() <= CUTOFF)
        { // Base case
            return x.multiply(y)
        }
        else
        {
            val n = Math.max(x.bitLength(), y.bitLength())
            val half = (n + 32) / 64 * 32 // Number of bits to use for the low part
            val mask = BigInteger.ONE.shiftLeft(half).subtract(BigInteger.ONE)
            val xlow = x.and(mask)
            val ylow = y.and(mask)
            val xhigh = x.shiftRight(half)
            val yhigh = y.shiftRight(half)
            val a = multiply(xhigh, yhigh)
            val b = multiply(xlow.add(xhigh), ylow.add(yhigh))
            val c = multiply(xlow, ylow)
            val d = b.subtract(a).subtract(c)
            return a.shiftLeft(half).add(d).shiftLeft(half).add(c)
        }
    }
}