package quo.yandex.financialawareness.data.mappers

import quo.yandex.financialawareness.data.api.response.CategoryDetailsResponse
import quo.yandex.financialawareness.data.models.category.CategoryDetailsDto
import javax.inject.Inject

class CategoryDetailsMapper @Inject constructor() {

    fun map(input: CategoryDetailsResponse?): CategoryDetailsDto {
        return input?.let {
            CategoryDetailsDto (
                id = it.categoryId ?: 0,
                name = it.categoryName.orEmpty(),
                emoji = it.emoji.orEmpty(),
                amount = input.amount?.toDoubleOrNull() ?: 0.0,
            )
        } ?: CategoryDetailsDto()
    }

    fun mapList(input: List<CategoryDetailsResponse>?): List<CategoryDetailsDto> {
        return input?.map { map(it) } ?: emptyList()
    }
}