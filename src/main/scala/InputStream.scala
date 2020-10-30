import java.io.FileReader

import scala.collection.mutable.ListBuffer
import scala.util.{Failure, Success, Try}

class InputStream(fileAddress: String){

  var stream: ListBuffer[Int] = new ListBuffer[Int]()

  def open = {
    new FileReader(fileAddress)
  }

  def readLine: ListBuffer[Int] = {
    val fileReader = open
    val nextChar = fileReader.read
    if( nextChar != '\n'){
      stream += nextChar
      readLine
    }
    else
      stream
  }

  def seek(pos: Int): Unit ={

  }

  def endOfStream = {

  }

}
