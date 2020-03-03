package modern.stack.videoclub

import java.util.UUID

import cats.effect.IO
import cats.effect.concurrent.Ref
import eu.timepit.refined.auto._

final class InMemoryVideoClub private (ref: Ref[IO, Map[MovieId, Set[DVD]]]) extends Api[IO] {
  override def addInventory(movie: Movie, qty: Qty): IO[Set[DVD]] = ref.modify { map =>
    val newDvds = Set.fill(qty)(UUID.randomUUID()).map(uuid => DVD(DvdId(uuid), movie.id))
    val updated = map.get(movie.id) match {
      case None      => map + (movie.id -> newDvds)
      case Some(set) => map + (movie.id -> (set ++ newDvds))
    }

    (updated, newDvds)
  }

  override def getDVD(movieId: MovieId): IO[Option[DVD]] = ref.modify { map =>
    map.get(movieId) match {
      case Some(dvds) if dvds.nonEmpty =>
        val dvd = dvds.head
        (map + (movieId -> (dvds - dvd)), Some(dvd))
      case _ => (map, None)
    }
  }
}

object InMemoryVideoClub {
  def make(): IO[Api[IO]] = Ref.of[IO, Map[MovieId, Set[DVD]]](Map.empty).map(new InMemoryVideoClub(_))
}
