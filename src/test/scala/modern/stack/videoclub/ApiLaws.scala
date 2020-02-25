package modern.stack.videoclub

import cats.Monad
import cats.implicits._
import cats.kernel.laws._
import eu.timepit.refined.auto._

trait ApiLaws[F[_]] {

  protected def api: Api[F]

  protected implicit def M: Monad[F]

  def forCreatedInventoryYouShouldFindQuantityAndNoMore(movie: Movie, qty: Qty): IsEq[F[(Int, Int)]] = {
    val result = for {
      _       <- api.addInventory(movie, qty)
      lookups <- List.fill(qty + 1)(()).traverse(_ => api.findDVD(movie.id))
    } yield lookups

    result.map(res => res.partition(_.isDefined).bimap(_.size, _.size)) <-> M.pure((qty.value, 1))
  }

}

object ApiLaws {
  def apply[F[_]](alg: Api[F])(implicit ev: Monad[F]): ApiLaws[F] = new ApiLaws[F] {
    override protected val api: Api[F]          = alg
    override protected implicit val M: Monad[F] = ev
  }
}
