package quo.yandex.financialawareness.income.impl.ui.screen.today.state

sealed class IncomeTodayEvent {
    object LoadIncome : IncomeTodayEvent()
    object HideErrorDialog : IncomeTodayEvent()
}