import Experiment_1_3.CombinedReadingAndWriting
import Streams.{InputStream, OutputStream}

import java.io.File

object TestAlgorithms extends App{

  val root = "src/main/resources/"
  val outPutFileAddress = root + "output.txt"
  val inputFilesAddress = Array(root + "IN_1.txt", root + "IN_2.txt" , root + "IN_3.txt")

//    val combinedReadingAndWriting = new CombinedReadingAndWriting(outPutFileAddress, inputFilesAddress)
//    combinedReadingAndWriting.rrmerge

//  val readerStream = new InputStream(new File(inputFilesAddress(0)))
val readerStream = new InputStream(new File(root + "aka.txt"))
  readerStream.open
  var line : StringBuffer = new StringBuffer()
//  readerStream.setBufferSize(1024)
//
    val outputStream = new OutputStream(new File(outPutFileAddress))
    outputStream.create
  outputStream.setBufferSize(1024)

  while(!readerStream.endOfStream){
    line = readerStream.readFromMappedMemory(1024)
    System.out.println(line)
    outputStream.writeInMappedMemory(1024, line.toString)
  }
  outputStream.close

}
