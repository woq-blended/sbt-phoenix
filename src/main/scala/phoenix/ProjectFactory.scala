package phoenix

import sbt._

trait ProjectFactory {
  def config: ProjectConfig

  lazy val project: Project = {
    val projectConfig = config
    config.createProject()
      .settings(projectConfig.settings)
      .enablePlugins(projectConfig.plugins: _*)
      .dependsOn(projectConfig.dependsOn: _*)
  }
}
