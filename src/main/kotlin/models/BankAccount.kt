package models

typealias AccountNumber = Int

data class AccountDetails(
    val userName: UserName,
    val balance: Int,
)

typealias BankAccounts =  MutableMap<AccountNumber, AccountDetails>