package quo.yandex.financialawareness.ui.util.extension

import kotlin.math.roundToInt

fun Double.toPercentString(): String {
    return "${this.roundToInt()}%"
}