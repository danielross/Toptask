import sbt._

trait Defaults {
    def androidPlatformName = "android-7"
}
class Parent(info: ProjectInfo) extends ParentProject(info) {
    override def shouldCheckOutputDirectories = false
    override def updateAction = task { None }

    lazy val main  = project(".", "Toptask", new MainProject(_))
    lazy val engine = project("gae", "gae", new EngineProject(_))
    //lazy val tests = project("tests",  "tests", new TestProject(_), main)

    class MainProject(info: ProjectInfo) extends AndroidProject(info) with Defaults with MarketPublish with TypedResources {
        val keyalias  = "change-me"
        val scalatest = "org.scalatest" % "scalatest" % "1.3" % "test"
        val lift_json = "net.liftweb" %% "lift-json" % "2.3"
        override def proguardOption = "-keep class org.restlet.** -keep class org.codehaus.jackson.**"
    }

    class EngineProject(info: ProjectInfo) extends AppengineProject(info) with Defaults {
        
        // appengine depends
        val gae = "com.google.appengine" % "appengine-api-1.0-sdk" % "1.3.8"
        val liftVersion = "2.3"
        val lift_json = "net.liftweb" %% "lift-json" % "2.3"

        //restlet jars added manually
                

        // database depends
        val objectifyRepo = "Objectify-appengine Repository" at "http://objectify-appengine.googlecode.com/svn/maven" 
        val objectify = "com.googlecode.objectify" % "objectify" % "3.0"
        
        val servelet = "javax.servlet" % "servlet-api" % "2.5" % "provided->default"

        // restlet and datanucleus jars added manually
        
    }    

    class TestProject(info: ProjectInfo) extends AndroidTestProject(info) with Defaults
}
