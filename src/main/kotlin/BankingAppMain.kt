import adapters.BankAccountRepositoryLocal
import adapters.UserInputProviderImpl
import controller.BankingControllerImpl
import service.BankAccountServiceImpl
import ports.BankAccountRepository
import ports.BankAccountService
import ports.BankingController
import ports.UserInputProvider

fun main() {

    val bankAccountRepository: BankAccountRepository = BankAccountRepositoryLocal()
    val bankAccountService: BankAccountService = BankAccountServiceImpl(bankAccountRepository)
    val userInput: UserInputProvider = UserInputProviderImpl()
    val bankingController: BankingController = BankingControllerImpl(bankAccountService, userInput)

}






