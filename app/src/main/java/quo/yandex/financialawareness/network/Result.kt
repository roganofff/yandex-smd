package quo.yandex.financialawareness.network

sealed class ResultState<out T> {
    data class Success<out T>(val data: T) : ResultState<T>()
    data class Error(val error: NetworkError) : ResultState<Nothing>()
}
