package quo.yandex.financialawareness.util

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

fun getCurrentMonth() : String {
    val formatter = DateTimeFormatter.ofPattern("LLLL yyyy", Locale("ru"))
    return LocalDate.now()
        .format(formatter)
        .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale("ru")) else it.toString() }
}