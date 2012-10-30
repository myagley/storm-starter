import com.twitter.sbt._
import com.twitter.scalatest._

seq((
  Project.defaultSettings ++
  StandardProject.newSettings ++
  SubversionPublisher.newSettings ++
  ScalaTestMixins.testSettings
): _*)

name := "Exclamation"

organization := "com.tempodb"

version := "1.0.0-SNAPSHOT"

scalaVersion := "2.9.1"

libraryDependencies ++= Seq(
  "storm" % "storm" % "0.8.1",
  "ring" % "ring-core" % "0.3.11"
)

resolvers += "clojars" at "http://clojars.org/repo/"

mainClass in (Compile, run) := Some("com.tempodb.Main")

scalacOptions += "-Yresolve-term-conflict:object"
