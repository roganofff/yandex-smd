package quo.yandex.financialawareness.domain.models.category

data class CategoryStatDto(
    val categoryId: Int, // 1
    val categoryName: String, // Зарплата
    val amount: String, // 5000.00
    val emoji: String // 💰
)