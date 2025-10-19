package service

import InputValidation
import models.AccountNumber
import models.UserName
import ports.AccountCreationSuccess
import ports.BankAccountService
import ports.BankAccountRepository
import ports.CreateAccountResult
import ports.ValidationError

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
}