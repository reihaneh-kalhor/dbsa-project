import Streams.{InputStream, OutputStream}

import java.io.File

object TestAlgorithms extends App{
//  val writerStream = new OutputStream(new File("src/main/resources/sample1.txt"))
//  writerStream.create()
//  writerStream.writeToMappedMemory(0,10000,"hello world")



  val readerStream = new InputStream(new File("src/main/resources/sample.txt"))
  readerStream.open
  val a: StringBuffer =  readerStream.readFromMappedMemory(0,6)
  print(a)

}
