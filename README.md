##[Tail Call Optimization / Kotlin  -  Fibonacci](http://kotlin.es/2015/11/tail-call-optimization/)
  

##Build
 [![Build Status](https://travis-ci.org/CodeNinjaResearch/tailCallFibonacci.svg?plastic)](https://travis-ci.org/CodeNinjaResearch/tailCallFibonacci) [![Kotlin](https://img.shields.io/badge/Kotlin-1.0.0--beta--1038-blue.svg?plastic)](http://kotlinlang.org) [![Maven Central](https://maven-badges.herokuapp.com/maven-central/org.eluder.coveralls/coveralls-maven-plugin/badge.svg)](https://maven-badges.herokuapp.com/maven-central/org.eluder.coveralls/coveralls-maven-plugin/) [![Analytics](https://ga-beacon.appspot.com/UA-68658653-7
/tailcallfibonacci/readme)](https://github.com/igrigorik/ga-beacon)

## [Video] (https://youtu.be/VZrOZFujnX0) - [w/recurise](https://youtu.be/HFLel3os0Z0)
## [Plot] (https://youtu.be/Xszz2NrJ6Ug)

![](http://i.imgur.com/GXZAVzT.png)
![](http://i.imgur.com/PBhzpzq.png)
![](http://i.imgur.com/inyu9Pn.png?1)
![](http://i.imgur.com/HiynqHL.png)

##Configure
```kotlin
private fun listFibonacci() = listOf(Recursive(), TailRec(),Doubling(), Imperative(), Matrix(), Functional())
```
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

![](http://i.imgur.com/GezCF76.png)


## Complexity: 
```
T(n) = O(n) Stack space

(a > 2) =  O(2^n) Operations
Lower Bound, T(n) = Ω 2 ^(n/2)
Upper Bound, T(n) = O 2 ^n
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

![](http://i.imgur.com/RUMssYQ.png)


## Functional with tail call optimization
```kotlin
class TailRec : Fibonacci {
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
```

![](http://i.imgur.com/mUdnc7a.png)



## Complexity: 
```
T(n) = 1 Stack space

(a > 2) =  O(2^n) Operations
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

![](http://i.imgur.com/uSbUFAD.png)

## Complexity:  
```
T(n) = 1 Stack space
T(n) = Θ (n) Operations
```

## Times in console mode (without Async Plot) 
```
0   Rec : 11385.0 ns          Func : 1235.0  ns     Imp : 1124.0 ns     TailRec : 2362.0 ns
1   Rec : 4670.0 ns           Func : 1275.0  ns     Imp : 1123.0 ns     TailRec : 7046.0 ns
2   Rec : 11440.0 ns          Func : 1635.0  ns     Imp : 1207.0 ns     TailRec : 1333.0 ns
3   Rec : 2224.0 ns           Func : 1291.0  ns     Imp : 1293.0 ns     TailRec : 1226.0 ns
4   Rec : 2927.0 ns           Func : 1503.0  ns     Imp : 1269.0 ns     TailRec : 1427.0 ns
5   Rec : 2566.0 ns           Func : 1653.0  ns     Imp : 1386.0 ns     TailRec : 1555.0 ns
6   Rec : 4332.0 ns           Func : 1740.0  ns     Imp : 1440.0 ns     TailRec : 1541.0 ns
7   Rec : 5506.0 ns           Func : 1801.0  ns     Imp : 1459.0 ns     TailRec : 2032.0 ns
8   Rec : 16759.0 ns          Func : 1938.0  ns     Imp : 1702.0 ns     TailRec : 2005.0 ns
9   Rec : 884881.0 ns         Func : 2053.0  ns     Imp : 1437.0 ns     TailRec : 2130.0 ns
10  Rec : 5622.0 ns           Func : 2027.0  ns     Imp : 1480.0 ns     TailRec : 2128.0 ns
11  Rec : 5862.0 ns           Func : 2255.0  ns     Imp : 1330.0 ns     TailRec : 2082.0 ns
12  Rec : 6432.0 ns           Func : 2611.0  ns     Imp : 1366.0 ns     TailRec : 2373.0 ns
13  Rec : 10852.0 ns          Func : 2493.0  ns     Imp : 1550.0 ns     TailRec : 2635.0 ns
14  Rec : 16083.0 ns          Func : 2796.0  ns     Imp : 1647.0 ns     TailRec : 2658.0 ns
15  Rec : 21540.0 ns          Func : 4909.0  ns     Imp : 1542.0 ns     TailRec : 4721.0 ns
16  Rec : 58812.0 ns          Func : 2858.0  ns     Imp : 1685.0 ns     TailRec : 2674.0 ns
17  Rec : 52865.0 ns          Func : 2962.0  ns     Imp : 1713.0 ns     TailRec : 3042.0 ns
18  Rec : 26185.0 ns          Func : 2944.0  ns     Imp : 1768.0 ns     TailRec : 2950.0 ns
19  Rec : 39537.0 ns          Func : 3210.0  ns     Imp : 1742.0 ns     TailRec : 2987.0 ns
20  Rec : 62469.0 ns          Func : 3625.0  ns     Imp : 1899.0 ns     TailRec : 3391.0 ns
21  Rec : 97884.0 ns          Func : 3631.0  ns     Imp : 1779.0 ns     TailRec : 3870.0 ns
22  Rec : 162044.0 ns         Func : 1519.09 ns     Imp : 1964.0 ns     TailRec : 9558.0 ns
23  Rec : 641014.0 ns         Func : 3924.0  ns     Imp : 1881.0 ns     TailRec : 3457.0 ns
24  Rec : 406499.0 ns         Func : 4104.0  ns     Imp : 1897.0 ns     TailRec : 3874.0 ns
25  Rec : 612826.0 ns         Func : 3911.0  ns     Imp : 2104.0 ns     TailRec : 4337.0 ns
26  Rec : 928203.0 ns         Func : 4127.0  ns     Imp : 2052.0 ns     TailRec : 3789.0 ns
27  Rec : 1680839.0 ns        Func : 6400.0  ns     Imp : 2065.0 ns     TailRec : 5199.0 ns
28  Rec : 2687233.0 ns        Func : 2157.06 ns     Imp : 2025.0 ns     TailRec : 4062.0 ns
29  Rec : 3294996.0 ns        Func : 1379.0  ns     Imp : 2272.0 ns     TailRec : 5239.0 ns
30  Rec : 6212158.0 ns        Func : 1156.0  ns     Imp : 2157.0 ns     TailRec : 4754.0 ns
31  Rec : 1.0790252E7 ns      Func : 1107.0  ns     Imp : 2184.0 ns     TailRec : 4656.0 ns
32  Rec : 1.457811E7 ns       Func : 1101.0  ns     Imp : 2253.0 ns     TailRec : 5062.0 ns
33  Rec : 2.8070948E7 ns      Func : 1214.0  ns     Imp : 2208.0 ns     TailRec : 6767.0 ns
34  Rec : 4.1788304E7 ns      Func : 1227.0  ns     Imp : 2157.0 ns     TailRec : 1972.0 ns
35  Rec : 6.6791257E7 ns      Func : 1186.0  ns     Imp : 2375.0 ns     TailRec : 4981.0 ns
36  Rec : 1.07003214E8 ns     Func : 1239.0  ns     Imp : 2289.0 ns     TailRec : 1229.0 ns
37  Rec : 1.66858731E8 ns     Func : 1189.0  ns     Imp : 2342.0 ns     TailRec : 1170.0 ns
38  Rec : 2.41178162E8 ns     Func : 1177.0  ns     Imp : 2464.0 ns     TailRec : 1304.0 ns
39  Rec : 3.79226065E8 ns     Func : 1049.0  ns     Imp : 2479.0 ns     TailRec : 1131.0 ns
40  Rec : 6.12267791E8 ns     Func : 1112.0  ns     Imp : 2352.0 ns     TailRec : 1150.0 ns
41  Rec : 1.002091413E9 ns    Func : 1362.0  ns     Imp : 2376.0 ns     TailRec : 1156.0 ns
42  Rec : 1.607548321E9 ns    Func : 1483.0  ns     Imp : 2721.0 ns     TailRec : 1454.0 ns
43  Rec : 2.64751597E9 ns     Func : 1198.0  ns     Imp : 2692.0 ns     TailRec : 1088.0 ns
44  Rec : 4.185048183E9 ns    Func : 1117.0  ns     Imp : 2622.0 ns     TailRec : 1067.0 ns
45  Rec : 8.028322656E9 ns    Func : 1263.0  ns     Imp : 2824.0 ns     TailRec : 1172.0 ns
46  Rec : 1.09966046E10 ns    Func : 1381.0  ns     Imp : 2810.0 ns     TailRec : 1132.0 ns
47  Rec : 1.7291312682E10 ns  Func : 1256.0  ns     Imp : 5943.0 ns     TailRec : 1313.0 ns
48  Rec : 2.7554132724E10 ns  Func : 1289.0  ns     Imp : 2764.0 ns     TailRec : 1312.0 ns
49  Rec : 4.6965113468E10 ns  Func : 1273.0  ns     Imp : 2533.0 ns     TailRec : 1303.0 ns
50  Rec : 7.6787577508E10 ns  Func : 5950.0  ns     Imp : 3226.0 ns     TailRec : 1300.0 ns
51  Rec : ... ns              Func : 1413.0  ns     Imp : 2941.0 ns     TailRec : 1878.0 ns
52  Rec : ... ns              Func : 1276.0  ns     Imp : 2592.0 ns     TailRec : 1125.0 ns
53  Rec : ... ns              Func : 1254.0  ns     Imp : 3029.0 ns     TailRec : 1108.0 ns
54  Rec : ... ns              Func : 1253.0  ns     Imp : 2800.0 ns     TailRec : 1083.0 ns
55  Rec : ... ns              Func : 1289.0  ns     Imp : 2690.0 ns     TailRec : 1091.0 ns
56  Rec : ... ns              Func : 1190.0  ns     Imp : 3117.0 ns     TailRec : 1163.0 ns
57  Rec : ... ns              Func : 1396.0  ns     Imp : 2909.0 ns     TailRec : 1095.0 ns
58  Rec : ... ns              Func : 1326.0  ns     Imp : 2682.0 ns     TailRec : 1255.0 ns
59  Rec : ... ns              Func : 1520.0  ns     Imp : 2601.0 ns     TailRec : 1123.0 ns
60  Rec : ... ns              Func : 1355.0  ns     Imp : 3006.0 ns     TailRec : 1117.0 ns
61  Rec : ... ns              Func : 1522.0  ns     Imp : 3144.0 ns     TailRec : 1222.0 ns
62  Rec : ... ns              Func : 1461.0  ns     Imp : 2716.0 ns     TailRec : 1203.0 ns
63  Rec : ... ns              Func : 1611.0  ns     Imp : 3040.0 ns     TailRec : 1504.0 ns
64  Rec : ... ns              Func : 1902.0  ns     Imp : 3486.0 ns     TailRec : 1508.0 ns
65  Rec : ... ns              Func : 1454.0  ns     Imp : 3067.0 ns     TailRec : 1375.0 ns
66  Rec : ... ns              Func : 1689.0  ns     Imp : 7018.0 ns     TailRec : 1155.0 ns
67  Rec : ... ns              Func : 1697.0  ns     Imp : 3407.0 ns     TailRec : 1274.0 ns
68  Rec : ... ns              Func : 1552.0  ns     Imp : 3287.0 ns     TailRec : 1157.0 ns
69  Rec : ... ns              Func : 1504.0  ns     Imp : 3402.0 ns     TailRec : 1304.0 ns
70  Rec : ... ns              Func : 1584.0  ns     Imp : 3404.0 ns     TailRec : 1180.0 ns
71  Rec : ... ns              Func : 1479.0  ns     Imp : 3582.0 ns     TailRec : 1171.0 ns
72  Rec : ... ns              Func : 1441.0  ns     Imp : 9376.0 ns     TailRec : 1135.0 ns
73  Rec : ... ns              Func : 1381.0  ns     Imp : 3815.0 ns     TailRec : 1300.0 ns
74  Rec : ... ns              Func : 1429.0  ns     Imp : 3355.0 ns     TailRec : 1313.0 ns
75  Rec : ... ns              Func : 1798.0  ns     Imp : 3474.0 ns     TailRec : 1255.0 ns
76  Rec : ... ns              Func : 1546.0  ns     Imp : 3356.0 ns     TailRec : 1291.0 ns
77  Rec : ... ns              Func : 1487.0  ns     Imp : 3628.0 ns     TailRec : 1411.0 ns
78  Rec : ... ns              Func : 1485.0  ns     Imp : 3317.0 ns     TailRec : 1334.0 ns
79  Rec : ... ns              Func : 2602.0  ns     Imp : 3611.0 ns     TailRec : 1270.0 ns
80  Rec : ... ns              Func : 1641.0  ns     Imp : 9835.0 ns     TailRec : 1324.0 ns
81  Rec : ... ns              Func : 5403.0  ns     Imp : 4017.0 ns     TailRec : 1339.0 ns
82  Rec : ... ns              Func : 1501.0  ns     Imp : 3585.0 ns     TailRec : 1352.0 ns
83  Rec : ... ns              Func : 1555.0  ns     Imp : 3912.0 ns     TailRec : 6060.0 ns
84  Rec : ... ns              Func : 1684.0  ns     Imp : 3407.0 ns     TailRec : 1363.0 ns
85  Rec : ... ns              Func : 1602.0  ns     Imp : 4154.0 ns     TailRec : 1436.0 ns
86  Rec : ... ns              Func : 1582.0  ns     Imp : 4036.0 ns     TailRec : 1297.0 ns
87  Rec : ... ns              Func : 1573.0  ns     Imp : 4014.0 ns     TailRec : 1277.0 ns
88  Rec : ... ns              Func : 1421.0  ns     Imp : 3931.0 ns     TailRec : 1372.0 ns
89  Rec : ... ns              Func : 1634.0  ns     Imp : 3884.0 ns     TailRec : 1263.0 ns
90  Rec : ... ns              Func : 1897.0  ns     Imp : 3742.0 ns     TailRec : 1251.0 ns
91  Rec : ... ns              Func : 1807.0  ns     Imp : 3955.0 ns     TailRec : 1283.0 ns
92  Rec : ... ns              Func : 2041.0  ns     Imp : 3886.0 ns     TailRec : 1342.0 ns
93  Rec : ... ns              Func : 4576.0  ns     Imp : 7291.0 ns     TailRec : 1284.0 ns
94  Rec : ... ns              Func : 1805.0  ns     Imp : 4744.0 ns     TailRec : 1469.0 ns
95  Rec : ... ns              Func : 1841.0  ns     Imp : 4531.0 ns     TailRec : 1280.0 ns
96  Rec : ... ns              Func : 1965.0  ns     Imp : 4045.0 ns     TailRec : 1458.0 ns
97  Rec : ... ns              Func : 1966.0  ns     Imp : 4442.0 ns     TailRec : 1289.0 ns
98  Rec : ... ns              Func : 1847.0  ns     Imp : 4426.0 ns     TailRec : 1397.0 ns
99  Rec : ... ns              Func : 1778.0  ns     Imp : 4619.0 ns     TailRec : 1568.0 ns
100 Rec : ... ns              Func : 1950.0  ns     Imp : 4976.0 ns     TailRec : 1585.0 ns
```

## Async Plot T(44) 
![](http://i.imgur.com/hNJahRz.png)

## Async Plot T(200) 
![](http://i.imgur.com/6KploMB.png)

##Async Plot T(565)
![](http://i.imgur.com/rvUDmfw.png)

##Async Plot T(1000)
![](http://i.imgur.com/tePi6fJ.png)

##Async Plot T(2035)
![](http://i.imgur.com/HzWqh91.png)

##Async Plot T(2499)
![](http://i.imgur.com/FfXjZ1c.png)



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
