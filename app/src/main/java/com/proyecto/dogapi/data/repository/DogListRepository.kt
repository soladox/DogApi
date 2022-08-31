package com.proyecto.dogapi.data.repository

import com.proyecto.dogapi.domain.model.*
import io.reactivex.Observable

interface DogListRepository {
    fun getRace(): Observable<Race>
    fun getImageDog(race_name: String): Observable<ImageDog>
    fun getNewImageDog(race_name: String): Observable<NewImageDog>
    fun getSubBreed(race_name: String): Observable<SubBreed>
    fun getSubBreedImage(race_name: String, sub_race: String): Observable<SubBreedImage>
}