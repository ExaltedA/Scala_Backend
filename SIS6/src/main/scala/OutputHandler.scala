import akka.actor.typed.ActorRef
import akka.actor.typed.Behavior
import akka.actor.typed.scaladsl.Behaviors

object OutputHandler {

  final case class Output(result: Float)

  def apply(): Behavior[Output] = Behaviors.receive { (context, message) =>
    context.log.info("Result: {}", message.result)
      Behaviors.same
  }
}