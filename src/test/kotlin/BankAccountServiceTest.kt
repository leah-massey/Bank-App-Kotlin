import adapters.BankAccountRepositoryLocal
import service.BankAccountServiceImpl
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import ports.ResultTypes.AccountCreationSuccess
import ports.BankAccountRepository
import ports.ResultTypes.BalanceAccountNotFound
import ports.ResultTypes.BalanceSuccess
import ports.ResultTypes.CreateAccountResult
import ports.ResultTypes.DepositSuccess
import ports.ResultTypes.InsufficientFunds
import ports.ResultTypes.InvalidStatementRequest
import ports.ResultTypes.InvalidWithdrawalRequest
import ports.ResultTypes.StatementAccountNotFound
import ports.ResultTypes.StatementSuccess
import ports.ResultTypes.WithdrawalAccountNotFound
import ports.ResultTypes.WithdrawalSuccess
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

    //TODO test for when bank account does not exist

    @Nested
    inner class WithdrawMoney {
        @Test
        fun `GIVEN a valid account with available funds, THEN WithdrawalSuccess is returned`() {
            val userDetails = listOf("Carlos", "Alcaraz")
            bankAccountService.createNewAccount(userDetails)
            bankAccountService.depositMoney(listOf("10.00", "10000"))
            val actual = bankAccountService.withdrawMoney(listOf("3.00", "10000"))

            assertEquals(WithdrawalSuccess("Withdrawal successful"), actual)
        }

        @Test
        fun `GIVEN account does not exist THEN WithdrawalAccountNotFound is returned`() {
            val actual = bankAccountService.withdrawMoney(listOf("3.00", "10000"))
            assertEquals(WithdrawalAccountNotFound("Withdrawal account not found"), actual)
        }

        @Test
        fun `GIVEN withdrawal amount is incorrectly formatted, THEN InvalidWithdrawalRequest is returned`() {
            val actual = bankAccountService.withdrawMoney(listOf("3ad", "10000"))
            assertEquals(InvalidWithdrawalRequest("Invalid input format"), actual)
        }

        @Test
        fun `GIVEN insufficient funds, THEN InsufficientFunds is returned`() {
            val userDetails = listOf("Carlos", "Alcaraz")
            bankAccountService.createNewAccount(userDetails)
            val actual = bankAccountService.withdrawMoney(listOf("3.00", "10000"))

            assertEquals(InsufficientFunds("Insufficient funds"), actual)
        }
    }

    @Nested
    inner class Balance {
        @Test
        fun `GIVEN account exists, THEN balance returned`() {
            val userDetails = listOf("Carlos", "Alcaraz")
            bankAccountService.createNewAccount(userDetails)
            val actual = bankAccountService.getBalance(listOf("10000"))

            assertEquals(BalanceSuccess("0.0"), actual)
        }

        @Test
        fun `GIVEN account does not exist, THEN BalanceAccountNotFound returned`() {
            val actual = bankAccountService.getBalance(listOf("10000"))

            assertEquals(BalanceAccountNotFound("Balance account not found"), actual)
        }
    }

    @Nested
    inner class Statement {
        @Test
        fun `GIVEN account exists, THEN a list of transactions is returned`() {
            val userDetails = listOf("Carlos", "Alcaraz")
            bankAccountService.createNewAccount(userDetails)
            bankAccountService.depositMoney(listOf("10.00", "10000"))
            val statement = bankAccountService.getStatement(listOf("10000"))

            assertEquals(StatementSuccess(mutableListOf(Pair("new account", 0.0), Pair("deposit 10.0", 10.00))), statement)
        }

        @Test
        fun `GIVEN account does not exist, THEN StatementAccountNotFound message is returned`() {
            val statement = bankAccountService.getStatement(listOf("10000"))
            assertEquals(StatementAccountNotFound("Statement account not found"), statement)
        }

        @Test
        fun `GIVEN incorrect formatting of request, THEN InvalidStatementRequest message is returned`() {
            val statement = bankAccountService.getStatement(listOf("1000", "40"))
            assertEquals(InvalidStatementRequest("Invalid input format"), statement)
        }
    }

}