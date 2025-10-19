package controller

import models.UserName
import ports.BankAccountService
import ports.BankingController
import ports.UserInputProvider

class BankingControllerImpl(val bankAccountService: BankAccountService, val userInput: UserInputProvider): BankingController {

    override fun startBanking() {
            println("Welcome to the Bank CLI!")
            println("NewAccount [FirstName] [LastName] -> creates new account")

            while (true) {
                print("> ")
                val processedUserInput = userInput.readLine().trim().lowercase().split(" ")
                println("🌸")
                println(processedUserInput)
                val command = processedUserInput.first()

                when {
                    (command == "newaccount") -> {
                        val (_, firstName, lastName) = processedUserInput

                        println( bankAccountService.createNewAccount(
                        UserName(
                            firstName,
                            lastName
                        )
                    ))}
                    else -> break
                }
            }
        }
    }