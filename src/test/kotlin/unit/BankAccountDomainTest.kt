package unit

import adapters.BankAccountRepositoryLocal
import domains.BankAccountDomainImpl
import domains.models.AccountNumber
import domains.models.UserName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import ports.BankAccountRepository
import kotlin.test.assertEquals

class BankAccountDomainTest {

    // before each = clear repo

    @Nested
    inner class CreateNewUser {
        @Test
        fun `returns the account number of the newly created user`() {
            val mockBankAccountRepository: BankAccountRepository = BankAccountRepositoryLocal()
            val bankAccountDomain = BankAccountDomainImpl(mockBankAccountRepository)
            val userName = UserName("Carlos", "Alcaraz")

            val accountNumber: AccountNumber = bankAccountDomain.createNewAccount(userName)
            assertEquals(10000, accountNumber)

        }
    }


}