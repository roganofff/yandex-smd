package quo.yandex.financialawareness.presentation.screens.account.state

import androidx.compose.runtime.Immutable
import quo.yandex.financialawareness.presentation.screens.account.model.AccountUIModel
import quo.yandex.financialawareness.presentation.screens.account.model.CurrencyUIModel

@Immutable
data class AccountUpdateState(
    val isLoading: Boolean = false,
    val account: AccountUIModel = AccountUIModel(),
    val currencies: List<CurrencyUIModel> = emptyList(),
    val showErrorDialog: String? = null,
    val showBottomSheet: Boolean = false
)
