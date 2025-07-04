package notes

import scala.annotation.tailrec

object _9functions extends App{

  println("In JVM, eveyrhting is related to Objected oriented onyl i.e class and objects, for functional programming, we will use this class objects itself as functions. Note,in Scala, all functions are either objects, or instances of a class. There is a concept, called Function types in scala, which r pre-defined traits, that represents functions. THere are a total of 22  function types in Scala. Examples:\n\nFunction1[A, B] → represents a function A => B\n\nFunction2[A, B, C] → represents a function (A, B) => C\n\n... all the way up to Function22 → for functions with 22 parameters.\n")


  val imAFunction = new explicitFunction {
    override def apply(s: String) = s"Here, imAFunction is an instance of the class explicitFunction, overriding its apply method, but in Scala, as its functionla porgramming, we can use this class instance, itself as an function, if i pass parameter to this imAFunction instance i.e Function,it will directly execute the apply method, passing the parameter ${s}"
  }

  println(imAFunction("Vishal"))


  val imAFunction2 = new Function2[String,String,String]{
    def apply(a: String, b: String): String = a+b
  }

  println(imAFunction2("Using ","function types"))

  val imASyntacticalSugarFunction2: (String, String)=>String = (A: String, B: String)=>A+B

  println(imASyntacticalSugarFunction2("We can represent the function types as syntactial sugar by ","Function2[A, B, C] = (A,B)=> C"))

  val iReturnAFunction: Function1[Int, Function1[Int,Int]] = new Function1[Int, Function1[Int,Int]]{
    override def apply(x: Int): Function1[Int, Int] = new Function1[Int, Int]{
      override def apply(v1: Int): Int = x+v1
    }
  }

  val iReturnAFunctionSyntacticalSugar: (Int)=>((Int)=>(Int)) = (A: Int)=>((B: Int)=>A+B)

  println("Returning a function inside a function in Scala "+iReturnAFunction(6)(7))

  println("Returning a function inside a function in Scala " + iReturnAFunctionSyntacticalSugar(6)(7)+"\nThis type of calling a function with some parameter, and then it returning a function, which having parameters is called as Curried function.")

  val withoutType: (String) => String = x =>s"The syntactical sugar anonymous functions, we write is called as Lambda functions.${x}"

  println(withoutType("Note, here in Lambda functions, if we mention the parameters type in the type of the ref variable itself, its optional to include the type of parameters in the function object creation part. Here, i didn't mention type for x in the object creation part"))

  val moreSyntacticalSugar: (String,String) => String = _ + _
  println(moreSyntacticalSugar("we can add more syntactical sugar to it ","by using _ symbol instead of eg., x=>x+1, just write x=>_+1, note here we didnt metnon teh type fo parameters, for _, sicne we already metnonde inte h ref varibael type, else compielr errror hap[pesn, as compierl wornt know teh type of the parameters else. Here, inthsi eg., we are adding 2 strings, sintead of (x:String, y:String)=>x+y, we write _ + _, note:type metninonign in the erf variable is mandatory in this case."))


  val addFunction: (Int) => Int = _ + 1

  def HOFadd(f: (Int)=>(Int), n:Int, x:Int): Int = {
    if (n <= 0) x
    else HOFadd(f,n-1,f(x))
  }

  println(HOFadd(addFunction,5,1))

  def HOFadd(f: (Int)=>Int, n: Int): Int=>Int = {
    if (n<=0) (x:Int)=>x
    else (x:Int)=>HOFadd(f,n-1)(f(x))
  }

  val calculated = HOFadd(addFunction,5)
  println("here, (x:Int)=> x is a identity function, i.e any x u give as input,the same x u get as output, when its returned from n = 0, in n = 1,it gets like   ((x:Int)=>x)(f(x)), i,e we apply the  (x:Int)=>x method,with the parameter x as f(x), then  (f(x):Int)=>f(x) results in f(x) only, so from n = 1, f(x) is returned, then at n = 2, f(x)(f(x)), so it becomes f(f(x)) and its returned, and finally at alst, we get the chain of functions ,f(f(f(f(f(x))))) i.e calculated = (x: Int) => f(f(f(f(f(x)))))")
  println(calculated(1))
  println("now,apply x i.e 1 to (x: Int) => f(f(f(f(f(x))))), where f(x) = (Int) => Int = _ + 1,so it gets incremeted 5 times as a chain")

