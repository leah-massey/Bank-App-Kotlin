package ports.commands

import ports.BankAccountService
import ports.ResultTypes.BalanceAccountNotFound
import ports.ResultTypes.BalanceSuccess
import ports.ResultTypes.InvalidBalanceRequest

class BalanceCommand(val bankAccountService: BankAccountService): Command {
    override fun execute(details: List<String>) {
        val result = bankAccountService.getBalance(details)

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