package quo.yandex.financialawareness.account.impl.domain.usecase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import quo.yandex.financialawareness.account.api.model.AccountModel
import quo.yandex.financialawareness.account.api.repository.AccountRepository
import quo.yandex.financialawareness.account.api.usecase.GetAccountUseCase
import quo.yandex.financialawareness.domain.di.qualifier.IoDispatchers
import quo.yandex.financialawareness.util.result.Result
import javax.inject.Inject

class GetAccountUseCaseImpl @Inject constructor(
    private val accountRepository: AccountRepository,
    @IoDispatchers private val dispatcher: CoroutineDispatcher
) : GetAccountUseCase {
    override suspend operator fun invoke(): Result<AccountModel> {
        return withContext(dispatcher) {
            when (val result = accountRepository.getAllAccounts()) {
                is Result.Success -> {
                    val account = result.data[0]
                    Result.Success(account)
                }

                is Result.Failure -> result
            }
        }
    }
}