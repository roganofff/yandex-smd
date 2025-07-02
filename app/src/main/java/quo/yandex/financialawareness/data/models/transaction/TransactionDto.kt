package quo.yandex.financialawareness.data.models.transaction

data class TransactionDto(
    val id: Int, // 1
    val accountId: Int, // 1
    val categoryId: Int, // 1
    val amount: String, // 500.00
    val transactionDate: String, // 2025-06-14T04:01:50.683Z
    val comment: String, // Зарплата за месяц
    val createdAt: String, // 2025-06-14T04:01:50.683Z
    val updatedAt: String // 2025-06-14T04:01:50.683Z
)