package src

import api.Fibonacci
import java.awt.Color

/**
 * Created by vicboma on 31/10/15.
 */
class Imperative : Fibonacci {
    init {
        println("Initialize Fibonacci Imperative")
    }

     override fun method(n: Long): Long =
        when (n) {
            0L -> 0L
            1L -> 1L
            else -> {
                var act: Long = 1L
                var ant: Long = 0L
                var tmp: Long
                for (i in 2..n) {
                    tmp = act + ant
                    ant = act
                    act = tmp
                }
                act
            }
        }

}
