package controller

import ports.ResultTypes.AccountCreationSuccess
import ports.BankAccountService
import ports.BankingController
import ports.ResultTypes.DepositAccountNotFound
import ports.ResultTypes.DepositSuccess
import ports.ResultTypes.InvalidDepositRequest
import ports.UserInputProvider
import ports.ResultTypes.ValidationError

class BankingControllerImpl(val bankAccountService: BankAccountService, val userInput: UserInputProvider) :
    BankingController {

    override fun startBanking() {
        println("Welcome to the Bank CLI!")
        println("NewAccount [FirstName] [LastName] -> creates new account")

        while (true) {
            print("> ")
            val processedUserInput = userInput.readLine().trim().lowercase().split(" ")
            val command = processedUserInput.first()
            val commandDetails = processedUserInput.drop(1)

            when {
                (command == "newaccount") -> {
                    val result = bankAccountService.createNewAccount(commandDetails)
                    when (result) {
                        is AccountCreationSuccess -> {
                            println(result.accountNumber)
                        }
                        is ValidationError -> {
                            println(result.message)
                        }
                    }
                }
                (command == "deposit") -> {
                    val result = bankAccountService.depositMoney(depositDetails = commandDetails)
                    when (result) {
                        is DepositSuccess -> {
                            println(result.message)
                        }
                        is DepositAccountNotFound -> {
                            println(result.message)
                        }
                        is InvalidDepositRequest -> {
                            println(result.message)
                        }
                    }
                }
                (command == "quit") -> break
                else -> println("I didn't quite get that, please try again")
            }
        }
    }
}