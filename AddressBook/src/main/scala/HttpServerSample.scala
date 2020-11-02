import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import org.slf4j.{Logger, LoggerFactory}

import scala.util.Try


object HttpServerSample {

  def main(args: Array[String]): Unit = {

    implicit val log: Logger = LoggerFactory.getLogger(getClass)

    val rootBehavior = Behaviors.setup[Nothing] { context =>

      val todos: Seq[Address] = Seq(
        Address("1", "title1", "description1"),
        Address("2", "title2", "description2")
      )

      val todoRepository = new InMemoryAddressRepository(todos)(context.executionContext)
      val router = new MyRouter(todoRepository)(context.system, context.executionContext)

      val host = "0.0.0.0"
      val port = Try(System.getenv("PORT")).map(_.toInt).getOrElse(9000)

      Server.startHttpServer(router.route, host, port)(context.system, context.executionContext)
      Behaviors.empty
    }
    val system = ActorSystem[Nothing](rootBehavior, "HelloAkkaHttpServer")
  }
}
