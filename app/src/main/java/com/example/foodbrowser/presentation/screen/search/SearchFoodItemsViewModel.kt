package com.example.foodbrowser.presentation.screen.search

import androidx.annotation.StringRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.foodbrowser.R
import com.example.foodbrowser.app.SchedulersProvider
import com.example.foodbrowser.domain.entity.SimpleFoodItem
import com.example.foodbrowser.domain.fooditems.SearchSimpleFoodItemsUseCase
import com.example.foodbrowser.utils.rx.plus
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.BehaviorSubject
import java.util.concurrent.TimeUnit

private const val MIN_SEARCH_SYMBOLS_AMOUNT = 3
private const val SEARCH_DELAY_MS = 300L

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

    private val disposables: CompositeDisposable = CompositeDisposable()

    private val searchSubject = BehaviorSubject.create<String>()

    init {
        observeSearchSubject()
    }

    fun search(query: String) {
        searchSubject.onNext(query)
    }

    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }

    private fun observeSearchSubject() {
        disposables + searchSubject
            .map { query -> query.trim() }
            .distinctUntilChanged()
            .withLoading()
            .debounce(SEARCH_DELAY_MS, TimeUnit.MILLISECONDS)
            .switchMapSingle { query ->
                if (query.length < MIN_SEARCH_SYMBOLS_AMOUNT) {
                    Single.just(query to emptyList())
                } else {
                    searchSimpleFoodItemsUseCase.search(query).map { searched -> query to searched }
                }
            }
            .observeOn(schedulersProvider.main)
            .subscribe(
                { (query, items) -> processQuery(query, items) },
                { error -> _state.value = State.Error(error) }
            )
    }

    private fun processQuery(query: String, items: List<SimpleFoodItem>) {
        //other validation logic can be easy added here, or moved to separate validation class for reusing
        when {
            query.isEmpty() -> _state.value = emptyState
            query.length < MIN_SEARCH_SYMBOLS_AMOUNT -> _state.value =
                State.Result(query = query, emptyList())

            else -> _state.value = State.Result(query, items)
        }
    }

    private fun <T> Observable<T>.withLoading(): Observable<T> =
        this.doOnNext { _state.postValue(State.Loading) }
}

private val emptyState: SearchFoodItemsViewModel.State
    get() = SearchFoodItemsViewModel.State.Message(stringId = R.string.message_enter_at_least_3_signs)
