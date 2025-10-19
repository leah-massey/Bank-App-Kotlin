package models

typealias AccountNumber = Int

data class AccountDetails(
    val accountNumber: AccountNumber,
    val userName: UserName,
    var balance: Double,
)

typealias BankAccounts =  MutableMap<AccountNumber, AccountDetails>