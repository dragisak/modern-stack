package modern.stack

import java.util.UUID

import eu.timepit.refined.types.numeric.PosInt
import io.circe.{Codec, Decoder, Encoder}
import io.estatico.newtype.macros.newtype

import scala.util.Try

package object videoclub {

  type Qty = PosInt

  @newtype final case class MovieId(uuid: UUID)

  @newtype final case class DvdId(uuid: UUID)


  implicit val movieIdCodec: Codec[MovieId] = Codec.from(
    Decoder.decodeString.emapTry(s => Try(MovieId(UUID.fromString(s)))),
    Encoder.encodeString.contramap(_.uuid.toString)
  )

  implicit val dvdIdCodec: Codec[DvdId] = Codec.from(
    Decoder.decodeString.emapTry(s => Try(DvdId(UUID.fromString(s)))),
    Encoder.encodeString.contramap(_.uuid.toString)
  )

}
