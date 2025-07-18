package quo.yandex.financialawareness.account.impl.ui.main.state

import androidx.compose.runtime.Immutable
import quo.yandex.financialawareness.account.impl.ui.model.AccountUIModel

@Immutable
data class AccountState(
    val isLoading: Boolean = false,
    val account: AccountUIModel = AccountUIModel(),
    val showErrorDialog: String? = null

)
