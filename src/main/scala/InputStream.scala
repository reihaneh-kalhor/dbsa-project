import java.io.{BufferedReader, File, FileReader, RandomAccessFile}

class InputStream(fileAddress: String){

  var fileReader: FileReader = null //main stream
  var bufferedReader: BufferedReader = null //used in second readLine

  private var randomAccessFile: RandomAccessFile = null //used in seek function
  private var stringBuffer: StringBuffer = null //to show output
  private var file: File = null

  private var nextChar : Char = " ".charAt(0)
  private var nextInt = 0

  private var nextLine = ""


  def open = {

    file = new File(fileAddress)
    nextChar = " ".charAt(0)

    if(!file.exists){
      throw new Exception("File does not exist ...")
    }

    fileReader = new FileReader(file)
    bufferedReader =  new BufferedReader(fileReader)
    stringBuffer = new StringBuffer
  }

  def close: Unit = {
    nextChar = " ".charAt(0)

    if(!file.exists){
      throw new Exception("File does not exist ...")
    }
    fileReader.close
    bufferedReader.close
    stringBuffer = new StringBuffer
  }

  def resetStringBuffer ={
    stringBuffer.delete(0, stringBuffer.length)
  }

  def readLine: StringBuffer = {

    if(fileReader == null){
      throw new Exception("Stream has not been opened ...")
    }

    while(nextInt != 10) {
      nextChar = nextInt.toChar
      stringBuffer.append(nextChar)
      nextInt = fileReader.read
    }
    stringBuffer
  }

  def endOfStream(fileReader: FileReader): Boolean = {

    if(fileReader == null){
      throw new Exception("Stream has not been opened ...")
    }

    nextInt = fileReader.read
    if(nextInt == -1){
      true
    }
    else {
      false
    }
  }

  def endOfStream(bufferedReader: BufferedReader): Boolean = {

    if(bufferedReader == null){
      throw new Exception("Stream has not been opened ...")
    }

    nextLine = bufferedReader.readLine
    if(nextLine == null) true
    else false
  }

  def readLineByBuffer: StringBuffer = {

    if(fileReader == null){
      throw new Exception("Stream has not been opened ...")
    }

    resetStringBuffer
    stringBuffer.append(nextLine)
    nextLine = ""
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
    stringBuffer.append(line)
    stringBuffer
  }

  override def toString: String = stringBuffer.toString

}
