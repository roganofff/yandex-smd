package quo.yandex.financialawareness.domain.models

data class HistoryDto(
    val id: Int, // 1
    val accountId: Int, // 1
    val changeType: String, // MODIFICATION
    val newState: NewStateDto,
    val previousState: PreviousStateDto,
    val createdAt: String, // 2025-06-14T03:55:22.252Z
    val changeTimestamp: String // 2025-06-14T03:55:22.252Z
)