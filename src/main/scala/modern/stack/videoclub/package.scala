package modern.stack

import java.util.UUID

import eu.timepit.refined.types.numeric.PosInt
import io.estatico.newtype.macros.newtype

package object videoclub {

  type Qty = PosInt

  @newtype final case class MovieId(uuid: UUID)
  @newtype final case class DvdId(uuid: UUID)

}
