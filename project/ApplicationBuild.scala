import sbt._

object ApplicationBuild extends Build {
  val vaadinVersion = "7.0-SNAPSHOT"

  val root = Project("vaadin-scala-project", file("."), settings = Defaults.defaultSettings ++ Seq(
    Keys.libraryDependencies ++= Seq(
      "com.vaadin" % "vaadin-client-compiled" % vaadinVersion
      , "com.vaadin" % "vaadin-themes" % vaadinVersion
    )
    , Keys.resolvers += "vaadin snapshots" at "https://oss.sonatype.org/content/repositories/vaadin-snapshots"
  )) dependsOn (uri("https://github.com/henrikerola/scaladin.git#3.0"))
}
