package Streams

import java.io.{BufferedWriter, File, FileOutputStream, FileWriter, RandomAccessFile}
import java.nio.{ByteBuffer, CharBuffer, MappedByteBuffer}
import java.nio.channels.FileChannel
import java.nio.charset.Charset


//1.2 Writing
class OutputStream(file: File) {

  private var fileWriter: FileWriter = null // Main writer stream.
  private var bufferedWriter: BufferedWriter = null // Used for writing line.
  private var randomAccessFile: RandomAccessFile = null // Used for seeking a position in file.
  private var mappedByteBuffer: MappedByteBuffer = null

  private var fileOutputStream: FileOutputStream = null
  private var fileChannel: FileChannel = null
  private var currentPosition: Int = 0
  private var channelSize: Long = 0

  var allFileWritten: Boolean = false

  // Open file for writing.
  def create: Unit = {
    try {
      //used in 1.1.1
      fileWriter = new FileWriter(file)

      //used in 1.1.2 and 1.1.3
      bufferedWriter = new BufferedWriter(fileWriter)

      //used in seek and 1.1.4
      randomAccessFile = new RandomAccessFile(file, "rw")

      //used in 1.1.4
      fileOutputStream = new FileOutputStream(file)
      fileChannel = randomAccessFile.getChannel
      channelSize = fileChannel.size
      currentPosition = 0

      allFileWritten = false

    } catch {
      case _ => throw new Exception("Exception in creating file ...")
    }
  }

  // Implementation 1.1.1
  // Write one character to file.
  def writeCharacter(line: String): Unit = {
    try {
      var i = 0
      while (i < line.length()) {
        fileWriter.write(line.charAt(i))
        i += 1
      }
      fileWriter.flush
    } catch {
      case _ => throw new Exception("Stream has not been created ...")
    }
  }

  // Implementation 1.1.2
  // Write one line to file.
  def writeLine(line: String): Unit = {
    try {
      bufferedWriter.write(line)
      bufferedWriter.flush()
    } catch {
      case _ => throw new Exception("Stream has not been created ...")
    }
  }

  //Set buffer size for BufferedReader
  def setBufferSize(bufferSize: Int): Unit = {
    try {
      bufferedWriter = new BufferedWriter(fileWriter, bufferSize)
    }
    catch {
      case _ => throw new Exception("Stream has not been opened ...")
    }
  }

  // Implementation 1.1.3
  //write B character of line into the stream
  def writeCharacterIntoBuffer(line: String): Unit = {
    try {
      var i = 0
      while (i < line.length()) {
        bufferedWriter.write(line.charAt(i))
        i += 1
      }
      bufferedWriter.flush()
    } catch {
      case _ => throw new Exception("Stream has not been opened ...")
    }
  }

  // Implementation 1.1.4
  def writeInMappedMemory(bufferSize: Int, string: String): Unit = {
    try {
      var size = 0
      var newString = string
      var endIndex = 0
      var bytesWritten = 0
      if (string.length > bufferSize) {
        size = bufferSize
        endIndex = bufferSize + currentPosition
        if ((bufferSize + currentPosition) > string.length) endIndex = string.length
        newString = string.substring(currentPosition, endIndex)
        currentPosition = currentPosition + bufferSize
      }
      else if(string.length <= bufferSize){
        size = string.length
      }

      mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_WRITE, currentPosition, size)

//    newString.foreach(str => {
//      mappedByteBuffer.put(str.asInstanceOf[Byte])
//      bytesWritten += 1
//    })


      val charBuffer: CharBuffer = CharBuffer.wrap(newString)
      while(charBuffer.hasRemaining){
        fileChannel.write(Charset.forName("utf-8").encode(charBuffer))
      }
      mappedByteBuffer.clear


      if (currentPosition >= string.length || bufferSize > string.length) {
        allFileWritten = true
      }
    } catch {
      case _ => throw new Exception("Stream has not been opened ...")
    }
  }


  def close: Unit = {
    try {
      fileWriter.close
      bufferedWriter.close
      if(mappedByteBuffer != null) mappedByteBuffer.force
      fileChannel.truncate(file.length())
    } catch {
      case _ => throw new Exception("Stream has not been created ...")
    }
  }
}
