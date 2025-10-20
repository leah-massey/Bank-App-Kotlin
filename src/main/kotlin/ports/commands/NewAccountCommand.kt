package ports.commands

import ports.BankAccountService
import models.ResultTypes.AccountCreationSuccess
import models.ResultTypes.ValidationError

class NewAccountCommand( val bankAccountService: BankAccountService): Command {
    override fun execute(details: List<String>) {
        val result =
            bankAccountService.createNewAccount(details)
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