import Streams.{InputStream, OutputStream}

import java.io.File

object TestAlgorithms extends App{

  val readerStream = new InputStream(new File("src/main/resources/aka_name.csv"))
  readerStream.open
  var line : StringBuffer = new StringBuffer()
//  readerStream.setBufferSize(1024)

  while(!readerStream.endOfStream){
    line = readerStream.readFromMappedMemory(170)
    println(line)
  }



}
