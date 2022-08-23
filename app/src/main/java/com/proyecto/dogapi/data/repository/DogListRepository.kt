package com.proyecto.dogapi.data.repository

import com.proyecto.dogapi.domain.model.ImageDog
import com.proyecto.dogapi.domain.model.Race
import io.reactivex.Observable

interface DogListRepository {
    fun getRace(): Observable<Race>
    fun getImageDog(race_name: String): Observable<ImageDog>
}