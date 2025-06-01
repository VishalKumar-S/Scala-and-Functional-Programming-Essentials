package notes
import scala.util.{Try, Success, Failure}
import scala.util.Random

case object tryExceptions extends App{
  println("Try is a class in Scala to handle execeptions, it contains  2 classes - Failure, which throws , whena nye xception occurs, Success -which just wraps teh correct comptuted output, when u u use try block. Whenever there is a chance of retunign null in ur funcitonn, use Options, whenver ther is a chacne of retrunign exceptions, use Try. We can use map, flatmap adn otehr higher order functinos in Try also. Try class is used since usign multipl try-catch statemnts woudl be pretty compilcated and dificult ")

  val succ = Success(5)
  val fail = Failure(new RuntimeException())

  println(succ)
  println(fail)


  def backupFunction(): Try[String] = {
    Success("I'm working fine")
  }

  def failureFunction(): String = throw new RuntimeException()

  println(fail.isSuccess)

  println(Try(failureFunction()).orElse(backupFunction()))

  println("better impleenteationw odul be using Try in return type itself, while writing the functions")

  def betterImplementedFailure(): Try[String] = Failure(new RuntimeException())

  def betterImplementedSuccess(): Try[String] = {
    Success("Success...")
  }

  println(betterImplementedFailure().orElse(betterImplementedSuccess()))

  val hostName = "localhost"
  val port = "8080"
  def renderHTML(page: String) = println(page)

  class Connection {
    def get(url: String): String = {
      val random = new Random(System.nanoTime())
      if (random.nextBoolean()) "<html>.....</html>"
      else throw new RuntimeException("Connection interrupted")
    }

    def getSafe(url: String): Try[String] = Try{
      get(url)
    }
  }


  object HTTPService {
    val random = new Random(System.nanoTime())

    def getConnection(host: String, port: String): Connection = {
      if (random.nextBoolean()) new Connection()
      else throw new RuntimeException("Port is not available")
    }

    def getSafeConnection(host: String, port: String): Try[Connection] = Try{
      getConnection(host, port)
    }
  }

  for (i <- 1 to 3) {
    println(s"\n--- Attempt $i ---")

    println("Using map in chaining style,do not use this, use flatMaps strucutre, ths sis written just to showcase")
    val try1 = (HTTPService.getSafeConnection(hostName, port)
      .map(connectionInstance => connectionInstance.getSafe("www.google.com"))).flatMap(identity) // This flattens Try[Try[String]] => Try[String]
    try1.foreach(content => renderHTML(content))

    println("Using flatMap in chaining style")
    val try2 = HTTPService.getSafeConnection(hostName, port)
      .flatMap(connectionInstance => connectionInstance.getSafe("www.google.com"))
    try2.foreach(content => renderHTML(content))

    println("Using for comprehension style")
    val forComprehension = for {
      connectionInstance <- HTTPService.getSafeConnection(hostName, port)
      websiteContent <- connectionInstance.getSafe("www.google.com")
    } yield renderHTML(websiteContent)
  }


  println("Imperative style with try-catch")
  for (i <- 1 to 3) {
    println(s"\n--- Attempt $i ---")

    try {
      val connection = HTTPService.getConnection(hostName, port)
      try {
        val htmlContent = connection.get("www.google.com")
        renderHTML(htmlContent)
      } catch {
        case e: Exception => println(s"Error while fetching webpage: ${e.getMessage}")
      }
    } catch {
      case e: Exception => println(s"Error while establishing connection: ${e.getMessage}")
    }
  }


}