  println("HOF (Higher Order Function) means teh function either gets the function as paremetr or retunrs its as a result, here in the first implemetnaiton, we immediately compute the answer for teh fucntion at each step adn returnt eh valeu finalyl, in the seocnd impemntation, we return a function, we do delayed executeion here, we ge the funciton as result, adn we cna re-use teh sma funcion fro repeietie takss, by plugging in the value alone. It separates logic building from value usage — a core idea in functional programming. first version uses tail recursion, second  one is not, it creates nested closures.")







  def greetMessage(Message: String)(repetitions: Int)(setStatus: Boolean)={
    if (setStatus) println(Message*repetitions)
    else println("This is a curried function, a curried function is a specific case of a higher-order function, where a function returns another function for each parameter, one at a time, from left to right direction. A curried function contains multiple parameter lists()()() and returns functions, supplying some arguments alone to a  curried function is called as Partially applied function. Currying hlpes in fcuntion re-use, u can preset common arguments, here 'def greetMessage(Message: String)(repetitions: Int)(setStatus: Boolean)' is actually  a syntactical sugar of def greetMessage(Message:String):Int=>Boolean=>String = (Message:String)=>(repetitions:Int)=>(setStatus: Boolean) => if (setStatus) println(Message*repetitions) else println('im the curried function without the syntactical sugar.'). Here,u can see first message is called with first message argument alone, now it retuns function fo remingin parameters i.e message = Int=>Boolean=>Unit function now, then repeatMessage passes second argument, then now repeatMessage have the returned function of Boolean=>Unit, then we pass the htird argument Boolean to repeatMessage, adn it finally returns teh Unit result i.e prints the statement. Here, it acts as a partially applied function.Curried functins r useful in code reuse")
  }

  val message = greetMessage("Hii Vishal\n")
  val repeatMessage = message(5)
  repeatMessage(true)
  repeatMessage(false)


  def greetMessageWithoutSyntacticalSugar(Message:String):Int=>Boolean=>Unit = (repetitions:Int)=>(setStatus: Boolean)=>
  {
    if (setStatus) println(Message*repetitions)
    else println("im the same equivalent of curried function without the syntactical sugar.")
  }

  greetMessageWithoutSyntacticalSugar("Hii Vishal")(1)(false)



  def nonCurriedtoCurried[x,y,z](f: (x, y) => z): x => y => z = x=>y=>f(x,y)

  val curriedFunction = nonCurriedtoCurried((x: String, y: Int) => println(s"${x * y}"))

  val greetMessage = curriedFunction("Converintg a non-curried function to a curried function\n")
  greetMessage(5)




  def CurriedtoNonCurried[x,y,z](f: x => y => z): (x,y) => z = (x,y) => f(x)(y)

  val nonCurriedFunction = CurriedtoNonCurried((x: String)=>(y: Int) => println(s"${x * y}"))

  nonCurriedFunction("Converting a curried function to a non-curried function\n",5)


  println("In both of teh curry to no curry adn vice vers,a we are expliceilty metioning teh type paratmerter [] near teh method name, so that it allows any type of x,y,z , act as genric unciton, if we dont wnat to metnon teh type poaramters expliceitly, then mention teh actual type itself , String=>Int=>Unit for example.")


  val f:Int=>Int = _*3
  val g:Int=>Int = _+5

  def compose(f: Int=>Int, g: Int=>Int): Int => Int = x=>f(g(x))

  def andThen(f: Int=>Int, g: Int=>Int): Int => Int = x=>g(f(x))

