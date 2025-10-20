package ports.commands

import ports.BankAccountService
import ports.ResultTypes.StatementSuccess

class StatementCommand(val bankAccountService: BankAccountService): Command {
    override fun execute(details: List<String>) {
        val result = bankAccountService.getStatement(details)
        when (result) {
            is StatementSuccess -> {
                result.message?.map { transaction ->
                    println("${transaction.first}")
                    println("balance: ${transaction.second}\n")
                }
            }
        }
    }
}