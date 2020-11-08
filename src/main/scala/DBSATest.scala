object DBSATest extends App {

  var inputStream = new InputStream(".\\sampleFile.csv")
  inputStream.open
  inputStream.readLine
  println(inputStream.endOfStream)
  inputStream.seek(5)
  inputStream.seek(13)
  println(inputStream.toString)
  println(inputStream.endOfStream)


  var outputStream = new OutputStream(".\\sampleOutput.csv")
  outputStream.create
  outputStream.writeLine("test1")
  outputStream.writeLine("test2")
  outputStream.writeLineByBuffer("test3")
  outputStream.writeLineByBuffer("test4")
  outputStream.close










}
