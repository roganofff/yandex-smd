package quo.yandex.financialawareness.transactions.impl.ui.model

import quo.yandex.financialawareness.util.DateHelper

data class TransactionUIModel(
    val id: Int = 0,
    val account: AccountUIModel = AccountUIModel(),
    val category: CategoryUIModel = CategoryUIModel(),
    val amount: String = "",
    val comment: String = "",
    val date: String = DateHelper.getTodayDisplayFormat(),
    val time: String = DateHelper.getCurrentTimeDisplayFormat()
)