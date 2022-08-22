package com.proyecto.dogapi.domain.usecase

import com.proyecto.dogapi.data.repository.DogListRepository
import com.proyecto.dogapi.domain.model.Race
import com.proyecto.dogapi.utils.UseCase
import io.reactivex.Observable
import javax.inject.Inject

class GetDogListUseCase @Inject constructor(private val dogListRepository: DogListRepository):
    UseCase<Race>(){
    override fun createObservableUseCase(): Observable<Race> {
        return dogListRepository.getRace()
    }
}