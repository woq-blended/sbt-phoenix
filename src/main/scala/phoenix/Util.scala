package phoenix

import java.io.File
import java.nio.file.Files

import sbt.librarymanagement.{Binary, Constant, ModuleID}

trait Utils {

  def deleteRecursive(f: File): Unit = {
    if (f.isDirectory()) {
      f.listFiles().foreach(deleteRecursive)
    }
    f.delete()
  }

  def readAsVersion(versionFile: File): String = {
    Files.readAllLines(versionFile.toPath()).get(0).trim()
  }

  /**
    *
    * @param moduleID
    * @param scalaBinVersion We hard-code the default, to avoid to make this def a sbt setting.
    * @return
    */
  def artifactNameSuffix(moduleID: ModuleID, scalaBinVersion: String = "2.12"): String = moduleID.crossVersion match {
    case b: Binary => s"_${b.prefix}${scalaBinVersion}${b.suffix}"
    case c: Constant => s"_${c.value}"
    case _ => ""
  }


}


object Utils extends Utils