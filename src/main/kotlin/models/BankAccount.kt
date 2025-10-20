package models

typealias AccountNumber = Int

data class AccountDetails(
    val accountNumber: AccountNumber,
    val userName: UserName,
    var transactions: MutableList<Pair<Double, Double>>
)

typealias BankAccounts =  MutableMap<AccountNumber, AccountDetails>