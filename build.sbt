name := """PrototypeSite"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.7"

libraryDependencies += "org.postgresql" % "postgresql" % "9.4-1206-jdbc4"

libraryDependencies += evolutions

libraryDependencies ++= Seq(
  jdbc,
  cache,
  ws,
  "com.typesafe.play" %% "anorm" % "2.5.0",
  "org.scalatestplus.play" %% "scalatestplus-play" % "1.5.1" % Test,
  "org.postgresql" % "postgresql" % "9.4-1206-jdbc4"
)

resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"

resolvers ++= Seq("scalaz-bintray" at "http://dl.bintray.com/scalaz/releases",
  "Typesafe Releases" at "http://repo.typesafe.com/typesafe/releases/")