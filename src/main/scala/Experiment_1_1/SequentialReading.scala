package Experiment_1_1

import Streams.InputStream

import java.io.File

object SequentialReading {

  def LengthByReadCharacter(inputStream: InputStream): Long = {

    var count = 0
    while (!inputStream.endOfStream) {
      var line = inputStream.readCharacter
      count += line.length
    }
    count
  }

  def LengthByReadBuffer(inputStream: InputStream): Long = {

    var count = 0
    while (!inputStream.endOfStream) {
      var line = inputStream.readLine //72031550
      count += line.length
    }
    count
  }

  def LengthByReadBufferSize(inputStream: InputStream, bufferSize: Int): Long = {

    var count = 0
    while (!inputStream.endOfStream) {
      inputStream.setBufferSize(bufferSize)
      var line = inputStream.readCharacterWithBuffer //72031550
      count += line.length
    }
    count
  }

  def LengthByMappedMemory(inputStream: InputStream, bufferSize: Int): Long = {

    var count = 0
    while (!inputStream.endOfStream) {
      var line = inputStream.readFromMappedMemory(bufferSize)
      count += line.length
    }
    count
  }
}
