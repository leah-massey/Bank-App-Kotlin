package models.ResultTypes

interface BalanceResult {
}

data class BalanceSuccess(val message: String): BalanceResult

data class InvalidBalanceRequest(val message: String): BalanceResult

data class BalanceAccountNotFound(val message: String): BalanceResult