package com.example.foodbrowser.utils.rx

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

operator fun CompositeDisposable.plus(disposable: Disposable) = this.add(disposable)