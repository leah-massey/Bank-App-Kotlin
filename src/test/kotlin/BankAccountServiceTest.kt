import adapters.BankAccountRepositoryLocal
import service.BankAccountServiceImpl
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import ports.ResultTypes.AccountCreationSuccess
import ports.BankAccountRepository
import ports.ResultTypes.CreateAccountResult
import ports.ResultTypes.DepositSuccess
import kotlin.test.assertEquals

class BankAccountServiceTest {

    val bankAccountRepository: BankAccountRepository = BankAccountRepositoryLocal()
    val bankAccountService = BankAccountServiceImpl(bankAccountRepository)

    @BeforeEach
    fun setUp() {
        bankAccountRepository.clear()
    }

    @Nested
    inner class CreateNewUser {
        @Test
        fun `returns a response containing the account number of the newly created user`() {
            val userDetails = listOf("Carlos", "Alcaraz")
            val actual: CreateAccountResult = bankAccountService.createNewAccount(userDetails)

            assertEquals(AccountCreationSuccess(10000), actual)
        }

        // TODO test for invalid request
    }

    @Nested
    inner class DepositMoney {
        @Test
        fun `deposits money when provided bank account exists`() {
            val userDetails = listOf("Carlos", "Alcaraz")
            bankAccountService.createNewAccount(userDetails)
            val actual = bankAccountService.depositMoney(listOf("10.00", "10000"))

            assertEquals(DepositSuccess("Deposit successful"), actual)
        }
    }
}