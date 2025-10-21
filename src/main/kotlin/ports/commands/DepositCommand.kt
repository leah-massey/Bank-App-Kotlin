package ports.commands

import ports.BankAccountService
import models.ResultTypes.DepositAccountNotFound
import models.ResultTypes.DepositSuccess
import models.ResultTypes.InvalidDepositRequest

class DepositCommand(): Command {
    override fun execute(bankAccountService: BankAccountService, commandDetails: List<String>) {
        val result = bankAccountService.depositMoney(commandDetails)
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
}