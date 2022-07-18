package pattern.chain.of.responsability

import java.io.BufferedReader
import java.io.InputStreamReader

fun main(args: Array<String>) {

    val demo = Demo()
    demo.init()
    var sucess: Boolean
    do {
        println("Enter email:")
        //var email = demo.reader.readLine()
        var email = "admin@example.com"
        println("Input password")
       // var passwor = demo.reader.readLine()
        var passwor = "admin_pass"

        sucess = demo.server.login(email, passwor)
    }while (!sucess)
}

class Demo {
    val reader: BufferedReader = BufferedReader(InputStreamReader(System.`in`))
    lateinit var server: Server

    fun init(){
        this.server = Server()

        server.register{
            put("admin@example.com", "admin_pass")
            put("user@example.com", "user_pass")
        }

        val middleware = buildChain {
            linkWith(UserExistsMiddleware(server))
            linkWith(RoleCheckMiddleware())
        }

        server.setMiddleWare(middleware)

    }

    private fun buildChain(middlewareAction: Middleware.() -> Unit): Middleware {
        val middlewareChain = ThrottlingMiddleware(2)
        middlewareChain.middlewareAction()
        return middlewareChain
    }




}