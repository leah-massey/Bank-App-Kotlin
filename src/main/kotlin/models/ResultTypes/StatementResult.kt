package models.ResultTypes

interface StatementResult {
}

data class StatementSuccess(val message: List<Pair<Double, Double>>?): StatementResult

data class InvalidStatementRequest(val message: String): StatementResult

data class StatementAccountNotFound(val message: String): StatementResult