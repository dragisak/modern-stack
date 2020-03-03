package modern.stack.videoclub

import cats.Eq

case class DVD(
    id: DvdId,
    movieId: MovieId
)

object DVD {
  implicit val dvdEq: Eq[DVD] = Eq.fromUniversalEquals
}
