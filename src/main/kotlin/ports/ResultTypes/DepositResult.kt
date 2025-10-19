package ports.ResultTypes

sealed interface DepositResult

data class DepositSuccess(val message: String): DepositResult

data class DepositAccountNotFound(val message: String): DepositResult

data class InvalidDepositRequest(val message: String): DepositResult