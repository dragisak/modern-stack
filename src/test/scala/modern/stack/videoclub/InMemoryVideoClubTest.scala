package modern.stack.videoclub
import cats.Eq
import cats.effect.IO
import cats.tests.CatsSuite
import org.scalacheck.{Arbitrary, Gen}
import eu.timepit.refined.scalacheck.numeric
import eu.timepit.refined.auto._

class InMemoryVideoClubTest extends CatsSuite {
  private val api: Api[IO] = InMemoryVideoClub.make().unsafeRunSync()

  private implicit val arbMovie: Arbitrary[Movie] = Arbitrary(
    for {
      uuid  <- Gen.uuid
      title <- Gen.alphaStr
    } yield Movie(MovieId(uuid), title)
  )

  private implicit def eqIO[T](implicit ev: Eq[T]): Eq[IO[T]] = new Eq[IO[T]] {
    override def eqv(x: IO[T], y: IO[T]): Boolean = {
      val a = x.unsafeRunSync()
      val b = y.unsafeRunSync()
      ev.eqv(a, b)
    }
  }

  private implicit val arbQty: Arbitrary[Qty] = Arbitrary(numeric.chooseRefinedNum(1, 100))

  checkAll("InMemoryVideoClub", ApiTest(api).videoClubLaws)
}
