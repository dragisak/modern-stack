package modern.stack.tickets

import cats.{Eq, Monad}
import org.scalacheck.Arbitrary
import org.scalacheck.Prop._
import org.typelevel.discipline.Laws
import cats.kernel.laws.discipline._

abstract class ApiTest[F[_]] extends Laws {
  protected def laws: ApiLaws[F]

  def algebra(
      implicit
      arbEventName: Arbitrary[Event.Name],
      arbEventTime: Arbitrary[Event.EventTime],
      arbQty: Arbitrary[SeatCount],
      arbUserId: Arbitrary[User.ID],
      eqFOptTicket: Eq[F[Option[SeatCount]]]
  ) = new SimpleRuleSet(
    name = "Api",
    "create event and find tickets" -> forAll(laws.createEventAndGetTickets _)
  )
}

object ApiTest {

  def apply[F[_]: Monad](instance: Api[F]): ApiTest[F] = new ApiTest[F] {
    override protected val laws: ApiLaws[F] = ApiLaws(instance)
  }

}
