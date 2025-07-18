package quo.yandex.financialawareness.account.impl.domain.usecase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import quo.yandex.financialawareness.account.api.model.AccountModel
import quo.yandex.financialawareness.account.api.repository.AccountRepository
import quo.yandex.financialawareness.domain.di.qualifier.IoDispatchers
import quo.yandex.financialawareness.util.result.Result
import javax.inject.Inject

class UpdateAccountUseCase @Inject constructor(
    private val accountRepository: AccountRepository,
    @IoDispatchers private val dispatcher: CoroutineDispatcher
) {
    suspend operator fun invoke(
        id: Int,
        name: String,
        balance: String,
        currency: String,
    ): Result<AccountModel> {
        return withContext(dispatcher) {
            when (val result = accountRepository.updateAccount(
                id = id,
                name = name,
                balance = balance,
                currency = currency
            )) {
                is Result.Success -> {
                    val account = result.data
                    Result.Success(account)
                }

                is Result.Failure -> result
            }
        }
    }
}