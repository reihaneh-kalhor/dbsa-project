package Experiment_1_1.SequentialReadingBenchmarks

import Experiment_1_1.SequentialReading
import Streams.InputStream

import java.io.File
import java.util

case class LengthByReadCharacterBM(fileAddress: String) extends Benchmark(fileAddress) {

  override var repeatIndex: Int = 0

  def benchmark: Unit = {

  //Run benchmark
  while (repeatIndex < REPEATS) {
  inputStream.open

  startTime = System.nanoTime

  length = SequentialReading.LengthByReadCharacter(inputStream)

  endTime = System.nanoTime

  inputStream.close

  val duration = (endTime - startTime) / 100000
  printIntermediateResult("Calculating length of File by \"LengthByReadCharacter\" Function takes: " + duration + "ms")
  if (repeatIndex >= WARMUP) durations.add(duration)
  repeatIndex += 1
}
  //Report Result
  averageTime = calculateAvgDuration(durations)
  printResults(WARMUP, REPEATS, averageTime, length)
}
}
