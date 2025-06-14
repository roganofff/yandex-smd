package quo.yandex.financialawareness.data

import quo.yandex.financialawareness.R

data class ItemData(
    val title: String,
    val comment: String?,
    val price: String?,
    val leadIcon: String?,
    val trailIcon: Int?,
    val isLeading: Boolean = false,
    val isEmojiIcon: Boolean = true,
)

fun provideExpensesMockData() = listOf(
    ItemData(
        title = "Всего",
        comment = null,
        price = "436 558 ₽",
        leadIcon = null,
        trailIcon = null,
        isLeading = true,
    ),
    ItemData(
        title = "Аренда квартиры",
        comment = null,
        price = "100 000 ₽",
        leadIcon = "\uD83C\uDFE1",
        trailIcon = R.drawable.ic_more_vert,
        isLeading = false,
    ),
    ItemData(
        title = "Одежда",
        comment = null,
        price = "100 000 ₽",
        leadIcon = "\uD83D\uDC57",
        trailIcon = R.drawable.ic_more_vert,
        isLeading = false,
    ),
    ItemData(
        title = "На собачку",
        comment = "Джек",
        price = "100 000 ₽",
        leadIcon = "\uD83D\uDC36",
        trailIcon = R.drawable.ic_more_vert,
        isLeading = false,
    ),
    ItemData(
        title = "На собачку",
        comment = "Энни",
        price = "100 000 ₽",
        leadIcon = "\uD83D\uDC36",
        trailIcon = R.drawable.ic_more_vert,
        isLeading = false,
    ),
    ItemData(
        title = "Ремонт квартиры",
        comment = null,
        price = "100 000 ₽",
        leadIcon = "РК",
        trailIcon = R.drawable.ic_more_vert,
        isLeading = false,
        isEmojiIcon = false,
    ),
    ItemData(
        title = "Продукты",
        comment = null,
        price = "100 000 ₽",
        leadIcon = "\uD83C\uDF6D",
        trailIcon = R.drawable.ic_more_vert,
        isLeading = false,
    ),
    ItemData(
        title = "Спортзал",
        comment = null,
        price = "100 000 ₽",
        leadIcon = "\uD83C\uDFCB\uFE0F",
        trailIcon = R.drawable.ic_more_vert,
        isLeading = false,
    ),
    ItemData(
        title = "Медицина",
        comment = null,
        price = "100 000 ₽",
        leadIcon = "\uD83D\uDC8A",
        trailIcon = R.drawable.ic_more_vert,
        isLeading = false,
    )
)