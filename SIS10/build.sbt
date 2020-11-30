name := "SIS10"

version := "1.0"

scalaVersion := "2.13.4"

val AkkaVersion = "2.6.10"
val AkkaHttpVersion = "10.2.1"

connectInput in run := true

libraryDependencies += "com.typesafe.akka" %% "akka-stream" % AkkaVersion
libraryDependencies += "com.typesafe.akka" %% "akka-stream-kafka" % "2.0.5"

libraryDependencies += "com.typesafe.akka" %% "akka-http-spray-json" % AkkaHttpVersion
libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-stream" % AkkaVersion,
  "com.typesafe.akka" %% "akka-http" % AkkaHttpVersion,
  "com.typesafe.akka" %% "akka-actor-typed" % AkkaVersion,
)

