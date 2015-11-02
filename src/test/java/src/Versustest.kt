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
        var matrix = Matrix()
        val doubling = Doubling()


        for(sequence in 0..20) {
            val toLong = sequence.toLong()
            val resR = fibR.method(toLong)
            val resI = fibI.method(toLong)
            val resF = fibF.method(toLong)
            val resT = tailRec.method(toLong)
            val resM = matrix.method(toLong)
            val resD = doubling.method(toLong)
            assertTrue {
                "Fail resutl"
                (resI == resR)
                (resI == resT)
                (resI == resF)
                (resF == resT)
                (resF == resR)
                (resR == resT)
                (resM == resT)
                (resM == resF)
                (resM == resI)
                (resD == resI)
                (resD == resR)
                (resD == resF)
                (resD == resT)
                (resD == resM)
            }
        }
    }
}


