package Experiment_1_3

import Streams.{InputStream, OutputStream}

import java.io.File
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.{Failure, Success}

//https://stackoverflow.com/questions/25086479/read-multiple-text-files-simultaneously-in-scala
class CombinedReadingAndWriting(outputFileAddress: String, inputFilesAddressArrays: Array[String]) {

  var line: StringBuffer = new StringBuffer()


  val outputStream = new OutputStream(new File(outputFileAddress))
  outputStream.create

  var inputStreamsList: Array[(InputStream, Boolean)] = new Array[(InputStream, Boolean)](inputFilesAddressArrays
    .length)

  inputFilesAddressArrays.foreach(fileAddress => {
    val inputStream = new InputStream(new File(fileAddress))
    inputStream.open
    inputStreamsList :+ (inputStream, false)
  })

  def readCharacterOfInputFileWriteCharacterInOutputFile(fileAddress: String, index: Int): Unit = {
    val inputStream = new InputStream(new File(fileAddress))
    inputStreamsList(index) = (inputStream, false)
    inputStream.open
    while (!inputStream.endOfStream) {
      line = inputStream.readCharacter
      outputStream.writeCharacter(line.toString)
    }
    inputStream.close
    inputStreamsList(index) = (inputStream, true)
  }

  def rrmerge: Unit = {
    inputFilesAddressArrays.zipWithIndex.map { case (fileAddress, index) => Future(readCharacterOfInputFileWriteCharacterInOutputFile(fileAddress, index))
      .onComplete {
        case Success(res) => {
          val allFilesRead = inputStreamsList.forall(tuple => tuple._2 == true)
          if (allFilesRead) outputStream.close
        }
        case Failure(e) => println("failure: " + e.getMessage)
      }
    }
  }
}

//object CombinedReadingAndWriting extends App{
//  val root = "src/main/resources/"
//  val outPutFileAddress = root + "output.txt"
//  val inputFilesAddress = Array(root + "IN_1.txt", root + "IN_2.txt" , root + "IN_3.txt")
//
//  val concurrentFileReadingAndWriting = new CombinedReadingAndWriting(outPutFileAddress, inputFilesAddress)
//
//}
