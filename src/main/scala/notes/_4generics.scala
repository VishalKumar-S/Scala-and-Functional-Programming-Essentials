package notes



object _4generics extends App{
    println("Generic types are within []. Like classes, traits can also have generic types. Variance defines the relationship between complex types based on their component types. In Scala, this applies particularly to generics (like List[T]). There r 3 variance notations in Scala")
    val fruitsContainer = new Container[Fruits](new Fruits("apple"))
    val foodContainer: Container[Food] = fruitsContainer
    foodContainer.consumeTValue(new Vegetables("BeetRoot"))
    foodContainer.produceStoredTValue()
    foodContainer.produceNewTValue()

    val readFile = new ReadPDFs[AllFileFormatReader](new AllFileFormatReader("demo.py","py"))
    val readPDF: ReadPDFs[PDFReader] = readFile
    readPDF.consume(new PDFReader("demo.pdf"))
    readPDF.produce()
    val dogBox = new MutableBox[Dog]
    dogBox.set(new Dog())
    println("In simple terms, +T → Covariant: Like an output (you can return it), -T → Contravariant: Like an input (you can take it), -T → Contravariant: Like an input (you can take it). Covariance allows substituting a generic type with its subtype. covariance makes it more generic — you can refer to a food of a specific type as a food of a more general type. i.e  container of vegetables can be seen as a container of food. Contravariance allows substituting a generic type with its supertype. contra-variance makes it more specific — i.e someone can handle all file types, can also handle pdf datatype. Invariant means exact that type only\n\n")

    println("Generic Immutable LinkedList")
    val firstElement = EmptyMyList.addNode("Vishal")
    val secondElement = firstElement.addNode(2)
    val thirdElement = secondElement.addNode(new Animal())
    val fourthElement = thirdElement.addNode("Kumar. S")

    println(fourthElement)
    println(
        """
  ┌─────────────────────────────────────────────────────────────────────────────────────────────────┐
  │                                       MyList Summary                                          │
  ├─────────────────────────────────────────────────────────────────────────────────────────────────┤
  │ We built an immutable linked-list, MyList[+T], starting from one singleton empty list          │
  │ (EmptyMyList: MyList[Nothing]) and prepending elements of widening types. Because MyList is    │
  │ covariant (+T), the compiler treats EmptyMyList (MyList[Nothing]) as MyList[String],           │
  │ then MyList[Any], etc., at each addNode call. Each call infers the smallest supertype S >: T   │
  │ that can hold both previous and new element, builds a new ExistingMyList[S], and returns it.   │
  │ Without +T, EmptyMyList could only ever be MyList[Nothing], forcing separate empty-list        │
  │ instances per T and breaking the “one empty list for all types” trick. The result is a clean,  │
  │ type-safe, immutable list that “widens” itself on demand, all checked at compile-time, while   │
  │ runtime objects remain unchanged.                                                               │
  └─────────────────────────────────────────────────────────────────────────────────────────────────┘

  ┌─────────────────────────────────────────────────────────────────────────────────────────────────┐
  │                          Step-by-Step Type-Flow Through addNode Calls                           │
  ├────┬──────────────────────────────────────────────┬────────────────────┬────────────┬────────────┤
  │ #  │ Expression                                   │ Receiver Type      │ Inferred S │ Resulting │
  │    │                                              │                    │            │ List Type │
  ├────┼──────────────────────────────────────────────┼────────────────────┼────────────┼────────────┤
  │ 1  │ EmptyMyList.addNode("Vishal")                │ MyList[Nothing]    │ String     │ MyList[  │
  │    │                                              │                    │            │ String ]  │
  ├────┼──────────────────────────────────────────────┼────────────────────┼────────────┼────────────┤
  │ 2  │ .addNode(2)                                  │ MyList[String]     │ Any        │ MyList[  │
  │    │                                              │                    │            │ Any    ]  │
  ├────┼──────────────────────────────────────────────┼────────────────────┼────────────┼────────────┤
  │ 3  │ .addNode(new Animal())                       │ MyList[Any]        │ Any        │ MyList[  │
  │    │                                              │                    │            │ Any    ]  │
  ├────┼──────────────────────────────────────────────┼────────────────────┼────────────┼────────────┤
  │ 4  │ .addNode("Kumar. S")                         │ MyList[Any]        │ Any        │ MyList[  │
  │    │                                              │                    │            │ Any    ]  │
  └────┴──────────────────────────────────────────────┴────────────────────┴────────────┴────────────┘

  ┌─────────────────────────────────────────────────────────────────────────────────────────────────┐
  │                              Covariance vs. Invariance Comparison                               │
  ├───────────────────┬──────────────────────────────────────────────┬─────────────────────────────┤
  │ Feature           │ Covariant (+T)                             │ Invariant (T)               │
  ├───────────────────┼──────────────────────────────────────────────┼─────────────────────────────┤
  │ Empty-list reuse  │ ✅ MyList[Nothing] works for all T          │ ❌ Only for T = Nothing      │
  │ Mixing types      │ ✅ Can widen String → Any on the fly         │ ❌ Cannot assign across Ts   │
  │ Safety            │ ✅ Produce-only (no in-place mutation)       │ ✅ Both produce & consume    │
  └───────────────────┴──────────────────────────────────────────────┴─────────────────────────────┘
  """)
    
