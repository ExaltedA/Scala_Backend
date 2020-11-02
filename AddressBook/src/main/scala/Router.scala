import akka.actor.typed.ActorSystem
import de.heikoseeberger.akkahttpcirce.FailFastCirceSupport._
import  io.circe.generic.auto._
import akka.http.scaladsl.server.{Directives, Route}

import scala.concurrent.ExecutionContext

trait Router {
  def route: Route
}

class MyRouter(val addressRepository: AddressRepository)(implicit system: ActorSystem[_], ex:ExecutionContext)
  extends  Router
    with  Directives
    with HealthCheckRoute
    with ValidatorDirectives
    with AddressDirectives {

  def address = {
    pathPrefix("address") {
      concat(
        pathEndOrSingleSlash {
          concat(
            get {
              complete(addressRepository.all())
            },
            post {
              entity(as[CreateAddress]) { createTodo =>
                validateWith(CreateAddressValidator)(createTodo) {
                  handleWithGeneric(addressRepository.create(createTodo)) { todo =>
                    complete(todo)
                  }
                }
              }
            },
            put {
              entity(as[CreateAddress]) { createTodo =>
                validateWith(CreateAddressValidator)(createTodo) {
                  handleWithGeneric(addressRepository.put(createTodo)) { todo =>
                    complete(todo)
                  }
                }
              }
            },
            delete {
              entity(as[CreateAddress]) { createTodo =>
                validateWith(CreateAddressValidator)(createTodo) {
                  handleWithGeneric(addressRepository.delete(createTodo)) { todo =>
                    complete(todo)
                  }
                }
              }
            }
          )
        }
      )
    }
  }

  override def route = {
    concat(
      healthCheck,
      address
    )
  }
}




