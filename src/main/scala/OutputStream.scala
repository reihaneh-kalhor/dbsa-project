import java.io.{BufferedWriter, FileWriter}

class OutputStream(fileAddress: String) {

  var fileWriter: FileWriter = null //main writer stream

  var bufferedWriter: BufferedWriter = null //used in second readLine
  var stringBuffer: StringBuffer = null //to show output


  def create = {
    fileWriter = new FileWriter(fileAddress)
  }

  def writeLine(str: String) = {
    fileWriter.write(str)
    fileWriter.write(System.lineSeparator)
  }

  def writeLineByBuffer(str: String) = {
    bufferedWriter = new BufferedWriter(fileWriter);
    bufferedWriter.write(str)
    bufferedWriter.write(System.lineSeparator)
    bufferedWriter.flush

  }

  def close = {
    if(fileWriter != null)
      fileWriter.close()
  }

  def bufferClose = {
    if(bufferedWriter != null)
      bufferedWriter.close
  }

}
