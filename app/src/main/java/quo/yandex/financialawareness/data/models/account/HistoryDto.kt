package quo.yandex.financialawareness.data.models.account

data class HistoryDto(
    val id: Int, // 1
    val accountId: Int, // 1
    val changeType: String, // MODIFICATION
    val newState: AccountState,
    val previousState: AccountState,
    val createdAt: String, // 2025-06-14T03:55:22.252Z
    val changeTimestamp: String // 2025-06-14T03:55:22.252Z
)