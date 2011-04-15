import sbt._

class HelloWorldProject(info: ProjectInfo) extends DefaultProject(info) {
  val specs2 = "org.specs2" %% "specs2" % "1.2-SNAPSHOT"
  val gson = "com.google.gson" % "gson" % "1.6.0" % "compile" from "http://google-gson.googlecode.com/files/google-gson-stream-1.6.jar"
  
  val htmlrunner = "org.pegdown" % "pegdown" % "0.9.0"
  def specs2Framework = new TestFramework("org.specs2.runner.SpecsFramework")
  override def testFrameworks = super.testFrameworks ++ Seq(specs2Framework)

  val snapshots = "snapshots" at "http://scala-tools.org/repo-snapshots"
  val releases = "releases" at "http://scala-tools.org/repo-releases"
  val gson_r = "gson_releases" at "http://google-gson.googlecode.com/svn/mavenrepo"
}
