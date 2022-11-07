organization := "tv.cntt"
name         := "xitrum-sockjs-test"
version      := "1.0-SNAPSHOT"

scalaVersion := "2.13.4"

scalacOptions ++= Seq("-deprecation", "-feature", "-unchecked")

// Xitrum requires Java 8
javacOptions ++= Seq("-source", "1.8", "-target", "1.8")

//------------------------------------------------------------------------------

libraryDependencies += "tv.cntt" %% "xitrum" % "3.30.2"

// Xitrum uses SLF4J, an implementation of SLF4J is needed
libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.2.3"

// For writing condition in logback.xml
libraryDependencies += "org.codehaus.janino" % "janino" % "3.1.2"

// Put config directory in classpath for easier development --------------------

// For "sbt console"
Compile / unmanagedClasspath += baseDirectory.value / "config"

// For "sbt fgRun"
Runtime / unmanagedClasspath += baseDirectory.value / "config"

// Copy these to target/xitrum when sbt xitrum-package is run
XitrumPackage.copy("config", "public", "script")

fork := true
