package ports.ResultTypes

sealed interface DepositResult

object DepositSuccess: DepositResult

data class DepositAccountNotFound(val message: String): DepositResult

data class InvalidDepositRequest(val message: String): DepositResult