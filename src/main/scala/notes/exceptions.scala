package notes

class CustomExceptionError extends Exception{
    println("im an error")
}


object exceptions extends App{
    println("Exceptions a re JVM concept, scala compielr hass notign to do with it.")



    def crashProgram(decision: Boolean)={
      if(decision) throw new CustomExceptionError
      18
    }

    val tryCatchBlocks = try{
      crashProgram(true)
    }
    catch{
      case e: CustomExceptionError => 18
    }

    finally{
      println("I execute whatever u do")
    }


  val tryCatchBlocks1 = try {
    crashProgram(false)
  }
  catch {
    case e: CustomExceptionError => println("The Scala compiler uses type inference to find a common supertype for all possible return values from the try block and all catch cases. Here, try returns integer, catch retursn unit, then supertype for both, AnyRef is the type of tryCatchBlocks1 duroing compile time, then durinf runtime, wither one of the blokcs -either try/cathc is gogint o get exeuted,right,tis type would ebe the type of tryCatchBlocks1")
  }

  try {
    val p = new PocketCalculator()
    println(p.add(Int.MaxValue, 10))
    println(p.add(Int.MinValue, -10))
    println(p.divide(Int.MaxValue, 0))
    println(p.multiply(Int.MaxValue, Int.MinValue))
    println(p.subtract(Int.MinValue, Int.MaxValue))
  }

  catch {
    case e: OverFlowException => println("Caught Overflow")
    case e: UnderFlowException => println("Caught Underflow")
    case e: MathCalculationException => println("Caught divide-by-zero")
  }


}

class OverFlowException extends Exception("It's int overflow")
class UnderFlowException extends Exception("It's int underflow")
class MathCalculationException extends Exception("It's division by zero")



class PocketCalculator {
  def add(x: Int,y: Int) = {
      val ans = x + y
      if (x > 0 && y > 0 && ans < 0) throw new OverFlowException
      else if (x <0 && y < 0 && ans > 0) throw new UnderFlowException
    else ans
  }

  def subtract(x: Int,y: Int) = {
    val ans = x - y
    if(x >0 && y<0 && ans<0) throw new OverFlowException
    else if(x<0 && y>0 && ans>0) throw new UnderFlowException
    else ans
  }

  def multiply(x: Int,y: Int) = {
    val ans = x * y
    if (x >0 && y>0 && ans<0) throw new OverFlowException
    else if (x <0 && y<0 && ans<0) throw new OverFlowException
    else if (x >0 && y<0 && ans>0) throw new UnderFlowException
    else if (x <0 && y>0 && ans>0) throw new UnderFlowException
    else ans

  }

  def divide(x: Int,y: Int) = {
    if (y==0) throw new MathCalculationException
    else x/y
  }

}
