package src

import org.junit.Test
import kotlin.test.assertTrue

/**
 * Created by vicboma on 31/10/15.
 */
class VersusTest {

    @Test
    fun testVersus() {
        val fibR = Recursive()
        val fibI = Imperative()

        for(sequence in 0..20) {
            val resR = fibR.method(sequence)
            val resI = fibI.method(sequence)

            assertTrue {
                "Fail resutl"
                resI == resR
            }
        }
    }
}


