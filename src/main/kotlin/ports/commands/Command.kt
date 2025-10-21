package ports.commands

import ports.BankAccountService

interface Command {
    fun execute(bankAccountService: BankAccountService, commandDetails: List<String>)
}
