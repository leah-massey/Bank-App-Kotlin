package ports

import domains.models.AccountDetails
import domains.models.AccountNumber
import domains.models.UserName

interface BankAccountRepository {
    fun create(userName: UserName): AccountNumber
    fun find(accountNumber: AccountNumber): AccountDetails?
}