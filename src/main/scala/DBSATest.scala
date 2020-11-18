import java.io.FileReader

import org.omg.CORBA.Environment

object DBSATest extends App {

  var inputStream = new InputStream(".\\sampleFile.txt")
  inputStream.open
  
  //go forward pointer for 40 position
  inputStream.seek(40)
  println(inputStream.toString)

  //read each lines using readline function
  while(!inputStream.endOfStream(inputStream.fileReader)) {
    inputStream.readLine
    println(inputStream.toString)
  }

  inputStream.resetStringBuffer
  inputStream.close
  
  //read each lines using readLineByBuffer function
  inputStream.open

  while(!inputStream.endOfStream(inputStream.bufferedReader)) {
    inputStream.readLineByBuffer
    println(inputStream.toString)
  }

  inputStream.close
}
