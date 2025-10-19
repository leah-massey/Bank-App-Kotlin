package ports.commands

interface Command {
    fun execute(details: List<String>)
}
