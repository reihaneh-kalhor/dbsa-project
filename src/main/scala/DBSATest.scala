object DBSATest extends App {

  var inputStream = new InputStream(".\\sampleFile.csv")
  inputStream.open

  inputStream.seek(6)
  println(inputStream.toString)

  while (!inputStream.endOfStream(inputStream.bufferedReader)){
    inputStream.readLineByBuffer
    println(inputStream.toString)
  }

}
