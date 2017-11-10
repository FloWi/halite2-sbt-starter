import Dependencies._


val myBotArtifactName = "MyBot" //don't change this - halite expects both the jar- and zip-file to be named MyBot.(jar|zip)

lazy val createZip = taskKey[File]("Creates a distributable zip file containing MyBot.jar and the LANGUAGE file.")
lazy val uploadBot = taskKey[Unit]("uploads the bot. make sure the hlt command is available and you already authenticated it.")

createZip := {

  val log = sLog.value

  val jarFile = assembly.value

  val zipFile = target.value / s"$myBotArtifactName.zip"
  val languageFile = baseDirectory.value / "LANGUAGE"

  log.debug(s"jarFile: $jarFile")
  log.debug(s"zipFile: $zipFile")

  log.info(s"deleting zipFile $zipFile")
  IO.delete(zipFile)

  IO.zip(List(
    (jarFile, jarFile.name),
    (languageFile, languageFile.name)
  ), zipFile)

  zipFile
}

uploadBot := {
  val log = sLog.value

  val zipfile = createZip.value
  log.info(s"uploading $zipfile using hlt command")
  s"hlt bot -b ${zipfile.absolutePath}" ! streams.value.log

  log.info(s"deleting $zipfile")
  IO.delete(zipfile)
}

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "de.flwi",
      scalaVersion := "2.12.3",
      version := "0.1.0-SNAPSHOT"
    )),
    name := "My Bot",
    libraryDependencies += scalaTest % Test
  ).
  settings(
    mainClass in assembly := Some(myBotArtifactName),
    assemblyJarName in assembly := s"$myBotArtifactName.jar"
  )

