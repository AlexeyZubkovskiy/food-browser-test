package com.example.foodbrowser.utils.rx

import io.reactivex.disposables.Disposable

operator fun MutableList<Disposable>.plus(disposable: Disposable) = this.add(disposable)

fun MutableList<Disposable>.disposeAllAndClear() {
    forEach { oldDisposable ->
        if (!oldDisposable.isDisposed) {
            oldDisposable.dispose()
        }
    }
    clear()
}

infix fun MutableList<Disposable>.cleanAndAdd(disposable: Disposable) {
    disposeAllAndClear()
    add(disposable)
}

