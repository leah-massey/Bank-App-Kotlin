package ports.ResultTypes

interface StatementResult {
}

data class StatementSuccess(val message: List<Pair<String, Double>>?): StatementResult

data class InvalidStatementRequest(val message: String): StatementResult

data class StatementAccountNotFound(val message: String): StatementResult