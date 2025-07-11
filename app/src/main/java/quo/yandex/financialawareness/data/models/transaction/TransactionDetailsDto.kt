package quo.yandex.financialawareness.data.models.transaction

import quo.yandex.financialawareness.data.models.account.AccountState
import quo.yandex.financialawareness.data.models.category.CategoriesItemDto
import java.util.Date

data class TransactionDetailsDto(
    val id: Int = 0,
    val account: AccountState = AccountState(),
    val category: CategoriesItemDto = CategoriesItemDto(),
    val amount: Double = 0.0,
    val transactionDate: Date = Date(),
    val comment: String = "",
    val createdAt: Date = Date(),
    val updatedAt: Date = Date(),
)