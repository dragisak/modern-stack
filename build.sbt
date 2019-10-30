name := "modern-stack"

version := "0.1"

scalaVersion := "2.13.1"

val versions = new {
  val circe      = "0.12.3"
  val ciris      = "1.0.0"
  val http4s     = "0.21.0-M5"
  val catsEffect = "2.0.0"
  val catsRetry  = "0.3.1"
  val jwtAuth    = "0.0.3"
  val log4cats   = "1.0.1"
  val logback    = "1.2.3"
  val mtl        = "0.4.0"
  val newtype    = "0.4.3"
  val redis4cats = "0.9.1"
  val skunk      = "0.0.4"
}

libraryDependencies ++= List(
  "org.typelevel"     %% "cats-effect"            % versions.catsEffect,
  "com.github.cb372"  %% "cats-retry-cats-effect" % versions.catsRetry,
  "io.circe"          %% "circe-core"             % versions.circe,
  "io.circe"          %% "circe-generic"          % versions.circe,
  "io.circe"          %% "circe-parser"           % versions.circe,
  "io.circe"          %% "circe-literal"          % versions.circe,
  "io.circe"          %% "circe-refined"          % versions.circe,
  "is.cir"            %% "ciris"                  % versions.ciris,
  "is.cir"            %% "ciris-refined"          % versions.ciris,
  "org.http4s"        %% "http4s-blaze-server"    % versions.http4s,
  "org.http4s"        %% "http4s-circe"           % versions.http4s,
  "org.http4s"        %% "http4s-dsl"             % versions.http4s,
  "dev.profunktor"    %% "http4s-jwt-auth"        % versions.jwtAuth,
  "io.chrisdavenport" %% "log4cats-slf4j"         % versions.log4cats,
  "ch.qos.logback"    % "logback-classic"         % versions.logback,
  "com.olegpy"        %% "meow-mtl-effects"       % versions.mtl,
  "io.estatico"       %% "newtype"                % versions.newtype,
  "dev.profunktor"    %% "redis4cats-effects"     % versions.redis4cats,
  "org.tpolecat"      %% "skunk-core"             % versions.skunk
  // "org.typelevel"     %% "squants"                % versions.squants
)

scalacOptions ++= List(
  "-Ymacro-annotations"
)

addCompilerPlugin("com.olegpy"    %% "better-monadic-for" % "0.3.1")
addCompilerPlugin("org.typelevel" %% "kind-projector"     % "0.11.0" cross CrossVersion.full)
