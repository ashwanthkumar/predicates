import sbt.Keys._
import sbt._

object Build extends Build {

  val AppVersion = "0.0.3-SNAPSHOT"
  val ScalaVersion = "2.11.1"

  lazy val main = Project("predicates", file("."), settings = defaultSettings)
    .settings(organization := "in.ashwanthkumar",
      version := AppVersion,
      libraryDependencies += (
        if (scalaVersion.value.startsWith("2.1")) "org.scalatest" %% "scalatest" % "2.1.3" % "test"
        else "org.scalatest" %% "scalatest" % "1.9.1" % "test"
      ),
      crossScalaVersions := Seq("2.11.1", "2.10.4", "2.9.1", "2.9.2", "2.9.3")
  )

  lazy val defaultSettings = super.settings ++ Seq(
    fork in run := false,
    parallelExecution in This := true,
    publishMavenStyle := true,
    crossPaths := true,
    publishArtifact in Test := false,
    publishArtifact in(Compile, packageDoc) := true,
    publishArtifact in(Compile, packageSrc) := true,
    credentials += Credentials(Path.userHome / ".ivy2" / ".credentials"),
    publishTo <<= version { (v: String) =>
      val nexus = "https://oss.sonatype.org/"
      if (v.trim.endsWith("SNAPSHOT"))
        Some("snapshots" at nexus + "content/repositories/snapshots")
      else
        Some("releases"  at nexus + "service/local/staging/deploy/maven2")
    },
    pomExtra := _pomExtra
  )

  val _pomExtra =
    <url>http://github.com/ashwanthkumar/predicates</url>
      <licenses>
        <license>
          <name>Apache License, Version 2.0</name>
          <url>http://www.apache.org/licenses/LICENSE-2.0.html</url>
          <distribution>repo</distribution>
        </license>
      </licenses>
      <scm>
        <url>git@github.com:ashwanthkumar/predicates.git</url>
        <connection>scm:git:git@github.com:ashwanthkumar/predicates.git</connection>
      </scm>
      <developers>
        <developer>
          <id>ashwanthkumar</id>
          <name>Ashwanth Kumar</name>
          <url>http://www.ashwanthkumar.in/</url>
        </developer>
      </developers>

}
