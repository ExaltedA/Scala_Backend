
import scala.collection.mutable.Stack
import scala.io.StdIn.readLine

class Core() {

  // ENUMERATION DECLARATION
  sealed trait State;
  case object INPUT extends State;
  case object EVALUATION extends State;
  case object OUTPUT extends State;
// Fields delaration
  var expr: String = ""
  var res: Float = 0;
  var isFinished = false;
  var curState: State = INPUT // always start from input logic

  /*Start method runs an entire appliction logic
  * uses states to handle any sort of situations
  * try - catch expression handles non-numeric or 'improper' input
  */

  def Start(): Any = {

    while (!isFinished) {
      curState match {
          //Simply handles input logic
        case INPUT => {
          println("Enter expression:")
          expr = readLine().replaceAll("\\s", "")
          curState = EVALUATION
        }
          //handles logic
        case EVALUATION => {
          try {
            //add to array all except operators and convert them to float
            val arr = expr.split("[+*=/-]").map(_.toFloat)
            // add to array all operators, for easier execution remove all dots, and = operator and separate by integers
            val signArr = expr.replaceAll("[.]", "").split("\\d+").drop(1).filter(!_.contains("="))
            //check if expression is in proper and simple format, NOTE THAT THIS APP DOESN'T SUPPORT CALCULATIONS WITH BRACKETS
            if (signArr.size + 1 != arr.size) {
              println("Invalid Expression! Try Again")
              //if not change to initial state
              curState = INPUT
            }
            if(curState == EVALUATION)
              for (i <- signArr.indices) {
                signArr(i) match {
                  case "+" => {
                    arr(i + 1) = arr(i) + arr(i + 1)
                  }
                  case "-" => {
                    arr(i + 1) = arr(i) - arr(i + 1)
                  }
                  case "*" => {
                    arr(i + 1) = arr(i) * arr(i + 1)
                  }
                  case "/" => {
                    arr(i + 1) = arr(i) / arr(i + 1)
                  }
                  case _ => println("You were trying to use several operators simultaneously")
                }
              }
            res = arr(arr.length - 1)
            if (curState == EVALUATION)
              curState = OUTPUT
          }
          catch {
            //handles exeptions, I have found only one, but if there are more it is easy to add logic here
            case e: NumberFormatException => {
              println("Invalid Expression! Try Again")
              curState = INPUT
            }
          }
        }
        case OUTPUT => {
          //finish program
          println(toString())
          isFinished = true
        }
        case _ => {
          println("ERROR! TRY AGAIN")
          curState = INPUT
        }
      }
    }
  }
  override def toString: String = s"Result is: $res"
}
