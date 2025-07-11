package quo.yandex.financialawareness.presentation.screens.account.update.viewmodel

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import quo.yandex.financialawareness.R
import quo.yandex.financialawareness.data.mappers.AccountUIMapper
import quo.yandex.financialawareness.data.mappers.CurrencyUIMapper
import quo.yandex.financialawareness.di.provider.ResourceProvider
import quo.yandex.financialawareness.domain.usecases.account.GetAccountUseCase
import quo.yandex.financialawareness.domain.usecases.account.GetAllCurrenciesUseCase
import quo.yandex.financialawareness.domain.usecases.account.UpdateAccountUseCase
import quo.yandex.financialawareness.presentation.screens.BaseViewModel
import quo.yandex.financialawareness.presentation.screens.account.model.AccountUIModel
import quo.yandex.financialawareness.presentation.screens.account.state.AccountUpdateEffect
import quo.yandex.financialawareness.presentation.screens.account.state.AccountUpdateEvent
import quo.yandex.financialawareness.presentation.screens.account.state.AccountUpdateState
import quo.yandex.financialawareness.util.result.FailureReason
import quo.yandex.financialawareness.util.result.ResultState
import javax.inject.Inject

@HiltViewModel
class AccountUpdateViewModel @Inject constructor(
    private val getAccountUseCase: GetAccountUseCase,
    private val updateAccountUseCase: UpdateAccountUseCase,
    private val getAllCurrenciesUseCase: GetAllCurrenciesUseCase,
    private val accountUIMapper: AccountUIMapper,
    private val currencyUIMapper: CurrencyUIMapper,
    private val resourceProvider: ResourceProvider
) : BaseViewModel<AccountUpdateState, AccountUpdateEvent, AccountUpdateEffect>() {

    override val _state = MutableStateFlow(AccountUpdateState())
    override val state: StateFlow<AccountUpdateState> = _state.asStateFlow()

    override val _effect = Channel<AccountUpdateEffect>(Channel.Factory.UNLIMITED)
    override val effect = _effect.receiveAsFlow()

    private var loadAccountJob: Job? = null
    private var updateAccountJob: Job? = null
    private var loadCurrenciesJob: Job? = null

    override fun reduce(event: AccountUpdateEvent) {
        when (event) {
            AccountUpdateEvent.HideErrorDialog ->
                _state.update {
                    it.copy(
                        showErrorDialog = null
                    )
                }

            AccountUpdateEvent.LoadData -> {
                _state.update {
                    it.copy(
                        showErrorDialog = null
                    )
                }
                loadAccount()
                loadCurrencies()
            }

            AccountUpdateEvent.HideCurrencyBottomSheet ->
                _state.update {
                    it.copy(
                        showBottomSheet = false
                    )
                }

            is AccountUpdateEvent.SelectCurrency -> {
                _state.update {
                    it.copy(
                        account = state.value.account.copy(
                            currency = event.currency
                        )
                    )
                }
            }

            AccountUpdateEvent.ShowCurrencyBottomSheet ->
                _state.update {
                    it.copy(
                        showBottomSheet = true
                    )
                }

            AccountUpdateEvent.OnDoneClicked -> updateAccount()
            is AccountUpdateEvent.UpdateBalance ->
                _state.update {
                    it.copy(
                        account = state.value.account.copy(
                            balance = event.balance
                        )
                    )
                }

            is AccountUpdateEvent.UpdateName ->
                _state.update {
                    it.copy(
                        account = state.value.account.copy(
                            name = event.name
                        )
                    )
                }
        }
    }

    private fun loadAccount() {
        loadAccountJob?.cancel()

        loadAccountJob = launchTask {
            _state.update { it.copy(isLoading = true) }
            delay(1000)

            when (val result = getAccountUseCase()) {
                is ResultState.Success -> {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            account = accountUIMapper.map(result.data)
                        )
                    }
                }

                is ResultState.Failure -> {
                    handleFailure(result.reason)
                }
            }
        }
    }

    private fun loadCurrencies() {
        loadCurrenciesJob?.cancel()

        loadCurrenciesJob = launchTask {
            _state.update { it.copy(isLoading = true) }
            delay(1000)

            when (val result = getAllCurrenciesUseCase()) {
                is ResultState.Success -> {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            currencies = currencyUIMapper.mapList(result.data)
                        )
                    }
                }

                is ResultState.Failure -> {
                    handleFailure(result.reason)
                }
            }
        }
    }

    private fun updateAccount() {
        updateAccountJob?.cancel()

        updateAccountJob = launchTask {
            _state.update { it.copy(isLoading = true) }
            delay(1000)

            when (val result = updateAccountUseCase(
                id = state.value.account.id,
                name = state.value.account.name,
                balance = state.value.account.balance.let {
                    it.ifEmpty { "0" }
                },
                currency = state.value.account.currency.currency,
            )) {
                is ResultState.Success -> {
                    _state.update {
                        it.copy(
                            isLoading = false,
                        )
                    }
                    sendEffect(
                        AccountUpdateEffect.ShowToast(
                            resourceProvider.getString(
                                R.string.account_updated_successfully
                            )
                        )
                    )
                    sendEffect(AccountUpdateEffect.AccountUpdated)
                }

                is ResultState.Failure -> {
                    handleFailure(result.reason)
                    sendEffect(
                        AccountUpdateEffect.ShowToast(
                            resourceProvider.getString(
                                R.string.account_not_updated
                            )
                        )
                    )
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