package modern.stack.videoclub

import cats.Monad
import cats.implicits._
import cats.kernel.laws._
import eu.timepit.refined.auto._

trait ApiLaws[F[_]] {

  protected def api: Api[F]

  protected implicit def M: Monad[F]

  def createInventoryAndFindDVD(movie: Movie, qty: Qty): IsEq[F[Boolean]] = {
    val result = for {
      dvds   <- api.addInventory(movie, qty)
      lookup <- api.findDVD(movie.id)
    } yield lookup

    val resultFound = if (qty > 0) true else false

    result.map(_.isDefined) <-> M.pure(resultFound)
  }

}

object ApiLaws {
  def apply[F[_]](alg: Api[F])(implicit ev: Monad[F]): ApiLaws[F] = new ApiLaws[F] {
    override protected val api: Api[F]          = alg
    override protected implicit val M: Monad[F] = ev
  }
}
