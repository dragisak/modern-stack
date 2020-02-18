package modern.stack.tickets

trait Api[F[_]] {

  def registerUser(name: User.Name, username: User.Username, password: User.Password): F[User]
  def logIn(userName: User.Username, password: User.Password): F[Option[User]]
  def logOut(userId: User.ID): F[Unit]

  def createEvent(name: Event.Name, time: Event.EventTime, seats: SeatCount): F[Event]
  def getCurrentCapacity(eventId: Event.ID): F[EventCapacity]

  def findTicket(userId: User.ID, eventId: Event.ID, qty: SeatCount): F[Option[Ticket]]
  def buyTicket(ticket: Ticket): F[Sale]
}
