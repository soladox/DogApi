package com.proyecto.dogapi.presentation.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.proyecto.dogapi.domain.model.Race
import com.proyecto.dogapi.domain.usecase.GetDogListUseCase
import com.proyecto.dogapi.utils.*
import javax.inject.Inject

class DogListViewModel @Inject constructor(private val getDogListUseCase: GetDogListUseCase): ViewModel() {
    var liveData: MutableLiveData<Resource<Race>> = MutableLiveData()

    fun getDogRace(){
        liveData.post(status = ResourceState.LOADING)
        getDogListUseCase.execute(object : UseCaseObserver<Race>(){
            override fun onNext(value: Race) {
                liveData.post(status = ResourceState.SUCCESS, data = value)
            }

            override fun onError(e: Throwable) {
                super.onError(e)
                liveData.post(status = ResourceState.ERROR, failure = Failure.Error(
                    "Ha ocurrido un error inesperado"
                ))
            }
        })
    }

    override fun onCleared() {
        getDogListUseCase.dispose()
    }
}