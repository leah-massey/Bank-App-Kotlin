package unit

import adapters.BankAccountRepositoryLocal
import models.AccountDetails
import models.AccountNumber
import models.UserName
import org.junit.jupiter.api.Test
import ports.BankAccountRepository
import kotlin.test.assertNotNull

class BankAccountRepositoryTest {
    @Test
    fun `a new user is created and stored in the repository`() {
        val bankAccountRepository: BankAccountRepository = BankAccountRepositoryLocal()
        val userName = UserName("Jasmine", "Paulini")

        val accountNumber: AccountNumber = bankAccountRepository.create(userName)
        val accountDetails: AccountDetails? = bankAccountRepository.find(accountNumber)

        assertNotNull(accountDetails)
    }
}