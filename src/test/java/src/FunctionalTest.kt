package src

import org.junit.Test
import kotlin.test.assertTrue

/**
 * Created by vicboma on 01/11/15.
 */
class FunctionalTest{

    val expected = arrayOf<Long>(0,1,1,2,3,5,8,13,21,34,55,89,144,233,377,610,987,1597,2584,4181,6765,10946,17711,28657,46368,75025,121393,196418,317811,514229,832040,1346269,2178309,3524578,5702887,9227465,14930352,24157817,39088169,63245986,102334155,165580141,267914296,433494437,701408733,1134903170)

    @Test
    fun testMethod() {
        val fibonacci = Functional()
        val size = expected.size-1
        for(sequence in 0..size) {
            val result = fibonacci.method(sequence.toLong())
            assertTrue {
                "Fail resutl"
                result == expected.get(sequence)
            }
        }
    }
}