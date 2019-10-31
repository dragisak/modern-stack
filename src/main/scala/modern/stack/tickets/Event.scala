package modern.stack.tickets

import java.time.ZonedDateTime
import java.util.UUID

import eu.timepit.refined.types.string.NonEmptyString
import io.estatico.newtype.macros.newtype
import modern.stack.tickets.Event._

final case class Event(
    id: ID,
    name: Name,
    time: DateTime
)

object Event {
  @newtype case class ID(id: UUID)
  @newtype case class Name(value: NonEmptyString)
  @newtype case class DateTime(value: ZonedDateTime)
}
