import Experiment_1_3.CombinedReadingAndWritingRR
import Streams.{InputStream, OutputStream}

import java.io.File

object TestAlgorithms extends App{

  val root = "src/main/resources/"
  val outPutFileAddress = root + "output.txt"
  val inputFilesAddress = Array(root + "IN_1.txt", root + "IN_2.txt" , root + "IN_3.txt")
//
  val crawrr = new CombinedReadingAndWritingRR(outPutFileAddress, inputFilesAddress)
  crawrr.rrmerge

//  val readerStream = new InputStream(new File(inputFilesAddress(0)))
//  readerStream.open
//  var line : StringBuffer = new StringBuffer()
//  readerStream.setBufferSize(1024)
//
//    val outputStream = new OutputStream(new File(outPutFileAddress))
//    outputStream.create
//  outputStream.setBufferSize(1024)

//  while(!readerStream.endOfStream){
//    line = readerStream.readFromMappedMemory(1024)
//    outputStream.writeInMappedMemory(1024, line.toString)
//  }
//  outputStream.close

}
