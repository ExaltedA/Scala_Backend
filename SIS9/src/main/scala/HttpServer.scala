import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.http.scaladsl.server.Directives._

import scala.concurrent.ExecutionContextExecutor
import scala.io.StdIn

object HttpServer{
    def callHttp(str: String): Unit ={
      implicit val system: ActorSystem[Nothing] = ActorSystem(Behaviors.empty, "my-system")
      // needed for the future flatMap/onComplete in the end
      implicit val executionContext: ExecutionContextExecutor = system.executionContext

      val route =
        path("hello") {
          get {
            complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, s"<h1>$str</h1>"))
          }
        }

      val bindingFuture = Http().newServerAt("localhost", 8080).bind(route)

      println(s"Server online at http://localhost:80800/hello\nPress RETURN to stop...")

      val a =StdIn.readLine() // let it run until user presses return

      bindingFuture
       .flatMap(_.unbind()) // trigger unbinding from the port
       .onComplete(_ => system.terminate()) // and shutdown when done
    }
}
