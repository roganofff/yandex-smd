package quo.yandex.financialawareness.transactions.impl.ui.mapper

import quo.yandex.financialawareness.transactions.api.model.CategoryModel
import quo.yandex.financialawareness.transactions.impl.ui.model.CategoryUIModel
import javax.inject.Inject

class CategoryUIMapper @Inject constructor() {
    fun map(category: CategoryModel): CategoryUIModel {
        return CategoryUIModel(
            id = category.id,
            name = category.name,
            emoji = category.emoji
        )
    }

    fun mapList(categories: List<CategoryModel>): List<CategoryUIModel> {
        return categories.map { map(it) }
    }
}