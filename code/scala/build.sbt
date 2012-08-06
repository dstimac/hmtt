organization := "com.example"

name := "HMTT"

version := "0.0.0"

scalaVersion := "2.9.1"

scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "UTF-8", "-optimise")

unmanagedSourceDirectories in Compile <<= (scalaSource in Compile)(_ :: Nil)

unmanagedSourceDirectories in Test <<= (scalaSource in Test)(_ :: Nil)

resolvers := Seq(
  "Element Nexus"             at "http://maven.element.hr/nexus/content/groups/public/",
  "Element Releases"          at "http://maven.element.hr/nexus/content/repositories/releases/",
  "Element Snapshots"         at "http://maven.element.hr/nexus/content/repositories/snapshots/",
  "Element Private Releases"  at "http://maven.element.hr/nexus/content/repositories/releases-private/",
  "Element Private Snapshots" at "http://maven.element.hr/nexus/content/repositories/snapshots-private/"
)

externalResolvers <<= resolvers map { rs =>
  Resolver.withDefaultResolvers(rs, mavenCentral = false, scalaTools = false)
}

libraryDependencies ++= Seq(
  "hr.ngs.templater" %% "templater" % "1.7.0",
  "org.scribe" % "scribe" % "1.3.0",
  "org.hsqldb" % "hsqldb" % "2.2.8",
  "org.scalaquery" %% "scalaquery" % "0.10.0-M1",
  "com.github.scala-incubator.io" %% "scala-io-file" % "0.4.0"
)

