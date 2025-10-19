package controller

import models.UserName
import ports.BankAccountService
import ports.BankingController
import ports.UserInputProvider

class BankingControllerImpl(val bankAccountService: BankAccountService, val userInput: UserInputProvider) :
    BankingController {

    override fun startBanking() {
        println("Welcome to the Bank CLI!")
        println("NewAccount [FirstName] [LastName] -> creates new account")

        while (true) {
            print("> ")
            val processedUserInput = userInput.readLine().trim().lowercase().split(" ")
            val command = processedUserInput.first()

            when {
                (command == "newaccount") -> {
//                    isValidInputLength(processedUserInput)
                    val (_, firstName, lastName) = processedUserInput

                    println(
                        bankAccountService.createNewAccount(
                            UserName(
                                firstName,
                                lastName
                            )
                        )
                    )
                }
                (command == "quit") -> break
                else -> println("I didn't quite get that, please try again")
            }
        }
    }
}