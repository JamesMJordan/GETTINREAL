name := """PrototypeSite"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  jdbc,
  cache,
  ws,
  evolutions,
  "com.typesafe.play"      %% "anorm"                             % "2.5.0",
  "org.scalatestplus.play" %% "scalatestplus-play"                % "1.5.1"                   % Test,
  "org.postgresql"         % "postgresql"                         % "9.4-1206-jdbc42",
  "jp.t2v"                 %% "play2-auth"                        % "0.14.2",
  "jp.t2v"                 %% "play2-auth-social"                 % "0.14.2",
  "jp.t2v"                 %% "play2-auth-test"                   % "0.14.2"                  % "test",
  "org.scalikejdbc"        %% "scalikejdbc"                       % "2.4.2",
  "org.scalikejdbc"        %% "scalikejdbc-syntax-support-macro"  % "2.4.2",
  "org.mindrot"            % "jbcrypt"                            %"0.3m",
  "org.scalikejdbc"        %% "scalikejdbc-config"                % "2.4.2",
  "org.scalikejdbc"        %% "scalikejdbc-play-initializer"      % "2.5.1",
  "org.flywaydb"           %% "flyway-play"                       % "2.0.1",
  specs2 % Test,
  play.sbt.Play.autoImport.cache
)

resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"

resolvers ++= Seq("scalaz-bintray" at "http://dl.bintray.com/scalaz/releases",
  "Typesafe Releases" at "http://repo.typesafe.com/typesafe/releases/")