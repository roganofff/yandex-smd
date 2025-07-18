package quo.yandex.financialawareness.transactions.impl.data.mapper

import quo.yandex.financialawareness.transactions.api.model.CategoryModel
import quo.yandex.financialawareness.transactions.impl.data.remote.pojo.response.CategoryResponse
import javax.inject.Inject

class CategoryMapper @Inject constructor() {

     fun map(input: CategoryResponse?): CategoryModel {
        return input?.let {
            CategoryModel (
                id = it.id ?: 0,
                name = it.name.orEmpty(),
                emoji = it.emoji.orEmpty(),
                isIncome = it.isIncome ?: false
            )
        } ?: CategoryModel()
    }
}