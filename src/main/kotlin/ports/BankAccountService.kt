package ports

import models.AccountNumber
import models.UserName

interface BankAccountService {
    fun createNewAccount(userDetails: List<String>): CreateAccountResult
}