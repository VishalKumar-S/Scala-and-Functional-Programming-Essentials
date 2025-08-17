package controllers

import javax.inject._
import play.api.mvc._
import play.api.libs.json._
import java.util.concurrent.atomic.AtomicLong
import scala.collection.mutable.ListBuffer


case class User(id: Long, name: String, age: Int, address: String)

//Play uses its own JSON library under play
//.api.libs.json.
//  Two important concepts:
//
//  Reads → How to deserialize JSON → Scala
//  object
//
//  Writes
//  → How to serialize Scala
//  object →
//  JSON
//
//  Format → Combines Reads +Writes
//
//  2. Defining Reads & Writes
//  You can define them manually or
//with macros(automatic derivation).
//
//  Example
//case class:
//
//  case class User(name: String, age: Int)
//
//  Macro format :
//
//    import play.api.libs.json._
//
//    object User {
//      implicit val userFormat: Format[User] = Json.format[User]
//    }
//    Now Play can automatically parse User from JSON and turn it back into JSON
//


object User {
  implicit val format: Format[User] = Json.format[User]
}


case class CreateUserInput(name: String, age: Int, address: String)


  object CreateUserInput {
  implicit val format: Format[CreateUserInput] = Json.format[CreateUserInput]

//    The
//    macro Json.format[T] auto -generates both Reads and Writes
//    for all fields of the case
//    class, so you don
//    ’t have to manually code them
//  .
//
//    This is why one line lets you:
//
//      Json.toJson(user) ✅ Scala → JSON
//
//    json.validate[User] ✅ JSON → Scala
//
//    Whats the need of JSON format
//    macro in
//    case class 's companion
//
//    object ?
//
//    Reason
//    1
//    —Implicit scope :
//      Play JSON needs an
//    implicit Format[T] in scope when you call Json.toJson(.
//  ..) or.validate[T].
//      Scala searches implicits in :
//
//      1. Local scope
//
//    2. Companion
//
//    object of
//
//    T
//
//    3. Imports
//
//    If you put it in the companion
//
//    object of
//
//    your
//    case class, Scala will always find it automatically when you work
//    with that
//    type—you don
//    ’t need to manually
//
//    import it
//
//    everywhere.
//
//      Reason
//    2
//    —Separation of concerns:
//      The
//      case class defines
//      the data structure.
//        The companion
//      object holds
//      utility code (like conversions)
//    —it keeps the
//
//    class definition
//
//    clean.
//

  }



@Singleton
class HomeController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {

  private val userList = ListBuffer[User]()
  private val idCounter = new AtomicLong()



  def createUser(): Action[JsValue] = Action(parse.json) { implicit request =>
    //  parse.json ensures Play expects a JSON request body
    //.
    //
    //  validate[User] tries to map the JSON to our
    //  case class.
    
  request.body.validate[CreateUserInput].fold(
      errors => {
        BadRequest(Json.obj("status" -> "error", "message" -> JsError.toJson(errors)))
      },
      userInput => {
        val newUser = User(
          id = idCounter.getAndIncrement(),
          name = userInput.name,
          age = userInput.age,
          address = userInput.address
        )
        
        
        userList += newUser
        Created(Json.toJson(newUser))
      }
    )
  }

//
//  Action = Defines an endpoint.
//
//  An Action[T] is itself something that handles an HTTP request and returns a Result
//.
//
//  The
//  type parameter
//  T inside Action[T] is not the method
//  's
//  return
//  type—it
//  ’s the
//  type of
//  the request body that this action will parse
//.


  //    Default content
//  type for incoming requests = AnyContent(accepts any
//  type).
//
//  You can restrict input to JSON
//  , text
//  , etc.
//  , but by default
//  , Play accepts anything.
//


  
  def hello() = Action{
    Ok(Json.obj("message" -> "Hello World"))
  }

  def getAllUsers: Action[AnyContent] = Action {
    Ok(Json.toJson(userList))
  }

