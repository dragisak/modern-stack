package modern.stack.tickets

import cats.{Eq, Monad}
import org.scalacheck.Arbitrary
import org.typelevel.discipline.Laws
import cats.kernel.laws.discipline._
import modern.stack.tickets.Event.EventTime
import eu.timepit.refined.auto._
import org.scalacheck.Prop.{forAll, propBoolean}

abstract class ApiTest[F[_]] extends Laws {
  protected def laws: ApiLaws[F]

  def algebra(
      implicit
      arbEventName: Arbitrary[Event.Name],
      arbEventTime: Arbitrary[Event.EventTime],
      arbQty: Arbitrary[SeatCount],
      arbUserId: Arbitrary[User.ID],
      eqFOptTicket: Eq[F[Option[SeatCount]]],
      eqFTicket: Eq[F[SeatCount]]
  ): RuleSet = new SimpleRuleSet(
    name = "Api",
    "create event and find tickets" -> forAll(
      laws.createEventAndGetTickets _
    ),
    "get ticket and buy it" -> forAll(
      (name: Event.Name, time: EventTime, capacity: SeatCount, userId: User.ID, qty: SeatCount) =>
        (capacity.n >= qty.n) ==>
          laws.getTicketAndBuyIt(name, time, capacity, userId, qty)
    )
  )
}

object ApiTest {

  def apply[F[_]: Monad](instance: Api[F]): ApiTest[F] = new ApiTest[F] {
    override protected val laws: ApiLaws[F] = ApiLaws(instance)
  }

}
