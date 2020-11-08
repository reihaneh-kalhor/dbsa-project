object DBSATest extends App {

  var inputStream = new InputStream(".\\sampleFile.csv")
  inputStream.open
  inputStream.readLine
  println(inputStream.endOfStream)
  inputStream.seek(5)
  inputStream.seek(13)
  println(inputStream.toString)
  println(inputStream.endOfStream)









}
