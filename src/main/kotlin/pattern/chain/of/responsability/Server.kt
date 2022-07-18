package pattern.chain.of.responsability

class Server() {
    val users: MutableMap<String, String> = mutableMapOf()
    lateinit var middleware: Middleware

    fun setMiddleWare(middleware: Middleware){
        this.middleware = middleware
    }

    fun login(email: String, password: String): Boolean{
        if(this.middleware.check(email, password)){
            println("Authorization have been successful!")
            return true
        }
        return false
    }

    /*fun register(email: String, password: String){
        users[email] = password
    }*/

    fun register(userAction: MutableMap<String, String>.() -> Unit) {
        users.userAction()
    }

    fun hasEmail(email: String) = this.users.containsKey(email)

    fun isValidPassword(email: String, password: String) = users[email]?.equals(password) ?: false


}