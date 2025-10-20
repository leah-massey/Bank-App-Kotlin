package ports

import models.ResultTypes.BalanceResult
import models.ResultTypes.CreateAccountResult
import models.ResultTypes.DepositResult
import models.ResultTypes.StatementResult
import models.ResultTypes.WithdrawalResult

interface BankAccountService {
    fun createNewAccount(userInputDetails: List<String>): CreateAccountResult
    fun depositMoney(depositInputDetails: List<String>): DepositResult
    fun withdrawMoney(withdrawalInputDetails: List<String>): WithdrawalResult
    fun getBalance(balanceInputDetails: List<String>): BalanceResult
    fun getStatement(statementInputDetails: List<String>): StatementResult
}