package modern.stack.videoclub

import io.circe.{Codec, Json}
import io.circe.generic.semiauto._

case class Movie(
    id: MovieId,
    title: String
)

object Movie {
  implicit val movieCodec: Codec[Movie] = deriveCodec[Movie]
}