  def listAllUsers() = Action{
//    In Play, implicit request is a powerful convenience pattern that makes the current HTTP request object automatically available where it's needed, especially in view templates, without you having to pass it around manually. Think of it like a toolbox on a workbench: instead of handing a worker (the view) a specific tool, you make the entire toolbox (request) available so it can grab whatever it needs.This pattern has two parts that work together:1.The Controller Provides It: In your Action block, you mark the request parameter as implicit. This "puts the toolbox on the workbench" for any code inside that block. The view template declares that it needs an implicit RequestHeader in its second parameter list. This is the template saying, "I need access to the toolbox to do my job."When you call views.html.users(...), the compiler sees the view's demand and automatically finds the implicit req you provided in the controller to satisfy it. The primary use is for Play's built-in helpers. Even if your code doesn't use @request directly, features like the reverse router (@routes.Assets.versioned(...)), CSRF protection tokens, and session/flash scope all rely on this implicit context to function correctly. If you didn't have it (i.e., you removed the implicit keyword from the controller), your application would fail to compile. The compiler would complain that it could not find implicit value for parameter request, because the view's demand for the "toolbox" was never met.

//    Twirl essentials (Play
//    ’s template engine
//    )
//    What is Twirl ?
//
//    Files end
//    with.scala.html and live in app / views /
//  .
//
//    A template is just a Scala -powered HTML file that takes parameters and returns HTML(play.twirl.api.HtmlFormat.Appendable).
//
//      Use
//    @...to embed Scala.
//
//      Template header (parameters & implicits)
//
//
//    @(title: String
//    , names: List[String]
//    ) (
//    implicit request: RequestHeader
//    )
//    First parens: template parameters you pass from your controller.
//
//    Second parens (optional)
//    : implicits from Play(handy
//    for CSRF
//    , reverse routes
//    , etc.
//    ).
//
//    Embedding Scala
//      Value: @title
//    Code block: @ {val x = 42}
    implicit req => 
      Ok(views.html.users("List of All Users: ",userList))
  }
  
  def getUserById(id: Long): Action[AnyContent] = Action {
    userList.find(_.id == id) match {
      case Some(user) => Ok(Json.toJson(user))
      case None => NotFound(Json.obj("status" -> "error", "message" -> s"User with id $id not found."))
    }
  }

  def greetName(name: String) = Action{
    Ok(s"Hello ${name}!!!. Just greeting u as a plain text")
  }

  def greetNameinJson(name: String) = Action{
    Ok(Json.obj("greeting"-> s"Hello ${name}!!! Just greeting u in Json"))
  }
  
  def getDataAsync() = Action.async {
    Future{
      Ok(s"Retreiving data from the database....\n Play is asynchronous by design — it’s built on Akka/Pekko and uses non-blocking I/O. If a request involves waiting (like calling a database or an external API), you don’t want to block a thread doing nothing — instead, you return a Future that will complete later. Action.async expects you to return a Future[Result] instead of a plain Result. Action.async expects you to return a Future[Result] instead of a plain Result. While waiting for the result, Play can handle other requests.")
    }
  }

  def updateUser(id: Long): Action[JsValue] = Action(parse.json) { implicit request =>
    request.body.validate[CreateUserInput].fold(
      errors => {
        BadRequest(Json.obj("status" -> "error", "message" -> JsError.toJson(errors)))
      },
      userInput => {
        userList.indexWhere(_.id == id) match {
          case -1 => NotFound(Json.obj("status" -> "error", "message" -> s"User with id $id not found."))
          case index =>
            val updatedUser = User(id, userInput.name, userInput.age, userInput.address)
            userList.update(index, updatedUser)
            Ok(Json.toJson(updatedUser))
        }
      }
    )
  }

  def deleteUser(id: Long): Action[AnyContent] = Action {
    userList.indexWhere(_.id == id) match {
      case -1 => NotFound(Json.obj("status" -> "error", "message" -> s"User with id $id not found."))
      case index =>
        userList.remove(index)
        NoContent
    }
  }
}
