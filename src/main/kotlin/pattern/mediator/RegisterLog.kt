package pattern.mediator

class RegisterLog: Command {
    override fun execute(data: Any) {
        println("testando command: $data")
    }


    override fun getName(): String = "RegisterLog"

}