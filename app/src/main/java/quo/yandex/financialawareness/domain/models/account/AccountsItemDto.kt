package quo.yandex.financialawareness.domain.models.account

data class AccountsItemDto(
    val id: Int, // 1
    val userId: Int, // 1
    val name: String, // Основной счёт
    val balance: String, // 1000.00
    val currency: String, // RUB
    val createdAt: String, // 2025-06-14T03:59:33.443Z
    val updatedAt: String // 2025-06-14T03:59:33.443Z
)