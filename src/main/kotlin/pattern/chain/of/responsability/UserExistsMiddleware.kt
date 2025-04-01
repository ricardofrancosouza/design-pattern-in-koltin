package pattern.chain.of.responsability

import pattern.mediator.Mediator

class UserExistsMiddleware(val server: Server): Middleware() {
    override fun check(email: String, password: String): Boolean {
        if(!server.hasEmail(email)){
            println("This email is not registered!")
            return false
        }
        if(!server.isValidPassword(email, password)){
            println("Wrong password")
            return false
        }
        mediator.notify("UserExistsMiddleware", "RegisterLog")
        return checkNext(email, password)
    }
}