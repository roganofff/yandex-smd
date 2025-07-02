package quo.yandex.financialawareness.data.models.account

data class AccountDto(
    val id: Int, // 1
    val name: String, // Основной счёт
    val balance: String, // 1000.00
    val currency: String, // RUB
)
