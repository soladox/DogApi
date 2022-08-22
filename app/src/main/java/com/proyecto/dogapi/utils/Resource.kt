package com.proyecto.dogapi.utils

import androidx.lifecycle.MutableLiveData

enum class ResourceState{
    LOADING,
    SUCCESS,
    ERROR
}

open class Resource<out T> constructor(val status: ResourceState, val data: T?, val failure: Failure?)

@Suppress("UNCHECKED_CAST")

fun <T> MutableLiveData<T>.post(status: ResourceState, data: Any? = null, failure: Failure? = null){
    this.postValue(Resource(status, data, failure)as T)
}

sealed class Failure{
    data class  Error(val errorMessage: String): Failure()
    object NetworkConnection: Failure()
    object ServerError: Failure()

    abstract class FeatureFailure: Failure()
}