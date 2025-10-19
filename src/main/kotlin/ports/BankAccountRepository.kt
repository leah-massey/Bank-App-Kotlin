package ports

import models.AccountDetails
import models.AccountNumber
import models.UserName

interface BankAccountRepository {
    fun create(userName: UserName): AccountNumber
    fun find(accountNumber: AccountNumber): AccountDetails?
}