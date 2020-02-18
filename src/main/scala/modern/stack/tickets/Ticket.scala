package modern.stack.tickets

import java.util.UUID

import io.estatico.newtype.macros.newtype
import modern.stack.tickets.Ticket._

final case class Ticket(
    id: ID,
    eventId: Event.ID,
    quantity: SeatCount
)

object Ticket {
  @newtype case class ID(id: UUID)
}
