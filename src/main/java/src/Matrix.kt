package src

import api.Fibonacci
import src.util.Karatsuba
import java.math.BigInteger
import kotlin.concurrent.withLock

/**
 * Created by vicboma on 31/10/15.
 */
class Matrix : Fibonacci {
    init {
        println("Initialize Fibonacci Matrix")

    }

    override fun method(n : Long) : Long {
        val lock = java.util.concurrent.locks.ReentrantLock()
        val matrix = arrayOf<BigInteger>(BigInteger.ONE, BigInteger.ONE, BigInteger.ONE, BigInteger.ZERO)
         fun pow(matrix:Array<BigInteger>, n:Long):Array<BigInteger> {
             var _result = arrayOf<BigInteger>(BigInteger.ONE, BigInteger.ZERO, BigInteger.ZERO, BigInteger.ONE)
             var _n = n
             var _matrix = matrix
             fun matrixMultiply(x:Array<BigInteger>, y:Array<BigInteger>):Array<BigInteger> {
                   fun multiply(x:BigInteger, y:BigInteger):BigInteger {
                       lock.withLock {
                           return Karatsuba.multiply(x, y)
                       }
                   }
                 return arrayOf(
                         multiply(x[0], y[0]).add(
                                 multiply(x[1], y[2])),
                                 multiply(x[0], y[1]).add(
                                         multiply(x[1], y[3])),
                                         multiply(x[2], y[0]).add(
                                                 multiply(x[3], y[2])),
                                                 multiply(x[2], y[1]).add(
                                                         multiply(x[3], y[3])))
             }
                 while (_n != 0L) {
                     if (_n % 2 != 0L)
                         _result = matrixMultiply(_result, _matrix)
                     _n /= 2
                     _matrix = matrixMultiply(_matrix, _matrix)
                 }

             return    _result
        }
        return pow(matrix,n)[1].toLong()
    }

}
