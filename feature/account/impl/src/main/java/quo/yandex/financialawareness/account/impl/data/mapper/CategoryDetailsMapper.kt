package quo.yandex.financialawareness.account.impl.data.mapper

import quo.yandex.financialawareness.account.api.model.CategoryDetailsModel
import quo.yandex.financialawareness.account.impl.data.remote.pojo.response.CategoryDetailsResponse
import javax.inject.Inject

class CategoryDetailsMapper @Inject constructor() {

    fun map(input: CategoryDetailsResponse?): CategoryDetailsModel {
        return input?.let {
            CategoryDetailsModel (
                id = it.categoryId ?: 0,
                name = it.categoryName.orEmpty(),
                emoji = it.emoji.orEmpty(),
                amount = input.amount?.toDoubleOrNull() ?: 0.0,
            )
        } ?: CategoryDetailsModel()
    }

    fun mapList(input: List<CategoryDetailsResponse>?): List<CategoryDetailsModel> {
        return input?.map { map(it) } ?: emptyList()
    }
}