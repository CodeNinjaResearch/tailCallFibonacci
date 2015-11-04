package src

import org.junit.Test
import kotlin.test.assertTrue

/**
 * Created by vicboma on 04/11/15.
 */
class FuncAbstractTest {

    @Test
    fun testMethodTail() {
        val expected = 8
        val a = 2
        val b = 2
        val result = testTail(a, b)
        assertTrue("Fail testTail", {
            expected === result
            a === 2
        })
    }

    @Test
    fun testMethod() {
        val expected = 8
        val a = 2
        val b = 2
        val result = test(a, b)
        assertTrue("Fail test", {
            expected === result
            a === 2
        })
    }

    tailrec fun testTail(a: Int, b: Int): Int {
        if (a <= 0)
            return b;
        return (testTail(a - 1, b + 1) + 2)
    }

    tailrec fun test(a: Int, b: Int): Int {
        if (a <= 0)
            return b;
        return test(a - 1, b + 1)
    }
}