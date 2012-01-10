organization := "net.hamnaberg"

name := "scala-irc"

version := "0.1-SNAPSHOT"

scalaVersion := "2.9.1"

libraryDependencies += "org.specs2" %% "specs2" % "1.6.1" % "test"

scalacOptions in Compile := Seq("-deprecation")