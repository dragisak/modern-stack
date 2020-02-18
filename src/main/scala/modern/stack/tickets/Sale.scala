package modern.stack.tickets

import java.time.Instant
import java.util.UUID

import io.estatico.newtype.macros.newtype

final case class Sale(
    ticket: Ticket,
    id: Sale.ID,
    timestamp: Sale.Timestamp
)

object Sale {
  @newtype case class ID(id: UUID)
  @newtype case class Timestamp(value: Instant)
}
