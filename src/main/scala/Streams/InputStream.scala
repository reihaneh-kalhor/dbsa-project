package Streams

import java.io.{BufferedReader, File, FileReader, IOException, RandomAccessFile}
import java.nio.channels.FileChannel
import java.io.FileNotFoundException
import java.nio.charset.StandardCharsets
import scala.util.control.Breaks.break


//1.1 Reading
class InputStream(file: File){

  private var fileReader: FileReader = null // Main reader stream.

  private var bufferedReader: BufferedReader = null // Used for reading line.
  private var randomAccessFile: RandomAccessFile = null // Used for seeking a position in file.

  private var stringBuffer: StringBuffer = null // Shows string output.

  var endOfStream = false //for detecting end of stream
  var isBufferFull = false //for detecting the full buffer
  var isEndOfMappedMemory = false

  // Initializing the fields for stream
  def open: Unit = {
    try{
      fileReader = new FileReader(file)
      bufferedReader =  new BufferedReader(fileReader)
      randomAccessFile = new RandomAccessFile(file, "rw")
      stringBuffer = new StringBuffer
    } catch {
      case _ => throw new Exception("Exception ...")
    }
  }

  // close file.
  def close: Unit = {
    try {
      fileReader.close
      bufferedReader.close
      randomAccessFile.close
      resetStringBuffer
    } catch {
      case _ => throw new Exception("Exception ...")
    }
  }

  // Implementation 1.1.1
  // Read one character and add to string buffer till end of line.
  def readCharacter: StringBuffer = {
    resetStringBuffer
    try {
      var data = fileReader.read //it reads the int of next character
      while(data != 10 && data != -1) { // 10 for detecting End of line or -1 for detecting end of file.
        stringBuffer.append(data.asInstanceOf[Char])
        data = fileReader.read
      }
      if (data == -1) endOfStream = true
      stringBuffer
    } catch {
      case _ => throw new Exception("Exception ...")
    }
  }

  // Implementation 1.1.2
  // Read one line and add to string buffer.
  def readLine: StringBuffer = {
    resetStringBuffer
    try {
      val data = bufferedReader.readLine
      if (data != 10 && data != null) { // End of line or end of file.
        stringBuffer.append(data)
      } else {
        endOfStream = true
      }
      stringBuffer
    } catch {
      case _ => throw new Exception("Exception ...")
    }
  }

  // Implementation 1.1.3
  // Read one character and add to buffer.
  def readCharacterWithBuffer(bufferSize : Int): StringBuffer = {
    resetStringBuffer
    try {
      var i = 0
      var data = fileReader.read

      while(data != -1 && i < bufferSize ) { //
        // End of line or end of file and end
        // of buffer
        stringBuffer.append(data.asInstanceOf[Char])
        data = fileReader.read
        i += 1
      }
      if(i == bufferSize) isBufferFull = true
      if (data == -1) endOfStream = true
      stringBuffer
    } catch {
      case _ => throw new Exception("Stream has not been opened ...")
    }
  }

  // Implementation 1.1.4
  // Read one character and add to buffer.
  //explanation of memorry mapping:
  //https://www.mathworks.com/help/matlab/import_export/overview-of-memory-mapping.html#:~:text=Memory%2Dmapping%20is%20a%20mechanism,within%20an%20application's%20address%20space.&text=This%20makes%20file%20reads%20and,such%20as%20fread%20and%20fwrite%20.
  //https://www.ibm.com/support/knowledgecenter/ssw_aix_72/generalprogramming/understanding_mem_mapping.html
  //https://howtodoinjava.com/java/nio/memory-mapped-files-mappedbytebuffer/
  //https://www.javacodegeeks.com/2013/05/power-of-java-memorymapped-file.html
  def readFromMappedMemory(startPosition : Int = 0, bufferSize : Int): StringBuffer = {
    try {
      resetStringBuffer
      val fileChannel: FileChannel = randomAccessFile.getChannel
      val memoryMapReader = fileChannel.map(FileChannel.MapMode.READ_WRITE, startPosition , bufferSize)
      var data = memoryMapReader.get.toChar

      while(memoryMapReader.hasRemaining)
      {
        stringBuffer.append(data)
        data = memoryMapReader.get.toChar
      }
      if(!memoryMapReader.hasRemaining) isEndOfMappedMemory = true
      if (data == -1) endOfStream = true

      stringBuffer

    } catch {
      case _ => throw new Exception("Stream has not been opened ...")
    }
  }

  // Seek position in line.
  def seek(pos: Long): Unit = {
    try {
      randomAccessFile.seek(pos)
      fileReader = new FileReader(randomAccessFile.getFD)
      bufferedReader =  new BufferedReader(fileReader)
    } catch {
      case _ => throw new Exception("File does not exist ...")
    }
  }

  def resetStringBuffer(): Unit = {
    stringBuffer = new StringBuffer()
  }
}

