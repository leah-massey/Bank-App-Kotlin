package ports

import models.AccountNumber
import ports.ResultTypes.BalanceResult
import ports.ResultTypes.CreateAccountResult
import ports.ResultTypes.DepositResult
import ports.ResultTypes.WithdrawalResult

interface BankAccountService {
    fun createNewAccount(userDetails: List<String>): CreateAccountResult
    fun depositMoney(depositDetails: List<String>): DepositResult
    fun withdrawMoney(withdrawalDetails: List<String>): WithdrawalResult
    fun getBalance(balanceDetails: List<String>): BalanceResult
}