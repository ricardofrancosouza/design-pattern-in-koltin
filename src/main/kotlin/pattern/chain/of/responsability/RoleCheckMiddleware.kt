package pattern.chain.of.responsability

class RoleCheckMiddleware: Middleware() {
    override fun check(email: String, password: String): Boolean {
        if(email.equals(("admin@exemple.com"))){
            println("Hello, admin!")
            return true
        }
        println("Hello, user!")
        return checkNext(email, password)
    }
}