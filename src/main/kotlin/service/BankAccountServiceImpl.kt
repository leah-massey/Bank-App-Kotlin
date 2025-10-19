package service

import InputValidation
import models.AccountNumber
import models.UserName
import ports.ResultTypes.AccountCreationSuccess
import ports.BankAccountService
import ports.BankAccountRepository
import ports.ResultTypes.BalanceAccountNotFound
import ports.ResultTypes.BalanceResult
import ports.ResultTypes.BalanceSuccess
import ports.ResultTypes.DepositAccountNotFound
import ports.ResultTypes.CreateAccountResult
import ports.ResultTypes.DepositResult
import ports.ResultTypes.DepositSuccess
import ports.ResultTypes.InsufficientFunds
import ports.ResultTypes.InvalidBalanceRequest
import ports.ResultTypes.InvalidDepositRequest
import ports.ResultTypes.InvalidWithdrawalRequest
import ports.ResultTypes.ValidationError
import ports.ResultTypes.WithdrawalAccountNotFound
import ports.ResultTypes.WithdrawalResult
import ports.ResultTypes.WithdrawalSuccess

class BankAccountServiceImpl(val repository: BankAccountRepository) : BankAccountService {

    val inputValidation = InputValidation()

    override fun createNewAccount(userDetails: List<String>): CreateAccountResult {

        if (!inputValidation.isValidInputLength(userDetails, 2)) {
            return ValidationError("Invalid input format")
        }

        val (firstName, lastName) = userDetails
        val accountNumber = repository.create(
            UserName(
                firstName,
                lastName
            )
        )
        return AccountCreationSuccess(accountNumber)
    }

    override fun depositMoney(depositDetails: List<String>): DepositResult {
        val isValidInputLength: Boolean = inputValidation.isValidInputLength(depositDetails, 2)

        val amount = depositDetails.getOrNull(0)?.toDoubleOrNull()
        val accountNumber = depositDetails.getOrNull(1)?.toIntOrNull()

        if (amount == null || accountNumber == null || !isValidInputLength) {
            return InvalidDepositRequest("Invalid input format")
        }

        if (!repository.accountExists(accountNumber)) {
            return DepositAccountNotFound("The provided account does not exist")
        }

        repository.deposit(amount, accountNumber)
        return DepositSuccess("Deposit successful")

    }

    override fun withdrawMoney(withdrawalDetails: List<String>): WithdrawalResult {
        val isValidInputLength: Boolean = inputValidation.isValidInputLength(withdrawalDetails, 2)
        val amount = withdrawalDetails.getOrNull(0)?.toDoubleOrNull()
        val accountNumber = withdrawalDetails.getOrNull(1)?.toIntOrNull()

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

    override fun getBalance(balanceDetails: List<String>): BalanceResult {
        val isValidInputLength: Boolean = inputValidation.isValidInputLength(balanceDetails, 1)
        val accountNumber = balanceDetails.getOrNull(0)?.toIntOrNull()

        if (accountNumber == null || !isValidInputLength) {
            return InvalidBalanceRequest("Invalid input format")
        }

        if (!repository.accountExists(accountNumber)) {
            return BalanceAccountNotFound("Balance account not found")
        }

        val balance = repository.balance(accountNumber)
        return BalanceSuccess(balance.toString())
    }

    private fun withdrawalFundsAvailable(withdrawalAmount: Double, accountNumber: AccountNumber): Boolean {
        val balance = repository.find(accountNumber)?.balance
        return (balance != null && balance >= withdrawalAmount)
    }
}