    println(
        """
      ┌────────────────────────────────────────────────────────────────────────────────────────────────────────────┐
      │                                          Enriched MyList Summary                                          │
      ├────────────────────────────────────────────────────────────────────────────────────────────────────────────┤
      │ We implemented an immutable, generic, functional-style singly linked list in Scala called `MyList[+T]`.    │
      │ It supports polymorphic behavior, higher-order functions (map, flatMap, filter), and reusable empty list.  │
      │                                                                                                            │
      │ ▌Traits Used                                                                                               │
      │────────────────────────────────────────────────────────────────────────────────────────────────────────────│
      │ ✔ `MyTransformer[-A, B]`: Generic trait with contravariant input A and covariant output B.                 │
      │ ✔ `MyPredicate[-T]`: Generic trait for filtering elements, contravariant in T.                             │
      │                                                                                                            │
      │ ▌Key Classes                                                                                               │
      │────────────────────────────────────────────────────────────────────────────────────────────────────────────│
      │ ✔ `EmptyMyList`: A singleton object representing an empty list.                                            │
      │ ✔ `ExistingMyList[T]`: A concrete list node holding `head: T` and `tail: MyList[T]`.                       │
      │ ✔ `MyList[+T]`: Covariant abstract base class with all high-order and utility methods.                     │
      │                                                                                                            │
      │ ▌High-Order Functional Methods                                                                             │
      │────────────────────────────────────────────────────────────────────────────────────────────────────────────│
      │ ✔ `map`: Applies a transformer of type `MyTransformer[T, B]` and returns a new `MyList[B]`.                │
      │ ✔ `flatMap`: Applies a transformer that returns a list per element, then concatenates via ++.             │
      │ ✔ `filter`: Applies a predicate and filters elements conditionally.                                       │
      │ ✔ `++`: Concatenates two lists.                                                                            │
      │                                                                                                            │
      │ ▌Covariance, Contravariance & Type Flexibility                                                             │
      │────────────────────────────────────────────────────────────────────────────────────────────────────────────│
      │ ✔ `+T` allows using a single EmptyMyList (of type MyList[Nothing]) across all possible MyList[T].         │
      │ ✔ `-T` in predicates and transformers allows using supertype functions safely (i.e., type-safe calls).     │
      │ ✔ `addNode[S >: T](h: S)` ensures widening of type during element prepending.                             │
      │                                                                                                            │
      │ ▌Behavior & Execution                                                                                      │
      │────────────────────────────────────────────────────────────────────────────────────────────────────────────│
      │ • `map` — transforms every element using a given transformer.                                              │
      │ • `flatMap` — transforms each element into a list and flattens the result.                                │
      │ • `filter` — keeps only the elements that satisfy the condition.                                           │
      │ • `++` — merges two lists.                                                                                 │
      │                                                                                                            │
      │ Example:                                                                                                   │
      │   val list = EmptyMyList.addNode(1).addNode(2).addNode(3)                                                 │
      │   println(list)                  // → [3 2 1]                                                               │
      │                                                                                                            │
      │   val mapped = list.map(_ * 2)     // [6 4 2]                                                               │
      │   val filtered = list.filter(_ % 2 == 0) // [2]                                                             │
      │   val flatMapped = list.flatMap(x => MyList(x, x+1)) // [3 4 2 3 1 2]                                       │
      └────────────────────────────────────────────────────────────────────────────────────────────────────────────┘
    
      ┌────────────────────────────────────────────────────────────────────────────────────────────────────────────┐
      │                         Step-by-Step Type Inference & Transformation in addNode                            │
      ├────┬──────────────────────────────────────────────┬────────────────────┬────────────┬────────────┤
      │ #  │ Expression                                   │ Receiver Type      │ Inferred S │ Resulting │
      │    │                                              │                    │            │ List Type │
      ├────┼──────────────────────────────────────────────┼────────────────────┼────────────┼────────────┤
      │ 1  │ EmptyMyList.addNode("Scala")                │ MyList[Nothing]    │ String     │ MyList[  │
      │    │                                              │                    │            │ String ]  │
      ├────┼──────────────────────────────────────────────┼────────────────────┼────────────┼────────────┤
      │ 2  │ .addNode(42)                                 │ MyList[String]     │ Any        │ MyList[  │
      │    │                                              │                    │            │ Any    ]  │
      ├────┼──────────────────────────────────────────────┼────────────────────┼────────────┼────────────┤
      │ 3  │ .addNode(true)                               │ MyList[Any]        │ Any        │ MyList[  │
      │    │                                              │                    │            │ Any    ]  │
      └────┴──────────────────────────────────────────────┴────────────────────┴────────────┴────────────┘
    
      ┌────────────────────────────────────────────────────────────────────────────────────────────────────────────┐
      │                                Covariance vs Contravariance Summary Table                                  │
      ├──────────────────────┬────────────────────────────────────────────┬──────────────────────────────────────┤
      │ Feature              │ Covariant `+T`                             │ Contravariant `-T`                   │
      ├──────────────────────┼────────────────────────────────────────────┼──────────────────────────────────────┤
      │ List flexibility     │ ✅ One EmptyMyList shared across types     │ ❌ Needs a concrete T per list        │
      │ Transformer reuse    │ ❌ Must transform exactly T                │ ✅ Accepts functions for supertype    │
      │ Type safety          │ ✅ Compile-time type widening              │ ✅ Used safely in input-only context  │
      │ Real use-case        │ MyList[+T]                                 │ MyPredicate[-T], MyTransformer[-A, B]│
      └──────────────────────┴────────────────────────────────────────────┴──────────────────────────────────────┘
      """)

