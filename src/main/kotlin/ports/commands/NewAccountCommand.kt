package ports.commands

import ports.BankAccountService
import models.ResultTypes.AccountCreationSuccess
import models.ResultTypes.ValidationError

class NewAccountCommand(): Command {
    override fun execute(bankAccountService: BankAccountService, commandDetails: List<String>) {
        val result =
            bankAccountService.createNewAccount(commandDetails)
        when (result) {
            is AccountCreationSuccess -> {
                println(result.accountNumber)
            }
            is ValidationError -> {
                println(result.message)
            }
        }
    }
}