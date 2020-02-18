package modern.stack.tickets

import cats.Monad
import cats.kernel.laws._
import cats.syntax.functor._
import cats.syntax.flatMap._
import eu.timepit.refined.auto._

abstract class ApiLaws[F[_]] {
  protected def algebra: Api[F]
  protected implicit def M: Monad[F]

  def createEventAndGetTickets(
      eventName: Event.Name,
      eventTime: Event.EventTime,
      seats: SeatCount,
      userId: User.ID,
      qty: SeatCount
  ): IsEq[F[Option[SeatCount]]] = {
    val expected = if (qty.n <= seats.n) Some(qty) else None

    val result = for {
      event       <- algebra.createEvent(eventName, eventTime, seats)
      maybeTicket <- algebra.findTicket(userId, event.id, qty)
    } yield maybeTicket

    result.map(_.map(_.quantity)) <-> M.pure(expected)
  }
}

object ApiLaws {
  def apply[F[_]](instance: Api[F])(implicit ev: Monad[F]): ApiLaws[F] = new ApiLaws[F] {
    override protected val algebra: Api[F]      = instance
    override protected implicit val M: Monad[F] = ev
  }
}
