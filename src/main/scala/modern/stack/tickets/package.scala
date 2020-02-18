package modern.stack

import eu.timepit.refined.api.Refined
import eu.timepit.refined.numeric.Positive
import io.estatico.newtype.macros.newtype

package object tickets {
  @newtype case class SeatCount(n: Int Refined Positive)
}
