
import api.Fibonacci
import org.jfree.chart.JFreeChart
import src.Recursive
import src.TailRec
import src.util.PlotK
import java.awt.EventQueue
import kotlin.concurrent.thread

/**
 * Created by vicboma on 31/10/15.
 */
fun main(args: Array<String>) {
    val rangeSequence = 50
    val namePlot = "Fibonacci"
    val plot = PlotK(namePlot,rangeSequence)
    val listOfFibo = listFibonacci()
    execute(listOfFibo, rangeSequence,plot)
}

private fun <T>execute(fibonacci: T, sequence: Long): Long where T: Fibonacci  = fibonacci.method(sequence)

private fun listFibonacci() = listOf(Recursive(), TailRec())


private fun execute<T>(listFibo: List<T>, rangeSequence: Int, plot : PlotK) where T: Fibonacci {

    for(fibo in listFibo) {
        thread{
            for (sequence in 0..rangeSequence) {
                val execute = calcule(fibo, sequence)
                draw(execute, fibo, plot, sequence)
            }
        }
    }
}

private fun <T> calcule(fibo: T, sequence: Int): Long where T : Fibonacci {
    val timeInit = System.nanoTime()
    val execute = execute(fibo, sequence.toLong())
    val timeEnd = System.nanoTime() - timeInit
    println("${fibo.javaClass.canonicalName} ${execute}")
    return execute
}

private fun <T> draw(execute: Long, fibo: T, plot: PlotK, sequence: Int) where T : Fibonacci {
    EventQueue.invokeAndWait {
        addValueDataSet(fibo, plot, sequence, execute)
        drawPlot(plot.chart, sequence)
    }
}

private fun addValueDataSet<T>(fibo: T, plot: PlotK, sequence: Int, value: Long) where T : api.Fibonacci {
    plot.dataset.addValue(value, fibo.javaClass.canonicalName, sequence)
}

private fun drawPlot(plot : JFreeChart, sequence: Int) {
    plot.setTitle("Fibonacci T($sequence)")
}
























