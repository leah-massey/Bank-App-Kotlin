package controller

import ports.BankAccountService
import ports.BankingController
import ports.UserInputProvider
import ports.commands.Command
import ports.commands.DepositCommand
import ports.commands.NewAccountCommand
import ports.commands.WithdrawalCommand

class BankingControllerImpl(val bankAccountService: BankAccountService, val userInput: UserInputProvider) :
    BankingController {

    override fun startBanking() {
        println("Welcome to the Bank CLI!")
        println("NewAccount [FirstName] [LastName] -> creates new account")

        val newAccountCommand = NewAccountCommand(bankAccountService)
        val depositCommand = DepositCommand(bankAccountService)
        val withdrawalCommand = WithdrawalCommand(bankAccountService)

        var commandMap: Map<String, Command> = mapOf(
            "newaccount" to newAccountCommand,
            "deposit" to depositCommand,
            "withdraw" to withdrawalCommand
        )

        while (true) {
            print("> ")
            val processedUserInput = userInput.readLine().trim().lowercase().split(" ")
            val command = processedUserInput.first()
            val commandDetails = processedUserInput.drop(1)

            when (commandMap[command]) {
                newAccountCommand -> newAccountCommand.execute(commandDetails)
                depositCommand -> depositCommand.execute(commandDetails)
                withdrawalCommand -> withdrawalCommand.execute(commandDetails)
                null -> if (command == "quit") break else println("I didn't quite get that, please try again")
            }
        }
    }
}