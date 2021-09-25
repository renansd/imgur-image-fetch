package com.example.network

import io.reactivex.Observable
import io.reactivex.disposables.Disposable

abstract class NetworkInteractor<T> {

    protected fun makeRequest(): Disposable =
        getRequestObservable().subscribe({ this.setResult(it) }, { this.handleError(it) })

    private fun setResult(result: T) {
        onResult(result)
    }

    private fun handleError(throwable: Throwable) {
        onError(throwable)
    }

    abstract fun getRequestObservable(): Observable<T>

    abstract fun onResult(result: T)

    abstract fun onError(throwable: Throwable)
}