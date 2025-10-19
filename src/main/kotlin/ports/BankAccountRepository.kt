package ports

import models.AccountDetails
import models.AccountNumber
import models.BankAccounts
import models.UserName

interface BankAccountRepository {
    fun create(userName: UserName): AccountNumber
    fun find(accountNumber: AccountNumber): AccountDetails?
    fun deposit(amount: Double, accountNumber: AccountNumber)
    fun clear()
    fun accountExists(accountNumber: AccountNumber): Boolean
    fun withdraw(amount: Double, accountNumber: AccountNumber)
    fun balance(accountNumber: AccountNumber): Double?
}