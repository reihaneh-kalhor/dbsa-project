package Experiment_1_3

import Streams.{InputStream, OutputStream}

import java.io.File

class CombinedReadingAndWritingRR(outputFileAddress: String, inputFilesAddressArrays: Array[String]){
  private val outputStream: OutputStream = new OutputStream(new File(outputFileAddress))
  outputStream.create

  //rrmerge function
  def rrmerge: Unit = {
    var i: Int = 0
    while (i < inputFilesAddressArrays.length){
      val writerThread = new Thread(WriteInFile(inputFilesAddressArrays(i)))
      writerThread.start
      i +=1
    }
  }


  case class WriteInFile(currentInputFileAddress: String) extends
  Runnable{

    private var currentLine: StringBuffer = new StringBuffer()
    private val currentInputStream: InputStream = new InputStream(new File(currentInputFileAddress))
    currentInputStream.open

    override def run(): Unit = {
        try{
          while(!currentInputStream.endOfStream){
            outputStream.writeCharacter(currentLine.toString)
            currentLine = currentInputStream.readCharacter
          }
          currentInputStream.close
        }catch {
          case _ => throw new Exception("Exception in synchronization")
        }
    }
  }
}
