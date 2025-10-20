import adapters.BankAccountRepositoryLocal
import controller.BankingControllerImpl
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import service.BankAccountServiceImpl
import java.io.ByteArrayOutputStream
import java.io.PrintStream
import kotlin.test.assertTrue

class BankingControllerTest {
    val bankAccountRepository = BankAccountRepositoryLocal()
    val bankAccountService = BankAccountServiceImpl(bankAccountRepository)

    @BeforeEach
    fun setUp() {
        bankAccountRepository.clear()
    }

    @Test
    fun `GIVEN a valid registration request, THEN the new account number should be logged to the console`() {
        val userInputProvider = FakeUserInputProvider(listOf("NewAccount Coco Gauff", "quit"))
        val bankingController = BankingControllerImpl(bankAccountService, userInputProvider)

        val outputStream = ByteArrayOutputStream()
        val printStream = PrintStream(outputStream, true, "UTF-8")
        val originalOut = System.out

        try{
            System.setOut(PrintStream(outputStream))
            bankingController.startBanking()
            printStream.flush()
            val output = outputStream.toString()
            assertTrue(output.contains("10000"))
        } finally{
            System.setOut(originalOut)
        }
    }

    @Test
    fun `GIVEN an command of incorrect length, THEN the user is notified by a message in the console`() {
        val userInputProvider = FakeUserInputProvider(listOf("NewAccount Coco Marie Gauff", "quit"))
        val bankingController = BankingControllerImpl(bankAccountService, userInputProvider)

        val outputStream = ByteArrayOutputStream()
        val printStream = PrintStream(outputStream, true, "UTF-8")
        val originalOut = System.out

        try{
            System.setOut(PrintStream(outputStream))
            bankingController.startBanking()
            printStream.flush()
            val output = outputStream.toString()
            assertTrue(output.contains("Invalid input format"))
        } finally{
            System.setOut(originalOut)
        }
    }

    @Nested
    inner class Deposit {
        @Test
        fun `GIVEN a valid deposit request, THEN a success message is logged to the console`() {
            val userInput = FakeUserInputProvider(listOf("NewAccount Coco Gauff", "deposit 10 10000", "quit"))
            val bankingController = BankingControllerImpl(bankAccountService, userInput)

            val outputStream = ByteArrayOutputStream()
            val printStream = PrintStream(outputStream, true, "UTF-8")
            val originalOut = System.out

            try{
                System.setOut(PrintStream(outputStream))
                bankingController.startBanking()
                printStream.flush()
                val output = outputStream.toString()
                assertTrue(output.contains("Deposit successful"))
            } finally{
                System.setOut(originalOut)
            }
        }

        @Test
        fun `GIVEN a deposit request for an account that does not exist, THEN an error message is logged to the console`() {
            val userInput = FakeUserInputProvider(listOf("deposit 10 10000", "quit"))
            val bankingController = BankingControllerImpl(bankAccountService, userInput)

            val outputStream = ByteArrayOutputStream()
            val printStream = PrintStream(outputStream, true, "UTF-8")
            val originalOut = System.out

            try{
                System.setOut(PrintStream(outputStream))
                bankingController.startBanking()
                printStream.flush()
                val output = outputStream.toString()
                assertTrue(output.contains("The provided account does not exist"))
            } finally{
                System.setOut(originalOut)
            }
        }

        @Test
        fun `GIVEN a deposit request that has an invalid currency format, THEN an error message is logged to the console`() {
            val userInput = FakeUserInputProvider(listOf("NewAccount Coco Gauff", "deposit 10ab 10000", "quit"))
            val bankingController = BankingControllerImpl(bankAccountService, userInput)

            val outputStream = ByteArrayOutputStream()
            val printStream = PrintStream(outputStream, true, "UTF-8")
            val originalOut = System.out

            try{
                System.setOut(PrintStream(outputStream))
                bankingController.startBanking()
                printStream.flush()
                val output = outputStream.toString()
                assertTrue(output.contains("Invalid input format"))
            } finally{
                System.setOut(originalOut)
            }
        }
    }

