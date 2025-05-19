object caseClasses extends App {
  val p1 = new caseClasses("1. In Case classes, all class parameters are class fiedls by default, no need of initialsiingin it val, it's automatically considered as class fields")
  println(p1.description)

  val p2 = new caseClasses("2. By default, classes inherit thr AnyRef's toString, hashCode, == method, by default, == check checks whether 2 objects points ot the smae mmeory lcoation or not in normal classes, but in case classes, ==  checks not for referne equality,it checsk for contenet equalirt, contenti.e all fields same or not only.")


  val p3 = new caseClasses("2. By default, classes inherit thr AnyRef's toString, hashCode, == method, by default, == check checks whether 2 objects points ot the smae mmeory lcoation or not in normal classes, but in case classes, ==  checks not for referne equality,it checsk for contenet equalirt, contenti.e all fields same or not only.")

  if (p2 == p3) println(s"{$p2}\n ALl fields of p2 and p3 objects r same, so both r same, according to case classes")

  val p4 = new caseClasses("3. In case classes, teh toString gives a neat represenation fot eh pobject, by givin its fields as output, ususlyl in tradiiaotnl classes, toString gives not class fieslas output, just check it out, also in Scala, case classes offer syntactcial sugar, by not evne to mention toString alone with obejct, jsut emntion the object naem alone,thea slo itpritns the fiedls in nice manner")

  println(p4)
  println("Normal Class: \n" + dummy("Ssgs") + "\n" + dummy("sgsgsgs").toString())
  println("Case Class: \n" + caseClasses("Ssgs") + "\n" + caseClasses("sgsgsgs").toString())

  val companionObject = caseClasses("4. Companion objects means they r the objects present in teh same, fiel whihc has teh same name and fields of the class in the file. Case classes, automatically creates comapnion objects, when they create case classes,i.e they create com[anion object of naem classClasses with description field, and caseclass's companion object contains apply method, that creates a new instance of the classClasses class, we already know taht apply emthod is used to call the object/class like an method, here the thign im writing is calling the apply method of the compainonobject of caseClassess, and it returns a new isntance of the CaseCLasses case, as this string as description.You can see i dindt use any enw keyword here")

  println(companionObject.description)
  println(companionObject.getClass.getName)
  println(caseObject.describe)
  val original = caseClasses("Im original case class's instance",1)
  val duplicate = original.copy("here, in thsi duplciate copy, im just having all the fields of orignial as such, only modifing this descirption fioeld as it eneeds tob chagend for me, so its very handy,with haveing the numeb fiedl vlaeu as such tis original valeu itself")
  println("Original Object")
  println(original)
  println("Duplicate Object")
  println(duplicate)
  println("Making a class as a case class,case objects, it amkes the class so powerful, by having it owsn == methpod, own toString methods, syntactical sugars, comapnion objects, so we canc reate instance fo casec alss without enw keyword, makign pareameters automatically as fiedls, supprigin serailsisation, pattern amthcing etc., , makse any class so pwoerful bya dding the keywrod case.")

  println("In Java, == checks for reference equality (if objects point to the same memory location). equals() is a method defined in Object class that by default checks reference equality, but is typically overridden to check content equality. In Scala, classes inherit from AnyRef (which is Scala's equivalent to Java's Object) == in Scala is actually a method that calls equals() underneath, not an operator like in Java. equals() behaves similarly to Java - it needs to be overridden for proper content comparison\n\neq method in Scala is used to check reference equality (similar to Java's ==)In Scala, For regular classes: You need to manually override equals() (and hashCode()) to implement content equality, By default, they use reference equality from AnyRef. For case classes: equals() and hashCode() are automatically generated to compare all fields. This means == will compare content equality for case classes without any additional work\n")
}

class dummy(description: String)

case class caseClasses(description: String = "", number: Int = 123456789) {
  println("In Scala, case classes r a special type of classes, they have special properties,like \n")
}

case object caseObject {
  val describe = "5. Case classes support serialisataion so, foe eg., we can transfer an object created here,to another system, and exefute over tehre ,its widely used in Akka framework \n 6. Case classes have extractor patterns, so case classes can be used in pattern matching \n 7. THer eia thign called Case objects, which acts similar to the case class, where  teh difference is in a case object, its companion object is the same object itself.\n 8. Case classes have handy copy methods. For example,u cna copy an isntance of a classt oa ntoerh sintance of a calss,jsut modifying a sepcific feidls aloen,if needed"
}




