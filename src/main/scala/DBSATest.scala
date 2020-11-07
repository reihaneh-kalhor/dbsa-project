object DBSATest extends App {

  var inputStream = new InputStream(".\\sampleFile.csv")
  var fileReader = inputStream.open
//  val line = inputStream.readLine(fileReader).mkString("")
//  println(line)

  val seekP = inputStream.seek(fileReader, 5)
  println(seekP.mkString(""))






}
