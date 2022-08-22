package com.proyecto.dogapi.utils

import io.reactivex.observers.DisposableObserver

abstract class UseCaseObserver<T>:DisposableObserver<T>() {
    override fun onNext(value: T){}

    override fun onError(e:Throwable){}

    override  fun onComplete(){}
}