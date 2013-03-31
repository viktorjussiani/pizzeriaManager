import sbt._
import Keys._
import PlayProject._

object ApplicationBuild extends Build {

    val appName         = "pizzeriaManager"
    val appVersion      = "1.0-SNAPSHOT"

    val appDependencies = Seq(
      "org.hibernate" % "hibernate-entitymanager" % "3.6.9.Final",      
       "commons-io"   %   "commons-io"  % "2.4",
      "com.google.code.gson" % "gson" %  "2.2.2",
      "com.thoughtworks.xstream"    % "xstream" % "1.4.3",
      "net.tanesha.recaptcha4j" % "recaptcha4j" % "0.0.7"
 
    )

    val main = PlayProject(appName, appVersion, appDependencies, mainLang = JAVA).settings(
      // Add your own project settings here      
            ebeanEnabled := false

    )

}
