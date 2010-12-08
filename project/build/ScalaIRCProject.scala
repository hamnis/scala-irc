import sbt._

class ScalaIRCProject(info: ProjectInfo) extends DefaultProject(info) {
  val scalaTest = "org.scalatest" % "scalatest" % "1.2" % "test" withSources
}
