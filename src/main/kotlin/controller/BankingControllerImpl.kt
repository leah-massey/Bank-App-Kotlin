package controller

import ports.BankAccountService
import ports.BankingController
import ports.UserInputProvider
import ports.commands.BalanceCommand
import ports.commands.Command
import ports.commands.DepositCommand
import ports.commands.NewAccountCommand
import ports.commands.StatementCommand
import ports.commands.WithdrawalCommand

class BankingControllerImpl(val bankAccountService: BankAccountService, val userInput: UserInputProvider) :
    BankingController {

    private val newAccountCommand = NewAccountCommand(bankAccountService)
    private val depositCommand = DepositCommand(bankAccountService)
    private val withdrawalCommand = WithdrawalCommand(bankAccountService)
    private val balanceCommand = BalanceCommand(bankAccountService)
    private val statementCommand = StatementCommand(bankAccountService)

    private val commandMap: Map<String, Command> = mapOf(
        "newaccount" to newAccountCommand,
        "deposit" to depositCommand,
        "withdraw" to withdrawalCommand,
        "balance" to balanceCommand,
        "statement" to statementCommand
    )

    override fun startBanking() {

        printInstructions()

        while (true) {
            print("> ")
            val processedUserInput = userInput.readLine().trim().lowercase().split(" ")
            val command = processedUserInput.first()
            val commandDetails = processedUserInput.drop(1)

            when (commandMap[command]) {
                newAccountCommand -> newAccountCommand.execute(commandDetails)
                depositCommand -> depositCommand.execute(commandDetails)
                withdrawalCommand -> withdrawalCommand.execute(commandDetails)
                balanceCommand -> balanceCommand.execute(commandDetails)
                statementCommand -> statementCommand.execute(commandDetails)
                null -> if (command == "quit") break else println("I didn't quite get that, please try again")
            }
        }
    }

    private fun printInstructions() {
        println("Welcome to the Bank CLI!")
        println("NewAccount [FirstName] [LastName] -> creates new account")
        println("Deposit [AccountId] [Amount]      -> Deposits funds into an account.")
        println("Withdraw [AccountId] [Amount]     -> Withdraws funds from an account.")
        println("Balance [AccountId]               -> Shows the current account balance.")
        println("Statement [AccountId]             -> Prints the transaction history.")
        println("Quit                              -> Quits the application.")
    }
}