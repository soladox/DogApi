package com.proyecto.dogapi.domain.usecase;

import com.proyecto.dogapi.data.repository.DogListRepository
import com.proyecto.dogapi.domain.model.NewImageDog
import com.proyecto.dogapi.utils.UseCase
import io.reactivex.Observable
import javax.inject.Inject;

public class GetNewImageDogListUseCase @Inject constructor(private val repository: DogListRepository):
    UseCase<NewImageDog>() {
    private lateinit var race_name: String

    fun setData(race_name: String): GetNewImageDogListUseCase{
        this.race_name = race_name
        return this
    }

    override fun createObservableUseCase(): Observable<NewImageDog> {
        return repository.getNewImageDog(race_name)
    }
}
