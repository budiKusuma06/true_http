val projectName = "true_http"
val currentVersion = "3.4.2"

val http = Seq(
  "dev.zio" %% "zio" % "2.1.7",
  "dev.zio" %% "zio-http" % "3.0.0-RC9",
  "dev.zio" %% "zio-json" % "0.7.2"
)
val html = Seq(
  "com.lihaoyi" %% "scalatags" % "0.13.1"
)

lazy val root = project.in(file(".")).settings(
    name := projectName,
    version := "0.1",
    scalaVersion := currentVersion,
    
    mainClass := Some("id.true_http.Main"),

    libraryDependencies ++= http ++ html
  )

