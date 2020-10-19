import akka.actor.typed.ActorRef
import akka.actor.typed.Behavior
import akka.actor.typed.scaladsl.Behaviors
import Calculator.Input

object Evaluator {
  final case class logic(evaluated: String, origin: ActorRef[Calculator.Input])

  def apply(): Behavior[logic] =Behaviors.receive { (context, message) =>
    context.log.info("Evaluating: {}", message.evaluated)
    val returnTo = context.spawn(OutputHandler(), "address")
    //logic start
    var result: Float = 0
    var ind = 0
    var temp = message.evaluated.replaceAll("\\s", "")
    var arr = temp.split("[+*=/-]").map(_.toFloat)
    val signArr = temp.replaceAll("[.]", "").split("\\d+").drop(1)
    if (signArr.size + 1 != arr.size) {
      println("Invalid Expression! Try Again")
      }
    if(signArr.contains("*") && signArr.length > 1){
      var tmp = signArr.indexOf("*")
      arr(tmp) = arr(tmp) * arr(tmp+1)

      signArr(tmp) = ""

    }
    if(signArr.contains("/") && signArr.length > 1){
      var tmp = signArr.indexOf("/")
      arr(tmp) = arr(tmp) / arr(tmp+1)

      signArr(tmp) = ""

    }
    for (i <- signArr.indices) {
      signArr(i) match {

        case "+" => {

          arr(i + 1) = arr(i) + arr(i + 1)
          ind = i

        }
        case "-" => {
          arr(i + 1) = arr(i) - arr(i + 1)
          ind = i
        }
        case "*" => {
          arr(i + 1) = arr(i) * arr(i + 1)
          ind = i
        }
        case "/" => {
          arr(i + 1) = arr(i) / arr(i + 1)
          ind = i
        }
        case "" => println()
        case _ => println("You were trying to use several operators simultaneously")
      }

    }
    result = arr(ind+1)
    //end
    returnTo ! OutputHandler.Output(result)
    Behaviors.same



}}
