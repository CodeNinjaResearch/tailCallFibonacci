import api.Fibonacci
import src.Imperative
import src.Recursive

/**
 * Created by vicboma on 31/10/15.
 */
fun main(args: Array<String>) {
    val rangeSequence = 50

    recursive(rangeSequence)
    imperative(rangeSequence)
}

private fun recursive(rangeSequence: Int) {
    val fibonacci = Recursive()
    for (sequence in 0..rangeSequence) {
        execute(fibonacci, sequence)
    }
}

private fun imperative(rangeSequence: Int) {
    val fibonacci = Imperative()
    for (sequence in 0..rangeSequence) {
        execute(fibonacci, sequence)
    }
}

private fun execute(fibonacci: Fibonacci, sequence: Int) {
    val timeInit = System.nanoTime();
    val result = fibonacci.method(sequence)
    val timeEnd = System.nanoTime() - timeInit;
    println(" F($sequence) : $result \t\t [ $timeEnd ns - ${timeEnd/1000000} ms ]")
}