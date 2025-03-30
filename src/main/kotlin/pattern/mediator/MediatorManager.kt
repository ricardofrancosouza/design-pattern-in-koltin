package pattern.mediator

class MediatorManager( ): Mediator{
    private val observers: MutableList<Command> = mutableListOf()
      override fun notify(data: Any, event: String) {
      observers.firstOrNull { it.getName() == event }.let { it?.execute(data) }
    }

    override fun linkCommand(command: Command) {
        observers.add(command)
    }
}