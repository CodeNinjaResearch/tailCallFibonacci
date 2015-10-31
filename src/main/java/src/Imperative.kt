package src

import api.Fibonacci

/**
 * Created by vicboma on 31/10/15.
 */
class Imperative : Fibonacci {
    init {
        println("Initialize Fibonacci Imperative with Tail-Call")
    }

    open override fun method(n : Int) : Long {
        var ant : Long = 1
        var act : Long = 1
        var tmp : Long
        for (i in 2..n) {
            tmp = act + ant
            ant = act
            act = tmp
        }
        return act
    }
}