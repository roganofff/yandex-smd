package quo.yandex.financialawareness.data.models.account

data class AccountState(
    val id: Int = 0, // 1
    val name: String = "", // Основной счет
    val balance: Double = 0.0, // 1000.00
    val currency: String = "" // USD
)