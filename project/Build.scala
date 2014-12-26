import sbt.Keys._
import sbt._

object Build extends Build {

  val AppVersion = "0.0.1"
  val ScalaVersion = "2.10.4"

  val main = Project("predicates", file("."))
    .settings(organization := "in.ashwanthkumar",
      version := AppVersion,
      libraryDependencies += (
        if (scalaVersion.value.startsWith("2.1")) "org.scalatest" %% "scalatest" % "2.1.3" % "test"
        else "org.scalatest" %% "scalatest" % "1.9.1" % "test"
      ),
      crossScalaVersions := Seq("2.11.1", "2.10.3", "2.9.1", "2.9.2", "2.9.3")
  )

  override val settings = super.settings ++ Seq(
    fork in run := false,
    resolvers += Resolver.sonatypeRepo("snapshots"),
    parallelExecution in This := false,
    publishMavenStyle := true,
    crossPaths := true,
    publishArtifact in Test := false,
    publishArtifact in(Compile, packageDoc) := false,
    // publishing the main sources jar
    publishArtifact in(Compile, packageSrc) := true,
    credentials += Credentials(Path.userHome / ".ivy2" / ".credentials")
  )

}