  println("Compose function "+compose(f,g)(3))
  println("andThen function "+andThen(f,g)(3))
  println("here, compose and anthen are implemetned as higher order functiosn, fxn which returns another funciton and input is applied on that returned function.")
  println("Upgrading our custom immutable list, by converting all function into lambda expressions, added functionalities of forEach, zip, sort, and fold.")
  val MapMyTransformer: (Int)=>Int = (A: Int)=> A*2


  val secondList = EmptyMyFunctionalList.addNode(1).addNode(2).addNode(3)
  println("List values: " + secondList.toString)

  println("Map Transformed Values: " + (secondList.map(MapMyTransformer)).toString)

  val flatMapTransform: Int => MyFunctionalList[Int] = A=> new ExistingMyFunctionalList(A, new ExistingMyFunctionalList(A + 1, EmptyMyFunctionalList))

  println("Flatten Map Transformed Values: " + (secondList.flatMap(flatMapTransform)).toString)



  val evenFilter: (Int)=>Boolean = _ % 2 == 0
  println("Even filtered Values: " + (secondList.filter(evenFilter)).toString)


  secondList.forEach(x=>println(x))

  println("Original List: "+secondList)
  println("Sorted List: " + secondList.sort((x,y)=>(y-x)))

  val thirdList = EmptyMyFunctionalList.addNode(3).addNode(6).addNode(9)
  println("Dividing "+thirdList+" by "+ secondList+"using custom zip function = "+ secondList.zipWith(thirdList,(x,y)=>y/x))

  println("Here, in ZipWith function,we use expliceit 2 type parameters U,V where U is the type of teh parametr seocnd list, and V is for teh resulting zipepd result type, V would be the common or least upper bound type fo result of each element")
  println("Fold function using "+thirdList + "to multiply all "+thirdList.fold(1,(x,y)=>(x*y)))
}

trait explicitFunction {
  def apply(s: String) = s"Hello World"
}

abstract class MyFunctionalList[+T] {
  def isEmpty(): Boolean

  def addNode[S >: T](h: S): MyFunctionalList[S]

  def gethead(): T

  def gettail(): MyFunctionalList[T]

  def printElements: String

  override def toString: String = "[" + printElements + "]"

  def map[B](mapTransformer: T=>B): MyFunctionalList[B]

  def flatMap[B](flatMapTransformer: T=> MyFunctionalList[B]): MyFunctionalList[B]

  def filter(condn: (T)=>Boolean): MyFunctionalList[T]

  def ++[B >: T](other: MyFunctionalList[B]): MyFunctionalList[B]

  def forEach(f: (T)=>Unit): Unit

  def sort(f: (T, T)=>Int): MyFunctionalList[T]

  def zipWith[U,V](list: MyFunctionalList[U], f: (T, U) => V): MyFunctionalList[V]

  def fold[B](start: B, f: (B, T) => B): B
}


case object EmptyMyFunctionalList extends MyFunctionalList[Nothing] {
  def isEmpty(): Boolean = true

  def gethead() = throw new NoSuchElementException

  def gettail() = throw new NoSuchElementException

  def addNode[S](h: S): MyFunctionalList[S] = new ExistingMyFunctionalList(h, EmptyMyFunctionalList)

  def printElements = ""

  def map[B](mapTransformer: Nothing=>B): MyFunctionalList[B] = EmptyMyFunctionalList

  def flatMap[B](flatMapTransformer: Nothing=> MyFunctionalList[B]): MyFunctionalList[B] = EmptyMyFunctionalList

  def filter(condn: (Nothing) => Boolean): MyFunctionalList[Nothing] = EmptyMyFunctionalList

  def ++[B](other: MyFunctionalList[B]): MyFunctionalList[B] = other

  def forEach(f: Nothing => Unit): Unit = ()

  def sort(f: (Nothing, Nothing) => Int): MyFunctionalList[Nothing] = this

  def zipWith[U,V](list: MyFunctionalList[U], f: (Nothing, U) => V): MyFunctionalList[V] = {
    if(!list.isEmpty()) throw new RuntimeException("Lists sizes are not equal")
    else this
  }

  def fold[B](start: B, f: (B, Nothing) => B): B = start


}


