package src

import api.Fibonacci
import java.awt.Color

/**
 * Created by vicboma on 31/10/15.
 */
class Recursive : Fibonacci {
    init {
        println("Initialize Fibonacci Recursive")
    }

    override fun method(n : Long) : Long =
            when (n) {
                0L -> 0L
                1L -> 1L
                else -> {
                    method(n - 1) + method(n - 2)
                }
            }
}