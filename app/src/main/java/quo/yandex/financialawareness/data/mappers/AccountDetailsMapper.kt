package quo.yandex.financialawareness.data.mappers

import quo.yandex.financialawareness.data.api.response.AccountDetailsResponse
import quo.yandex.financialawareness.data.models.account.AccountDetailsDto
import quo.yandex.financialawareness.util.DateHelper
import java.util.Date
import javax.inject.Inject

class AccountDetailsMapper @Inject constructor(
    private val categoryDetailsMapper: CategoryDetailsMapper
) {

    fun map(input: AccountDetailsResponse?): AccountDetailsDto {
        return input?.let {
            AccountDetailsDto (
                id = input.id ?: 0,
                name = input.name.orEmpty(),
                balance = input.balance?.toDoubleOrNull() ?: 0.0,
                currency = input.currency.orEmpty(),
                incomeStats = categoryDetailsMapper.mapList(input.incomeStats),
                expenseStats = categoryDetailsMapper.mapList(input.expenseStats),
                createdAt = input.createdAt?.let { DateHelper.parseApiDateTime(it) } ?: Date(),
                updatedAt = input.updatedAt?.let { DateHelper.parseApiDateTime(it) } ?: Date(),
            )
        } ?: AccountDetailsDto()
    }
}