    val secondList = EmptyMyList.addNode(1).addNode(2).addNode(3)
    println("List values: " + secondList.toString)

    val mapTransform = new MapMyTransformer()
    println("Map Transformed Values: " + (secondList.map(mapTransform)).toString)


    val flattenMapTransform = new MyTransformer[Int, MyList[Int]] {
        override def transform(input: Int): MyList[Int] = new ExistingMyList(input, new ExistingMyList(input+1, EmptyMyList))
    }
    println("Flatten Map Transformed Values: " + (secondList.flattenMap(flattenMapTransform)).toString)


    val evenFilter = new MyPredicate[Int] {
        override def test(item: Int): Boolean = item % 2 == 0
    }
    println("Even filtered Values: " + (secondList.filter(evenFilter)).toString)

    println(anonymousClass.description)
}



class Animal
class Dog extends Animal
class Food(foodName: String)
class Fruits(fruitName: String) extends Food(fruitName)
class Vegetables(vegetableName: String) extends Food(vegetableName)

class Container[+T](val thing: T) {
    println("Co-variance (using [+T]) means that if Child is a subtype of Parent, then GenericClass[Child] is a subtype of GenericClass[Parent]")

//    def consumeTValue(foodType: T): Unit = {
//    }
    
    def consumeTValue[S>:T](item: S): Container[S] = {
        println("You can only produce i.e return object of type T in co-variance, but u cannot consume type T object, i. u can;t use it as a method parameter. You can see the above commented lines, if i uncomment ti, it shows compile error. the reason is, we r considering a container of fruits to be a container of food, but the issue is now, anyone, will try to add container of vegetables to the container of frutis itself, since its considered as container of food, but its not allowed, u can't add container of vegetables to a  container of fruits.  To overcome it well use workarounds via typebounds, let us say, if we have a a container of fruits to be considered as container of food, then suppose we add a container of vegetables, let us consider teh container to be food itself, instead of fruits, since we are adding container of vegetables to the  container of fruits, if we consume the vegetable container, with teh existing generic type of fruits contianer, it will cause issue, so we'll create a a new container of it's  supertype i.e foods container and add frutis and vegetables to it and return it, instead of modifying teh existing container type. so it wont cause any issues and it also satisfies the co-variance. We cahieve this, by considering a gemenric type S, whihcsi asueprtype of T, anf we use thhat supertype i.e foods as container. here, wel just return the vegetable as sueprtype Food containe")
        new Container[S](item)
    }

