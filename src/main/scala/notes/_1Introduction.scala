package notes

import scala.annotation.tailrec
import scala.util.control.Breaks.*

object _1Introduction {
    def main(args: Array[String]): Unit = {
        println("Scala compiler compiles tje scala code into bytecode, same as java, adn uses jvm as interpreter. Scala has val - which is used to create immutable variables, simlart to final in Java. var is used to create mutable variabbesl. Mentioning the type in variabels is optional in Scala.   compielr takes care fo the type, evn if we didnt mention. The varaible type is knwon by hte compielr, absed on the rihgt hand side expression, and the return type of hte function is known by the implementation. if we uneed to sue long or float or doubtl mention L,f,d explciity to teh valeu fot he viabel, so tht the compielr would know which type we r reffering to. in Scala, it is compulsory to explicitly mention the type of each parameter in the method parameters.. Semicolon is optional if we sue signle epxpression in a line in Scalca.It is only needded if we use multiple expressions in a single line. Val is mostly used, instead of var in Scala, since\n Functions in Purely Functional Programming Languages are exactly like Functions in Mathematics: they produce a result value based on their argument values and only based on their argument values.\n A Side-Effect (often just called Effect) is everything else. I.e. everything that is not reading the arguments and returning a result is a Side-Effect. These include:\n      - Modifying variables outside its scope\n      - I/O operations (reading/writing files, network calls)\n      - Printing to console\n      - Throwing exceptions\n      - Modifying data structures\n\n      In functional programming, the goal is to minimize side effects because:\n      - Predictability: Functions without side effects always produce the same output for the same input\n      - Testability: Pure functions (no side effects) are easier to test\n      - Reasoning: Code is easier to understand and debug\n      - Concurrency: Code without side effects is safer in multi-threaded environments\n\n      In functional programming with Scala, you typically:\n      - Use immutable data structures\n      - Return new values instead of modifying existing ones\n      - Make functions depend only on their inputs\n      - Keep side effects at the 'edges' of your program\n\n      This approach leads to code that's easier to reason about and less prone to bugs, especially in complex systems.\"\"\"")

        val x: Int = 5;
        val y: Int = 5

        val r = 5;
        val g = 10
        val z = 10
        val f = 10.20f
        val d = 10.546d
        val l = 122222222222222L

        var mutableVariable = 10
        println("Old var: " + mutableVariable)

        mutableVariable = 20
        println("New var: " + mutableVariable)

        println("Instruction vs Expressions\n Instructions- u command system to do something e,.g change var value, print something, that does not return anything. \n Expression-  You do a taks and return a value. IN scala adn fucntional programming in general, we wont prefer using  instructions. We only prefer using expressions. Here, IF - is an expression in Scala i.e returns a value. Using for/while loop,s which r commonly used in other iterative programmign language liek java or python are not not recommedned to use in Scala, these are considered as bad practice for functional programming, as using them in scala, leads to side effects. All instructions r side effects. In Scala, side effects are also considered to be expressions, but which returns a Unit, instead fo a value. Unit is similar to vodi in other programming languages, which means, it returns something not useful. Almost everything you write in Scala code is an expression that returns a value, including things that perform side effects. The exceptions are structural definitions like class, import, package, etc. e.,g in Java, if staememtn is an conditioanl statement,it wont return anything, but in Scala, u can assion the if statement to a variabel adn it returns a value.")

        var count = 0
        while (count <= 5) {
            println("Do not use loops (BAD PRACTICE), here both print statement and updating count are a side effect. " + count)
            count += 1
        }

        val unitReturn = (count+=1)
        val printUnitReturn = println("Unit return Value: "+unitReturn+"\n Since updating count value is a instruction i.e aa  expression in Scala, that returns Unit,so it returnsed like this")
        println("Printing print instruction, which is a side effect, acting as a expression, which returns Unit value"+printUnitReturn)
        println(if (x == 5) "IF expression is used here within print statement, it returns this statement string" else "")


        val store = if (x == 5) "Storing the if expression value in a  variable" else ""
        println(store)

        val codeBlocks = {
            var x = 2
            val y = x +6
            val z  = y%2 == 0

            if (z) {
                "Code blocks are also expressions in Scala. They can contain anything within—`var`, `val`, etc. Anything you declare within code blocks is non-visible outside of the code block. The last executed statement in the code block is considered to be the return value of the code block expression. Here, the `if` is the last statement in the code block, so its value is returned."
            } else {
                "The condition evaluated to false, so this is returned instead."
            }
        }

        println(codeBlocks)

        val lastExecutedExpression = {
            2<3

            if (true) 5 else 10

            "Here, the if statements is of no use in this code block, it's not the last constituent expression of this code blokc, the string im currenyl, is the last constituent of this code block, so im gogin to be tthe return value of this code block expression, not the if block value"


        }

        println(lastExecutedExpression)

        def parameterisedFunction(a: String, b: Int): String = {
            a + " " + b
        }

        def nonParameterised = {
            "We can call non-parameterised methods, without even using (), just the method name is enough. But make sure when u call a method name without (), then the method should also eb defined without parenthesis()"
        }
        println(parameterisedFunction("Vishal",5))
        println(nonParameterised)

        def recursiveFunction(n: Int): String = {
            if (n==1) "In Scala and functional programming, instead of loops, use recursive functions"
            else "In Scala and functional programming, instead of loops, use recursive functions\n"+ recursiveFunction(n-1)
        }

        def outerFunction():Unit = {
            println("\"We can have auxiliary functions inside code blocks, mentioning return type for a function is optional, but for a recursive functioning mentioning return type is mandatory,as the compiler don;t know the return type of the inner recursive calls's parts. Here in the function, the return type is made as Unit, so for that, im writing this print statement, as print statements an instruction, which gives side effect, that is it si a expression, which returns Unit")
            def innerFunction():String = "We can have inner functions inside a function"

            innerFunction()
        }
        println("Print n times")
        println(recursiveFunction(5))
        println(outerFunction())

        var stackFrameSize = 0
        def factorialNonTailRec(x: BigInt): BigInt = {
            try{
                if (x <= 1) 1
                else
                    println("stack frame current at size: " + stackFrameSize)
                    stackFrameSize += 1
                    x * factorialNonTailRec(x - 1)
            }

            catch{
                case e: StackOverflowError => println("After this, recursion gets stack overflow, we can't proceed further. That's why we r going to tail recursion. Here, in this non-tail recusivon, if hte number is small, it would retunr teh factorail value,so we declared return type as BigInteger ( we declared return type as BigINteger, instead fo int, since very the factorial intermediate valeu becomes so big,, exceeds Int capacity,  leading to integer overflow,at some point,it becomes 0, then further all calcualtions become 0 ,since 0 multiplied by anything is 0). If the number is very large, is will cause stack voerflow, then theis catch blokci executed, catch block doesnt execute and return anything, it just logs teh error emssages, so it returns only Unit.So,it causes compiel error, due to type mismatch of return type diference of BigType in function declaration vs catch block returns Unit. So, to hadnle this compielr issue, we created a dumym BIgInt valeua t the end of this cathc blokc as returning value. So, now no issues.")
                BigInt(7777777)
            }

        }

        @tailrec
        def factorialTailRec(x: Int, accumulator: BigInt): BigInt = {
            if (x <= 1) accumulator
            else factorialTailRec(x - 1, x*accumulator)
        }

        println("Although we said use recursive function instead of loops for iteration. BUt hte maind isadvatnage of reursive function is it's stack space during computation. SO, for large values e.g, 5000, we cant find factorail, unlike iteration loop, it leads tos tack overflow error. So, the solution is to use Tail recursion, which  is a specific type of recursion in which the recursive call is the last operation performed by a function before returning its result. This makes tail-recursive functions more memory-efficient because the compiler can optimize them using tail-call elimination, which avoids adding additional stack frames for each recursive call.In tail recursion:\nThe result of the recursive call is directly returned, without any further computation after the call. The function doesn't need to \"remember\" the previous state, allowing optimization. In the factorialTailRec, the recursive call (factorialTailRec(n - 1, accumulator * n)) is the last operation, making it tail-recursive. In Non-Tail-Recursive Function, the multiplication (n * factorialNonTailRec(n - 1)) occurs after the recursive call, making it not tail-recursive. To identify a recursive function is tailre cursion or not, check if the function could theoretically be rewritten as an iterative loop (this is often a hallmark of tail recursion). or use hte annotator @tailrec abvoe a recursive function. If that recursiv function is non-tail,it will lead to compiler issue. SO, Tail recursion is optimized by the compiler, using constant stack space instead of growing the stack with each recursive call and can handle larger inputs without causing stack overflow. Note, althouhg tehoerietically, this most imperatie languages (c++,java,pytohn,javascript) wont support this Tail compoiler optimsiation (TCO), they create new stack frame, since imperative lanagues expects \"step-by-step execution\", keeping the call stack alive for debugging and tracing. only functional programming(Haskell, Schema,  Scala) langauges only support this TCO, Languge differnce :\\n Pure Functional = No changing variables, recursion, immutable data everywhere (Haskell, Scheme). Multi-Paradigm = Mix of both Functional + Object-oriented (Scala, Python, JavaScript, C#). Pure Imperative = Focus only on commands and changing states (C, old-school Java). Even though Python/Java/JS allow functional style, their main programming culture is imperative → so no TCO. (since peiple woudl run a for i in range(100000), instead of using recursion, so  those langauges dont cae about optimsiign recursion), whereas Scala leans heavily functional by design, so TCO is a must. i.e Scala cares about recursion, Python/JS/Java care about loops.")
        println("Non-tail recursive factorial calculation of large number 5000: "+factorialNonTailRec(5000))
        println("Tail recursive factorial calculation of large number 5000: " + factorialTailRec(5000,1))


        def fibonacci(n: Int): Int = {
            @tailrec
            def tailFibonacci(i:Int,firstLast:Int,secondLast:Int): Int = {
                if (i>=n) firstLast
                else tailFibonacci(i+1, firstLast + secondLast,firstLast)
            }
            tailFibonacci(2,1,1)
        }

        println("Calculating fibonacci series using tail recursion with 2 accumulator-like variables "+fibonacci(6))

        def callByValue(x: Long): Unit = {
            println("Here, in callby value, the value si pre-computed already when sent it as a paramter, and the value is same eveywhere we use it inise th mehtod definition.")
            println("Current time by value "+ x)
            println("Current time by value "+ x)
            println("Current time by value "+ x)
        }

        def callByName(x: => Long): Unit = {
            println("Here, => does lazy evaluation of the expression, passed as a parameter. The value of the expression is not pre-computed, the expression will be computed only when it's used. So, each time when we use within the method definition, we'll se a fresh computed value for teh expression, each time we use")
            println("Current time by name "+ x)
            println("Current time by name "+ x)
            println("Current time by name "+ x)

        }

        def infiniteLoop(): Int = 1 + infiniteLoop()

        def callInfiniteLoopByValue(x: Int, y: Int): Unit = println("Value of x: "+x)


        def callInfiniteLoopByName(x: Int, y: =>Int): Unit = println("Here, since it's call by name, the infiniteLoop, which will lead to stack overflow, will be called only wehn we sue it i.e lazy evaluation, here we didnt use the infiriteLoop() anywhere, so that exprsesiion is not comptued, so no issues, value fo x: "+x)

        callByValue(System.nanoTime())
        callByName(System.nanoTime())

        try{
            callInfiniteLoopByValue(5,infiniteLoop())
        }
        catch{
            case e:StackOverflowError => println("Issue is: "+e+"\nHere, since it's call by value, the expression infinteLoop() value should be pre-computeed, and passed as a value to the parameter in the method defininton, but since it's pre-comptueed,whcih elasd to stack voerflow, evnthough thsi expression is used nowhere within the methdo, but duee ot htis pre-compuation, method didnt get executed.")
        }

        callInfiniteLoopByName(10,infiniteLoop())

        def defaultArguments(name: String = "Vishal Kumar",age: Int = 5, address: String = "India"): Unit = {
            println("Name: "+name + "\nAge: "+age+"\naddress: "+address)
        }

        println("We can set default values to the parameters of the methods, but when u try to give the parametre explciityl, i.e u dont wnat to use th e default arguemtn values, then, either u can give explcieit valeus fro all parameters lke this");
        defaultArguments("eger",534,"Nepal");
        println(" \n Or, if u need to give value to only few arguments explcelty, u cant avoid the leading agument i.e for e.g, defaultArguemnt(361,'Lanka'), here whethe rthe first paremter u gave explciteyl, whether refers to the first argument or second arguemtn,hwo teh ompielr knwoes, it will treat ti as the first agrgument only, so then it iwll elad to compiler issue, since first apramter is string in our case, so we cant skip the first paratmer for giving values explciietly, so we can give few aprametns valeus onyl explieitly, if we give in order, like\n");
        defaultArguments("eveve",58);
        println("\n if u dont wnat to give the paramets valeus expicielty in roder form startign, sicne u need to modify only a/few in between parameter values, then mention the oaremter name itself, it iwll be working fine, like this");
        defaultArguments(age = 42);

        val asString = "100"
        val aNumber = asString.toInt
        println("Vishal"+aNumber+"Kumar")
        println(asString.take(2))

        val name = "pie"
        val value = 3.14159265359d
        println("s interpolated string: here, we can inject the varaibles using $")
        println(s" Value of $name is $value")
        println("f interpolated string: here, we can inject the varaibles using %, also it acts as printf, we can have format specifiers, ansue its features, also it us helpful to esnusre correct type during compile time itself")
        println(f"Value of $name%s is $value%.2f")
        println("raw interpolated string: here, the esscape characters like '\\n', wont working within the string, but if u inject any string, that contains the escape characters,then it will work")
        println(raw" Value of $name is \n  $value")
        val rawString = s"Value of $name is \n $value"
        println(raw"$rawString")


    }
}

