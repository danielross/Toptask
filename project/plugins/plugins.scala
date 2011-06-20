import sbt._

class Plugins(info: ProjectInfo) extends PluginDefinition(info) {
    val android = "org.scala-tools.sbt" % "sbt-android-plugin" % "0.5.1"
    val appenginePlugin = "net.stbbs.yasushi" % "sbt-appengine-plugin" % "2.2"
}
