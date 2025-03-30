package pattern.mediator

interface Command {
    fun execute(data: Any)
    fun getName(): String
}