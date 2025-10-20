package ports.commands

import ports.BankAccountService
import models.ResultTypes.InvalidWithdrawalRequest
import models.ResultTypes.WithdrawalAccountNotFound
import models.ResultTypes.WithdrawalSuccess

class WithdrawalCommand(val bankAccountService: BankAccountService): Command {
    override fun execute(details: List<String>) {
        val result = bankAccountService.withdrawMoney(details)

        when (result) {
            is WithdrawalSuccess -> {
                println(result.message)
            }
            is WithdrawalAccountNotFound -> {
                println(result.message)
            }
            is InvalidWithdrawalRequest -> {
                println(result.message)
            }
        }
    }
}