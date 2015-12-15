lazy val root = (project in file(".")).
  settings(
    name := "spark-streaming-facebook",
    version := "0.1",
    scalaVersion := "2.10.4",
    mainClass in Compile := Some("FacebookStreamingApp")
  )

libraryDependencies ++= Seq(
  "com.github.scala-incubator.io" %% "scala-io-core" % "0.4.3",
  "com.github.scala-incubator.io" %% "scala-io-file" % "0.4.3",
  "org.apache.spark" %% "spark-core" % "1.5.1" % "provided",
  "org.apache.spark" %% "spark-streaming" % "1.5.1" % "provided",
  "org.apache.spark" %% "spark-streaming-facebook" % "1.5.1",
  "org.facebook4j" % "facebook4j-core" % "3.0.6",
  "com.datastax.spark" %% "spark-cassandra-connector" % "1.5.0-M2",
  "org.scalatest" %% "scalatest" % "2.2.4" % "test",
  "org.scalaj" % "scalaj-http_2.8.1" % "0.3.0"
)

// META-INF discarding
mergeStrategy in assembly <<= (mergeStrategy in assembly) { (old) =>
   {
    case PathList("META-INF", xs @ _*) => MergeStrategy.discard
    case x => MergeStrategy.first
   }
}