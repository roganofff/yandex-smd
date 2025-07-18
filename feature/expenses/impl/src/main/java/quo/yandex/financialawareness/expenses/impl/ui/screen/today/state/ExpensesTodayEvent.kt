package quo.yandex.financialawareness.expenses.impl.ui.screen.today.state

sealed class ExpensesTodayEvent {
    object LoadExpenses : ExpensesTodayEvent()
    object HideErrorDialog : ExpensesTodayEvent()
}