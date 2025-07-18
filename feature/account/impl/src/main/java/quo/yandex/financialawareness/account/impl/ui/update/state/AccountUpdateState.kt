package quo.yandex.financialawareness.account.impl.ui.update.state

import androidx.compose.runtime.Immutable
import quo.yandex.financialawareness.account.impl.ui.model.AccountUIModel
import quo.yandex.financialawareness.account.impl.ui.model.CurrencyUIModel

@Immutable
data class AccountUpdateState(
    val isLoading: Boolean = false,
    val account: AccountUIModel = AccountUIModel(),
    val currencies: List<CurrencyUIModel> = emptyList(),
    val showErrorDialog: String? = null,
    val showBottomSheet: Boolean = false
)
