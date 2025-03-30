package pattern.mediator

interface Mediator {
    fun notify(data: Any, event: String)
    fun linkCommand(command: Command)
}