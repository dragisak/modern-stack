package modern.stack.videoclub

trait Api[F[_]] {

  def addInventory(movie: Movie, qty: Qty): F[Set[DVD]]

  def getDVD(movieId: MovieId): F[Option[DVD]]

}
