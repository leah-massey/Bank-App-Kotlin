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
        val newAccountDetails = AccountDetails(accountNumber = newAccountNumber, userName = userName, transactions = mutableListOf(Pair("new account", 0.0)))

        repository[newAccountNumber] = newAccountDetails
        return newAccountNumber
    }

    override fun deposit(amount: Double, accountNumber: AccountNumber) {
        val account = find(accountNumber)

        if (account != null ) {
            val transactions = account.transactions
            val existingBalance = transactions[account.transactions.size -1].second
            val newBalance = existingBalance + amount
            val newTransaction = Pair("deposit $amount", newBalance)
            transactions.add(newTransaction)

            val updatedAccount = account.copy(transactions = transactions)
            repository[accountNumber] = updatedAccount
        }
    }

    override fun withdraw(amount: Double, accountNumber: AccountNumber) {
        val account = find(accountNumber)

        if(account != null) {
            val transactions = account.transactions
            val existingBalance = transactions[account.transactions.size -1].second
            val newBalance = existingBalance - amount
            val newTransaction = Pair("withdraw $amount", newBalance)
            transactions.add(newTransaction)

            val updatedAccount = account.copy(transactions = transactions)

            repository[accountNumber] = updatedAccount
        }
    }

    override fun balance(accountNumber: AccountNumber): Double? {
        val account = find(accountNumber)

        return account?.transactions[account.transactions.size -1]?.second
    }

    override fun statement(accountNumber: AccountNumber): List<Pair<String, Double>>? {
        val account = find(accountNumber)
        return account?.transactions
    }

    override fun clear() {
       repository.clear()
    }

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