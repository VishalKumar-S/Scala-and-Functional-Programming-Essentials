package notes

object _15patternMatching extends App{
  println("Pattern matching is used to match a value's structure with case statements, if none of the mentioend cases match with the value, the default case woudl execute, we will execute default case, using _ . If ther is no default case, it would hrow werror,id none of th cases match. Pattern amthcing is onde iwth only case classese, not iwth normal classes. WHeever an first case i smathced, it woudl return that case experession, the type of the pattern amtching expression woudl be the least supertype of all the case expressions. In pattern matching, we can decompose valeus  i.e fields from the instance of the class. This feature is autoamtically provided by the compielr for case classses, it applied an .unapply() method on the comapnion case object to take instance of the class as input and generate Option, containsing teh construcotr paramters, whereas fro anormla class,its not psosibel autaotmaiclaly, u need to do manual impelemtnaiton to extract teh fields. We can have guards in teh case expressions, i.e fitler like option if statemtns in the case option itself. ALthough pattern amtching resemebles like a swithc statemtn, but tis more than that, it supports deconstruction i.e extract fields from case classes or from data structures(e.,g list), it can do pattern amtching with complex data types like tuples, case classes, lists, options, guards, custom extractors, whereas switch is limtied to onyly fewer primitive types, next is pattern matching offers exhaustive checking for sealed traits, i.e when u use a sealed class's instance as a value, the compiler shows warning, if u missed any case,  switch does not retuns  avlue, but e pattern matching retursn a value, it can have nested cases, etc.,")


  def matching(value: Any) = value match {
    case 1 => println("Im One")
    case 5 => println("Im five")
    case Parent(a,b) if (!(a.length>2) && !(b.length>2)) => println(s"His father is ${b} and mother is ${a}")
    case (1,2) => println("im a tuple")
    case(anything,2) => println("Pattern amtching in a tuple with only few fields")
    case(anything,(anything_inside,7)) => println(" NestedPattern amtching in a tuple with only few fields")
    case EmptyMyFunctionalList => println("Im a  empty List")
    case ExistingMyFunctionalList(_,ExistingMyFunctionalList(_,ExistingMyFunctionalList(1, EmptyMyFunctionalList))) => println("CUstom pattern amtching in a custom list")
    case List(firstValue,2, thirdValue,5)=> println("Although List is not an case class/object, it has constructors to extract firedls from it, so its possible to do it here.")
    case nameBinding @ head::2::tail => println("Name binding lets you give a name to the entire matched value while destructuring part of it.\nName binded variable is: " + nameBinding+"\nDeconstructed head:  "+ head+"\n Deconstructed tail: "+ tail)
      case List(2,_) | List(_,5) | List(_,4,_) => println("This is used for OR pattern atmching, multiple matching patterns for exeute a sepcpfic thing,useful when mutliple strucure requries same fucntionaity. All the strucutes used to be of same type i.e  same type of unapply() extractor")
    case head::secondElement::Nil=> println(s"First = ${head}, Second = ${secondElement}")
    case x: List[Int] => println("I'm of type List[Int]. Here,im giving explicit type specifier. Due to type erasure during runtime, jvm can't differntiate between teh generic types, it onyl checks the raw types, not th e paraemter type i.e List[String], List[Int] all woudl bec isndiered for this case, it jsut hcecks its  a list or not")
    case x: List[String] => println("I'm of type List[String]. Here,im giving explicit type specifier. Due to type erasure during runtime, jvm can't differntiate between teh generic types, it onyl checks the raw types, not th e paraemter type i.e List[String], List[Int] all woudl bec isndiered for this case, it jsut hcecks its  a list or not")
    case List(a,b,c,d,_*)=>println("It can contain arbitrayr number of elements. Its onyl psosiebl in List, tuples have fuxed no of elements, tupele and sets doesnt have thid ufncitonality,u cant use it fro them")
    case _ => println("Im default")
  }

  case class Parent(mother: String, Father: String)
  val person = new Parent("XX", "XY")

  val ls = EmptyMyFunctionalList.addNode(1).addNode(2).addNode(3)

