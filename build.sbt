name := "modern-stack"

version := "0.1"

scalaVersion := "2.13.1"

val versions = new {
  val circe      = "0.13.0"
  val ciris      = "1.0.4"
  val fs2        = "2.0.1"
  val http4s     = "0.21.1"
  val catsEffect = "2.1.1"
  val catsRetry  = "1.1.0"
  val jwtAuth    = "0.0.4"
  val log4cats   = "1.0.1"
  val logback    = "1.2.3"
  val mtl        = "0.4.0"
  val newtype    = "0.4.3"
  val redis4cats = "0.9.3"
  val skunk      = "0.0.7"
  val slf4j      = "1.7.30"
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
  "-deprecation", // Emit warning and location for usages of deprecated APIs.
  "-encoding",
  "utf-8",                         // Specify character encoding used by source files.
  "-explaintypes",                 // Explain type errors in more detail.
  "-feature",                      // Emit warning and location for usages of features that should be imported explicitly.
  "-language:existentials",        // Existential types (besides wildcard types) can be written and inferred
  "-language:experimental.macros", // Allow macro definition (besides implementation and application)
  "-language:higherKinds",         // Allow higher-kinded types
  "-language:implicitConversions", // Allow definition of implicit functions called views
  "-unchecked",                    // Enable additional warnings where generated code depends on assumptions.
  "-Xcheckinit",                   // Wrap field accessors to throw an exception on uninitialized access.
  "-Ymacro-annotations"
)

dependencyOverrides ++= List(
  "org.slf4j" % "slf4j-api" % versions.slf4j
)

addCompilerPlugin("com.olegpy"    %% "better-monadic-for" % "0.3.1")
addCompilerPlugin("org.typelevel" %% "kind-projector"     % "0.11.0" cross CrossVersion.full)

mainClass := Some("modern.stack.Main")
