package notes

import scala.language.postfixOps

object 3inheritance extends App {
  val p = new Parent()
  val c = new Child("Overrided child content: It's possible to override parent class attributes in the child class constructor itself")
  println(p.content)
  println(c.content)
  println(p.content2)
  (p print)
  (c print)


  println("Upcasting: treating a child class object as an instance of its parent class or trait.\n")
  val upcasting: Parent = c
  upcasting.print

  val sp = new SmartPhone()
  sp.takePhoto()
  sp.editImages()
  sp.playMusic()
  sp.trimMusic()
  println("In Scala type heirarchy, scala.any is the root of all types, it contains ==, equlas, hashcode,toString, its direct subtypes/subclasses are AnyVal (parent of all primitive data types) and AnyRef (parent of all reference types, its equivalent to java.lang.Object, it includes all scala classes, java classes, collections,tuples, string)\n scala.Null derives/extends from scala.AnyRef, so that is why for any ref type, u can replace what it;s pointing to, to Null, without any issues, since Null is a subtype of all the classes, the ref type is potinign to. \n scala.Nothing derives/inherits from both scala.AnyVal as well as scala.Null, i.e it is the bottom most subclasses, its inheriting all the classes used in Scala. nothing is used in exceptions. nothing does refers to instance of any class. nothign means nothing, it is of no type. ")

  println("IS list empty: "+EmptyList.isEmpty())
  val newList = EmptyList.addNode(1)
  println("IS list empty: " + newList.isEmpty())
  val addNodes = new ExistingLinkedList(newList.gethead(), new ExistingLinkedList(2, new ExistingLinkedList(3, new ExistingLinkedList(4, new ExistingLinkedList(5, EmptyList)))))

//  addNodes.printElements
  println(addNodes.toString)

} 


abstract class Phone(val CompanyName: String, val SimCardProvider: String, val phoneNo: Int){

  println("Abstract classes, can contain both abstract as well as non abstract methods. It's subclass must implement it's abstract definitions. Abstract classes cannot be instantiated")
  def makeCall() = println(s"Calling from $phoneNo")
  def sendMessage() = println(s"Messaging from $phoneNo")
  def setAlarm() = println("Alarm set at 5:00 A.M")
}


trait Camera {
  println("Traits are a type in Scala, here also both abstract and non abstract methods can be present, like abstract classes, but the difference is one class, can inherit from only 1 class, but it can inherit from multiple traits. SPo,it acts as multiple inheritance.")
  def takePhoto(): Unit
  def editImages(): Unit
}

trait MusicPlayer {
  println("CLasses = like a thing, Traits = acts as a behavior ")
  def playMusic(): Unit
  def trimMusic(): Unit
}


class SmartPhone extends Phone("Samsung","Jio",123456789) with Camera with MusicPlayer {
  println("Overriding abstracrt methods fo an abstract class/trait on its subclass, using overrided keyword is optional only.")

  println(s"Phone Details: \n $CompanyName\n$SimCardProvider\n$phoneNo")

    makeCall()
    sendMessage()
    setAlarm()

  def takePhoto() = println("Go to Camera App and press button")

  def editImages() = println("Go to Camera App and edit images")

  def playMusic() = println("Go to Music App and press button")

  def trimMusic() = println("Go to Music App and trim button")

  }





sealed class Parent() {
  val content = "Parent content: Im parent field"
  val content2 = "Parent content2: Im parent field"

  println("Parent: In Scala, it supports single class inheritance and multiple trait inheritance.")

  def print = println(s"Original Parent method")
  
  protected def protectedMethod():Unit = println("U cannot access a protected attribtue or emthod, outsied. U can access protected fields, only within the class or in its subclass")
  
  def this(a: String) = {
    this()
    println("Auxillary Parent Consturctor: "+a)
  }

  final def superKeyword() = println("I can'tt be overriden by any classes, since im a final method. 'super' keyword is used to access the parent's fields/members. It wsi sued inc ases, where u don't want to use the overriden child field members, instead use parent's fields")


}

final class Child(override val content: String) extends Parent("In Scala, whenver u try to execute a child class, before executign the child class constructor, if its inherits any parent, it's parents constructors r checked first, before child class constructors, if the child class constructos correctly inherit the valid constructor of parent class(either primary constructor or auxillary constructors, then only , it compiles, for e.g, here we can inherit class Child extends Parent {} \n class Child extends Parent(a: String) {}\n It would execute fine, but if, we exevute like class Child extends Parent(a: String, b:int) {} => it will lead to error, since there is no valid constructor of this type in the parent class.") {

  println(s"Child: $content")
  override  val content2 = "Overriding the parent class attribute, inside the child class body using override keyword"

  override def print = {
    println(s"$content2\n We can use final keyword, to make methods/classes not overriden/extended. If we use seal keyword, only the classes present in this file, would be able to extend the class, no any other classes in other fields can't extend the class. Here, sealed parent class can't be extended, in any other files classes.")
    protectedMethod()
    super.superKeyword()

  }


}



abstract class list {
  def isEmpty(): Boolean
  def addNode(h:Int,t: list): list
  def gethead(): Int
  def gettail(): list
  def printElements: String

  override def toString: String = "["+printElements+"]"
}


object EmptyList extends list{
  def isEmpty():Boolean = true
  def gethead() = throw new NoSuchElementException
  def gettail() = throw new NoSuchElementException
  def addNode(h: Int,t: list = null): list = new ExistingLinkedList(h,t)

  def printElements = ""
}


class ExistingLinkedList(val head: Int, val tail: list) extends list{
  def addNode(h:Int, t: list) = new ExistingLinkedList(h,t)
  def isEmpty(): Boolean = false
  def gethead() = head
  def gettail() = tail

  def printElements = {
    if (tail.isEmpty()) "" + head
    else head + " " + tail.printElements
  }


}










