package quo.yandex.financialawareness.transactions.api.model

enum class TransactionType {
    INCOME,
    EXPENSE,
    ANY;

    companion object {
        fun fromString(value: String): TransactionType {
            return when (value.uppercase()) {
                "INCOME" -> INCOME
                "EXPENSE" -> EXPENSE
                "ANY" -> ANY
                else -> ANY
            }
        }
    }
}