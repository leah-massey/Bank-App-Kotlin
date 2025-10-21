package ports.commands

import ports.BankAccountService
import models.ResultTypes.InvalidWithdrawalRequest
import models.ResultTypes.WithdrawalAccountNotFound
import models.ResultTypes.WithdrawalSuccess

class WithdrawalCommand(): Command {
    override fun execute(bankAccountService: BankAccountService, commandDetails: List<String>) {
        val result = bankAccountService.withdrawMoney(commandDetails)

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