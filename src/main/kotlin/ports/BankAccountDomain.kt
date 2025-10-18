package ports

import domains.models.AccountNumber
import domains.models.UserName

interface BankAccountDomain {
    fun createNewAccount(userName: UserName): AccountNumber
}