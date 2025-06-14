package quo.yandex.financialawareness.domain.models

data class TransactionDetailsDto(
    val id: Int, // 1
    val account: AccountDto,
    val category: CategoriesItemDto,
    val amount: String, // 500.00
    val transactionDate: String, // 2025-06-14T04:03:54.647Z
    val comment: String, // Зарплата за месяц
    val createdAt: String, // 2025-06-14T04:03:54.647Z
    val updatedAt: String // 2025-06-14T04:03:54.647Z
)