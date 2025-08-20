ThisBuild / scalaVersion := "2.13.16"

lazy val root = (project in file("."))
  .enablePlugins(ProtocPlugin) // required for ScalaPB
  .settings(
    name := "learn-play-grpc",
    // ScalaPB codegen settings
    Compile / PB.targets := Seq(
      scalapb.gen() -> (Compile / sourceManaged).value / "scalapb"
    ),

    libraryDependencies ++= Seq(
      // ScalaPB runtime
      "com.thesamet.scalapb" %% "scalapb-runtime" % scalapb.compiler.Version.scalapbVersion % "protobuf",
      "com.thesamet.scalapb" %% "scalapb-runtime-grpc" % scalapb.compiler.Version.scalapbVersion,

      // gRPC Java dependencies (required for io.grpc.* classes)
      "io.grpc" % "grpc-netty" % "1.62.2",
      "io.grpc" % "grpc-stub" % "1.62.2",
      "io.grpc" % "grpc-protobuf" % "1.62.2",

      // Optional: logging & testing
      "ch.qos.logback" % "logback-classic" % "1.5.6",
      "org.scalatest" %% "scalatest" % "3.2.18" % Test
    )
  )
