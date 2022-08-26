package com.proyecto.dogapi.di.module

import com.proyecto.dogapi.data.remote.RaceApi
import com.proyecto.dogapi.data.repository.DogListRepository
import com.proyecto.dogapi.data.repository.DogListRepositoryImp
import com.proyecto.dogapi.data.repository.mapper.ImageDogEntityToDomainMapper
import com.proyecto.dogapi.data.repository.mapper.NewImageDogEntityToDomainMapper
import com.proyecto.dogapi.data.repository.mapper.RaceEntityToDomainMapper
import com.proyecto.dogapi.utils.ApiServiceFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient

@Module
class DogListRepositoryModule {
    @Provides

    fun provideRepository(
        api: RaceApi, mapper: RaceEntityToDomainMapper, mapperImageDog:ImageDogEntityToDomainMapper, mapperNewImageDog: NewImageDogEntityToDomainMapper):
            DogListRepository{
        return DogListRepositoryImp(api, mapper, mapperImageDog, mapperNewImageDog)
    }

    @Provides

    fun provideApiService(): RaceApi{
        val okHttpClient = OkHttpClient()
        return ApiServiceFactory.build(okHttpClient,RaceApi::class.java,"https://dog.ceo/api/")
    }
}