    def produceStoredTValue():T = {
        println("Im returning already stoerrd obejct of T type")
        return thing
    }
    def produceNewTValue():T = {
        try {
            ???
        }
        catch case e:NotImplementedError => println("??? Is called as 'Not Yet Implemented' in Scala. u can use it as a placeholder to indicate unimplmented code and, when executed, throws a NotImplementedError.This makes it useful during development when you want to outline the structure of your code but haven't yet provided an implementation for certain methods or expressions.\n")
         new Fruits("orange").asInstanceOf[T]
    }



}

class AllFileFormatReader(val fileName: String, val fileType: String, val AllFileFormatReaderdescription: String = "I can read all file formats") {
    def readCSV(): Unit = {}
    def readJSON(): Unit = {}
    def readPDF(): Unit = {}
    def readXML(): Unit = {}
}
class PDFReader(val pdfName: String,val PDFReaderdescription: String = "I can read only pdf file format") extends AllFileFormatReader(pdfName, pdfName.substring(pdfName.indexOf(".")+1)){
    override def readPDF(): Unit = println("I can read only pdfs")
}

class ReadPDFs[-T](pdfFile: T) {
    println("Contravariant represented by teh symbol [-T], it means substitutability flows in the opposite direction, if Child is a subtype of Parent, then GenericClass[Parent] is a subtype of GenericClass[Child]. If a AllfileReader can handle any file format, it can definitely handle a SPECIFIC type of file format  like a pdf file.")

    def consume[T <:AllFileFormatReader](file: T): Unit = {
        println("In contravariant, its possible to consume, but does not produce values due to type issues.")
        println("The readPDF reference is of type ReadPDFs[PDFReader], so consume expects a PDFReader argument, if u pass AllFileFormatReader object as parameter to the consumer method, it rhorws compiler error. The compiler checks types based on the reference type (ReadPDFs[PDFReader]), not the instance type (ReadPDFs[AllFileFormatReader]). Even though the instance can handle AllFileFormatReader, the reference readPDF enforces that consume must accept PDFReader (as declared in its type).At runtime, The JVM uses type erasure, so generic types like T in ReadPDFs[T] are erased. The actual method called is the one from the instance (ReadPDFs[AllFileFormatReader]), but the compiler ensures at compile time that the argument matches the reference’s declared type (PDFReader).\n Here, we have used upperbound voluntarily sicne we are accessing the attributs of file, during compile time, scala compiler, knows file is of generic type T, but not of which type exactly, so it dont knwo whthe teh attirbtue su are accessing r actaully exisits with file, so i use lowerbound to mention teh generic type being used compulsory be a AllFileFormatReader or a subtype of it.")
        println("File name: "+file.fileName+"\nFile Format: "+file.fileType)
        file.readPDF()


    }

//    def produce():T = {
//        pdfFile
//    }

    def produce():Unit = {
        println("Here, contravariant can't be producers since, the above commented  produce method would cause compiler issue, since we pointed the allfileformatreader object to the pdfreader ref type variable, stating, we can also use allfileformatreader to read pdf files, so we point it to our pdfreader ref type readPDF variable, but if u try to store/return of the contrapositive T type parameter pdfFile object, which is of type AllFIleFormatReader, then ur giving access to read all types of files to the people, where u should give access to read only pdf files to the people(since ref var type is pdfreader only), its dangeorus, so above produecer emthod is not allowed.")

    }
}



