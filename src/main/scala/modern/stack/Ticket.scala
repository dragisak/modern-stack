package modern.stack

import java.util.UUID

import eu.timepit.refined.numeric.Positive
import io.estatico.newtype.macros.newtype
import modern.stack.Ticket._

final case class Ticket(
    id: ID,
    userId: User.ID,
    quantity: Qty
)

object Ticket {
  @newtype case class ID(id: UUID)
  @newtype case class Qty(n: Positive)
}
