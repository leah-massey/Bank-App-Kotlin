package ports

import ports.ResultTypes.BalanceResult
import ports.ResultTypes.CreateAccountResult
import ports.ResultTypes.DepositResult
import ports.ResultTypes.StatementResult
import ports.ResultTypes.WithdrawalResult

interface BankAccountService {
    fun createNewAccount(userInputDetails: List<String>): CreateAccountResult
    fun depositMoney(depositInputDetails: List<String>): DepositResult
    fun withdrawMoney(withdrawalInputDetails: List<String>): WithdrawalResult
    fun getBalance(balanceInputDetails: List<String>): BalanceResult
    fun getStatement(statementInputDetails: List<String>): StatementResult
}