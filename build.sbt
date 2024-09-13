name := """backend-malla-vial"""
organization := "com.pruebatecnica"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.13.14"

libraryDependencies += guice