  val list = List(1,2,3,5)
  val stringList: List[String] = List("SFsvr")

  matching(5)
  matching((1,2))
  matching((8,2))
  matching((10,(20,7)))
  matching(ls)
  matching(list)
  matching(person)
  matching(stringList)
  matching((1,List(2,3)))
  matching("Abcsfdvv")
  matching(List(2,3))
  matching(List(3,5))
  matching(List(1,2,3,4,5,6,7,8,9))
  matching(9::8::Nil)
  matching(List(4,5))
println("When u do head :: tail => ...  You're using pattern matching with Scala’s List. Internally, :: is a case class, and :: and Nil have unapply methods used in pattern matching.")

val tupleList = List((1,2),(3,4),(5,6))
for(x<-tupleList){
  println("A generator is the part inside a for-comprehension that extracts values from a collection (or something similar like an Option or Future"+x)

}

  println("We can do pattern matching in generators, generators in for comprehensions r pattern matching, we can also do name binding in generators")
for(singleElement @ (firstValue, secondValue) <- tupleList){
  println("Total element: "+singleElement+"\n"+"First Value: "+firstValue+"\n"+"Second Value: "+secondValue)
}

println("pattern matching can be done in anonymous function,  ")
val li = List(1,2,3)
val transformedli = li.map{
  case x if x%2==0 => "Im even"
  case y if y%2!=0 => "Im odd"
}

println(transformedli)


  try {
    val x = 10 / 0
  } catch {
    case e: ArithmeticException => println("Arithmetic error!")
    case e: Exception => println("General exception")
  }

  try {
    println("under the hood, this is the syntactical sugar fo try-catch blcok statment, catch statemt also uses aptten matching only.")
    val x = 10 / 0
  } catch {
    new PartialFunction[Throwable, Unit] {
      def apply(e: Throwable) = e match {
        case e: ArithmeticException => println("Arithmetic error!")
        case e: Exception => println("General exception")
      }

      def isDefinedAt(e: Throwable) = true
    }
  }


  sealed trait Expression
  case class Number(n: Int) extends Expression
  case class Add(n1: Expression, n2: Expression) extends Expression
  case class Substract(n1: Expression, n2: Expression) extends Expression
  case class Multiply(n1: Expression, n2: Expression) extends Expression
  case class Divide(n1: Expression, n2: Expression) extends Expression


  def expressionMatching(exp: Expression): String = exp match{
    case  Number(n: Int)=> s"${n}"
    case  Add(n1: Expression, n2: Expression) => s"(${expressionMatching(n1)}+${expressionMatching(n2)})"
    case  Substract(n1: Expression, n2: Expression) => s"(${expressionMatching(n1)}-${expressionMatching(n2)})"
    case  Multiply(n1: Expression, n2: Expression) => s"(${expressionMatching(n1)}*${expressionMatching(n2)})"
    case  Divide(n1: Expression, n2: Expression) => s"(${expressionMatching(n1)}/${expressionMatching(n2)})"
  }

  println(expressionMatching(Multiply(Add(Number(1),Number(2)),Number(3))))
  println(expressionMatching(Multiply(Add(Number(1), Number(2)), Add(Number(3), Number(4)))))



  class braceless:
    if 2<3 then println("Scala;s braceless syntax allows it to wrtei syntaxed of if statemtns, for comprehension, function, anonymoud methods, classes, obejcts, traits wihtout curly braces, similat to Python. For if statmetn without () use then. WHenever tehre are a lot of statemsn wihtina  a block , then its hard to dfind the end of that block, so use end block iin all code blocks iof this bracelss syntax whevner code increases, end token can be used fro all types -if statemtns, methods, for , classes")

    val bracelessForComprehension =
      for
        x <- li

      yield x*2

    println("Im a braceless syntax for for comprehensions")
    println(bracelessForComprehension)

    def method(): Unit=
      println("Ima braceless method")

    end method

    println(method())

    val bracelessPatternMatch =
      li match
        case List(1,_*)=> println("Im braceless apttern amtching")
        case _ => println("im default")

    end bracelessPatternMatch

    println(bracelessPatternMatch)

  end braceless










}
