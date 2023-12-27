package com.example.foodbrowser.presentation.screen.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.foodbrowser.R
import com.example.foodbrowser.domain.entity.SimpleFoodItem
import com.example.foodbrowser.presentation.uicommon.DefaultErrorState
import com.example.foodbrowser.presentation.uicommon.DefaultLoadingState
import com.example.foodbrowser.presentation.uicommon.DefaultMessage
import com.example.foodbrowser.presentation.uicommon.DefaultTextItem


/**
 * For optimisations of search and reducing amount of backend calls here should be implemented
 * mechanism with small delay between sign input and search process initialisation,
 * but I just omitted it for increasing speed
 */
@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun SearchFoodItemsView(
    modifier: Modifier = Modifier,
    state: SearchFoodItemsViewModel.State,
    onQueryChanged: (String) -> Unit,
    onFoodItemClick: (String) -> Unit
) {

    var query by remember { mutableStateOf(selectCorrectInitialState(state)) }

    SearchBar(
        modifier = modifier.padding(4.dp),
        query = query,
        onQueryChange = { newQuery ->
            onQueryChanged(newQuery)
            query = newQuery
        },
        onSearch = {},
        placeholder = {
            Text(text = stringResource(id = R.string.message_enter_at_least_3_signs))
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                tint = MaterialTheme.colorScheme.onSurface,
                contentDescription = null
            )
        },
        trailingIcon = {},
        content = {
            when (state) {

                is SearchFoodItemsViewModel.State.Error -> DefaultErrorState(
                    text = state.exception.message
                        ?: stringResource(id = R.string.error_message_default)
                )

                SearchFoodItemsViewModel.State.Loading -> DefaultLoadingState()

                is SearchFoodItemsViewModel.State.Message -> DefaultMessage(
                    modifier = Modifier.padding(4.dp),
                    text = stringResource(id = state.stringId)
                )

                is SearchFoodItemsViewModel.State.Result -> SearchFoodItemsContent(
                    state = state,
                    onFoodItemClick = onFoodItemClick
                )
            }
        },
        active = true,
        onActiveChange = {},
        tonalElevation = 0.dp,
    )


}

@Composable
fun SearchFoodItemsContent(
    modifier: Modifier = Modifier,
    state: SearchFoodItemsViewModel.State.Result,
    onFoodItemClick: (String) -> Unit
) {
    LazyColumn(modifier = modifier) {
        items(state.items) { item ->
            SearchFoodItemItem(
                modifier = Modifier.fillMaxWidth(),
                item = item,
                onFoodItemClick = onFoodItemClick
            )
        }
    }
}

@Composable
fun SearchFoodItemItem(
    modifier: Modifier = Modifier,
    item: SimpleFoodItem,
    onFoodItemClick: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onFoodItemClick(item.id) },
        verticalAlignment = Alignment.CenterVertically,
    ) {
        DefaultTextItem(text = item.name, scrollable = true, modifier = modifier.padding(4.dp))
    }
}

/**
 * For handling screen rotation
 * */
private fun selectCorrectInitialState(
    state: SearchFoodItemsViewModel.State,
    default: String = ""
): String =
    if ((state is SearchFoodItemsViewModel.State.Result) && state.query.isNotEmpty() && default.isEmpty()) {
        state.query
    } else default