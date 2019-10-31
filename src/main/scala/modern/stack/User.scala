package modern.stack

import java.util.UUID

import eu.timepit.refined.types.string.NonEmptyString
import io.estatico.newtype.macros.newtype
import modern.stack.User._

final case class User(
    id: ID,
    name: Name,
    address: Address,
    password: Password
)

object User {
  @newtype case class ID(id: UUID)
  @newtype case class Name(value: NonEmptyString)
  @newtype case class Address(value: NonEmptyString)
  @newtype case class Password(value: NonEmptyString)
}
