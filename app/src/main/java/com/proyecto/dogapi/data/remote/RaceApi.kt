package com.proyecto.dogapi.data.remote

import com.proyecto.dogapi.data.entity.*
import retrofit2.http.GET
import retrofit2.http.Path
import io.reactivex.Observable

interface RaceApi {
    @GET("breeds/list")
    fun getRace(): Observable<RaceEntity>

    @GET("breed/{race_name}/images")
    fun getImageDog(@Path("race_name") race_name:String): Observable<ImageDogEntity>

    @GET("breed/{race_name}/list")
    fun getSubBreed(@Path("race_name") race_name:String): Observable<SubBreedEntity>

    @GET("breed/{race_name}/{sub_race}/images")
    fun getSubBreedImage(@Path("race_name") race_name:String, @Path("sub_race") sub_race:String): Observable<SubBreedImageEntity>

    @GET("breed/{race_name}/images")
    fun getNewImageDog(@Path("race_name") race_name:String): Observable<NewImageDogEntity>
}