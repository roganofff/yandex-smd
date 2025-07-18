package quo.yandex.financialawareness.categories.impl.ui

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import quo.yandex.financialawareness.categories.impl.R
import quo.yandex.financialawareness.categories.impl.ui.component.CategoriesList
import quo.yandex.financialawareness.categories.impl.ui.component.CategoriesShimmerList
import quo.yandex.financialawareness.categories.impl.ui.state.CategoriesEvent
import quo.yandex.financialawareness.ui.component.FAIcon
import quo.yandex.financialawareness.ui.component.FAIconButton
import quo.yandex.financialawareness.ui.component.FATextField
import quo.yandex.financialawareness.ui.component.FACenterAlignedTopAppBar
import quo.yandex.financialawareness.ui.component.FAErrorDialog

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoriesScreen(
    viewModel: CategoriesViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val focusManager = LocalFocusManager.current

    LaunchedEffect(Unit) {
        viewModel.reduce(CategoriesEvent.LoadCategories)
    }

    DisposableEffect(viewModel) {
        onDispose {
            viewModel.cancelAllTasks()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTapGestures(onTap = {
                    focusManager.clearFocus()
                })
            }
    ) {
        FACenterAlignedTopAppBar(
            title = stringResource(R.string.my_categories)
        )

        Box(
            modifier = Modifier.weight(1f)
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                FATextField(
                    value = state.input,
                    onValueChange = { newInput ->
                        viewModel.reduce(CategoriesEvent.OnInputChanged(newInput))
                    },
                    placeholder = stringResource(R.string.find_category),
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Search
                    ),
                    keyboardActions = KeyboardActions(
                        onSearch = {
                            viewModel.reduce(CategoriesEvent.OnSearch)
                            focusManager.clearFocus()
                        }
                    ),
                    trailingIcon = {
                        FAIconButton(
                            onClick = {
                                viewModel.reduce(CategoriesEvent.OnSearch)
                                focusManager.clearFocus()
                            }
                        ) {
                            FAIcon(
                                painter = painterResource(R.drawable.ic_search),
                                contentDescription = stringResource(quo.yandex.financialawareness.ui.R.string.search),
                            )
                        }
                    },
                )

                HorizontalDivider()

                when {
                    state.isLoading -> {
                        CategoriesShimmerList()
                    }
                    else -> {
                        CategoriesList(
                            categories = state.categories,
                            onCategoryClick = { category ->
                                focusManager.clearFocus()
                            }
                        )
                    }
                }
            }
        }
    }

    state.showErrorDialog?.let { message ->
        FAErrorDialog(
            message = message,
            confirmButtonText = stringResource(quo.yandex.financialawareness.ui.R.string.repeat),
            dismissButtonText = stringResource(quo.yandex.financialawareness.ui.R.string.exit),
            onConfirm = {
                viewModel.reduce(CategoriesEvent.LoadCategories)
            },
            onDismiss = {
                viewModel.reduce(CategoriesEvent.HideErrorDialog)
            }
        )
    }
}
