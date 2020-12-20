import Streams.{InputStream, OutputStream}

import java.io.File

object TestAlgorithms extends App{

  val readerStream = new InputStream(new File("src/main/resources/sample.txt"))
  readerStream.open
  var line : StringBuffer = new StringBuffer()
//  readerStream.setBufferSize(1024)

  while(!readerStream.endOfStream){
    line = readerStream.readFromMappedMemory(170)
    println(line)
  }

//  val outputStream = new OutputStream(new File("src/main/resources/sample.txt"))
//  outputStream.create
//  var i: Int = 0
//  outputStream.setBufferSize(5)
//  var line = ""
//  while( i <= 20){
//    line = line + i + "- Hello World" + System.lineSeparator
//    i += 1
//  }
////  outputStream.setBufferSize(50)
//  while(!outputStream.allFileWritten) {
//    outputStream.writeInMappedMemory(100, line)
//  }



}
