package com.proyecto.dogapi.domain.usecase

import com.proyecto.dogapi.data.repository.DogListRepository
import com.proyecto.dogapi.domain.model.SubBreed
import com.proyecto.dogapi.utils.UseCase
import io.reactivex.Observable
import javax.inject.Inject

class GetSubBreedListUseCase @Inject constructor(private val repository: DogListRepository):
    UseCase<SubBreed>(){
    private lateinit var race_name: String

    fun setData(race_name: String): GetSubBreedListUseCase{
        this.race_name = race_name
        return this
    }

    override fun createObservableUseCase(): Observable<SubBreed> {
        return repository.getSubBreed(race_name)
    }
}