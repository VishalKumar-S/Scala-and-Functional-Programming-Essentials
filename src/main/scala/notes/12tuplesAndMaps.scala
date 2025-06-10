package notes

object 12tuplesAndMaps extends App{
  println("tuples r used to fourp differnt types together,its a data strcuture, scala like function types, it can only have 22 parameter limit (Tuple1 to Tuple22), uc ant have more than 22 parameters in a tuple in Scala2, then in Scala 3 , this oaremetr limti was removed")
  val randomTuple = Tuple3("Im first element",2,"Im third element")

  val largeTuple = (1, 2, 3, 4, 5, 6, 7, 8, 9, 10,
    11, 12, 13, 14, 15, 16, 17, 18, 19, 20,
    21, 22, 23, 24, 25, 26, 27, 28, 29, 30)


  println(randomTuple._1)
  println(randomTuple.copy(_2 = randomTuple._2 + " Modifying the second element of the randomTuple and then returning a new Tuple"))

  val homogenousMap: Map[Int, String] = Map()

  val heterogenousMap: Map[Any,Any] = Map(("vishal",123), (123,456), (575,"avevs")).withDefaultValue("With DEfault vlaue, helps u print his default statemtns, if ua ccess an invalid key from the hashmap")

  println(heterogenousMap.contains("vishal"))
  println(heterogenousMap(987654321))

  val updated = homogenousMap+ ((1, "You can represtne tupes with eitehr () paranthesis, or else with single arrow also ->. (1,'efef') = 1-> 'efef'"))

  println(updated.foreach((key,value)=>println(s"Key: ${key}  Value: ${value}")))

  val updated2 = updated + (2->"THis is my syntactical sugar way of represetning the tuple")

  println(updated2.foreach((key, value) => println(s"Key: ${key}  Value: ${value}")))

  println("Map in Map\n"+updated2.map(pair=> pair._1+10 -> pair._2))
  println(updated2)
  val sdv = updated2.view.filterKeys(x=>x%2==0)
  println("Filter in Map\n")

  println("Filter in Map\n" + updated2.view.filterKeys(x => x % 2 == 0).toMap)

  println("Map values "+ updated2.view.mapValues(x=>x+"12345").toMap)

  val li = List(1,2,3,4,5,6,7,8,9,10)
  println(li.groupBy(x=>x%2==0))


  val network: Map[String, Set[String]]  = Map()


  def addPerson(network: Map[String, Set[String]], person: String) = {
    network + (person->Set())
  }


  def unFriends(network: Map[String, Set[String]], person: String, removePerson: String) = {
    network + (person->(network(person)-removePerson)) + (removePerson->(network(removePerson)-person))

  }

  def friends(network: Map[String, Set[String]], person: String, friend: String) = {
    network + (person->(network(person)+friend)) + (friend->(network(friend)+person))
  }

  def mostFriends(network: Map[String, Set[String]]) = {
    network.maxBy(pair=> pair._2.size)._1
  }


  val Anetwork = addPerson(network, "A")
  val ABnetwork = addPerson(Anetwork, "B")
  val ABCnetwork = addPerson(ABnetwork, "C")
  val ABCDEnetwork = addPerson(addPerson(ABCnetwork,"D"),"E")
  val ABFriends = friends(ABCDEnetwork, "A","B")
  val BCFriends = friends(ABFriends, "B", "C")
  val finalNetwork = friends(BCFriends,  "C", "D")
  println(finalNetwork)
  printf("The person with maximum friends are "+finalNetwork.maxBy(pair=>pair._2.size)._1+"\n")
  printf("The person with 0 friends are "+finalNetwork.filter(pair=>(pair._2.size==0))+"\n")

  println("Check whetheer A and D have any relationship?")
  AnyConnectionExist("A","D",finalNetwork)


  def AnyConnectionExist(person1: String, person2: String, network: Map[String,Set[String]]): Unit = {
    def bfs(person2: String, visited: Set[String], toVisit: Set[String]): Boolean = {
      if (toVisit.isEmpty) false
      else {
        val curr = toVisit.head
        if (person2 == curr) true
        else if (visited.contains(curr)) bfs(person2, visited, toVisit.tail)
        else bfs(person2, visited + curr, toVisit.tail ++ network(curr))
      }
    }
    if(bfs(person2, Set(), network(person1))) println("Yes, there is a connection between both")
    else println("Nope")
  }



}
