object DBSATest extends App {

  var inputStream = new InputStream(".\\sampleFile.csv")
  inputStream.open

  while (!inputStream.endOfStream){
    inputStream.readLineByBuffer
    println(inputStream.toString)
  }













}
