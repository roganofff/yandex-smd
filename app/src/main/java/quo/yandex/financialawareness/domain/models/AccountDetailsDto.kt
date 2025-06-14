package quo.yandex.financialawareness.domain.models

data class AccountDetailsDto(
    val id: Int, // 1
    val name: String, // Основной счёт
    val balance: String, // 1000.00
    val currency: String, // RUB
    val incomeStats: List<IncomeStatDto>,
    val expenseStats: List<ExpenseStatDto>,
    val createdAt: String, // 2025-06-14T03:55:22.246Z
    val updatedAt: String // 2025-06-14T03:55:22.246Z
)