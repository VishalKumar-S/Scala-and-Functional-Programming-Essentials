package notes

import scala.language.postfixOps

object _2oops extends App {
  println("When your object extends App, the Scala compiler implicitly generates a public static void main(String[] args) method (the standard entry point for a Java Virtual Machine - JVM) within the corresponding Java class. The code you write directly inside the body of your App-extending object gets translated into the body of this generated main method.\n")

  println("In Scala, unlike other languages, the class definition itself acts as constructor. We can have all the necessary parameters we need to have in the class. Note, that the parameters defined in the class name, are not class fields, they r class parameters. We cant access like student.name since all the parameters defined in the class r class parameres Thye only become class fileds, if u mention val in those parameter variables, then only they become class fiedls, we can access liek its a class memeber. ANtyhing u devlar iwhtin the class body is cosnidered to class mamber only. Here, in method overloading, there should be difference between no of parameters/type of aprameters used if ht emethod name is same. Just difference in the return type, but same method name with smae method signature cant be considered as method overlaoding. Here, in Scala, multiple consturcotrs can be declared, but is of no use. If u need a consutrctor with different parameters, u need to declare a suzillary constuctor,a dn tha tocnstuor implementaiton is, to call any of the already exsiting constructor, by giving out the fields, that the current constuctor misses out, finally all the invoked cosntructoes owudlc allteh primary constuctor at last. The first statemnt fo the auxially constuctor is to call its preisvouly deifned cosntuctor, any othe stametn will lead to issue.,it wokrs like this only, so it is of complete no use. So, instead of having mulitple auxialry constructors, u can use default parameters in teh class name itself.")
    println("We can access name attribtue of class, sicne its delcared iwth val,so its now a class field/memebr, not just a class parameter. BUt if u tyr to access rollNo, it causes compierl issue,since rollNo is just a class parameter,its not a class field")
    val s1 = new Student("Vishal",122,"India")
    println("Accesing class field name, simiarlyl, we cant access rollNo,as rollNo is not a cclass field, as it didnt have val keyword, Name: "+s1.name)
    println(s1.greet("Blue"))
    println(s1.greet("Blue","Indian Food"))
    println("if we create a method in the class like def greet(color: String,food: String): String , it will deicintely cause compiler issue,s ince smae emthdo is already savibael with onyl return type dfifference as Unit all toehr emthdo signatue r same, so compielr confueses b/w calling which method.")
    val s2 = new Student("kumar",122)
    val s3 = new Student("kumar")

  val c1 = new Counter()
    val c2 = c1.inc
    val c3 = c2.dec
    c3.print
    val c4 = c3.inc(10)
    c4.print
    val c5 = c4.dec(5)
    c5.print

    c5.dec.dec.dec.dec.dec.print
    println("Current c5 value: \n"+c5.print)
    c5.dec.dec.dec.dec.dec.print

    val name = new SyntacticalSugar("Vishal")

    println("**Infix notation: 1 parameter methods **")
    println(name.issame("vishal"))
    println(name issame "vishal")

    println(name.multiply(4))
    println(name multiply 4)

    println(name.+(5))
    println(name + 5)
    println(name+ " Im an overlaoaded infix notation method")
    println(name ? "sgdvdvd")
    println(name.?("sgdvdvd"))
    println("**Prefix notation: Unary operators **")
    println(name.unary_-)
    println(name.unary_+)

    println(-name)
    println(+name)
    println(~name)
    println(!name)


  println("**Postfix notation: Non parameter methods**")
  println(name.postfixMethod)
  println(name postfixMethod)

  println("Apply method")
  println(name.apply())
  println(name())
  name.addAge(5)
  val fullName = (name fullNameObject "Kumar. S")
  fullName.addAge(5)
  fullName("Here, i created a new object using infix notation and then added age attribute to the object,by declareign age varibel within the class body adn usign helper method, and then using this postfix expression to execute overloaded apply method")

  println("In Scala, there is no class-level/static attribtues for a class, that we can access without creatign an isntance of teh class, like other programming langauges. So, to voercome it , in Scala, we have object, object is a own unqiue type. when we define a  object, it acts as a singleton instance i.e only instance of it's type. So, inside object, we can have all the methods, fields adn we can access it directly using the object, there are no parameters for a scala object, whenever an class and an object is declared at the same scope, they both r called as companions. Companions can access also each otehr;s private memebrs. That is inside a class, we can have inner class or have object, adn we either create an instance of other class within the class, or use the instance fields of the current class, or if we want to access-static like fields, we are going to use the objects, where objects also singleton instance, so to conclude, in Scala, everything we access is only instance based. A Scala application  = Scala Object + def main(args: Array[String]): Unit method, it resembles the same Java structures public class Main ->psvm, here since every scala application or code is a scala object, we can access the main method, like class-attribute, resemebles teh static keywrod in psvm, here the main emthod retursn Unit, which resmbles the return type void of psvm in Java. With the help of scala objects, we can easily create singleton classes liek type, with just one line of scala object declaration, isntead of any other extra code, written by us.")

  val p1 =  new Persons()

}



class Persons() {

  object Person {
    val hair = "Black"
    val eyes = "Blue"

    def apply(Mother: Person, Father: Person) = {
      println("Child name is: " + (Mother.name + Father.name) + s" with $hair hair and $eyes eyes")
      new Person(Mother.name + Father.name)
    }
  }

  class Person(val name: String = "Human")
  {
    println(s"Variables of Person Object: \nName: ${name}\n hair: "+Person.hair+"\nColor: "+Person.eyes)
  }

  val objPerson1 =  Person
  val objPerson2 = Person
  val instPerson1 = new Person()
  val instPerson2 = new Person()

  println("Do both objects of object Person r same? "+ (objPerson1 == objPerson2))

