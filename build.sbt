name := "scala-slick-test"

version := "1.0"

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  "com.typesafe.slick" %% "slick" % "3.1.1",
  "org.slf4j" % "slf4j-nop" % "1.6.4",
  "com.typesafe" % "config" % "1.3.1",
  "com.typesafe.slick" %% "slick-hikaricp" % "3.1.1",
  "postgresql" % "postgresql" % "9.4.1208-jdbc42-atlassian-hosted"
)

