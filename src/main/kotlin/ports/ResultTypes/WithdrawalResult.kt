package ports.ResultTypes

interface WithdrawalResult {
}

data class WithdrawalSuccess(val message: String): WithdrawalResult

data class WithdrawalAccountNotFound(val message: String): WithdrawalResult