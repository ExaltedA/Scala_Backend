import akka.actor.typed.Behavior
import akka.actor.typed.scaladsl.Behaviors

object Calculator {
  final case class Input(data: String)

  def apply(): Behavior[Input] =
    Behaviors.setup { context =>
      val evaluator = context.spawn(Evaluator(), "evaluator")

        Behaviors.receiveMessage { message =>
          context.log.info("Calculate {}", message.data)
          evaluator ! Evaluator.logic(message.data, context.self)

          Behaviors.same
        }
      }


}


