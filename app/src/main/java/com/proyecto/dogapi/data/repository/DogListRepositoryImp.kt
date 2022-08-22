package com.proyecto.dogapi.data.repository

import com.proyecto.dogapi.data.remote.RaceApi
import com.proyecto.dogapi.data.repository.mapper.ImageDogEntityToDomainMapper
import com.proyecto.dogapi.data.repository.mapper.RaceEntityToDomainMapper
import com.proyecto.dogapi.domain.model.ImageDog
import com.proyecto.dogapi.domain.model.Race
import io.reactivex.Observable
import java.lang.Exception

class DogListRepositoryImp (
    private val api: RaceApi,
    private val mapper: RaceEntityToDomainMapper,
    private val mapperImageDog: ImageDogEntityToDomainMapper
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
}