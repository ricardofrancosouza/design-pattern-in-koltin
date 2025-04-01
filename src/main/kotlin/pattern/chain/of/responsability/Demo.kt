package pattern.chain.of.responsability

import pattern.mediator.Mediator
import pattern.mediator.MediatorManager
import pattern.mediator.RegisterLog
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

        val mediator = buildMediator {
            linkCommand(RegisterLog())
            linkCommand(RegisterLog())
        }
        val userExistsMiddleware = UserExistsMiddleware(server)
        userExistsMiddleware.defineMediator(mediator)
        val roleCheckMiddleware = RoleCheckMiddleware()
        roleCheckMiddleware.defineMediator(mediator)
        val middleware = buildChain {
            linkWith(roleCheckMiddleware)
            linkWith(userExistsMiddleware)
        }
        middleware.defineMediator(mediator)

        server.setMiddleWare(middleware)

    }

    private fun buildMediator(mediatorAction: Mediator.() -> Unit) : Mediator {
       val mediatorManager = MediatorManager()
        mediatorManager.mediatorAction()
        return mediatorManager
    }
    private fun buildChain(middlewareAction: Middleware.() -> Unit): Middleware {
        val middlewareChain = ThrottlingMiddleware(2)
        middlewareChain.middlewareAction()
        return middlewareChain
    }




}