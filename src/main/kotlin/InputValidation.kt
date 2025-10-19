
class InputValidation {

    fun isValidInputLength(input: List<String>, requiredLength: Int): Boolean {
        return (input.size == requiredLength)
    }

    fun isValidCurrencyFormat(amount: String): Boolean {
        return amount.toDoubleOrNull() != null
    }

}