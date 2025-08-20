import io.grpc.ServerBuilder
import scala.concurrent.{Future, ExecutionContext}
import greeter.greeter._

class GreeterService extends GreeterGrpc.Greeter {
  override def sayHello(request: HelloRequest): Future[HelloReply] = {
    val reply = HelloReply(message = s"Hi, ${request.name}! Happy learning gRPC")
    Future.successful(reply)
  }
}

object GreeterServer {
  def main(args: Array[String]): Unit = {
    val ec = ExecutionContext.global
    val service = new GreeterService()

    val server = ServerBuilder
      .forPort(50051)
      .addService(GreeterGrpc.bindService(service, ec))
      .build()
      .start()


    println(s"Server started, listening on ${server.getPort}")
    server.awaitTermination()
  }
}
