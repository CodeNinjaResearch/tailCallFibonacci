package src

import api.Fibonacci

/**
 * Created by vicboma on 31/10/15.
 */
class Recursive : Fibonacci {
    init {
        println("Initialize Fibonacci Recursive")
    }

    open override fun method(n : Int) : Long {
        if (n < 2)
            return 1
        else
            return method(n - 1) + method(n - 2)
    }
}