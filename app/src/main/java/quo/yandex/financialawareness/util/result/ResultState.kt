package quo.yandex.financialawareness.util.result

sealed class ResultState<out T> {
    data class Success<out T>(val data: T) : ResultState<T>()
    data class Failure(val reason: FailureReason) : ResultState<Nothing>()
}