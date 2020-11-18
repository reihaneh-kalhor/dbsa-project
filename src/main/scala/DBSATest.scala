import java.io.FileReader

import org.omg.CORBA.Environment

object DBSATest extends App {

  var inputStream = new InputStream(".\\sampleFile.txt")
  inputStream.open

  inputStream.seek(40)
  println(inputStream.toString)

  while(!inputStream.endOfStream(inputStream.fileReader)) {
    inputStream.readLine
    println(inputStream.toString)
  }

  inputStream.resetStringBuffer

  inputStream.close
  inputStream.open

  while(!inputStream.endOfStream(inputStream.bufferedReader)) {
    inputStream.readLineByBuffer
    println(inputStream.toString)
  }

  inputStream.close
}
