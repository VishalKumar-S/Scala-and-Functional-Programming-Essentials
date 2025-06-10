package notes

import scala.annotation.tailrec
import scala.util.Random

object 13Options extends App {
  println("Option is used to replace the null pointer returning/ null [pointer represetnation issue, instead return a value representing there is no valeu exists. Option has a case object None and case class Some. So, its very usedul in API's , whereas whenever an issue happens, do not return the result direclty, that might be a null value,it wodul lead to issue, instead return it a Option[Value] , so that errors  r handled gracefully.")

  val value = Some(4)
  val noValue1 = None
  val noValue2 = Some(null)
  val noValue3 = Option(null)

  println(value)
  println(noValue1)
  println(noValue2)
  println(noValue3)

  def server1Issue(): String = {
    println("Server 1 is down")
    null
  }

  def server2(): Option[Unit] = {
    Some(println("Connecting with server2..."))
  }

  println("Or else statement wil execute, if the first method returns None, so it cana ct as if seomwhow first got issue,e xecute second, so tore turn teh issue i.e nulla s a value, we wrap teh retured vlaeu with Option, which will be None now")
  Option(server1Issue()).orElse(server2())


  def betterImplementedServer1Issue(): Option[String] = {
    println("Server 1 is down")
    None
  }

  def betterImplementedServer2(): Option[Unit] = {
    Some(println("Connecting with server2..."))
  }

  println("Previously, we did manually wrap the returned from method 1 with Option, but a ebtter practice is always u return with Option type tisefl,so the user, need not toe xplcielty fo with it,whielc alling the method.")
  betterImplementedServer1Issue().orElse(betterImplementedServer2())


  val config: Map[String, String] = Map(
    ("host"-> "128.36.41.1"),
    ("port"-> "80")
    )


  class Connection{
    def connect() = println("Connected...")
  }

  object Connection{
    def apply(host: String, port: String): Option[Connection] = {
      val ran = Random(System.nanoTime())
      if (ran.nextBoolean()) Some(new Connection())
      else None
    }
  }


println("Use map when your function returns a value. Use flatmap when your function returns a collection (or Option, etc.), and you want to flatten it. i.es unpacks into a one level of structure. flatMap expects its  each lambda funciton iteration to  return the same  collction type of the input type. If the current elmetn is None, the map, fitlerMap functions wont iterate or apply the fucntion over it, it iterates only if the element is Some.For e.g, input is of type Option[A], assume the lambda function returns a unit value, then if u apply map od this input, it applied the lmabda function, and retuns the Unit value, and it wraps th eoutpu value to tie actual container type i.e Option[Unit], whereas if u have applied flatmap on the input, u woudl have got compiel error, since flatmap requries each of tis lambda function to output type Option[B] B-> refers to anythign, here in ou r case Unit,i.e Option[Unit], but out labda function returns only Unit, not Option[Unit]. So,we cant use flatpmap here, since it needs to retune the smae colelcton type, so either use map, or instead of lambda function returning unit, change th retunr type to Option[Unit] for the flatmap to work, now flatmap works fine,  and it computes the Option[B] for eah cof its iteration, adnthen cretea a flatten Option[B] structure, wih allte h vlaeus, but if u had this return type of Option[Unit] with the map function, then lambda function returns Option[Unit] adn the result fo teh map funciton wraps teh input type, so the answer would be Option[Option[Unit]], instead of simple Option[Unit], it might lead to confusions or issue to access the nested inner element, suppose any toehr elemtn here instaead of unit,that needs to extracted for further use. If u use flatmap itself with this Option[B] return type, it wont create in ebtween ensted Option{Option[Option..]]]] structure, as ti makes eveyrhting into a single layer")

  val seed = System.nanoTime()
  println("Instead of retreiving  anythign directly from the map, we use get method, it will returns an Option of Some or None. If we acces an invaldi item from teh dicitonary, ti will atuomtaiclly cause issue, but uisng .get metho, will not throw werror, fro e.g config.get('Svsvsvsv') = "+config.get("Svsvsvsv"))

  val host = config.get("host")
  val port = config.get("port")
  println(host)
  println(port)

  @tailrec
  def imperativeStyleIssueHandling(trials: Int): Unit = {
    if (trials>5) return
    else {
      println(s"Trial ${trials}: ")
      val host = config("host")
      val port = config("port")
      val connection = if(host.isEmpty || port.isEmpty) None else Connection.apply(host, port)
      println("Connection: "+ connection)
      val connectionStatus = if(connection.isEmpty) None else (connection.get).connect()
      println("Connection Status: "+ connectionStatus)
      imperativeStyleIssueHandling(trials+1)
    }
  }

  println("Imperative style of issue handling")
  imperativeStyleIssueHandling(0)

  @tailrec
  def functionalStyleIssueHandling(trials: Int): Unit = {
    if (trials>5) return
    else {
      println(s"Trial ${trials}: ")
      val host = config.get("host")
      val port = config.get("port")
      println("Host: "+host+"\n"+"Port: "+port)
      val connection = host.flatMap(h => port.flatMap(p => Connection.apply(h, p)))
      println("Connection: "+ connection)
      val connectionStatus = connection.map(x => x.connect())
      println("Connection Status: ")
      connectionStatus.foreach(println)
      functionalStyleIssueHandling(trials+1)
    }
  }
  println("here, we will use all fucntional programming HOF's, such  that ,it only iterate the actual values,it wont iterate/execute methods, if the conenction is None. flatmap here will onyl iterate the lambda function here, if both host and port have some value, neither of not been None, then only it returns Connection instance, if any one of the host or port is not Some, it wont iterate, and retuns None, and then map iterates the connnection Instance, and if the conneciton Instance is been a None value,it wont iterate to clal teh connect method, so it reutns None, else the conneciton successful print stamten is storesd, and theforEch will iterat teh conenctionstatsu elmetns, onyl if its not None, if any elemtn is Noen,ti wont iterate, thus ensuing execution with no issues. ")
  functionalStyleIssueHandling(0)

  println("BY chaining style of writing the code")
  config.get("host").flatMap(h=> config.get("port").flatMap(p=>Connection.apply(h, p))).map(x=>x.connect()).foreach(println)

  println("BY for comprehensions of writing the code")

  val forComprehension = for{
    host<-config.get("host")
    port<-config.get("port")
    connection<- Connection.apply(host, port)
  } yield connection.connect()
  forComprehension.foreach(println)
}