package quo.yandex.financialawareness.account.impl.ui.main

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import quo.yandex.financialawareness.account.api.usecase.GetAccountUseCase
import quo.yandex.financialawareness.account.impl.ui.main.state.AccountEffect
import quo.yandex.financialawareness.account.impl.ui.main.state.AccountEvent
import quo.yandex.financialawareness.account.impl.ui.main.state.AccountState
import quo.yandex.financialawareness.domain.provider.ResourceProvider
import quo.yandex.financialawareness.account.impl.ui.mapper.AccountUIMapper
import quo.yandex.financialawareness.account.impl.ui.model.AccountUIModel
import quo.yandex.financialawareness.util.result.FailureReason
import quo.yandex.financialawareness.util.result.Result
import javax.inject.Inject
import quo.yandex.financialawareness.ui.R
import quo.yandex.financialawareness.ui.base.BaseViewModel

@HiltViewModel
class AccountViewModel @Inject constructor(
    private val getAccountUseCase: GetAccountUseCase,
    private val accountUIMapper: AccountUIMapper,
    private val resourceProvider: ResourceProvider
) : BaseViewModel<AccountState, AccountEvent, AccountEffect>() {

    override val _state = MutableStateFlow(AccountState())
    override val state: StateFlow<AccountState> = _state.asStateFlow()

    override val _effect = Channel<AccountEffect>(Channel.UNLIMITED)
    override val effect = _effect.receiveAsFlow()

    private var loadAccountJob: Job? = null

    override fun reduce(event: AccountEvent) {
        when (event) {
            AccountEvent.HideErrorDialog ->
                _state.update {
                    it.copy(
                        showErrorDialog = null
                    )
                }
            AccountEvent.LoadAccount -> {
                _state.update {
                    it.copy(
                        showErrorDialog = null
                    )
                }
                loadAccount()
            }
        }
    }

    private fun loadAccount() {
        loadAccountJob?.cancel()

        loadAccountJob = launchTask {
            _state.update { it.copy(isLoading = true) }
            delay(1000)

            when (val result = getAccountUseCase()) {
                is Result.Success -> {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            account = accountUIMapper.map(result.data)
                        )
                    }
                }
                is Result.Failure -> {
                    handleFailure(result.reason)
                }
            }
        }
    }

    private fun handleFailure(failureReason: FailureReason) {
        val message = when (failureReason) {
            is FailureReason.Network -> resourceProvider.getString(R.string.failure_network)
            is FailureReason.Unauthorized -> resourceProvider.getString(R.string.failure_unauthorized)
            is FailureReason.Server -> resourceProvider.getString(R.string.failure_server)
            else -> resourceProvider.getString(R.string.failure_unknown)
        }
        _state.update {
            it.copy(
                isLoading = false,
                account = AccountUIModel(),
                showErrorDialog = message
            )
        }
    }
}