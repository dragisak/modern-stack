package modern.stack.videoclub

trait Api[F[_]] {

  def addInventory(movie: Movie, qty: Qty): F[List[DVD]]

  def findDVD(movieId: MovieId): F[Option[DVD]]

}
