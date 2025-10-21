package ports.commands

import ports.BankAccountService
import models.ResultTypes.InvalidStatementRequest
import models.ResultTypes.StatementAccountNotFound
import models.ResultTypes.StatementSuccess

class StatementCommand(): Command {
    override fun execute(bankAccountService: BankAccountService, commandDetails: List<String>) {
        val result = bankAccountService.getStatement(commandDetails)
        when (result) {
            is StatementSuccess -> {
                result.message?.map { transaction ->
                    if (transaction == result.message.first()) {
                        println("account created")
                        println("balance: ${transaction.second}\n")
                    } else {
                        println("${transaction.first}")
                        println("balance: ${transaction.second}\n")
                    }
                }
            }
            is StatementAccountNotFound -> {
                println(result.message)
            }
            is InvalidStatementRequest -> {
                println(result.message)
            }
        }
    }
}