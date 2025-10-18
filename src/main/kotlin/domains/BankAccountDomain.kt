package domains

import domains.models.AccountNumber
import domains.models.UserName
import ports.BankAccountDomain
import ports.BankAccountRepository

class BankAccountDomain(val repository: BankAccountRepository): BankAccountDomain {

    override fun createNewAccount(userName: UserName): AccountNumber {
        return repository.create(userName)
    }
}