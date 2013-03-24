/* Copyright (C) 2013 sumito3478 <sumito3478@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package info.sumito3478.aprikot.sbt

import sbt._
import Keys._

object JavaProject extends Plugin {
  val newSettings = Seq(
    autoScalaLibrary := false,
    crossPaths := false,
    fork := false,
    organization := "info.sumito3478",
    publishMavenStyle := true,
    publishTo := Some(Resolver.sftp(
      "sumito3478 Maven Repository (push)",
      "maven.sumito3478.info",
      46877,
      "/var/www/maven.sumito3478.info") as (
        "sumito3478",
        new java.io.File(
          new java.io.File(System.getProperty("user.home")),
          ".ssh/sumito3478-sshkey"))),
    resolvers := Seq(
      "sumito3478 Maven Repository (pull)" at "http://maven.sumito3478.info/",
      "Typesafe Snapshots" at "http://repo.typesafe.com/typesafe/snapshots",
      "Maven Repository Mirror" at "http://uk.maven.org/maven2"),
    externalResolvers <<= resolvers map {
      rs =>
        Resolver.withDefaultResolvers(rs, mavenCentral = false)
    },
    libraryDependencies ++= Seq())
}