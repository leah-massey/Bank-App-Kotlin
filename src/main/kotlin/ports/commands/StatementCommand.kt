package ports.commands

import ports.BankAccountService
import ports.ResultTypes.StatementAccountNotFound
import ports.ResultTypes.StatementSuccess

class StatementCommand(val bankAccountService: BankAccountService): Command {
    override fun execute(details: List<String>) {
        val result = bankAccountService.getStatement(details)
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
        }
    }
}