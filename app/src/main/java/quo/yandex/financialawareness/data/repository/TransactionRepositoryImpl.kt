package quo.yandex.financialawareness.data.repository

import quo.yandex.financialawareness.data.models.transaction.TransactionDetailsDto
import quo.yandex.financialawareness.domain.api.FinancialApi
//import quo.yandex.financialawareness.domain.repository.TransactionRepository
import quo.yandex.financialawareness.network.resolveCurrentAccountId
import quo.yandex.financialawareness.util.result.ResultState
import java.time.LocalDate
import java.time.format.DateTimeFormatter
//
//class TransactionRepositoryImpl(
//    private val api: FinancialApi
//) : TransactionRepository {
//    override suspend fun getHistory(
//        startDate: String,
//        endDate: String
//    ): ResultState<List<TransactionDetailsDto>> {
//        return safeNetworkCall {
//            val accountId = resolveCurrentAccountId(api)
//            api.getTransactionsForPeriod(accountId, startDate, endDate)
//        }
//    }
//
//    override suspend fun getExpensesTransactions(): ResultState<List<TransactionDetailsDto>> {
//        return when (val result = getHistoryForCurrentMonth()) {
//            is ResultState.Success -> {
//                val expenses = result.data.filter { !it.category.isIncome }
//                ResultState.Success(expenses)
//            }
//
//            is ResultState.Error -> result
//        }
//    }
//
//    override suspend fun getIncomeTransactions(): ResultState<List<TransactionDetailsDto>> {
//        return when (val result = getHistoryForCurrentMonth()) {
//            is ResultState.Success -> {
//                val incomes = result.data.filter { it.category.isIncome }
//                ResultState.Success(incomes)
//            }
//
//            is ResultState.Error -> result
//        }
//    }
//
//    private suspend fun getHistoryForCurrentMonth(): ResultState<List<TransactionDetailsDto>> {
//        val today = LocalDate.now()
//        val startOfMonth = today.withDayOfMonth(1)
//        val formatter = DateTimeFormatter.ISO_LOCAL_DATE
//        return getHistory(startOfMonth.format(formatter), today.format(formatter))
//    }
//}