package com.proyecto.dogapi.presentation.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.proyecto.dogapi.domain.model.ImageDog
import com.proyecto.dogapi.domain.usecase.GetImageDogListUseCase
import com.proyecto.dogapi.utils.*
import javax.inject.Inject

class DogListDetailsViewModel @Inject constructor(private val getImageDogListUseCase: GetImageDogListUseCase):
    ViewModel(){
        var liveData: MutableLiveData<Resource<List<String>>> = MutableLiveData()
        private lateinit var auxListImageDog: ArrayList<String>
        private var countStart: Int=0

        fun getImageRace(race_name: String){
            liveData.post(status = ResourceState.LOADING)
            getImageDogListUseCase.setData(race_name).execute(object : UseCaseObserver<ImageDog>(){
                override fun onNext(value: ImageDog) {
                    auxListImageDog = ArrayList()
                    auxListImageDog.addAll(value.message)
                    loadImageList()
                }

                override fun onError(e: Throwable) {
                    liveData.post(status = ResourceState.ERROR, failure = Failure.Error(
                        "Ha ocurrido un error inesperado"))
                }
            })
        }

    fun loadImageList(){
        if(countStart<auxListImageDog.size){
            var countEnd = countStart+10

            if (countEnd>auxListImageDog.size){
                countEnd = countStart+(auxListImageDog.size-countStart)
            }
            liveData.post(status = ResourceState.SUCCESS, data = auxListImageDog.subList(countStart, countEnd))
            countStart = countEnd
        }
    }

    override fun onCleared() {
        getImageDogListUseCase.dispose()
    }
}