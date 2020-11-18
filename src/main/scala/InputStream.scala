import java.io.{BufferedReader, File, FileInputStream, FileReader, RandomAccessFile}

class InputStream(fileAddress: String){

  var fileReader: FileReader = null //main stream

  var randomAccessFile: RandomAccessFile = null //used in seek function
  var bufferedReader: BufferedReader = null //used in second readLine
  var stringBuffer: StringBuffer = null //to show output
  var file: File = null

  var cursorPosition: Long = 0

  def open = {

    cursorPosition = 0
    file = new File(fileAddress)

    if(!file.exists){
      throw new Exception("File does not exist ...")
    }

    fileReader = new FileReader(fileAddress)
    bufferedReader =  new BufferedReader(fileReader)
    stringBuffer = new StringBuffer
  }

  def resetStringBuffer ={
    stringBuffer = new StringBuffer
  }

  def readLine: StringBuffer = {

    if(fileReader == null){
      throw new Exception("Stream has not been opened ...")
    }

    resetStringBuffer
    var nextChar = fileReader.read.toChar
    while(nextChar != '\r') {
      stringBuffer.append(nextChar)
      nextChar = fileReader.read.toChar
      cursorPosition += cursorPosition
    }
    stringBuffer
  }

  def readLineByBuffer: StringBuffer = {

    if(fileReader == null){
      throw new Exception("Stream has not been opened ...")
    }

    resetStringBuffer
    val line: String = bufferedReader.readLine.toString
    cursorPosition += line.length
    stringBuffer.append(line)
    stringBuffer
  }

  def seek(pos: Int): StringBuffer  = {

    if(!new File(fileAddress).exists){
      throw new Exception("File does not exist ...")
    }

    randomAccessFile = new RandomAccessFile(fileAddress, "r")
    randomAccessFile.seek(pos)
    fileReader = new FileReader(randomAccessFile.getFD)
    bufferedReader =  new BufferedReader(fileReader);
    val line = bufferedReader.readLine
    cursorPosition += line.length
    stringBuffer.append(line)
    if(!endOfStream) stringBuffer.append(System.lineSeparator())
    stringBuffer
  }

  def endOfStream: Boolean = {
    cursorPosition >= file.length()
  }

  override def toString: String = stringBuffer.toString


  def readLineByBuffer(bufferSize: Int) = ???

}
