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
        val balance = bankAccountRepository.balance(10000)

        assertEquals(10.0, balance)
    }

    @Test
    fun `a withdrawal can be made from an account`() {
        val userName = UserName("Jasmine", "Paulini")
        bankAccountRepository.create(userName)
        bankAccountRepository.deposit(10.00, 10000)
        bankAccountRepository.withdraw(7.00, 10000)

        val balance = bankAccountRepository.balance(10000)

        assertEquals(3.0, balance)
    }

    @Test
    fun `returns the balance of a given account`() {
        val userName = UserName("Jasmine", "Paulini")
        bankAccountRepository.create(userName)
        bankAccountRepository.deposit(10.00, 10000)

        val balance = bankAccountRepository.balance(10000)
        assertEquals(10.00, balance)
    }

//    @Test
//    fun `returns a list of transactions`() {
//        val userName = UserName("Jasmine", "Paulini")
//        bankAccountRepository.create(userName)
//        bankAccountRepository.deposit(10.00, 10000)
//        bankAccountRepository.withdraw(7.00, 10000)
//
//        val statement = bankAccountRepository.statement(10000)
//        assertEquals(listOf(Pair("new account", 0.0), Pair("deposit 10", 10.00), Pair("withdraw 7", 3.00)), statement)
//    }
}