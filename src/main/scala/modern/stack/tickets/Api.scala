package modern.stack.tickets

trait Api[F[_]] {

  def registerUser(name: User.Name, username: User.Username, password: User.Password): F[User]
  def logIn(userName: User.Username, password: User.Password): F[Option[User]]
  def logOut(userName: User.Username, password: User.Password): F[Unit]

  def findTicket(userId: User.ID, eventId: Event.ID, n: Ticket.Qty): F[Option[Ticket]]
}
