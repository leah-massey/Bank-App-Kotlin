package service

import models.AccountNumber
import models.UserName
import ports.BankAccountService
import ports.BankAccountRepository

class BankAccountServiceImpl(val repository: BankAccountRepository): BankAccountService {

    override fun createNewAccount(userName: UserName): AccountNumber {
        return repository.create(userName)
    }
}