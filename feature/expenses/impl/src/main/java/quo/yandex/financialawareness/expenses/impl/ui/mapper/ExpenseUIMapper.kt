package quo.yandex.financialawareness.expenses.impl.ui.mapper

import quo.yandex.financialawareness.expenses.impl.ui.model.ExpenseUIModel
import quo.yandex.financialawareness.transactions.api.model.TransactionModel
import quo.yandex.financialawareness.ui.util.extension.formatAmount
import quo.yandex.financialawareness.ui.util.extension.toCurrencySymbol
import quo.yandex.financialawareness.util.DateHelper
import javax.inject.Inject

class ExpenseUIMapper @Inject constructor() {
    fun map(transaction: TransactionModel): ExpenseUIModel {
        return ExpenseUIModel(
            id = transaction.id,
            name = transaction.category.name,
            emoji = transaction.category.emoji,
            comment = transaction.comment,
            amount = "${transaction.amount.formatAmount()} ${transaction.account.currency.toCurrencySymbol()}",
            date = DateHelper.formatDateForDisplay(transaction.transactionDate) ?: "01.01.2025",
            time = DateHelper.formatTimeForDisplay(transaction.transactionDate) ?: "00:00"
        )
    }

    fun mapList(transactions: List<TransactionModel>): List<ExpenseUIModel> {
        return transactions.map { map(it) }
    }

    fun mapTotal(totalSum: Double, currency: String) : String {
        return "${totalSum.formatAmount()} ${currency.toCurrencySymbol()}"
    }
}