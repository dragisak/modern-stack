package modern.stack

import cats.effect.{ExitCode, IO, IOApp}
import cats.implicits._
import org.http4s.HttpRoutes
import org.http4s.dsl.io._
import org.http4s.implicits._
import org.http4s.server.blaze.BlazeServerBuilder

object Main extends IOApp {

  private val helloWorldService = HttpRoutes
    .of[IO] {
      case GET -> Root                  => Ok("Hello, my fiend.")
      case GET -> Root / "hello" / name => Ok(s"Hello, $name.")
    }

  override def run(args: List[String]): IO[ExitCode] = {
    BlazeServerBuilder[IO]
      .bindHttp(8080, "localhost")
      .withHttpApp(helloWorldService.orNotFound)
      .serve
      .compile
      .drain
      .as(ExitCode.Success)
  }
}
