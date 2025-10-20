package ports.commands

import ports.BankAccountService
import models.ResultTypes.DepositAccountNotFound
import models.ResultTypes.DepositSuccess
import models.ResultTypes.InvalidDepositRequest

class DepositCommand(val bankAccountService: BankAccountService): Command {
    override fun execute(details: List<String>) {
        val result = bankAccountService.depositMoney(details)
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