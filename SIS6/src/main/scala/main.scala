import Calculator.Input
import akka.actor.typed.ActorSystem


object main extends App{
  val system: ActorSystem[Calculator.Input] = ActorSystem(Calculator(), "main")
  system ! Input("12.2/6.0")
}