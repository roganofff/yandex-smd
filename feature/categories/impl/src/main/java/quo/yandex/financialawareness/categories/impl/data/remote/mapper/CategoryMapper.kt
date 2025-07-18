package quo.yandex.financialawareness.categories.impl.data.remote.mapper

import quo.yandex.financialawareness.categories.impl.data.remote.remote.pojo.response.CategoryResponse
import quo.yandex.financialawareness.transactions.api.model.CategoryModel
import javax.inject.Inject

class CategoryMapper @Inject constructor() {

    fun map(input: CategoryResponse?): CategoryModel {
        return input?.let {
            CategoryModel (
                id = it.id ?: 0,
                name = it.name.orEmpty(),
                emoji = it.emoji.orEmpty(),
                isIncome = input.isIncome ?: false,
            )
        } ?: CategoryModel()
    }

    fun mapList(input: List<CategoryResponse>?): List<CategoryModel> {
        return input?.map { map(it) } ?: emptyList()
    }
}