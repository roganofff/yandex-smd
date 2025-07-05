package quo.yandex.financialawareness.data.mappers

import quo.yandex.financialawareness.data.models.account.AccountDto
import quo.yandex.financialawareness.util.DateHelper
import java.util.Date
import javax.inject.Inject

class AccountMapper @Inject constructor(){
    fun map(input: AccountDto): AccountDto {
        return input.let {
            AccountDto(
                id = it.id ?: 0,
                name = it.name.orEmpty(),
                balance = input.balance,
                currency = it.currency.orEmpty(),
                createdAt = input.createdAt.let { DateHelper.parseApiDateTime(it) } ?: Date(),
                updatedAt = input.updatedAt.let { DateHelper.parseApiDateTime(it) } ?: Date(),
            )
        } ?: AccountDto()
    }

    fun mapList(input: List<AccountDto>?): List<AccountDto> {
        return input?.map { map(it) } ?: emptyList()
    }
}