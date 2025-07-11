package quo.yandex.financialawareness.data.mappers

import quo.yandex.financialawareness.data.api.response.AccountResponse
import quo.yandex.financialawareness.data.models.account.AccountDto
import quo.yandex.financialawareness.util.DateHelper
import java.util.Date
import javax.inject.Inject
import kotlin.collections.map
import kotlin.let
import kotlin.text.orEmpty

class AccountMapper @Inject constructor(){
    fun map(input: AccountResponse?): AccountDto {
        return input?.let {
            AccountDto(
                id = it.id ?: 0,
                name = it.name.orEmpty(),
                balance = input.balance?.toDoubleOrNull() ?: 0.0,
                currency = it.currency.orEmpty(),
                createdAt = input.createdAt?.let { DateHelper.parseApiDateTime(it) } ?: Date(),
                updatedAt = input.updatedAt?.let { DateHelper.parseApiDateTime(it) } ?: Date(),
            )
        } ?: AccountDto()
    }

    fun mapList(input: List<AccountResponse>?): List<AccountDto> {
        return input?.map { map(it) } ?: emptyList()
    }
}