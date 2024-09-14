name := """backend-malla-vial"""
organization := "com.pruebatecnica"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.13.14"

libraryDependencies ++= Seq(
    guice,
    javaJdbc,
    "org.postgresql" % "postgresql" % "42.7.4",
    "org.hibernate" % "hibernate-core" % "5.6.11.Final"
)