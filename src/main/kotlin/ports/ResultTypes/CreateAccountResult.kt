package ports.ResultTypes

sealed interface CreateAccountResult {
}

data class AccountCreationSuccess(val accountNumber : Int): CreateAccountResult
data class ValidationError(val message: String): CreateAccountResult