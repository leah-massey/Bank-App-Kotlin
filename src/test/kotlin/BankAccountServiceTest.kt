import adapters.BankAccountRepositoryLocal
import service.BankAccountServiceImpl
import models.AccountNumber
import models.UserName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import ports.BankAccountRepository
import kotlin.test.assertEquals

class BankAccountServiceTest {

    // before each = clear repo

    @Nested
    inner class CreateNewUser {
        @Test
        fun `returns the account number of the newly created user`() {
            val mockBankAccountRepository: BankAccountRepository = BankAccountRepositoryLocal()
            val bankAccountDomain = BankAccountServiceImpl(mockBankAccountRepository)
            val userName = UserName("Carlos", "Alcaraz")

            val accountNumber: AccountNumber = bankAccountDomain.createNewAccount(userName)
            assertEquals(10000, accountNumber)
        }
    }


}