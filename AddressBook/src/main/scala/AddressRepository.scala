import java.util.UUID

import scala.concurrent.{ExecutionContext, Future}

trait AddressRepository {
  def all(): Future[Seq[Address]]
  def create(createAddress:CreateAddress): Future[Address]
  def put(createAddress:CreateAddress): Future[Address]
  def delete(createAddress: CreateAddress) : Future[Address]
}


class InMemoryAddressRepository(todo:Seq[Address] = Seq.empty)(implicit ex:ExecutionContext) extends AddressRepository {
  private var addresses: Vector[Address] = todo.toVector

  override def all(): Future[Seq[Address]] = Future.successful(addresses)


  override def create(createAddress: CreateAddress): Future[Address] = Future.successful {
    val address = Address(
      id = UUID.randomUUID().toString,
      fullName = createAddress.fullName,
      address = createAddress.address
    )
    addresses = addresses :+ address
    address
  }

  override def put(createAddress:CreateAddress): Future[Address] = Future.successful {
    var newAddress = Address("","","")
    for(address <- addresses){
      if(address.fullName == createAddress.fullName){
        newAddress = address.copy(fullName = createAddress.fullName, address = createAddress.address)
        addresses = addresses.filter(_ != address)
        addresses = addresses :+ newAddress
      }
    }
    newAddress
  }

  override def delete(createAddress: CreateAddress) = Future.successful{
    var newAddress = Address("","","")
    for(address <- addresses){
      if(address.fullName == createAddress.fullName){
        addresses = addresses.filter(_ != address)
        newAddress = address
      }
    }
    newAddress
  }
}