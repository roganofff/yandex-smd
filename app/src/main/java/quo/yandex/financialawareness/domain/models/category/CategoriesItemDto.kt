package quo.yandex.financialawareness.domain.models.category

data class CategoriesItemDto(
    val id: Int, // 1
    val name: String, // Зарплата
    val emoji: String, // 💰
    val isIncome: Boolean // true
)