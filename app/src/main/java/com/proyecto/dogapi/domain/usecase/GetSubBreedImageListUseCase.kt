package com.proyecto.dogapi.domain.usecase

import com.proyecto.dogapi.data.repository.DogListRepository
import com.proyecto.dogapi.domain.model.SubBreedImage
import com.proyecto.dogapi.utils.UseCase
import io.reactivex.Observable
import javax.inject.Inject

class GetSubBreedImageListUseCase @Inject constructor(private val repository: DogListRepository):
    UseCase<SubBreedImage>(){
    private lateinit var race_name: String
    private lateinit var sub_breed: String

    fun setData(race_name: String, sub_breed: String): GetSubBreedImageListUseCase{
        this.race_name = race_name
        this.sub_breed = sub_breed
        return this
    }

    override fun createObservableUseCase(): Observable<SubBreedImage> {
        return repository.getSubBreedImage(race_name,sub_breed)
    }
}