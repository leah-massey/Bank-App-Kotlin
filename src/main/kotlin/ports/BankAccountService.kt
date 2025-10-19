package ports

import models.AccountNumber
import ports.ResultTypes.CreateAccountResult
import ports.ResultTypes.DepositResult

interface BankAccountService {
    fun createNewAccount(userDetails: List<String>): CreateAccountResult
    fun depositMoney(amount: Int, accountNumber: AccountNumber): DepositResult
}