package ports

interface UserInputProvider {
    fun readLine(): String
}