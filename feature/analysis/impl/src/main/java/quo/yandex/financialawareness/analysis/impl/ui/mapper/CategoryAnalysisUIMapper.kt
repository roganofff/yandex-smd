package quo.yandex.financialawareness.analysis.impl.ui.mapper

import quo.yandex.financialawareness.analysis.impl.domain.model.CategoryWithPercentageModel
import quo.yandex.financialawareness.analysis.impl.ui.model.CategoryAnalysisUIModel
import quo.yandex.financialawareness.ui.util.extension.formatAmount
import quo.yandex.financialawareness.ui.util.extension.toCurrencySymbol
import quo.yandex.financialawareness.ui.util.extension.toPercentString
import javax.inject.Inject

class CategoryAnalysisUIMapper @Inject constructor() {
    fun map(category: CategoryWithPercentageModel): CategoryAnalysisUIModel {
        return CategoryAnalysisUIModel(
            id = category.id,
            name = category.name,
            amount = "${category.amount.formatAmount()} ${category.currency.toCurrencySymbol()}",
            emoji = category.emoji,
            percentage = category.percentage.toPercentString()
        )
    }

    fun mapList(category: List<CategoryWithPercentageModel>): List<CategoryAnalysisUIModel> {
        return category.map { map(it) }
    }

    fun mapTotal(totalSum: Double, currency: String) : String {
        return "${totalSum.formatAmount()} ${currency.toCurrencySymbol()}"
    }
}