package com.example.foodbrowser.presentation.screen.detail

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.foodbrowser.R
import com.example.foodbrowser.domain.entity.ExtendedFoodItem
import com.example.foodbrowser.presentation.uicommon.DefaultLoadingState

@Composable
fun FoodItemDetailsView(
    modifier: Modifier = Modifier,
    state: FoodItemDetailsViewModel.State,
    onCloseScreen: () -> Unit
) {
    when (state) {
        FoodItemDetailsViewModel.State.Loading -> DefaultLoadingState()
        is FoodItemDetailsViewModel.State.Result -> FoodItemDetailsResultView(
            modifier = modifier,
            item = state.item,
            onCloseScreen = onCloseScreen
        )

        is FoodItemDetailsViewModel.State.Error -> FoodItemDetailsErrorView(
            modifier = modifier,
            error = state.throwable,
            onCloseScreen = onCloseScreen
        )
    }
}

@Composable
fun FoodItemDetailsResultView(
    modifier: Modifier, item: ExtendedFoodItem, onCloseScreen: () -> Unit
) {
    AlertDialog(
        modifier = modifier,
        title = { Text(text = item.name) },
        text = { Text(text = item.brand) },
        onDismissRequest = { onCloseScreen() },
        confirmButton = {
            TextButton(onClick = { onCloseScreen() }) {
                Text(text = stringResource(id = R.string.dialog_confirm))
            }
        },
    )
}

@Composable
fun FoodItemDetailsErrorView(
    modifier: Modifier,
    error: Throwable,
    onCloseScreen: () -> Unit
) {
    AlertDialog(
        modifier = modifier,
        title = { Text(text = stringResource(id = R.string.title_dialog_error)) },
        text = { Text(text = error.message.orEmpty()) },
        onDismissRequest = { onCloseScreen() },
        confirmButton = {
            TextButton(onClick = { onCloseScreen() }) {
                Text(text = stringResource(id = R.string.dialog_ok))
            }
        },
    )
}