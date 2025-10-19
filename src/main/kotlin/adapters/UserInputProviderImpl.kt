package adapters

import ports.UserInputProvider

class UserInputProviderImpl: UserInputProvider {
    override fun readLine() = readln()
}