package unit

import adapters.BankAccountRepositoryLocal
import models.AccountDetails
import models.AccountNumber
import models.UserName
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import ports.BankAccountRepository
import kotlin.test.assertNotNull

class BankAccountRepositoryTest {
    val bankAccountRepository: BankAccountRepository = BankAccountRepositoryLocal()

    @BeforeEach
    fun setUp() {
        bankAccountRepository.clear()
    }

    @Test
    fun `a new user is created and stored in the repository`() {
        val userName = UserName("Jasmine", "Paulini")

        val accountNumber: AccountNumber = bankAccountRepository.create(userName)
        val accountDetails: AccountDetails? = bankAccountRepository.find(accountNumber)

        assertNotNull(accountDetails)
    }

    @Test
    fun `a deposit is made to an account`() {
        val userName = UserName("Jasmine", "Paulini")
        bankAccountRepository.create(userName)
        bankAccountRepository.deposit(10.0, 10000)
        val balance = bankAccountRepository.find(10000)?.balance

        assertEquals(10.0, balance)
    }
}