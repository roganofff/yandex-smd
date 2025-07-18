package quo.yandex.financialawareness.account.api.usecase

import quo.yandex.financialawareness.account.api.model.AccountModel
import quo.yandex.financialawareness.util.result.Result


interface GetAccountUseCase {
    suspend operator fun invoke(): Result<AccountModel>
}