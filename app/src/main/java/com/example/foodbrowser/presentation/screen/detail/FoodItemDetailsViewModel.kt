package com.example.foodbrowser.presentation.screen.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.foodbrowser.app.SchedulersProvider
import com.example.foodbrowser.domain.entity.ExtendedFoodItem
import com.example.foodbrowser.domain.fooditemdetails.GetFoodItemDetailsUseCase
import com.example.foodbrowser.utils.rx.plus
import io.reactivex.disposables.CompositeDisposable

class FoodItemDetailsViewModel(
    private val itemId: String,
    private val schedulersProvider: SchedulersProvider,
    private val foodItemDetailsUseCase: GetFoodItemDetailsUseCase
) : ViewModel() {

    //All stuff related to the State and disposables should be generic and stored in BaseViewModel
    //I broke DRY principle in this project just for saving time
    sealed class State {
        data object Loading : State()

        data class Result(val item: ExtendedFoodItem) : State()

        data class Error(val throwable: Throwable) : State()
    }

    val state: LiveData<State> get() = _state

    private val _state: MutableLiveData<State> = MutableLiveData(State.Loading)

    private val disposables: CompositeDisposable = CompositeDisposable()

    init {
        loadFoodDetailsInfo()
    }

    private fun loadFoodDetailsInfo() {
        disposables + foodItemDetailsUseCase.getFoodItemDetails(itemId)
            .observeOn(schedulersProvider.main)
            .subscribe(
                { foodItem -> _state.value = State.Result(foodItem) },
                { error -> _state.value = State.Error(error) }
            )
    }

    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }

}