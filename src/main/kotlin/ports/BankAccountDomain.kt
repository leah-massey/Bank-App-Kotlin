package ports

interface BankAccountDomain {
    fun createNewAccount(firstName: String, lastName: String) // maybe shouldn't return the value
}