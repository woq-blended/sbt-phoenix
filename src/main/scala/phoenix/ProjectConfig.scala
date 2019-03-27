package phoenix

import sbt.{AutoPlugin, ClasspathDep, Keys, Project, ProjectReference, file}

/**
 * Configuration of a project.
 * Projects and configuration facets are intended to inherit this trait and can be used as stackable trait.s
 */
trait ProjectConfig {

  /**
   * Create the sbt project.
   * Override this to customize the creation.
   */
  def createProject(): Project = {
    val name = projectName.split("[._]").foldLeft("") {
      case ("", next) => next
      case (name, next) => name + next.capitalize
    }
    Project(name, file(projectDir.getOrElse(projectName)))
  }

  /**
   * The Project name.
   */
  def projectName: String

  /**
   * Optional project directory (use this if not equal to project name)
   */
  def projectDir: Option[String] = None

  /**
   * Create a seq of sbt settings from this configuration.
   * If you override this method in your config, don't forget to call [[super.settings]],
   * so that it works properly as stackable trait.
   */
  def settings: Seq[sbt.Setting[_]] = Seq(
    Keys.name := projectName,
    Keys.moduleName := Keys.name.value
  )

  /**
   * Project dependencies.
   */
  def dependsOn: Seq[ClasspathDep[ProjectReference]] = Seq()

  /**
   * Project aggregations.
   */
  def aggregate: Seq[ProjectReference] = Seq()

  /**
   * The plugins to be used by this project.
   */
  def plugins: Seq[AutoPlugin] = Seq()

}
