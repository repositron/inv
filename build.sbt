name := "inv"

organization := "org.ljw"

version := "0.0.1"

scalaVersion := "2.11.0"

libraryDependencies ++= Seq(
  "org.specs2" %% "specs2" % "2.3.11" % "test"
)

scalacOptions in Test ++= Seq("-Yrangepos")

// Read here for optional dependencies:
// http://etorreborre.github.io/specs2/guide/org.specs2.guide.Runners.html#Dependencies

resolvers ++= Seq("snapshots", "releases").map(Resolver.sonatypeRepo)

initialCommands := "import org.ljw.inv._"
