import adapters.BankAccountRepositoryLocal
import controller.BankingControllerImpl
import org.junit.jupiter.api.Test
import service.BankAccountServiceImpl
import java.io.ByteArrayOutputStream
import java.io.PrintStream
import kotlin.test.assertTrue

class BankingControllerTest {

    @Test
    fun `GIVEN a valid registration request, THEN the new account number should be logged to the console`() {
        val bankAccountRepository = BankAccountRepositoryLocal()
        val bankAccountService = BankAccountServiceImpl(bankAccountRepository)
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
        val bankAccountRepository = BankAccountRepositoryLocal()
        val bankAccountService = BankAccountServiceImpl(bankAccountRepository)
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
            assertTrue(output.contains("Incorrect format, please try again"))
        } finally{
            System.setOut(originalOut)
        }
    }



}