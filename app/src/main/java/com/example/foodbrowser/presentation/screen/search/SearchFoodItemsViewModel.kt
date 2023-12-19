package com.example.foodbrowser.presentation.screen.search

import androidx.annotation.StringRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.foodbrowser.R
import com.example.foodbrowser.app.SchedulersProvider
import com.example.foodbrowser.domain.entity.SimpleFoodItem
import com.example.foodbrowser.domain.fooditems.SearchSimpleFoodItemsUseCase
import com.example.foodbrowser.utils.rx.cleanAndAdd
import com.example.foodbrowser.utils.rx.disposeAllAndClear
import io.reactivex.Single
import io.reactivex.disposables.Disposable

private const val MIN_SEARCH_SYMBOLS_AMOUNT = 3

/**
 * In real project view model should be generic and has base view model class with utils methods.
 * In this example it omitted for reducing amount of code
 */
class SearchFoodItemsViewModel(
    private val schedulersProvider: SchedulersProvider,
    private val searchSimpleFoodItemsUseCase: SearchSimpleFoodItemsUseCase
) : ViewModel() {

    sealed class State {
        data class Result(val query: String, val items: List<SimpleFoodItem>) : State()
        data class Error(val exception: Throwable) : State()
        data class Message(@StringRes val stringId: Int) : State()
        data object Loading : State()
    }

    val state: LiveData<State> get() = _state

    private val _state: MutableLiveData<State> = MutableLiveData(emptyState)

    private val disposables: MutableList<Disposable> = mutableListOf()

    fun search(query: String) {
        println("QUERY = $query")
        doIfQueryValid(query) { validQuery ->
            disposables cleanAndAdd searchSimpleFoodItemsUseCase.search(validQuery)
                .withLoading()
                .observeOn(schedulersProvider.main)
                .subscribe(
                    { items -> _state.value = State.Result(validQuery, items) },
                    { error -> _state.value = State.Error(error) }
                )
        }
    }

    override fun onCleared() {
        super.onCleared()
        disposables.disposeAllAndClear()
    }

    private fun doIfQueryValid(query: String, action: (String) -> Unit) {
        //other validation logic can be easy added here, or moved to separate validation class for reusing
        when {
            query.isEmpty() ->  _state.value = emptyState
            query.length < MIN_SEARCH_SYMBOLS_AMOUNT -> _state.value = State.Result(query = query, emptyList())
            else -> action(query)
        }
    }

    private fun <T> Single<T>.withLoading(): Single<T> =
        this.doOnSubscribe { _state.postValue(State.Loading) }
}

private val emptyState: SearchFoodItemsViewModel.State
    get() = SearchFoodItemsViewModel.State.Message(stringId = R.string.message_enter_at_least_3_signs)
