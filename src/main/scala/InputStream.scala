import java.io.{File, FileReader}
import java.io.RandomAccessFile

import scala.collection.mutable.ListBuffer

class InputStream(fileAddress: String){

  var stream: Array[Char] = Array()

  def open = {
    new FileReader(fileAddress)
  }

  def readLine(fileReader: FileReader): Array[Char] = {
    val nextChar = fileReader.read.toChar
    if( nextChar != '\r'){
      stream = stream :+ nextChar
      readLine(fileReader)
    }
    else
      stream
  }

  def seek(fileReader: FileReader, pos: Int)  ={

    def readLine(randomAccessFile: RandomAccessFile): Array[Char] = {
      val nextChar = randomAccessFile.readByte.toChar
      if( nextChar != '\r'){
        stream = stream :+ nextChar
        readLine(randomAccessFile)
      }
      else
        stream
    }

      val raf = new RandomAccessFile(fileAddress, "r")
      raf.seek(pos)
      readLine(raf)
  }

  def endOfStream = {

  }

}
