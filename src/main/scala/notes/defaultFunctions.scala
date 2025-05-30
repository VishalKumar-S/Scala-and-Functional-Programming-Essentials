package notes

import notes.functions.*

import scala.util.Random

object defaultFunctions extends App{

  val num = List(1,2,3)
  val smallChar = List('a','b','c')
  val bigChar = List('A','B','C')


  println(num.flatMap(x=>List(x,x*7)))
  println(num.filter(x=>x%2==0))
  println("For each: ")
  println(num.foreach(println))

  println("Printing all combinatons of num, smallchar, bigchar")
  println(num.flatMap(n=> smallChar.flatMap(sC=> bigChar.map(bC => n.toString()+ bC + sC))))
  println("We can use for - comprehensions, intead of this complex chainign of flatpmaps and maps, which woudl make more readable, tpo ahcive the same result. Also in for comprehensiosn, we can have condiotns for each expressions, called as guards, which willa ct as filtr consiosnt, just the compierl will apply filter fucntion before applying that expression, let us see for each exmaple acheviing teh same resuslts along with ti contiaingin guards. For comprehensions r reqritten by the compiler, wit flaptmaps and maps")

  val forComprehensions = for {
    n<- num if n<10
    sC<- smallChar
    bC <- bigChar
  } yield n.toString()+ bC + sC

  println(forComprehensions)

  for{
    n<-num if (n%2)==0
  }println(n)

  val intList: MyFunctionalList[Int] = EmptyMyFunctionalList.addNode(1).addNode(2).addNode(3)
  val stringSmallList: MyFunctionalList[Char] = EmptyMyFunctionalList.addNode('a').addNode('b').addNode('c')
  val stringBigList: MyFunctionalList[Char] = EmptyMyFunctionalList.addNode('A').addNode('B').addNode('C')


  val forComprehensionOfMyFunctionalList = for{
    n<- intList
    sC<- stringSmallList
    bC <- stringBigList
  } yield n.toString()+ bC + sC


  println("Here, in our custom myList, we can use thsi for comprehensions since, we hav eimeplemtned the map, flatMap functison in our custoList,a dn teh compielr converts the for comrpehsesniosn into teh chain of flatMaps and Maps, if we didn't had theeses implrmentaations in our class,w e wodulnt be able to executed the for comprehensions")
  println(forComprehensionOfMyFunctionalList)

  println("May be useful in having a optional colelction liek sttuurtur which means have 1 valeu or no value to it")
  val maybeInstance = new Existingmaybe(1)
  println(maybeInstance)
  println(maybeInstance.map(x => x * 10))
  println(maybeInstance.flatMap(x=> new Existingmaybe(x+1)))
  println(maybeInstance.filter(x => x % 2 == 0))

  println(repeatedFill(5)("hello"))

  print("Sequence")
  val aSeq = Seq(1,2,3,4)
  println("" + aSeq + "\n" + aSeq.reverse + "\n" + (aSeq ++ aSeq.reverse) + "\n" + aSeq.map(x => x * 2) + "\n")

  print("List: In List, ther wodil be abstract class List, contains object Nil (equal to our EmptyFuncitonalMylist) and then class ::(which is eequal to the exisitingMyFInctionalList), the symbol :: is used to prepend elmens in list")
  val aList = List(1,2,3,4)
  println("" + aList + "\n" + aList.reverse + "\n" + (aList ++ aList.reverse) + "\n" + aList.map(x => x * 2) + "\n")
  println("Append: " + aList:+3)
  println("Prepend using +: : " + (10+:aList))
  println("Prepend  using :: : " + (10::aList))


  val aRange = 1 until 5
  aRange.foreach(x=>println(x))
  println(aRange ++ (6 to 10))
  val aArray = Array("A array in Scala, maps to teh native Array type in Java","It contains predefiend number of elements","We can ssign an array to an  Sequne, without any issues, compielr will do impleicit conversion of th array into a Sequcne liek sturcutre called wrappedArray, int4ernally")

  println(aArray)
  println(aArray.mkString("\n "))
  val seqArray: Seq[String] = aArray
  println(seqArray)
  println(seqArray.mkString(" "))
  val secondArray = Array.ofDim[String](3)
  secondArray(0) = "BY default, in Arrays, we can modify the element,s using () itself, its an syntactial usgqar for secondArray.update() method"
  secondArray(1) = "For prmiritves, defautl values would be 0 for array declaration"
  secondArray(2) = "For ref types such as Strings, the default values would eb NUll"

  secondArray.foreach(println(_))
  val maxSize = 100000

  def calculateTime(collection: Seq[Int]) = {
    val listTime = for {
      iteration <- 1 to 1000
    } yield {
      val randomIndex = Random.nextInt(maxSize)
      val currTime = System.nanoTime()
      collection.updated(randomIndex, randomIndex)
      System.nanoTime() - currTime
    }

    listTime.sum/1000
  }

  val ListTimeTaken = calculateTime(List.fill(maxSize)(0))
  val VectorTimeTaken = calculateTime(Vector.fill(maxSize)(0))

  println(s"Time Taken by list is ${ListTimeTaken}\n"+s"Time Taken by Vector is ${VectorTimeTaken}")

  
}


abstract class maybe[+T] {
  def map[B](function: (T=>B)): maybe[B]

  def flatMap[B](function: (T => maybe[B])): maybe[B]
  
  def filter(function: (T=>Boolean)): maybe[T]
  
}

object Emptymaybe extends maybe[Nothing] {
  def map[B](function: (Nothing => B)): maybe[B] = this

  def flatMap[B](function: (Nothing => maybe[B])): maybe[B]  = this

  def filter(function: (Nothing => Boolean)): maybe[Nothing] = this

  override def toString: String = "Emptymaybe"

}

class Existingmaybe[+T](node: T) extends maybe[T]{
  def map[B](function: (T=>B)): maybe[B] = new Existingmaybe(function(node))

  def flatMap[B](function: (T => maybe[B])): maybe[B] = function(node)

  def filter(function: (T=>Boolean)): maybe[T] = {
    if (function(node)) this
    else Emptymaybe
  }

  override def toString: String = s"Existingmaybe($node)"
}



def repeatedFill(x: Int): String=> List[String] = (s: String)=> {
  def rec(x: Int): List[String] = {
    if (x==0) Nil
    else s :: rec(x-1)
  }
  rec(x)
}

