package phoenix

import sbt._

trait ProjectFactory {

  /**
   * Configuration of the project.
   */
  def config: ProjectConfig

  /**
   * Access to the created singleton project.
   */
  lazy val project: Project = createProject()

  /**
   * Override this to customize project creation.
   * This is only needed in very rare situation.
   * One of such situation could be two projects which need to refer to each other (via dependsOn/aggregate).
   * In such a case it might be necessary to break up the cycle by not referring to the other project via [[ProjectConfig.dependsOn]]
   * and then invoke [[Project.dependsOn]] afterwards.
   */
  protected def createProject(): Project = {
    val projectConfig = config
    config.createProject()
      .settings(projectConfig.settings)
      .enablePlugins(projectConfig.plugins: _*)
      .dependsOn(projectConfig.dependsOn: _*)
      .aggregate(projectConfig.aggregate: _*)
  }
}
