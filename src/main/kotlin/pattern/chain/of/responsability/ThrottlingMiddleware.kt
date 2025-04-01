package pattern.chain.of.responsability

import pattern.mediator.Mediator

class ThrottlingMiddleware(val requestPerMinute: Int,
                           var currentTime: Long = System.currentTimeMillis(),
                           ): Middleware() {
     var request: Int = 0

    override fun check(email: String, password: String): Boolean {
        if(System.currentTimeMillis() > currentTime + 60_000){
            this.request = 0
            currentTime = System.currentTimeMillis()
        }
        request = request.plus(1)

        if(request > requestPerMinute){
            println("Request limit exceeded!")
            Thread.currentThread().stop()
        }
        super.mediator.notify("ThrottlingMiddleware","RegisterLog")
        return checkNext(email, password)
    }

}