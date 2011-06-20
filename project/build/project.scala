import sbt._

trait Defaults {
    def androidPlatformName = "android-7"
}
class Parent(info: ProjectInfo) extends ParentProject(info) {
    override def shouldCheckOutputDirectories = false
    override def updateAction = task { None }

    lazy val main  = project(".", "Toptask", new MainProject(_))
    lazy val engine = project("gae", "gae", new EngineProject(_))
    lazy val tests = project("tests",  "tests", new TestProject(_), main)

    class MainProject(info: ProjectInfo) extends AndroidProject(info) with Defaults with MarketPublish with TypedResources {
        val keyalias  = "change-me"
        val scalatest = "org.scalatest" % "scalatest" % "1.3" % "test"
        val lift_json = "net.liftweb" %% "lift-json" % "2.3"
    }

    class EngineProject(info: ProjectInfo) extends AppengineProject(info) with Defaults {
        
        // appengine depends
        val gae = "com.google.appengine" % "appengine-api-1.0-sdk" % "1.3.8"

        // database depends
        val objectifyRepo = "Objectify-appengine Repository" at "http://objectify-appengine.googlecode.com/svn/maven" 
        val objectify = "com.googlecode.objectify" % "objectify" % "2.2.2"
        val jpa = "javax.persistence" % "persistence-api" % "1.0"
    }    

    class TestProject(info: ProjectInfo) extends AndroidTestProject(info) with Defaults
}
