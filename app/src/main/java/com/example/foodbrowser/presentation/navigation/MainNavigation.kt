package com.example.foodbrowser.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.foodbrowser.presentation.screen.detail.FoodItemDetailsView
import com.example.foodbrowser.presentation.screen.detail.FoodItemDetailsViewModel
import com.example.foodbrowser.presentation.screen.search.SearchFoodItemsView
import com.example.foodbrowser.presentation.screen.search.SearchFoodItemsViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun MainNavigation(navHost: NavHostController, modifier: Modifier) {

    NavHost(
        navController = navHost,
        startDestination = Routes.SearchFoodItems.route,
        modifier = modifier
    ) {

        composable(route = Routes.SearchFoodItems.route) {
            //this solution is not the best, but fast enough for this example
            val viewModel = koinViewModel<SearchFoodItemsViewModel>()
            val observableState = viewModel.state.observeAsState()
            observableState.value?.let { state ->
                SearchFoodItemsView(
                    state = state,
                    onQueryChanged = viewModel::search,
                    onFoodItemClick = { foodItemId ->
                        navHost.navigate("${Routes.FoodItemDetails.route}/$foodItemId")
                    }
                )
            }
        }

        composable(
            route = "${Routes.FoodItemDetails.route}/{${Arg.FOOD_ITEM_ID}}",
            arguments = listOf(navArgument(Arg.FOOD_ITEM_ID) { type = NavType.StringType })
        ) { backStackEntry ->
            val itemId = backStackEntry.arguments?.getString(Arg.FOOD_ITEM_ID)
                ?: throw IllegalArgumentException("ItemId mustn't be null")
            val viewModel = koinViewModel<FoodItemDetailsViewModel> { parametersOf(itemId) }
            val observableState = viewModel.state.observeAsState()
            observableState.value?.let { state ->
                FoodItemDetailsView(
                    state = state,
                    onCloseScreen = { navHost.popBackStack() })
            }
        }

    }
}