package service

import InputValidation
import models.AccountNumber
import models.UserName
import ports.ResultTypes.AccountCreationSuccess
import ports.BankAccountService
import ports.BankAccountRepository
import ports.ResultTypes.AccountNotFound
import ports.ResultTypes.CreateAccountResult
import ports.ResultTypes.DepositResult
import ports.ResultTypes.DepositSuccess
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

    override fun depositMoney(amount: Int, accountNumber: AccountNumber): DepositResult {

        // TODO validation

        if (repository.accountExists(accountNumber)) {
            repository.deposit(amount, accountNumber)
            return DepositSuccess
        } else {
            return AccountNotFound("The provided account does not exist")
        }
    }
}