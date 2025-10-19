package ports.ResultTypes

import javax.annotation.processing.Messager

interface BalanceResult {
}

data class BalanceSuccess(val message: String): BalanceResult

data class InvalidBalanceRequest(val message: String): BalanceResult

data class BalanceAccountNotFound(val message: String): BalanceResult