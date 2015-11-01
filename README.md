# Tail Call Optimization / Kotlin  -  Fibonacci  

##Build
 [![Kotlin](https://img.shields.io/badge/Kotlin-1.0.0--beta--1038-blue.svg?plastic)](http://kotlinlang.org) [![Maven Central](https://maven-badges.herokuapp.com/maven-central/org.eluder.coveralls/coveralls-maven-plugin/badge.svg)](https://maven-badges.herokuapp.com/maven-central/org.eluder.coveralls/coveralls-maven-plugin/) [![Analytics](https://ga-beacon.appspot.com/UA-68658653-7
/tailCallFibonacci/readme)](https://github.com/igrigorik/ga-beacon)


## Recursive vs Imperative - [Video teaser] (https://www.youtube.com/watch?v=EiZ1liht97s)
![](http://i.imgur.com/IqiJmyx.png?1)

## Api
```kotlin
interface Fibonacci {
    fun method(n : Int) : Long
}
```
## Recursive

```kotlin
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
```

## Functional

```kotlin
override fun method(n : Long) : Long {
        fun _method(index: Long, ant: Long = 0, act: Long = 1): Long =
                when (index) {
                    0L -> ant
                    else -> _method(index - 1, act, ant + act)
                }
        return _method(n, 0, 1)
    }
```

## Functional with tail call optimization
```kotlin
class TailRec : Fibonacci {
    init {
        println("Initialize Fibonacci TailRec")
    }

    override tailrec fun method(n : Long) : Long {
         fun _method(index:Long, ant: Long = 0, act:Long  = 1) : Long =
         when(index) {
             0L -> ant
             else -> _method( index - 1 , act, ant + act)
         }
        return _method(n, 0, 1)
    }
}
```

Complexity: 
```
(a > 2) =  O(2^n) 
Lower Bound, T(n) = Ω 2 ^(n/2)
Upper Bound, T(n) = O 2 ^n
```

## Imperative 

```kotlin
class Imperative : Fibonacci {
    init {
        println("Initialize Fibonacci Imperative with Tail-Call")
    }
    open override fun method(n : Int) : Long {
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
}
```

Complexity:  
```
T(n) = Θ (n)
```


References:
* The Original 'Lambda Papers' by Guy Steele and Gerald Sussman, http://library.readscheme.org/page1.html
* Steven S.Munchnick (2000). Advanced Compiler Design implementation, Procedure Optimizations, 15, 461 – 470.
* Suceción de Fibonacci, https://es.wikipedia.org/wiki/Sucesión_de_Fibonacci
* John Hudson Tiner (200). Exploring the World of Mathematics: From Ancient Record Keeping to the Latest Advances in Computers. New Leaf Publishing Group. ISBN 9781614581550.
* Tail Recursive, https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/tail-recursive/
* Tail Recursion Elimination, http://www.program-transformation.org/Transform/TailRecursionElimination
* Tail call, https://en.wikipedia.org/wiki/Tail_call 
* What is difference between tail calls and tail recursion ?, http://stackoverflow.com/questions/12045299/what-is-difference-between-tail-calls-and-tail-recursion
* “Assembly Language:   Function Calls", Jennifer Rexford http://www.cs.princeton.edu/courses/archive/spr11/cos217/lectures/15AssemblyFunctions.pdf
* Tail call Optimization, http://tratt.net/laurie/blog/entries/tail_call_optimization
* Tail calls in the VM, https://blogs.oracle.com/jrose/entry/tail_calls_in_the_vm
* Introduce Tail Call in Kotin, http://blog.jetbrains.com/kotlin/2013/12/m6-2-available/
* [Refactored to/from TailRecursion] - November 10, 2006 - http://c2.com/cgi/wiki?TailCallOptimization
* Pattern Matching, http://kenbarclay.blogspot.com.es/2014/04/kotlin-pattern-matching.html
