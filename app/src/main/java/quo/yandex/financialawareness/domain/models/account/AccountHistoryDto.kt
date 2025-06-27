package quo.yandex.financialawareness.domain.models.account

data class AccountHistoryDto(
    val accountId: Int, // 1
    val accountName: String, // Основной счет
    val currency: String, // USD
    val currentBalance: String, // 2000.00
    val history: List<HistoryDto>
)