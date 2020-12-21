import akka.actor.Actor.Receive
import akka.actor._
import akka.routing._;
import akka.util._

import scala.concurrent.{Await, Promise}
import scala.concurrent.duration._

//source https://stackoverflow.com/questions/26293368/what-is-the-correct-way-to-implement-producer-consumer-in-scala
//example 
class Producer(val pool:ActorRef)(val name:String) extends Actor {

  def receive = {
    case _ =>
      while (true) {
        val sleepTime = scala.util.Random.nextInt(1000)
        Thread.sleep(sleepTime)
        //Read line from the file and send it to the consumer
        println("Producer %s send food" format name)
        pool ! name
      }
  }
}

class Consumer(supervisor : ActorRef)(val name:String) extends Actor {

  var counter = 0

  def receive = {
    case s =>
      counter += 1
      println("%s eat food produced by %s" format (name,s))

      if (counter >= 10) {
        println("%s is full" format name)

        context.stop(self)
        supervisor ! 1
      }
  }
}

class Supervisor(p:Promise[String]) extends Actor {

  var r = 3

  def receive = {
    case _ =>
      r -= 1
      if (0 == r) {
        println("All consumer stopped")
        context.stop(self)
        p success ("Good")
      }
  }

}

object ProduceConsumer {

  def work(): Unit = {
    val system = ActorSystem("sys1")
    val nProducer = 5;
    val nConsumer = 3;
    val p = Promise[String]
    val supervisor = system.actorOf(Props(new Supervisor(p)));
    val arrConsumer = for ( i <-  1 to nConsumer) yield system.actorOf( Props( new Consumer(supervisor)( "Consumer %d" format (i) ) ) )
    val poolConsumer = system.actorOf(Props.empty.withRouter( RoundRobinRouter(arrConsumer) ))
    val arrProducer = for ( i <-  1 to nProducer) yield system.actorOf( Props( new Producer(poolConsumer)( "Producer %d" format (i) ) ) )

    arrProducer foreach (_ ! "start")

    Await.result(p.future,Duration.Inf)
    println("great!")
//    system.shutdown
  }

  def main(args:Array[String]): Unit = {
    work()
  }

}
