import java.io.{File, FileReader, RandomAccessFile}
import Streams.InputStream
import TestAlgorithms.readerStream

object SequentialReading {

  // High order function that takes readln as function.
  def Length(file: File): Long = {

    var inputStream = new InputStream(file)
    inputStream.open
    var count = 0
    //    inputStream.setBufferSize(1024)
    while (!inputStream.endOfStream) {
//      var line = inputStream.readCharacter //72031550
//      var line = inputStream.readLine //72031550
//      var line = inputStream.readCharacterWithBuffer //72031550
      var line = inputStream.readFromMappedMemory(1024)

      // println(counter + ": " + line + " length: " + line.length())
      count += line.length
    }
    inputStream.close
    count
  }

  def main(args: Array[String]): Unit = {

    var file = new File("src/main/resources/aka_name.csv")
    println(Length(file))

  }
}

