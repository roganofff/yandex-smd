package quo.yandex.financialawareness.data.models.account

import java.util.Date

data class AccountDto(
    val id: Int = 0,
    val name: String = "",
    val balance: Double = 0.00,
    val currency: String = "₽",
    val createdAt: Date = Date(),
    val updatedAt: Date = Date(),
)
