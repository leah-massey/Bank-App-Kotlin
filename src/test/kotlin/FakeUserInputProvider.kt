import ports.UserInputProvider

class FakeUserInputProvider(val userInput: List<String>): UserInputProvider {

    var readCount = 0
    override fun readLine(): String {

        if (userInput.size > readCount) {
            val command = userInput[readCount]
            readCount++
            return command
        }
        return ""
    }
}