    @Nested
    inner class Withdrawal {
        @Test
        fun `GIVEN a valid withdrawal request, THEN a success message is logged to the console`() {
            val userInput = FakeUserInputProvider(listOf("NewAccount Coco Gauff", "deposit 10 10000", "withdraw 7 10000", "quit"))
            val bankingController = BankingControllerImpl(bankAccountService, userInput)

            val outputStream = ByteArrayOutputStream()
            val printStream = PrintStream(outputStream, true, "UTF-8")
            val originalOut = System.out

            try{
                System.setOut(PrintStream(outputStream))
                bankingController.startBanking()
                printStream.flush()
                val output = outputStream.toString()
                assertTrue(output.contains("Withdrawal successful"))
            } finally{
                System.setOut(originalOut)
            }
        }

        @Test
        fun `GIVEN a withdrawal request for an account that does not exist, THEN an error message is logged to the console`() {
            val userInput = FakeUserInputProvider(listOf("withdraw 7 10000", "quit"))
            val bankingController = BankingControllerImpl(bankAccountService, userInput)

            val outputStream = ByteArrayOutputStream()
            val printStream = PrintStream(outputStream, true, "UTF-8")
            val originalOut = System.out

            try{
                System.setOut(PrintStream(outputStream))
                bankingController.startBanking()
                printStream.flush()
                val output = outputStream.toString()
                assertTrue(output.contains("Withdrawal account not found"))
            } finally{
                System.setOut(originalOut)
            }
        }

        @Test
        fun `GIVEN a withdrawal request that has an invalid currency format, THEN an error message is logged to the console`() {
            val userInput = FakeUserInputProvider(listOf("withdraw 7sa 10000", "quit"))
            val bankingController = BankingControllerImpl(bankAccountService, userInput)

            val outputStream = ByteArrayOutputStream()
            val printStream = PrintStream(outputStream, true, "UTF-8")
            val originalOut = System.out

            try{
                System.setOut(PrintStream(outputStream))
                bankingController.startBanking()
                printStream.flush()
                val output = outputStream.toString()
                assertTrue(output.contains("Invalid input format"))
            } finally{
                System.setOut(originalOut)
            }

        }
    }

    @Nested
    inner class Balance {
        @Test
        fun `GIVEN a valid balance request, THEN the provided account's balance is logged to the console`() {
            val userInput = FakeUserInputProvider(listOf("NewAccount Coco Gauff", "deposit 10 10000", "balance 10000", "quit"))
            val bankingController = BankingControllerImpl(bankAccountService, userInput)

            val outputStream = ByteArrayOutputStream()
            val printStream = PrintStream(outputStream, true, "UTF-8")
            val originalOut = System.out

            try{
                System.setOut(PrintStream(outputStream))
                bankingController.startBanking()
                printStream.flush()
                val output = outputStream.toString()
                assertTrue(output.contains("10.0"))
            } finally{
                System.setOut(originalOut)
            }

        }

        @Test
        fun `GIVEN a balance request for an invalid account number, THEN an error message is logged to the console`() {
            val userInput = FakeUserInputProvider(listOf("NewAccount Coco Gauff", "deposit 10 10000", "balance 10002", "quit"))
            val bankingController = BankingControllerImpl(bankAccountService, userInput)

            val outputStream = ByteArrayOutputStream()
            val printStream = PrintStream(outputStream, true, "UTF-8")
            val originalOut = System.out

            try{
                System.setOut(PrintStream(outputStream))
                bankingController.startBanking()
                printStream.flush()
                val output = outputStream.toString()
                assertTrue(output.contains("Balance account not found"))
            } finally{
                System.setOut(originalOut)
            }
        }

        @Test
        fun `GIVEN a balance request with invalid format, THEN an error message is logged to the console`() {
            val userInput = FakeUserInputProvider(listOf("NewAccount Coco Gauff", "deposit 10 10000", "balance 10 10002", "quit"))
            val bankingController = BankingControllerImpl(bankAccountService, userInput)

            val outputStream = ByteArrayOutputStream()
            val printStream = PrintStream(outputStream, true, "UTF-8")
            val originalOut = System.out

            try{
                System.setOut(PrintStream(outputStream))
                bankingController.startBanking()
                printStream.flush()
                val output = outputStream.toString()
                assertTrue(output.contains("Invalid input format"))
            } finally{
                System.setOut(originalOut)
            }
        }
    }

    @Nested
    inner class Statement {
        @Test
        fun `GIVEN a valid statement request, a formatted statement is returned`() {
            val userInput = FakeUserInputProvider(listOf("NewAccount Coco Gauff", "deposit 10 10000", "statement 10000", "quit"))
            val bankingController = BankingControllerImpl(bankAccountService, userInput)

            val outputStream = ByteArrayOutputStream()
            val printStream = PrintStream(outputStream, true, "UTF-8")
            val originalOut = System.out

            try{
                System.setOut(PrintStream(outputStream))
                bankingController.startBanking()
                printStream.flush()
                val output = outputStream.toString()
                assertTrue(output.contains("deposit 10"))
            } finally{
                System.setOut(originalOut)
            }
        }
    }
}