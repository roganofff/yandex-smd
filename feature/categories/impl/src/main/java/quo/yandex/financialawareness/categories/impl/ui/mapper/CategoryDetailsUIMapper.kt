package quo.yandex.financialawareness.categories.impl.ui.mapper

import quo.yandex.financialawareness.account.api.model.CategoryDetailsModel
import quo.yandex.financialawareness.categories.impl.ui.model.CategoryDetailsUIModel
import javax.inject.Inject

class CategoryDetailsUIMapper @Inject constructor() {
    fun map(category: CategoryDetailsModel): CategoryDetailsUIModel {
        return CategoryDetailsUIModel(
            id = category.id,
            name = category.name,
            emoji = category.emoji
        )
    }

    fun mapList(categories: List<CategoryDetailsModel>): List<CategoryDetailsUIModel> {
        return categories.map { map(it) }
    }
}