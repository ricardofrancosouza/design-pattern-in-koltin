package pattern.chain.of.responsability

import pattern.mediator.Mediator

class RoleCheckMiddleware(): Middleware() {
    override fun check(email: String, password: String): Boolean {
        if(email.equals(("admin@exemple.com"))){
            println("Hello, admin!")
            return true
        }
        println("Hello, user!")
        super.mediator.notify("RoleCheckMiddleware","RegisterLog")
        return checkNext(email, password)
    }
}