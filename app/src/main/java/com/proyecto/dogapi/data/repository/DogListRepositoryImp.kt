package com.proyecto.dogapi.data.repository

import com.proyecto.dogapi.data.remote.RaceApi
import com.proyecto.dogapi.data.repository.mapper.*
import com.proyecto.dogapi.domain.model.*
import io.reactivex.Observable
import java.lang.Exception

class DogListRepositoryImp (
    private val api: RaceApi,
    private val mapper: RaceEntityToDomainMapper,
    private val mapperImageDog: ImageDogEntityToDomainMapper,
    private val mapperNewImageDog: NewImageDogEntityToDomainMapper,
    private val mapperSubBreed: SubBreedEntityToDomainMapper,
    private val mapperSubBreedImage: SubBreedImageEntityToDomainMapper
): DogListRepository{
    override fun getRace(): Observable<Race> {
        return api.getRace().map {
            response ->
                if(!response.status.equals("success")) {
                    throw Exception("Error")
                }
            mapper.map(response)
        }
    }

    override fun getImageDog(race_name: String): Observable<ImageDog> {
        return api.getImageDog(race_name).map {
            response ->
                if(!response.status.equals("success")){
                    throw Exception("error")
                }
            mapperImageDog.map(response)
        }
    }

    override fun getNewImageDog(race_name: String): Observable<NewImageDog> {
        return api.getNewImageDog(race_name).map{
                response ->
            if(!response.status.equals("success")){
                throw Exception("error")
            }
            mapperNewImageDog.map(response)
        }
    }

    override fun getSubBreed(race_name: String): Observable<SubBreed> {
        return api.getSubBreed(race_name).map{
                response ->
            if(!response.status.equals("success")){
                throw Exception("error")
            }
            mapperSubBreed.map(response)
        }
    }

    override fun getSubBreedImage(race_name: String, sub_race: String): Observable<SubBreedImage> {
        return api.getSubBreedImage(race_name, sub_race).map{
                response ->
            if(!response.status.equals("success")){
                throw Exception("error")
            }
            mapperSubBreedImage.map(response)
        }
    }
}