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
import ports.ResultTypes.ValidationError

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
        val amount = amountString.toDouble()
        val accountNumber = accountNumberString.toInt()

        if (!isValidInputLength || !depositIsValidCurrencyFormat) {
            return InvalidDepositRequest("Your input does not have the correct format")
        }

        if ( repository.accountExists(accountNumber)) {
            repository.deposit(amount, accountNumber)
            return DepositSuccess("Deposit successful")
        } else {
            return DepositAccountNotFound("The provided account does not exist")
        }
    }
}