package src

import api.Fibonacci

/**
 * Created by vicboma on 31/10/15.
 */
class TailRec : Fibonacci{
    init {
        println("Initialize Fibonacci TailRec")
    }

    override fun method(n : Long) : Long {
         tailrec fun _method(index:Long, ant: Long = 0, act:Long  = 1) : Long =
         when(index) {
             0L -> ant
             else -> _method( index - 1 , act, ant + act)
         }
        return _method(n, 0, 1)
    }
}