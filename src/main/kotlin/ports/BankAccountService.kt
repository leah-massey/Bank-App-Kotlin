package ports

import models.AccountNumber
import models.UserName

interface BankAccountService {
    fun createNewAccount(userName: UserName): AccountNumber
}