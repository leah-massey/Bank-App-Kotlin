package ports.ResultTypes

sealed interface DepositResult

object DepositSuccess: DepositResult
data class AccountNotFound(val message: String): DepositResult