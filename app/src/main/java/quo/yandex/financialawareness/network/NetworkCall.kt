package quo.yandex.financialawareness.network

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import quo.yandex.financialawareness.domain.api.FinancialApi
import quo.yandex.financialawareness.util.result.ResultState
import retrofit2.HttpException

//suspend inline fun <T> safeNetworkCall(crossinline apiCall: suspend () -> T): ResultState<T> {
//    return withContext(Dispatchers.IO) {
//        try {
//            ResultState.Success(apiCall.invoke())
//        } catch (e: Exception) {
//            when (e) {
//                is NoConnectionException -> ResultState.Failure(NetworkError.NoInternetError)
//                is HttpException -> {
//                    if (e.code() >= 500) {
//                        ResultState.Error(NetworkError.ServerError)
//                    } else {
//                        ResultState.Error(NetworkError.UnknownError(e))
//                    }
//                }
//
//                else -> ResultState.Error(NetworkError.UnknownError(e))
//            }
//        }
//    }
//}

suspend fun resolveCurrentAccountId(api: FinancialApi): Int {
    val accounts = api.getAccounts()
    val firstAccountId = accounts.firstOrNull()?.id
        ?: throw IllegalStateException("No accounts found for the user.")
    return firstAccountId
}