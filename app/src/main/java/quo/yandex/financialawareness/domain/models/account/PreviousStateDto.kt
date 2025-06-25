package quo.yandex.financialawareness.domain.models.account

data class PreviousStateDto(
    val id: Int, // 1
    val name: String, // Основной счет
    val balance: String, // 1000.00
    val currency: String // USD
)