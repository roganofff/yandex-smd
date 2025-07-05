package quo.yandex.financialawareness.data.models.account

import quo.yandex.financialawareness.data.models.category.CategoryStatDto
import java.util.Date

data class AccountDetailsDto(
    val id: Int, // 1
    val name: String, // Основной счёт
    val balance: Double, // 1000.00
    val currency: String, // RUB
    val incomeStats: List<CategoryStatDto>,
    val expenseStats: List<CategoryStatDto>,
    val createdAt: Date, // 2025-06-14T03:55:22.246Z
    val updatedAt: Date // 2025-06-14T03:55:22.246Z
)