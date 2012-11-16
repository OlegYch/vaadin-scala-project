import sbt._

object ApplicationBuild extends Build {
  val vaadinVersion = "7.0.0.beta9"
  //  val vaadinVersion = "7.0-SNAPSHOT"

  val root = Project("vaadin-scala-project", file("."), settings = Defaults.defaultSettings ++ Seq(
    Keys.libraryDependencies ++= Seq(
      "com.vaadin" % "vaadin-client-compiled" % vaadinVersion
      , "com.vaadin" % "vaadin-themes" % vaadinVersion
      , "com.vaadin" % "vaadin-shared" % vaadinVersion
      , "com.vaadin" % "vaadin-server" % vaadinVersion
      , "com.vaadin" % "vaadin-theme-compiler" % vaadinVersion
      , "org.eclipse.jetty" % "jetty-webapp" % "8.0.4.v20111024"
      , "ch.qos.logback" % "logback-classic" % "1.0.7"
      , "org.slf4j" % "jul-to-slf4j" % "1.7.2"
    )
    , Keys.resolvers += "vaadin snapshots" at "https://oss.sonatype.org/content/repositories/vaadin-snapshots"
    , Keys.scalaVersion := "2.10.0-RC2"
    , Keys.unmanagedResourceDirectories in Compile += file("src/main/webapp")
  )) dependsOn (uri("https://github.com/henrikerola/scaladin.git#3.0"))
}
