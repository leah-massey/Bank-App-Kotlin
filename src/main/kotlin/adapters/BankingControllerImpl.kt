package adapters

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

    private val newAccountCommand = NewAccountCommand()
    private val depositCommand = DepositCommand()
    private val withdrawalCommand = WithdrawalCommand()
    private val balanceCommand = BalanceCommand()
    private val statementCommand = StatementCommand()

    private val commandMap: MutableMap<String, Command> = mutableMapOf(
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
            val commandString = processedUserInput.first()
            val commandDetails = processedUserInput.drop(1)

            when (val command = commandMap[commandString]) {
                is Command -> command.execute(bankAccountService, commandDetails)
                null -> if (commandString == "quit") break else println("I didn't quite get that, please try again")
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