class MutableBox[T] {
    private var item: T = _
    def get(): T = item
    def set(newItem: T): Unit = { item = newItem }

    println("Invariance means no subtype relationship exists between GenericClass[child] and GenericClass[parent], regardless of the relationship between child and parent. IT oes not contain any annotation. If we try  val parentBox: MutableBox[parent] = childBox, it leads to compile error, since if it compiled, it could lead to operations like parentBox.set(new Cat())  Now childBox contains a Cat!\n val myChild: child = childBox.get() // RUNTIME ERROR! It's actually a Cat.")
}






abstract class MyList[+T] {
    def isEmpty(): Boolean
    def addNode[S>: T](h:S): MyList[S]
    def gethead(): T
    def gettail(): MyList[T]
    def printElements: String

    override def toString: String = "["+printElements+"]"

    def map[B](mapTransformer : MyTransformer[T,B]): MyList[B]

    def flattenMap[B](flattenMapTransformer: MyTransformer[T, MyList[B]]): MyList[B]

    def filter(condn: MyPredicate[T]): MyList[T]

    def ++[B>:T](other: MyList[B]): MyList[B]
}


object EmptyMyList extends MyList[Nothing]{
    def isEmpty():Boolean = true
    def gethead() = throw new NoSuchElementException
    def gettail() = throw new NoSuchElementException
    def addNode[S](h: S): MyList[S] = new ExistingMyList(h, EmptyMyList)
    def printElements = ""

    def map[B](mapTransformer : MyTransformer[Nothing,B]): MyList[B] = EmptyMyList

    def flattenMap[B](flattenMapTransformer: MyTransformer[Nothing, MyList[B]]): MyList[B] =  EmptyMyList

    def filter(condn: MyPredicate[Nothing]): MyList[Nothing] =  EmptyMyList

    def ++[B](other: MyList[B]): MyList[B] = other
}


class ExistingMyList[+T](val head: T,val tail: MyList[T]) extends MyList[T]{
    def addNode[S>:T](h:S): MyList[S] = new ExistingMyList(h,this)
    def isEmpty(): Boolean = false
    def gethead() = head
    def gettail() = tail

    def printElements = {
        if (tail.isEmpty()) "" + head
        else head.toString + " " + tail.printElements
    }

    def map[B](mapTransformer: MyTransformer[T,B]): MyList[B] = {
        new ExistingMyList(mapTransformer.transform(head), tail.map(mapTransformer))
    }

    def flattenMap[B](flattenedMapTransformer: MyTransformer[T,MyList[B]]):MyList[B] = {
        flattenedMapTransformer.transform(head) ++ tail.flattenMap(flattenedMapTransformer)
    }

    def ++[B>:T](other: MyList[B]): MyList[B] = {
        new ExistingMyList(head, tail ++ other)
    }
    
    
    def filter(evenFilter: MyPredicate[T]): MyList[T] = {
        if evenFilter.test(head) then new ExistingMyList(head, tail.filter(evenFilter))
        else tail.filter(evenFilter)
    }
}


trait MyPredicate[-T] {
    def test(condition: T): Boolean

}

trait MyTransformer[-A,B] {
    def transform(input: A): B
}


class MapMyTransformer extends MyTransformer[Int, Int]
{
    override def transform(input: Int): Int = input * 2
}



abstract class demo {
    def description: Unit
}

//class generics$package$$anon$1() extends demo{
//    override def description: Unit = println(s"Im an anonymous class, overriding the abstract method of abstract class. anaoynmous classes works for both abstract /non abstract classes and traits. Actualy ebhidn the hood, scala creates a new class with soem dummy name like, ${anonymousClass.getClass
//    }")
//}
//
//val anonymousClass: demo = new generics$package$$anon$1()

val anonymousClass: demo = new demo{
    override def description: Unit = println(s"Im an anonymous class, overriding the abstract method of abstract class. anaoynmous classes works for both abstract /non abstract classes and traits. Actualy ebhidn the hood, scala creates a new class with soem dummy name like, ${anonymousClass.getClass
    }. I have commented out, hwo inteernally scala would implemented this anonymous class")

}














