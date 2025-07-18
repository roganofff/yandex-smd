package quo.yandex.financialawareness.categories.impl.ui

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import quo.yandex.financialawareness.categories.impl.domain.usecase.GetUsedCategoriesUseCase
import quo.yandex.financialawareness.categories.impl.ui.state.CategoriesEffect
import quo.yandex.financialawareness.categories.impl.ui.state.CategoriesEvent
import quo.yandex.financialawareness.categories.impl.ui.state.CategoriesState
import quo.yandex.financialawareness.domain.provider.ResourceProvider
import quo.yandex.financialawareness.categories.impl.ui.mapper.CategoryDetailsUIMapper
import quo.yandex.financialawareness.transactions.api.model.TransactionType
import quo.yandex.financialawareness.util.result.FailureReason
import quo.yandex.financialawareness.util.result.Result
import quo.yandex.financialawareness.ui.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor(
    private val getUsedCategoriesUseCase: GetUsedCategoriesUseCase,
    private val categoryDetailsUIMapper: CategoryDetailsUIMapper,
    private val resourceProvider: ResourceProvider
) : BaseViewModel<CategoriesState, CategoriesEvent, CategoriesEffect>() {

    override val _state = MutableStateFlow(CategoriesState())
    override val state: StateFlow<CategoriesState> = _state.asStateFlow()

    override val _effect = Channel<CategoriesEffect>(Channel.UNLIMITED)
    override val effect = _effect.receiveAsFlow()

    private var loadCategoriesJob: Job? = null

    override fun reduce(event: CategoriesEvent) {
        when (event) {
            is CategoriesEvent.OnInputChanged -> {
                _state.update {
                    it.copy(
                        input = event.input,
                    )
                }
                loadExpenseCategories()
            }
            is CategoriesEvent.OnSearch -> {
                loadExpenseCategories()
            }
            CategoriesEvent.HideErrorDialog ->
                _state.update {
                    it.copy(
                        showErrorDialog = null
                    )
                }
            CategoriesEvent.LoadCategories -> {
                _state.update {
                    it.copy(
                        showErrorDialog = null
                    )
                }
                loadExpenseCategories()
            }
        }
    }

    private fun loadExpenseCategories() {
        loadCategoriesJob?.cancel()

        loadCategoriesJob = launchTask {
            _state.update { it.copy(isLoading = true) }
            delay(1000)

            when (val result = getUsedCategoriesUseCase(
                transactionType = TransactionType.EXPENSE,
                query = state.value.input
            )) {
                is Result.Success -> {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            categories = categoryDetailsUIMapper.mapList(result.data)
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
            is FailureReason.Network -> resourceProvider.getString(quo.yandex.financialawareness.ui.R.string.failure_network)
            is FailureReason.Unauthorized -> resourceProvider.getString(quo.yandex.financialawareness.ui.R.string.failure_unauthorized)
            is FailureReason.Server -> resourceProvider.getString(quo.yandex.financialawareness.ui.R.string.failure_server)
            is FailureReason.NotFound -> resourceProvider.getString(quo.yandex.financialawareness.ui.R.string.failure_not_found)
            else -> resourceProvider.getString(quo.yandex.financialawareness.ui.R.string.failure_unknown)
        }
        _state.update {
            it.copy(
                isLoading = false,
                categories = emptyList(),
                showErrorDialog = message
            )
        }
    }
}