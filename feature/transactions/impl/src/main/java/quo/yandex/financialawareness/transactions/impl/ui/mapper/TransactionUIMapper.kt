package quo.yandex.financialawareness.transactions.impl.ui.mapper

import quo.yandex.financialawareness.transactions.api.model.TransactionModel
import quo.yandex.financialawareness.transactions.impl.ui.model.AccountUIModel
import quo.yandex.financialawareness.transactions.impl.ui.model.TransactionUIModel
import quo.yandex.financialawareness.ui.util.extension.formatAmount
import quo.yandex.financialawareness.ui.util.extension.toCurrencySymbol
import quo.yandex.financialawareness.util.DateHelper
import javax.inject.Inject

class TransactionUIMapper @Inject constructor(
    private val categoryUIMapper: CategoryUIMapper
) {
    fun map(transaction: TransactionModel): TransactionUIModel {
        return TransactionUIModel(
            id = transaction.id,
            account = AccountUIModel(
                id = transaction.account.id,
                name = transaction.account.name,
                currencySymbol = transaction.account.currency.toCurrencySymbol()
            ),
            category = categoryUIMapper.map(transaction.category),
            comment = transaction.comment,
            amount = transaction.amount.formatAmount(),
            date = DateHelper.formatDateForDisplay(transaction.transactionDate) ?: DateHelper.getTodayDisplayFormat(),
            time = DateHelper.formatTimeForDisplay(transaction.transactionDate) ?: DateHelper.getCurrentTimeDisplayFormat()
        )
    }
}