case class ExistingMyFunctionalList[+T](val head: T, val tail: MyFunctionalList[T]) extends MyFunctionalList[T] {
  def addNode[S >: T](h: S): MyFunctionalList[S] = new ExistingMyFunctionalList(h, this)

  def isEmpty(): Boolean = false

  def gethead() = head

  def gettail() = tail

  def printElements = {
    if (tail.isEmpty()) "" + head
    else head.toString + " " + tail.printElements
  }

  def map[B](mapTransformer: Function1[T, B]): MyFunctionalList[B] = {
    new ExistingMyFunctionalList(mapTransformer(head), tail.map(mapTransformer))
  }

  def flatMap[B](flattenedMapTransformer: Function1[T, MyFunctionalList[B]]): MyFunctionalList[B] = {
    flattenedMapTransformer(head) ++ tail.flatMap(flattenedMapTransformer)
  }

  def ++[B >: T](other: MyFunctionalList[B]): MyFunctionalList[B] = {
    new ExistingMyFunctionalList(head, tail ++ other)
  }


  def filter(evenFilter: (T) => Boolean): MyFunctionalList[T] = {
    if evenFilter(head) then new ExistingMyFunctionalList(head, tail.filter(evenFilter))
    else tail.filter(evenFilter)

  }

  def forEach(f: (T) => Unit): Unit = {
    f(head)
    tail.forEach(f)
  }

  def sort(f: (T, T) => Int): MyFunctionalList[T] = {
    def merge(li1: MyFunctionalList[T], li2: MyFunctionalList[T]): MyFunctionalList[T] = {
      if (li1.isEmpty()) return li2
      if (li2.isEmpty()) return li1

      if (f(li1.gethead(), li2.gethead()) > 0) {
        new ExistingMyFunctionalList(li1.gethead(), merge(li1.gettail(), li2))
      }

      else if (f(li1.gethead(), li2.gethead()) < 0) {
        new ExistingMyFunctionalList(li2.gethead(), merge(li2.gettail(), li1))
      }
      else {
        new ExistingMyFunctionalList(li1.gethead(), merge(li1.gettail(), li2))
      }
    }

    def getLength(List: MyFunctionalList[T], count: Int): Int = {
      if (List.isEmpty()) return count

      getLength(List.gettail(), count + 1)
    }

    def splitList(length: Int, counter: Int, list: MyFunctionalList[T]): (MyFunctionalList[T], MyFunctionalList[T]) = {
      if (counter == length / 2) (EmptyMyFunctionalList, list)
      else {
        val (fHalf, sHalf) = splitList(length, counter + 1, list.gettail())
        val newFirstHalf = new ExistingMyFunctionalList(list.gethead(), fHalf)
        (newFirstHalf, sHalf)
      }
    }

    def merge_sort(list: MyFunctionalList[T]): MyFunctionalList[T] = {
      if (list.gettail().isEmpty()) list
      else {
        val length = getLength(list, 0)
        val (f_h, s_h) = splitList(length, 0, list)
        val first = merge_sort(f_h)
        val second = merge_sort(s_h)
        return merge(first, second)
      }

    }

    merge_sort(this)
  }

  def zipWith[U,V](list: MyFunctionalList[U], f: (T, U) => V): MyFunctionalList[V] = {

    if(this.isEmpty() || list.isEmpty()) EmptyMyFunctionalList
    else new ExistingMyFunctionalList(f(this.gethead(), list.gethead()), (this.gettail()).zipWith(list.gettail(), f))
  }


  def getNode[B >: T](head: MyFunctionalList[B], start: Int, counter: Int): MyFunctionalList[B] = {
    if (counter == start) head
    else getNode(head.gettail(), start, counter + 1)
  }

  def fold[B](start: B, f: (B, T) => B): B = {
    this.gettail().fold(f(start,this.gethead()) ,f)
  }
}

