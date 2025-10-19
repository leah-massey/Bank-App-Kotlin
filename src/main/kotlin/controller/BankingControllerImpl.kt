package controller

import InputValidation
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

            val inputValidation = InputValidation()

            when {
                (command == "newaccount") -> {
                    if (inputValidation.isValidInputLength(processedUserInput, 3)) {
                        val (_, firstName, lastName) = processedUserInput

                        println(
                            bankAccountService.createNewAccount(
                                UserName(
                                    firstName,
                                    lastName
                                )
                            )
                        )
                    } else {
                        println("Incorrect format, please try again")
                    }
                }
                (command == "quit") -> break
                else -> println("I didn't quite get that, please try again")
            }
        }
    }
}