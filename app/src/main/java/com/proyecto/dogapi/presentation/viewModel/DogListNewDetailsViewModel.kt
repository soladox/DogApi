package com.proyecto.dogapi.presentation.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.proyecto.dogapi.domain.model.NewImageDog
import com.proyecto.dogapi.domain.usecase.GetNewImageDogListUseCase
import com.proyecto.dogapi.utils.*
import javax.inject.Inject

class DogListNewDetailsViewModel @Inject constructor(private val getNewImageDogListUseCase: GetNewImageDogListUseCase):ViewModel(){
    var liveData: MutableLiveData<Resource<List<String>>> = MutableLiveData()
    private lateinit var auxNewListImageDog: ArrayList<String>
    private var countStart: Int=0

    fun getImageRace(race_name: String){
        liveData.post(status = ResourceState.LOADING)
        getNewImageDogListUseCase.setData(race_name).execute(object : UseCaseObserver<NewImageDog>(){
            override fun onNext(value: NewImageDog) {
                auxNewListImageDog = ArrayList()
                auxNewListImageDog.addAll(value.message)
                loadImageList()
            }

            override fun onError(e: Throwable) {
                liveData.post(status = ResourceState.ERROR, failure = Failure.Error("Error!"))
            }
        })
    }

    fun loadImageList(){
        if(countStart<auxNewListImageDog.size){
            var countEnd = countStart+10
            if (countEnd>auxNewListImageDog.size){
                countEnd = countStart+(auxNewListImageDog.size-countStart)
            }
            liveData.post(status = ResourceState.SUCCESS, data = auxNewListImageDog.subList(countStart, countEnd))
            countStart = countEnd
        }
    }

    override fun onCleared(){
        getNewImageDogListUseCase.dispose()
    }
}