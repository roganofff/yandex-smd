package quo.yandex.financialawareness.account.impl.data.mapper

import quo.yandex.financialawareness.account.api.model.AccountDetailsModel
import quo.yandex.financialawareness.account.impl.data.remote.pojo.response.AccountDetailsResponse
import quo.yandex.financialawareness.util.DateHelper
import java.util.Date
import javax.inject.Inject

class AccountDetailsMapper @Inject constructor(
    private val categoryDetailsMapper: CategoryDetailsMapper
) {

    fun map(input: AccountDetailsResponse?): AccountDetailsModel {
        return input?.let {
            AccountDetailsModel (
            id = input.id ?: 0,
            name = input.name.orEmpty(),
            balance = input.balance?.toDoubleOrNull() ?: 0.0,
            currency = input.currency.orEmpty(),
            incomeCategories = categoryDetailsMapper.mapList(input.incomeStats),
            expenseCategories = categoryDetailsMapper.mapList(input.expenseStats),
            createdAt = input.createdAt?.let { DateHelper.parseApiDateTime(it) } ?: Date(),
            updatedAt = input.updatedAt?.let { DateHelper.parseApiDateTime(it) } ?: Date(),
            )
        } ?: AccountDetailsModel()
    }
}