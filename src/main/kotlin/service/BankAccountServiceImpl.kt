package service

import utils.InputValidation
import models.AccountNumber
import models.UserName
import models.ResultTypes.AccountCreationSuccess
import ports.BankAccountService
import ports.BankAccountRepository
import models.ResultTypes.BalanceAccountNotFound
import models.ResultTypes.BalanceResult
import models.ResultTypes.BalanceSuccess
import models.ResultTypes.DepositAccountNotFound
import models.ResultTypes.CreateAccountResult
import models.ResultTypes.DepositResult
import models.ResultTypes.DepositSuccess
import models.ResultTypes.InsufficientFunds
import models.ResultTypes.InvalidBalanceRequest
import models.ResultTypes.InvalidDepositRequest
import models.ResultTypes.InvalidStatementRequest
import models.ResultTypes.InvalidWithdrawalRequest
import models.ResultTypes.StatementAccountNotFound
import models.ResultTypes.StatementResult
import models.ResultTypes.StatementSuccess
import models.ResultTypes.ValidationError
import models.ResultTypes.WithdrawalAccountNotFound
import models.ResultTypes.WithdrawalResult
import models.ResultTypes.WithdrawalSuccess

class BankAccountServiceImpl(val repository: BankAccountRepository) : BankAccountService {

    val inputValidation = InputValidation()

    override fun createNewAccount(userInputDetails: List<String>): CreateAccountResult {

        if (!inputValidation.isValidInputLength(userInputDetails, 2)) {
            return ValidationError("Invalid input format")
        }

        val (firstName, lastName) = userInputDetails
        val accountNumber = repository.create(
            UserName(
                firstName,
                lastName
            )
        )
        return AccountCreationSuccess(accountNumber)
    }

    override fun depositMoney(depositInputDetails: List<String>): DepositResult {
        val isValidInputLength: Boolean = inputValidation.isValidInputLength(depositInputDetails, 2)

        val amount = depositInputDetails.getOrNull(0)?.toDoubleOrNull()
        val accountNumber = depositInputDetails.getOrNull(1)?.toIntOrNull()

        if (amount == null || accountNumber == null || !isValidInputLength) {
            return InvalidDepositRequest("Invalid input format")
        }

        if (!repository.accountExists(accountNumber)) {
            return DepositAccountNotFound("The provided account does not exist")
        }

        repository.deposit(amount, accountNumber)
        return DepositSuccess("Deposit successful")

    }

    override fun withdrawMoney(withdrawalInputDetails: List<String>): WithdrawalResult {
        val isValidInputLength: Boolean = inputValidation.isValidInputLength(withdrawalInputDetails, 2)
        val amount = withdrawalInputDetails.getOrNull(0)?.toDoubleOrNull()
        val accountNumber = withdrawalInputDetails.getOrNull(1)?.toIntOrNull()

        if (accountNumber == null || amount == null || !isValidInputLength) {
            return InvalidWithdrawalRequest("Invalid input format")
        }

        if (!repository.accountExists(accountNumber)) {
            return WithdrawalAccountNotFound("Withdrawal account not found")
        }

        if (!withdrawalFundsAvailable(amount, accountNumber)) {
            return InsufficientFunds("Insufficient funds")
        }

        repository.withdraw(amount, accountNumber)
        return WithdrawalSuccess("Withdrawal successful")
    }

    override fun getBalance(balanceInputDetails: List<String>): BalanceResult {
        val isValidInputLength: Boolean = inputValidation.isValidInputLength(balanceInputDetails, 1)
        val accountNumber = balanceInputDetails.getOrNull(0)?.toIntOrNull()

        if (accountNumber == null || !isValidInputLength) {
            return InvalidBalanceRequest("Invalid input format")
        }

        if (!repository.accountExists(accountNumber)) {
            return BalanceAccountNotFound("Balance account not found")
        }

        val balance = repository.balance(accountNumber)
        return BalanceSuccess(balance.toString())
    }

    override fun getStatement(statementInputDetails: List<String>): StatementResult {
        val isValidInputLength: Boolean = inputValidation.isValidInputLength(statementInputDetails, 1)
        val accountNumber = statementInputDetails.getOrNull(0)?.toIntOrNull()

        if (accountNumber == null || !isValidInputLength) {
            return InvalidStatementRequest("Invalid input format")
        }

        if (!repository.accountExists(accountNumber)) {
            return StatementAccountNotFound("Statement account not found")
        }

        val statement = repository.statement(accountNumber)
        return StatementSuccess(statement)
    }

    private fun withdrawalFundsAvailable(withdrawalAmount: Double, accountNumber: AccountNumber): Boolean {
        val balance = repository.balance(accountNumber)
        return (balance != null && balance >= withdrawalAmount)
    }
}