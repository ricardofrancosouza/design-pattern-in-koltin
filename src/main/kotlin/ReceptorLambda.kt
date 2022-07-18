fun main(args: Array<String>) {
    // for (i in 0..30) println("$i" + Fibonacci().fibo(i))

    val s = buildString {
        append("Hello, ")
        append("world!")
    }
    println(s)

    //Um lambda com um func de ext
    val appendExcl: StringBuilder.() -> Unit = { this.append("!") }
    val stringBuilder = StringBuilder("Hi")
    stringBuilder.appendExcl()
    println(stringBuilder)

    val bavarianGreeter = Greeter("Servus")
    bavarianGreeter("Dmitry")

    val dependencyHandler = DependencyHandler()
    dependencyHandler.compile("org.jetbrains.kotlin-stdlib:1.0.0")

    dependencyHandler{
        compile("org.jetbrains.kotlin-stdlib:2.0.0")
        compile("org.jetbrains.kotlin-stdlib:3.0.0")
    }

    val s2 = "kotlin is the best"
    s2 should startWith("kot")

    "kotlin" should start with "kot"
}



//Existe esse objeto com fim gramatical
object start

infix fun String.should(x: start): StartWrapper = StartWrapper(this)

//Esse é o wrapper de start
class StartWrapper(val value: String){
    infix fun with(prefix: String) {
        if (!value.startsWith(prefix))
            throw AssertionError("String does not start with $prefix: $value")
    }
}

infix fun <T> T.should(matcher: Matcher<T>) = matcher.test(this)
interface Matcher<T>{
    fun test(value: T)
}
class startWith(val prefix: String): Matcher<String>{
    override fun test(value: String){
        if(!value.startsWith(prefix))
            throw AssertionError("String $value does not start with $prefix")
    }
}

class DependencyHandler {
    fun compile(coordinate: String) {
        println("Added dependecy on $coordinate")
    }

    operator fun invoke(body: DependencyHandler.() -> Unit) {
        body()
    }
}

//Class com a convencao do invoke
class Greeter(val greeting: String) {
    operator fun invoke(name: String) = println("$greeting, $name!")
}

fun buildString(builderAction: StringBuilder.() -> Unit): String {
    val sb = StringBuilder()
    sb.builderAction()
    return sb.toString()
}

class Fibonacci() {
    fun fibo(num: Int): Int {
        if (num < 2) {
            return num
        } else {
            return fibo(num - 1) + fibo(num - 2)
        }
    }
}