lazy val root = (project in file("."))
  .enablePlugins(SbtPlugin)
  .settings(
    organization := "de.wayofquality.sbt",
    name := "sbt-phoenix",
    version := "0.1.0",

    licenses += ("Apache-2.0", url("https://www.apache.org/licenses/LICENSE-2.0.html")),

    scalaVersion := "2.12.8",
    scalacOptions ++= Seq(
      "-unchecked",
      "-deprecation",
      "-Xlint",
      "-encoding", "UTF-8"
    ),

    libraryDependencies ++= Seq(
    ),

    // Scripted plugin allows us the integration test the sbt plugin
    scriptedLaunchOpts ++= Seq(
      "-Xmx1024m",
      s"-Dplugin.org=${organization.value}",
      s"-Dplugin.name=${name.value}",
      s"-Dplugin.version=${version.value}"
    ),
    scriptedBufferLog := false
  )

