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
        val fibF = Functional()
        val tailRec = TailRec()


        for(sequence in 0..20) {
            val resR = fibR.method(sequence.toLong())
            val resI = fibI.method(sequence.toLong())
            val resF = fibF.method(sequence.toLong())
            val resT = tailRec.method(sequence.toLong())

            assertTrue {
                "Fail resutl"
                (resI == resR) && (resI == resT)
                (resI == resF) && (resR == resI)
                (resR == resT) && (resF == resT)
                (resF == resR)
            }
        }
    }
}


