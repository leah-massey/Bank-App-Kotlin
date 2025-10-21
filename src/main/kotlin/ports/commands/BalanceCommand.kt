package ports.commands

import models.ResultTypes.BalanceAccountNotFound
import models.ResultTypes.BalanceSuccess
import models.ResultTypes.InvalidBalanceRequest
import ports.BankAccountService

class BalanceCommand(): Command {
    override fun execute(bankAccountService: BankAccountService, commandDetails: List<String>) {
        val result = bankAccountService.getBalance(commandDetails)

        when (result) {
            is BalanceSuccess -> {
                println(result.message)
            }
            is BalanceAccountNotFound -> {
                println(result.message)
            }
            is InvalidBalanceRequest -> {
                println(result.message)
            }
        }
    }
}