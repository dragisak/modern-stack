package modern.stack.videoclub

import cats.{Eq, Monad}
import org.typelevel.discipline.Laws
import cats.kernel.laws.discipline._
import org.scalacheck.Arbitrary
import org.scalacheck.Prop._

trait ApiTest[F[_]] extends Laws {
  protected def laws: ApiLaws[F]

  def videoClubLaws(
      implicit arbMovie: Arbitrary[Movie],
      arbQty: Arbitrary[Qty],
      eqFBoolean: Eq[F[Boolean]]
  ): RuleSet = new SimpleRuleSet(
    "Video Club Laws",
    "Create inventory and find a DVD" -> forAll(laws.createInventoryAndFindDVD _)
  )

}

object ApiTest {
  def apply[F[_]: Monad](alg: Api[F]): ApiTest[F] = new ApiTest[F] {
    override protected val laws: ApiLaws[F] = ApiLaws(alg)
  }
}
