

import api.Fibonacci
import org.jfree.chart.ChartFactory
import org.jfree.chart.ChartPanel
import org.jfree.chart.JFreeChart
import org.jfree.chart.axis.CategoryLabelPositions
import org.jfree.chart.plot.PlotOrientation
import org.jfree.data.category.DefaultCategoryDataset
import src.Functional
import src.Imperative
import src.Recursive
import src.TailRec
import java.awt.Color
import java.awt.EventQueue
import javax.swing.JFrame
import kotlin.concurrent.thread

/**
 * Created by vicboma on 31/10/15.
 */
fun main(args: Array<String>) {
    var dataset = DefaultCategoryDataset();
    val rangeSequence = 50
    val plot = configurePlot(dataset,rangeSequence)
    val listOfFibo = listFibonacci()
    execute(listOfFibo, rangeSequence,plot,dataset)
}

private fun <T>execute(fibonacci: T, sequence: Long): Long where T: Fibonacci  = fibonacci.method(sequence)

private fun listFibonacci() = listOf(Recursive(), Functional(), Imperative(), TailRec())

private fun execute<T : Fibonacci>(listFibo: List<T>, rangeSequence: Int, plot : JFreeChart,dataset: DefaultCategoryDataset) {
    val max = 14000L
    for(fibo in listFibo) {
        thread{
            for (sequence in 0..rangeSequence) {
                val timeInit = System.nanoTime()
                execute(fibo, sequence.toLong())
                val timeElapsed = System.nanoTime() - timeInit
                var timeEnd = timeElapsed
                if(timeElapsed > max)
                    timeEnd = max

                drawAndUpdatePlot(plot, dataset, fibo, sequence, timeEnd.toDouble())
            }
        }
    }
}

private fun <T : Fibonacci> drawAndUpdatePlot(plot : JFreeChart,dataset: DefaultCategoryDataset, fibo: T, sequence: Int, timeEnd: Double) {
    EventQueue.invokeAndWait {
        plot.setTitle("Async Chart Fibonacci T($sequence)")
        dataset.addValue(timeEnd, fibo.javaClass.canonicalName, sequence)
        println("${fibo.javaClass.canonicalName} ${timeEnd}")
    }
}


private fun configurePlot(DefaultCategoryDataset : DefaultCategoryDataset, iter : Int): JFreeChart {
    val (chart, chartPanel) = configureChart(DefaultCategoryDataset, iter)

    val frame = JFrame("Fibonacci");
    frame.setSize(1280, 670);
    frame.setContentPane(chartPanel);
    frame.setVisible(true);

    return chart
}

private fun configureChart(DefaultCategoryDataset: DefaultCategoryDataset, iter: Int): Pair<JFreeChart, ChartPanel> {
    val chart = ChartFactory.createBarChart(
            "Async Chart Fibonacci T($iter)",
            "Iteration",
            "Time ns",
            DefaultCategoryDataset,
            PlotOrientation.VERTICAL,
            true,
            true,
            false
    );

    chart.setBackgroundPaint(Color.white);
    val plot = chart.getCategoryPlot();
    plot.setBackgroundPaint(Color.lightGray);
    plot.setDomainGridlinePaint(Color.white);
    plot.setDomainGridlinesVisible(true);
    plot.setRangeGridlinePaint(Color.white);

    val domainAxis = plot.getDomainAxis();
    domainAxis.setCategoryLabelPositions(CategoryLabelPositions.createUpRotationLabelPositions(Math.PI / 6.0));

    val chartPanel = ChartPanel(chart);
    return Pair(chart, chartPanel)
}

