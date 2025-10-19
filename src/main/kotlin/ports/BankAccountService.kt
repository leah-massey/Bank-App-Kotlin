package ports

import models.AccountNumber
import ports.ResultTypes.CreateAccountResult
import ports.ResultTypes.DepositResult

interface BankAccountService {
    fun createNewAccount(userDetails: List<String>): CreateAccountResult
    fun depositMoney(depositDetails: List<String>): DepositResult
}