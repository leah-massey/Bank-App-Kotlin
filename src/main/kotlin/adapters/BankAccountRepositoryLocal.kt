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
        val newAccountDetails = AccountDetails(accountNumber = newAccountNumber, userName = userName, balance = 0.0)

        repository[newAccountNumber] = newAccountDetails
        return newAccountNumber
    }

    override fun deposit(amount: Double, accountNumber: AccountNumber) {
        // TODO check account exists should happen in service layer
        val account = find(accountNumber)

        if (account != null ) {
            val existingBalance = account.balance
            val newBalance = existingBalance + amount
            val updatedAccount = account.copy(balance = newBalance)
            repository[accountNumber] = updatedAccount
        }
    }

    override fun clear() {
       repository.clear()
    }


    // TODO accountExist = repo.containsKey

    override fun find(accountNumber: AccountNumber): AccountDetails? {
        return repository.get(accountNumber)
    }

    override fun accountExists(accountNumber: AccountNumber): Boolean {
        return repository.containsKey(accountNumber)
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