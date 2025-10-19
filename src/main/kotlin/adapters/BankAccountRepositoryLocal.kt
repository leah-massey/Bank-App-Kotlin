package adapters

import models.AccountDetails
import models.AccountNumber
import models.BankAccounts
import models.UserName
import ports.BankAccountRepository

class BankAccountRepositoryLocal: BankAccountRepository {
    private val repository: BankAccounts = mutableMapOf()

    override fun create(userName: UserName): AccountNumber {
        val newAccountNumber = generateNewAccountNumber()
        val newAccountDetails = AccountDetails(userName = userName, balance = 0)

        repository[newAccountNumber] = newAccountDetails
        return newAccountNumber
    }

    override fun find(accountNumber: AccountNumber): AccountDetails? {
        return repository.get(accountNumber)
    }

    private fun generateNewAccountNumber(): AccountNumber {

        if (repository.isNotEmpty()) {
            val previousAccountNumber = repository.toSortedMap().lastKey()
            return previousAccountNumber + 1
        } else {
            return 10000
        }
    }
}