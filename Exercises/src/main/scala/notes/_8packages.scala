package notes

//import learnPackages._
import learnPackages.{Vishal,Kumar}
import learnPackages.{Vishal=>firstName,Kumar=>lastName}

object _8packages extends App{
  println("Build tool (sbt, maven,gradle) are used to turn your .scala files into .class files (Java bytecode), Downloads libraries (like Play, Akka, etc.) from the internet,Follows and enforces folder conventions (like src/main/scala),Lets you run, test, and create .jar files,Helps IntelliJ automatically detect structure and suggestions, Helps IntelliJ automatically detect structure and suggestions , here it decides which path of ou directory contains the soruce code files and test files, if u need to change it,u need ot configure it, her we use sbt build tool, where it considers all th files inside src/main/scala to be considered as source code files, so if u create any package outside of this source file location,it will not considered, u cant import it. A package declaration tells the Scala compiler which namespace your code belongs to. It looks like this: package mypackage. This should be placed at the top of your Scala file. The physical location of the file inside the package, does not automatically put your code in that package. Scala needs to be told which package your code belongs to. Every Scala file should have a package declaration and the package name should match the directory structure Without a package declaration, your code exists in the \"default\" (unnamed) package, which has limitations. In Scala, to import multiple classes from the package, we mention those within the {}, and for aliasing package names, we use the symbol =>. Ther eis a concept called package object, it is ued to use the methods, varaibles - which are general, common to all, accessed by anyone directly, without the help of any class. all the things defined under package objects are universal to all, anyone can use it directly")
  val v = new Vishal()
  val k = new Kumar()

  println("Aliased classes")
  val aliasV = new firstName()
  val aliasK = new lastName()

  println("Package objects")
  println(AUTHOR_NAME)
  packageObject

}
