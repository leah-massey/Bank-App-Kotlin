import adapters.BankAccountRepositoryLocal
import domains.BankAccountDomainImpl
import domains.models.UserName
import ports.BankAccountRepository
import ports.BankAccountDomain

fun main() {

    val bankAccountRepository: BankAccountRepository = BankAccountRepositoryLocal()
    val bankAccountDomain: BankAccountDomain = BankAccountDomainImpl(bankAccountRepository)

    println("Welcome to the Bank CLI!")
    println("NewAccount [FirstName] [LastName] -> creates new account")

    while (true) {
        print("> ")
        val (command, firstName, lastName) = readln().trim().lowercase().split(" ")

        when {
            (command == "newaccount") -> println( bankAccountDomain.createNewAccount(UserName(firstName, lastName)))
            else -> break
        }
    }
}






