package Streams

import java.io.{BufferedWriter, File, FileWriter, RandomAccessFile}
import java.nio.channels.FileChannel

//1.2 Writing
class OutputStream(file: File) {

  private var fileWriter: FileWriter = null // Main writer stream.
  private var bufferedWriter: BufferedWriter = null // Used for writing line.
  private var randomAccessFile: RandomAccessFile = null // Used for seeking a position in file.

  private var stringBuffer: StringBuffer = null // To show output.

  // Open file for writing.
  def create(): Unit = {
    try {
      fileWriter = new FileWriter(file)
      bufferedWriter = new BufferedWriter(fileWriter)
      randomAccessFile = new RandomAccessFile(file, "rw")
    } catch {
      case _ => throw new Exception("Exception in creating file ...")
    }
  }

  // Implementation 1.1.1
  // Write one character to file.
  def writeCharacter(line: StringBuffer): Unit = {
    try {
      var i = 0
      while (i < line.length()) {
        fileWriter.write(line.charAt(i))
        i += 1
      }
      fileWriter.write(System.lineSeparator)
    } catch {
      case _ => throw new Exception("Stream has not been created ...")
    }
  }

  // Implementation 1.1.2
  // Write one line to file.
  def writeLine(line: String): Unit = {
    try{
      bufferedWriter.write(line)
      bufferedWriter.write(System.lineSeparator)
      bufferedWriter.flush()
    } catch {
      case _ => throw new Exception("Stream has not been created ...")
    }
  }

  // Implementation 1.1.3
  //write B character of line into the stream
  def writeCharacterWithBuffer(bufferSize : Int, line: String): Unit = {
    try{
      var i = 0
      while(i < bufferSize && i < line.length()){
        fileWriter.write(line.charAt(i))
        i += 1
      }
    }catch {
      case _ => throw new Exception("Stream has not been opened ...")
    }
  }

  // Implementation 1.1.4
  def writeToMappedMemory(startPosition : Int = 0 ,bufferSize : Int, string: String): Unit = {
    try {
      val fileChannel: FileChannel = randomAccessFile.getChannel()
      var writerMemoryMapping = fileChannel.map(FileChannel.MapMode.READ_WRITE, 0 , bufferSize)
      var i = 0
      while(writerMemoryMapping.hasRemaining && i < fileChannel.size() && i<bufferSize && i< string.length )
      {
        writerMemoryMapping.putInt(string.charAt(i).asInstanceOf[Int])
        i += 8
      }
    } catch {
      case _ => throw new Exception("Stream has not been opened ...")
    }
  }


  def close: Unit = {
    try {
      fileWriter.close()
      bufferedWriter.close
    } catch {
      case _ => throw new Exception("Stream has not been created ...")
    }
  }
}
