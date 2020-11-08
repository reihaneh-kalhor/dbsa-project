import java.io.{BufferedReader, File, FileInputStream, FileReader, RandomAccessFile}

import scala.collection.mutable.ListBuffer

class InputStream(fileAddress: String){

  var fileReader: FileReader = null //main stream

  var randomAccessFile: RandomAccessFile = null //used in seek function
  var bufferedReader: BufferedReader = null //used in second readLine
  var stringBuffer: StringBuffer = null //to show output

  def open = {
    fileReader = new FileReader(fileAddress)
    bufferedReader =  new BufferedReader(fileReader)
    stringBuffer = new StringBuffer
  }

  def resetStringBuffer ={
    stringBuffer = new StringBuffer
  }

  def readLine: StringBuffer = {
    val nextChar = fileReader.read.toChar
    if( nextChar != '\r'){
      stringBuffer.append(nextChar)
      readLine
    }
    else
      stringBuffer.append(System.lineSeparator())
      stringBuffer
  }

  def readLineByBuffer: StringBuffer = {
    bufferedReader = new BufferedReader(fileReader)
    val line = bufferedReader.readLine()
    stringBuffer.append(line).append(System.lineSeparator())
    stringBuffer
  }

  def seek(pos: Int): StringBuffer = {
    randomAccessFile = new RandomAccessFile(fileAddress, "r")
    randomAccessFile.seek(pos)
    fileReader = new FileReader(randomAccessFile.getFD)
    bufferedReader =  new BufferedReader(fileReader);
    val line = bufferedReader.readLine
    stringBuffer.append(line)
    if(!endOfStream) stringBuffer.append(System.lineSeparator())
    stringBuffer
  }

  def endOfStream: Boolean = {
    bufferedReader.readLine() == null
  }

  override def toString: String = stringBuffer.toString

}
