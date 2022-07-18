package pattern.chain.of.responsability

abstract class Middleware() {
    var next: Middleware? = null
    fun linkWith(next: Middleware): Middleware{
        this.next = next
        return next
    }

    abstract fun check(email: String, password: String): Boolean

     fun checkNext(email: String, password: String): Boolean{
         if (next == null) {
             return true;
         }
         return next!!.check(email, password);
     }

}