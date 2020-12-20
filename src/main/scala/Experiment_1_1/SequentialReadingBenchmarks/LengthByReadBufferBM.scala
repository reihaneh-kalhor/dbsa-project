package Experiment_1_1.SequentialReadingBenchmarks
import Experiment_1_1.SequentialReading
import Streams.InputStream

import java.io.File

object LengthByReadBufferBM extends Benchmark("src/main/resources/aka_name.csv"){

  def main(args: Array[String]): Unit = {

    //Run benchmark
    while (repeatIndex < REPEATS) {
      inputStream.open

      startTime = System.nanoTime

      length = SequentialReading.LengthByReadBuffer(inputStream)

      endTime = System.nanoTime

      inputStream.close

      val duration = (endTime - startTime) / 100000
      printIntermediateResult("Calculating length of File by \"LengthByReadBuffer\" Function takes: " +
        duration + "ms")
      if (repeatIndex >= WARMUP) durations.add(duration)
      repeatIndex += 1
    }
    //Report Result
    averageTime = calculateAvgDuration(durations)
    printResults(WARMUP, REPEATS, averageTime, length)
  }

}
