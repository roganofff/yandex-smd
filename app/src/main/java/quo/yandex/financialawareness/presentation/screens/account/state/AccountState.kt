package quo.yandex.financialawareness.presentation.screens.account.state

import androidx.compose.runtime.Immutable
import quo.yandex.financialawareness.presentation.screens.account.model.AccountUIModel

@Immutable
data class AccountState(
    val isLoading: Boolean = false,
    val account: AccountUIModel = AccountUIModel(),
    val showErrorDialog: String? = null
)
