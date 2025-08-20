import greeter.greeter.{GreeterGrpc, HelloReply, HelloRequest}
import io.grpc.ManagedChannelBuilder

object GreeterClient extends App {
  val channel = ManagedChannelBuilder.forAddress("localhost", 50051).usePlaintext().build()
  val request = HelloRequest(name = "Vishal Kumar. S")

//  The stub is the client’s way of calling the server methods.
//
//    Think of it as a remote control that knows how to talk to your server.
//
//    blockingStub means when you call sayHello, it will wait for the server’s reply before continuing (synchronous).

  val blockingStub = GreeterGrpc.blockingStub(channel)
  for(i<-1 to 10){
    val reply: HelloReply = blockingStub.sayHello(request)
    println(reply)
  }

  channel.shutdownNow()


}

