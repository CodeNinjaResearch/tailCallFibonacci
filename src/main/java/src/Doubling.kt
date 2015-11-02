package src

import api.Fibonacci
import src.util.Karatsuba
import java.math.BigInteger
import kotlin.concurrent.withLock

/**
 * Created by vicboma on 31/10/15.
 */
class Doubling : Fibonacci {
    init {
        println("Initialize Fibonacci Doubling")
    }

   override fun method(n:Long) : Long {
        val lock = java.util.concurrent.locks.ReentrantLock()
        fun multiply(x:BigInteger, y:BigInteger):BigInteger {
             lock.withLock {
                 return Karatsuba.multiply(x, y)
             }
        }

        var a = BigInteger.ZERO
        var b = BigInteger.ONE
        var m = 0
        for (i in 31 - Integer.numberOfLeadingZeros(n.toInt()) downTo 0)
        {
            // Double it
            var d = multiply(a, b.shiftLeft(1).subtract(a))
            var e = multiply(a, a).add(multiply(b, b))
            a = d
            b = e
            m *= 2
            if (((n.ushr(i)) and 1) != 0L)
            {
                val c = a.add(b)
                a = b
                b = c
                m++
            }
        }
        return a.toLong()
    }
}
