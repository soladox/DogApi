package com.proyecto.dogapi.data.remote

import com.proyecto.dogapi.data.entity.ImageDogEntity
import com.proyecto.dogapi.data.entity.RaceEntity
import retrofit2.http.GET
import retrofit2.http.Path
import io.reactivex.Observable

interface RaceApi {
    @GET("breeds/list")
    fun getRace(): Observable<RaceEntity>

    @GET("breed/{race_name}/images")
    fun getImageDog(@Path("race_name") race_name:String): Observable<ImageDogEntity>
}