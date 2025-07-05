package quo.yandex.financialawareness.data.models.account

import java.util.Date

data class AccountsItemDto(
    val id: Int, // 1
    val userId: Int, // 1
    val name: String, // Основной счёт
    val balance: Double, // 1000.00
    val currency: String, // RUB
    val createdAt: Date, // 2025-06-14T03:59:33.443Z
    val updatedAt: Date // 2025-06-14T03:59:33.443Z
)