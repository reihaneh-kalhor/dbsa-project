import java.io.FileReader

import scala.collection.mutable.ListBuffer
import scala.util.{Failure, Success, Try}

class InputStream(fileAddress: String){

  var stream: ListBuffer[String] = new ListBuffer[String]()

  def open = {
    new FileReader(fileAddress)
  }

  def readln = {
    val fileReader = open
    fileReader.read
  }

  def seek(pos: Int): Unit ={

  }

  def endOfStream = {

  }

}
