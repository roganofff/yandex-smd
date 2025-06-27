package quo.yandex.financialawareness.domain.models.account

import quo.yandex.financialawareness.domain.models.category.CategoryStatDto

data class AccountDetailsDto(
    val id: Int, // 1
    val name: String, // Основной счёт
    val balance: String, // 1000.00
    val currency: String, // RUB
    val incomeStats: List<CategoryStatDto>,
    val expenseStats: List<CategoryStatDto>,
    val createdAt: String, // 2025-06-14T03:55:22.246Z
    val updatedAt: String // 2025-06-14T03:55:22.246Z
)