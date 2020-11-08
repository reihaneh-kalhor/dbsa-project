object DBSATest extends App {

  var inputStream = new InputStream(".\\sampleFile1.csv")
  inputStream.open
//  inputStream.readLine
//  println(inputStream.endOfStream)
  inputStream.seek(5)
//  inputStream.seek(13)
  println(inputStream.toString)
//  println(inputStream.endOfStream)
//
//  var outputStream = new OutputStream(".\\sampleOutput.csv")
//  outputStream.create
//  outputStream.writeLine("my name is reihaneh")
//  outputStream.writeLine("kalhor")
//
//
//  outputStream.writeLineByBuffer("Hello")
//  outputStream.writeLineByBuffer("My name is Reyhaneh")










}
