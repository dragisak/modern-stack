package modern.stack

import java.util.UUID

import eu.timepit.refined.api.Refined
import eu.timepit.refined.numeric.Positive
import io.estatico.newtype.macros.newtype

package object videoclub {

  type Qty = Int Refined Positive

  @newtype final case class MovieId(uuid: UUID)
  @newtype final case class DvdId(uuid: UUID)

}