  println("Do both objects of class Person r same? " + (instPerson1 == instPerson2))

  val mother = new Person("Lavanya")
  val father = new Person("Kumar")

  val child = Person(mother, father)
  
  
  
}





class SyntacticalSugar(val name: String = "Vishal Kumar. S") {
  def multiply(repetitions: Int): String = name*repetitions
  def +(repetitions: Int):String = name+repetitions
  def +(lastName: String): String = name + lastName
  def issame(name: String) = name==name
  def ?(d: String) = s"Can't understand what u mean by $d"


  def fullNameObject(lastName: String): SyntacticalSugar = new SyntacticalSugar(this + s"$lastName (Using Overloaded + infix notation method)")


  def unary_- = -1 + name

  def unary_! = "!" + name

  def unary_~ = "~" + name

  def unary_+ = "+" + name

  def postfixMethod = "Prefix and infix are unambiguous and widely accepted, as prefix notation has only  4 symbols, hardcoded into the compiler as method names like unary_+, unary_-, etc. In Scala, a postfix expression is when a method is called on an object without any arguments and without using a dot (.) before the method name.The method being called takes no parameters.Instead of object.method, you just write object method. Postfix adds risk, especially when methods and fields overlap. Using postfix notation is deprecated, since it leads to ambiguities,its behavior can be inconsistent depending on the context, and it can lead to code that's harder to read and maintain. The syntax like name postfixMethod() is not allowed since when you write name postfixMethod(), it becomes ambiguous whether you're trying to call postfixMethod with no arguments on name, and then call the result as a function Or you're trying to call postfixMethod() on name using postfix notation. So, use like println(name postfixMethod). If u r psotifx method does not return anything i.e Unit returning method, then if u access like name postfixMethod , will lead to compiler issue. It work if used like (name postfixMethod), that too only if u import import scala.language.postfixOps, since In Scala, postfix notation (i.e., calling a method without . and ()) like p print or even (p print) is disabled by default, regardless of parentheses, to avoid ambiguity and encourage clear syntax.\n"

  def apply() = "There is a special method named apply(), if we use that method, we even don't want to call the method name, we can directly call the object like a method, the apply method lets you call the object itself as a function."

  var age: Int = 0
  def addAge(age: Int) = {
    this.age = age
  }

  def apply(content: String) = println(content + "\nNew full Name: "+this.name+"\nNew age attribute: "+this.age)




  println("In Scala, naming methods are more permissive. i.e we can name methods whatever we want, inclduing symbols like +/*-, no isseus in it in Scala, whereeas in other languages, it is reserved. In Scala, for 1 parameter methods, we need not need to use . keyword to access the method, we can just simpy write teh method word between the obj and actual parameter for e.g println(student.studies(5)) can be written as println(student studies 5), this method acts like an operator. i.e All operators we use in Scala are methods by default e.,g 1 + 2 = 1.+(2) def +(a) = {}. This easy way of acessing for one parameter methods is called as infix/operator notation. Prefix notation means unary operators, there  r 4 unary operators  +,-,~,! .All unary opertors/methods have prefix method syntax unary_{UNARYSYMBOL}. Unary methods are ALWAYS called without parentheses: +name.You cannot call them as +(name) or +()name - these are syntax errors. The prefix notation is special syntax, not a typical method call. Postfix notations r used for non parameter methods." +
    " e.g obj.isAlive() =  obj isAlive . Postfix notation is rearely used, as its syntax causes confusion This way of writing easily in natural language, instead of writing it in a complex manner is called as Syntactical Sugar. Infix/operator, Prefix and Postfix all these notatons r examples for syntactical sugar.")

}

class Counter(val count: Int = 0) {

  def inc: Counter = {
    println("Incrementing from " + count + "to " + (count + 1))
    new Counter(count + 1)
  }

  def dec: Counter = {
    println("Decrementing from " + count + "to " + (count - 1))
    new Counter(count - 1)
  }

  def inc(n:Int): Counter = {
    if (n <= 0) return this
    else inc.inc(n - 1)
  }

  def dec(n: Int): Counter = {
    if (n <= 0) return this
    else dec.dec(n - 1)
  }

  def print: Unit = {
    println("Current count value: " + count)
  }



}





class Student(val name: String, rollNo: Int, address: String) {
  def greet(color: String): Unit = {
    println(s"$name's favorite color is $color")
  }

  def greet(color: String,food: String): Unit = {
    println(s"$name's favorite color is $color and favoruite food is $food")
  }

  def this(name: String, rollNo: Int) = {
    this(name, rollNo, "Nepal")
    println("Im currenlty in this(name: String,rollNo: Int). This is an suxilllay constructor, which calls the already defined pimary constucorr ,with giving the value for the address parameter.")
  }

  def this(name: String) = {
    this(name, 122)
    println("Im currently in .this(name: String) This is an auxilllay constructor. Auxiliary Constructors must be named this. THey must call another constructor ,eventually to teh primary cosntructor as their first action. It Cannot have the same name as the class, like oither programmign languages. Auxillay means suplementing or helper\n. The current auxillaly constructor which calls the already defined auxillay constucorr,which calls the primary constuctor at last. with giving the value for the address parameter. Instead of all thses unnecesary thigns, use default parameters itself, so no need of this unnecesary ausxialalry constrcutors.\n Have like class Student(val name: String = 'vishal', val rollNo: Int = 122, val address: String = 'India')")
  }

  println("In Scala, you can have code directly within the class body, outside of any method or constructor. This code is executed as part of the class initialization process. when u create a enw isntance of a class, memory is allocate for the object, adn the contructor is called, before the constructor body is executed,  all the statements in the class body (that are not part of a method or constructor) are executed. then The constructor body is executed.")
}








