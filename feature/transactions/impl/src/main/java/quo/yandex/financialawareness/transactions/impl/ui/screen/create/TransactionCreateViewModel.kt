package quo.yandex.financialawareness.transactions.impl.ui.screen.create

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
import quo.yandex.financialawareness.categories.api.usecase.GetCategoriesUseCase
import quo.yandex.financialawareness.domain.provider.ResourceProvider
import quo.yandex.financialawareness.transactions.api.model.TransactionType
import quo.yandex.financialawareness.transactions.impl.R
import quo.yandex.financialawareness.transactions.impl.domain.usecase.CreateTransactionUseCase
import quo.yandex.financialawareness.transactions.impl.ui.mapper.AccountUIMapper
import quo.yandex.financialawareness.transactions.impl.ui.mapper.CategoryUIMapper
import quo.yandex.financialawareness.transactions.impl.ui.model.TransactionUIModel
import quo.yandex.financialawareness.transactions.impl.ui.screen.create.state.TransactionCreateEffect
import quo.yandex.financialawareness.transactions.impl.ui.screen.create.state.TransactionCreateEvent
import quo.yandex.financialawareness.transactions.impl.ui.screen.create.state.TransactionCreateState
import quo.yandex.financialawareness.ui.base.BaseViewModel
import quo.yandex.financialawareness.util.DateHelper
import quo.yandex.financialawareness.util.result.FailureReason
import quo.yandex.financialawareness.util.result.Result
import javax.inject.Inject

@HiltViewModel
class TransactionCreateViewModel @Inject constructor(
    private val createTransactionUseCase: CreateTransactionUseCase,
    private val getAccountUseCase: GetAccountUseCase,
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val categoryUIMapper: CategoryUIMapper,
    private val accountUIMapper: AccountUIMapper,
    private val resourceProvider: ResourceProvider
) : BaseViewModel<TransactionCreateState, TransactionCreateEvent, TransactionCreateEffect>() {

    override val _state = MutableStateFlow(TransactionCreateState())
    override val state: StateFlow<TransactionCreateState> = _state.asStateFlow()

    override val _effect = Channel<TransactionCreateEffect>(Channel.UNLIMITED)
    override val effect = _effect.receiveAsFlow()

    private var loadAccountJob: Job? = null
    private var loadCategoriesJob: Job? = null
    private var createTransactionJob: Job? = null

    override fun reduce(event: TransactionCreateEvent) {
        when (event) {
            is TransactionCreateEvent.HideErrorDialog ->
                _state.update {
                    it.copy(
                        showErrorDialog = null
                    )
                }
            is TransactionCreateEvent.LoadData -> {
                _state.update {
                    it.copy(
                        showErrorDialog = null
                    )
                }
                loadCategories(event.transactionType)
                loadAccount()
            }

            is TransactionCreateEvent.HideCategoryBottomSheet ->
                _state.update {
                    it.copy(
                        showBottomSheet = false
                    )
                }
            is TransactionCreateEvent.HideDatePicker -> {
                _state.update {
                    it.copy(
                        showDatePicker = false,
                    )
                }
            }
            is TransactionCreateEvent.HideTimePicker -> {
                _state.update {
                    it.copy(
                        showTimePicker = false,
                    )
                }
            }
            is TransactionCreateEvent.OnDateSelected -> {
                val formattedDate = DateHelper.formatDateForDisplay(event.date) ?: return
                _state.update {
                    it.copy(
                        transaction = it.transaction.copy(
                            date = formattedDate
                        )
                    )
                }
            }
            is TransactionCreateEvent.OnDoneClicked -> createTransaction()
            is TransactionCreateEvent.OnTimeSelected -> {
                val formattedTime = DateHelper.formatTimeForDisplay(event.time) ?: return
                _state.update {
                    it.copy(
                        transaction = it.transaction.copy(
                            time = formattedTime
                        )
                    )
                }
            }
            is TransactionCreateEvent.SelectCategory -> {
                _state.update {
                    it.copy(
                        transaction = it.transaction.copy(
                            category = event.category
                        )
                    )
                }
            }
            is TransactionCreateEvent.ShowCategoryBottomSheet ->
                _state.update {
                    it.copy(
                        showBottomSheet = true
                    )
                }
            is TransactionCreateEvent.ShowDatePicker -> {
                _state.update { it.copy(showDatePicker = true) }
            }
            is TransactionCreateEvent.ShowTimePicker -> {
                _state.update { it.copy(showTimePicker = true) }
            }
            is TransactionCreateEvent.UpdateAmount -> _state.update {
                it.copy(
                    transaction = state.value.transaction.copy(
                        amount = event.amount
                    )
                )
            }
            is TransactionCreateEvent.UpdateComment -> _state.update {
                it.copy(
                    transaction = state.value.transaction.copy(
                        comment = event.comment
                    )
                )
            }
        }
    }

    private fun createTransaction() {
        createTransactionJob?.cancel()

        validateInputs()?.let { errorMessage ->
            sendEffect(TransactionCreateEffect.ShowToast(errorMessage))
            return
        }

        createTransactionJob = launchTask {
            _state.update { it.copy(isLoading = true) }
            delay(1000)

            when (val result = createTransactionUseCase(
                accountId = state.value.transaction.account.id,
                categoryId = state.value.transaction.category.id,
                amount = state.value.transaction.amount.let {
                    it.ifEmpty { "0" }
                },
                transactionDate = DateHelper.parseDisplayDateAndTime(
                    state.value.transaction.date,
                    state.value.transaction.time,
                ),
                comment = state.value.transaction.comment,
            )) {
                is Result.Success -> {
                    sendEffect(TransactionCreateEffect.ShowToast(
                        resourceProvider.getString(
                            R.string.transaction_created_successfully
                        )))
                    sendEffect(TransactionCreateEffect.TransactionCreated)
                    _state.update {
                        it.copy(
                            isLoading = false,
                        )
                    }
                }
                is Result.Failure -> {
                    handleFailure(result.reason)
                }
            }
        }
    }

    private fun validateInputs() : String? {
        return when {
            state.value.transaction.account.id == 0
                -> resourceProvider.getString(R.string.error_empty_account)
            state.value.transaction.category.id == 0
                -> resourceProvider.getString(R.string.error_empty_category)
            else -> null
        }
    }

    private fun loadCategories(transactionType: TransactionType) {
        loadCategoriesJob?.cancel()

        loadCategoriesJob = launchTask {
            _state.update { it.copy(isLoading = true) }
            delay(1000)

            when (val result = getCategoriesUseCase(transactionType)) {
                is Result.Success -> {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            categories = categoryUIMapper.mapList(result.data)
                        )
                    }
                }
                is Result.Failure -> {
                    handleFailure(result.reason)
                }
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
                            transaction = it.transaction.copy(
                                account = accountUIMapper.map(result.data)
                            )
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
            is FailureReason.BadRequest -> resourceProvider.getString(R.string.failure_bad_request)
            else -> resourceProvider.getString(R.string.failure_unknown)
        }
        _state.update {
            it.copy(
                isLoading = false,
                transaction = TransactionUIModel(),
                showErrorDialog = message
            )
        }
    }
}