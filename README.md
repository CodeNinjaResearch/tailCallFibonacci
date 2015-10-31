# Tail Call Optimization / Kotlin  -  Fibonacci  
# Recursive vs Imperative 

##Build
 [![Kotlin](https://img.shields.io/badge/Kotlin-1.0.0--beta--1038-blue.svg?plastic)](http://kotlinlang.org) [![Maven Central](https://maven-badges.herokuapp.com/maven-central/org.eluder.coveralls/coveralls-maven-plugin/badge.svg)](https://maven-badges.herokuapp.com/maven-central/org.eluder.coveralls/coveralls-maven-plugin/) [![Analytics](https://ga-beacon.appspot.com/UA-68658653-7
/tailCallFibonacci/readme)](https://github.com/igrigorik/ga-beacon)

## Recursive

```kotlin
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
```

Result
```
Initialize Fibonacci Recursive

F(0) : 1             [ 7743 ns - 0 ms ]
F(1) : 1             [ 694 ns - 0 ms ]
F(2) : 2             [ 8636 ns - 0 ms ]
F(3) : 3             [ 1237 ns - 0 ms ]
F(4) : 5             [ 1682 ns - 0 ms ]
F(5) : 8             [ 1978 ns - 0 ms ]
F(6) : 13            [ 2725 ns - 0 ms ]
F(7) : 21            [ 4053 ns - 0 ms ]
F(8) : 34            [ 8171 ns - 0 ms ]
F(9) : 55            [ 11604 ns - 0 ms ]
F(10) : 89           [ 17622 ns - 0 ms ]
F(11) : 144          [ 44721 ns - 0 ms ]
F(12) : 233          [ 47848 ns - 0 ms ]
F(13) : 377          [ 99682 ns - 0 ms ]
F(14) : 610          [ 123040 ns - 0 ms ]
F(15) : 987          [ 199922 ns - 0 ms ]
F(16) : 1597         [ 753854 ns - 0 ms ]
F(17) : 2584         [ 22576 ns - 0 ms ]
F(18) : 4181         [ 34622 ns - 0 ms ]
F(19) : 6765         [ 55543 ns - 0 ms ]
F(20) : 10946        [ 87667 ns - 0 ms ]
F(21) : 17711        [ 143205 ns - 0 ms ]
F(22) : 28657        [ 228928 ns - 0 ms ]
F(23) : 46368        [ 397407 ns - 0 ms ]
F(24) : 75025        [ 839320 ns - 0 ms ]
F(25) : 121393       [ 998492 ns - 0 ms ]
F(26) : 196418       [ 1138915 ns - 1 ms ]
F(27) : 317811       [ 1892820 ns - 1 ms ]
F(28) : 514229       [ 3134251 ns - 3 ms ]
F(29) : 832040       [ 4932314 ns - 4 ms ]
F(30) : 1346269      [ 6279154 ns - 6 ms ]
F(31) : 2178309      [ 10885655 ns - 10 ms ]
F(32) : 3524578      [ 46768334 ns - 46 ms ]
F(33) : 5702887      [ 35289855 ns - 35 ms ]
F(34) : 9227465      [ 59495797 ns - 59 ms ]
F(35) : 14930352     [ 90938456 ns - 90 ms ]
F(36) : 24157817     [ 162509353 ns - 162 ms ]
F(37) : 39088169     [ 257875718 ns - 257 ms ]
F(38) : 63245986     [ 413580234 ns - 413 ms ]
F(39) : 102334155    [ 592970608 ns - 592 ms ]
F(40) : 165580141    [ 1138700741 ns - 1138 ms ]
F(41) : 267914296    [ 1302453394 ns - 1302 ms ]
F(42) : 433494437    [ 2098757755 ns - 2098 ms ]
F(43) : 701408733    [ 3231101819 ns - 3231 ms ]
F(44) : 1134903170   [ 5980221111 ns - 5980 ms ]
F(45) : 1836311903   [ 8978036162 ns - 8978 ms ]
F(46) : 2971215073   [ 14194385764 ns - 14194 ms ]
F(47) : 4807526976   [ 22805612301 ns - 22805 ms ]
F(48) : 7778742049   [ 36056905386 ns - 36056 ms ]
F(49) : 12586269025  [ 50053799784 ns - 50053 ms ]
F(50) : 20365011074  [ 80552317073 ns - 80552 ms ]
```

Complexity: 
```
(a > 2) =  O(2^n) 
Lower Bound, T(n) = Ω 2 ^(n/2)
Upper Bound, T(n) = O 2 ^n
```

## Imperative with Tail Call 

```kotlin
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
```

Result 
```
Initialize Fibonacci Imperative with Tail-Call

F(0) : 1   		     [ 2554 ns - 0 ms ]
F(1) : 1 	   	     [ 701 ns  - 0 ms ] 
F(2) : 2  		     [ 1189 ns - 0 ms ]
F(3) : 3  		     [ 1058 ns - 0 ms ]
F(4) : 5  		     [ 897 ns  - 0 ms ]
F(5) : 8  		     [ 864 ns  - 0 ms ]
F(6) : 13 		     [ 933 ns  - 0 ms ]
F(7) : 21  		     [ 901 ns  - 0 ms ]
F(8) : 34  		     [ 970 ns  - 0 ms ]
F(9) : 55 	 	     [ 1035 ns - 0 ms ]
F(10) : 89 	 	     [ 1116 ns - 0 ms ]
F(11) : 144 		 [ 1170 ns - 0 ms ]
F(12) : 233 	     [ 1207 ns - 0 ms ]
F(13) : 377 		 [ 1170 ns - 0 ms ]
F(14) : 610 	     [ 1084 ns - 0 ms ]
F(15) : 987 		 [ 1073 ns - 0 ms ]
F(16) : 1597 		 [ 1066 ns - 0 ms ]
F(17) : 2584 		 [ 1358 ns - 0 ms ]
F(18) : 4181 		 [ 1135 ns - 0 ms ]
F(19) : 6765 		 [ 1184 ns - 0 ms ]
F(20) : 10946 	     [ 1146 ns - 0 ms ]
F(21) : 17711 	     [ 1012 ns - 0 ms ]
F(22) : 28657	     [ 1030 ns - 0 ms ]
F(23) : 46368	     [ 1149 ns - 0 ms ]
F(24) : 75025 	     [ 1072 ns - 0 ms ]
F(25) : 121393 	     [ 1093 ns - 0 ms ]
F(26) : 196418 	     [ 1279 ns - 0 ms ]
F(27) : 317811       [ 1133 ns - 0 ms ]
F(28) : 514229 	     [ 1040 ns - 0 ms ]
F(29) : 832040 	     [ 1056 ns - 0 ms ]
F(30) : 1346269      [ 1823 ns - 0 ms ]
F(31) : 2178309	     [ 1459 ns - 0 ms ]
F(32) : 3524578      [ 1112 ns - 0 ms ]
F(33) : 5702887      [ 1201 ns - 0 ms ]
F(34) : 9227465      [ 1278 ns - 0 ms ]
F(35) : 14930352 	 [ 1186 ns - 0 ms ]
F(36) : 24157817 	 [ 1205 ns - 0 ms ]
F(37) : 39088169 	 [ 1773 ns - 0 ms ]
F(38) : 63245986 	 [ 1249 ns - 0 ms ]
F(39) : 102334155 	 [ 1234 ns - 0 ms ] 
F(40) : 165580141 	 [ 1236 ns - 0 ms ]
F(41) : 267914296 	 [ 1330 ns - 0 ms ]
F(42) : 433494437 	 [ 1344 ns - 0 ms ]
F(43) : 701408733 	 [ 1330 ns - 0 ms ]
F(44) : 1134903170 	 [ 1339 ns - 0 ms ]
F(45) : 1836311903 	 [ 1366 ns - 0 ms ]
F(46) : 2971215073   [ 1780 ns - 0 ms ]
F(47) : 4807526976 	 [ 4500 ns - 0 ms ]
F(48) : 7778742049 	 [ 1453 ns - 0 ms ]
F(49) : 12586269025  [ 1440 ns - 0 ms ]
F(50) : 20365011074  [ 1848 ns - 0 ms ]
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
* http://blog.jetbrains.com/kotlin/2013/12/m6-2-available/
