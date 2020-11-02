trait Validator[T] {
  def validate(t: T): Option[ApiError]
}

object CreateAddressValidator extends Validator[CreateAddress] {
  def validate(createAddress: CreateAddress): Option[ApiError] =
    if (createAddress.fullName.isEmpty)
      Some(ApiError.emptyTitleField)
    else
      None
}
