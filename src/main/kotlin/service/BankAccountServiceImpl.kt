package service

import InputValidation
import models.UserName
import ports.ResultTypes.AccountCreationSuccess
import ports.BankAccountService
import ports.BankAccountRepository
import ports.ResultTypes.DepositAccountNotFound
import ports.ResultTypes.CreateAccountResult
import ports.ResultTypes.DepositResult
import ports.ResultTypes.DepositSuccess
import ports.ResultTypes.InvalidDepositRequest
import ports.ResultTypes.InvalidWithdrawalRequest
import ports.ResultTypes.ValidationError
import ports.ResultTypes.WithdrawalAccountNotFound
import ports.ResultTypes.WithdrawalResult
import ports.ResultTypes.WithdrawalSuccess

class BankAccountServiceImpl(val repository: BankAccountRepository) : BankAccountService {

    val inputValidation = InputValidation()

    override fun createNewAccount(userDetails: List<String>): CreateAccountResult {

        if (inputValidation.isValidInputLength(userDetails, 2)) {
            val (firstName, lastName) = userDetails
            val accountNumber =  repository.create(
                UserName(
                    firstName,
                    lastName
                )
            )
            return AccountCreationSuccess(accountNumber)
        } else {
            return ValidationError("Incorrect format, please try again")
        }
    }

    override fun depositMoney(depositDetails: List<String>): DepositResult {

        val isValidInputLength: Boolean = inputValidation.isValidInputLength(depositDetails, 2)
        val depositIsValidCurrencyFormat: Boolean = inputValidation.isValidCurrencyFormat(depositDetails[0])

        val (amountString, accountNumberString) = depositDetails

        if (!isValidInputLength || !depositIsValidCurrencyFormat) {
            return InvalidDepositRequest("Your input does not have the correct format")
        }

        val amount = amountString.toDoubleOrNull()
        val accountNumber = accountNumberString.toIntOrNull()

        if ( accountNumber != null && repository.accountExists(accountNumber)) {
            repository.deposit(amount!!, accountNumber)
            return DepositSuccess("Deposit successful")
        } else {
            return DepositAccountNotFound("The provided account does not exist")
        }
    }

    override fun withdrawMoney(withdrawalDetails: List<String>): WithdrawalResult {
        val isValidInputLength: Boolean = inputValidation.isValidInputLength(withdrawalDetails, 2)
        val depositIsValidCurrencyFormat: Boolean = inputValidation.isValidCurrencyFormat(withdrawalDetails[0])

        val (amountString, accountNumberString) = withdrawalDetails

        if (!isValidInputLength || !depositIsValidCurrencyFormat) {
            return InvalidWithdrawalRequest("Invalid input format")
        }

        val amount = amountString.toDoubleOrNull()
        val accountNumber = accountNumberString.toIntOrNull()

        if( accountNumber != null && repository.accountExists(accountNumber)) {
            repository.withdraw(amount!!, accountNumber)
            return WithdrawalSuccess("Withdrawal successful")
        } else {
            return WithdrawalAccountNotFound("Withdrawal account not found")
        }

    }
}