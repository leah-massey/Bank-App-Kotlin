import adapters.BankAccountRepositoryLocal
import service.BankAccountServiceImpl
import models.AccountNumber
import models.UserName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import ports.AccountCreationSuccess
import ports.BankAccountRepository
import ports.CreateAccountResult
import kotlin.test.assertEquals

class BankAccountServiceTest {

    // before each = clear repo

    @Nested
    inner class CreateNewUser {
        @Test
        fun `returns a response containing the account number of the newly created user`() {
            val bankAccountRepository: BankAccountRepository = BankAccountRepositoryLocal()
            val bankAccountService = BankAccountServiceImpl(bankAccountRepository)
            val userDetails = listOf("Carlos", "Alcaraz")

            val actual: CreateAccountResult = bankAccountService.createNewAccount(userDetails)

            assertEquals(AccountCreationSuccess(10000), actual)
        }
    }


}