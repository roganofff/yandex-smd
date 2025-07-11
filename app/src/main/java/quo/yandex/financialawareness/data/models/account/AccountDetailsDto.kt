package quo.yandex.financialawareness.data.models.account

import quo.yandex.financialawareness.data.models.category.CategoryDetailsDto
import java.util.Date

data class AccountDetailsDto(
    val id: Int = 0, // 1
    val name: String = "", // Основной счёт
    val balance: Double = 0.0, // 1000.00
    val currency: String = "", // RUB
    val incomeStats: List<CategoryDetailsDto> = emptyList(),
    val expenseStats: List<CategoryDetailsDto> = emptyList(),
    val createdAt: Date = Date(), // 2025-06-14T03:55:22.246Z
    val updatedAt: Date = Date() // 2025-06-14T03:55:22.246Z
)