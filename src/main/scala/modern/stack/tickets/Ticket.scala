package modern.stack.tickets

import java.util.UUID

import eu.timepit.refined.api.Refined
import eu.timepit.refined.numeric.Positive
import io.estatico.newtype.macros.newtype
import modern.stack.tickets.Ticket._

final case class Ticket(
    id: ID,
    userId: User.ID,
    eventId: Event.ID,
    quantity: Qty
)

object Ticket {
  @newtype case class ID(id: UUID)
  @newtype case class Qty(n: Int Refined Positive)
}
