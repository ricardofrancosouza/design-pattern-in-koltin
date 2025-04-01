package pattern.chain.of.responsability

import pattern.mediator.Mediator

abstract class Middleware() {
    var next: Middleware? = null
    protected lateinit var mediator: Mediator
    fun linkWith(next: Middleware): Middleware{
        this.next = next
        return next
    }
    fun defineMediator(mediator: Mediator){
        this.mediator = mediator
    }

    abstract fun check(email: String, password: String): Boolean

     fun checkNext(email: String, password: String): Boolean{
         if (next == null) {
             return true;
         }
         return next!!.check(email, password);
     }

}