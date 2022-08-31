package com.proyecto.dogapi.di.module

import com.proyecto.dogapi.data.remote.RaceApi
import com.proyecto.dogapi.data.repository.DogListRepository
import com.proyecto.dogapi.data.repository.DogListRepositoryImp
import com.proyecto.dogapi.data.repository.mapper.*
import com.proyecto.dogapi.utils.ApiServiceFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient

@Module
class DogListRepositoryModule {
    @Provides

    fun provideRepository(
        api: RaceApi, mapper: RaceEntityToDomainMapper, mapperImageDog:ImageDogEntityToDomainMapper, mapperNewImageDog: NewImageDogEntityToDomainMapper, mapperSubBreed: SubBreedEntityToDomainMapper, mapperSubBreedImage: SubBreedImageEntityToDomainMapper):
            DogListRepository{
        return DogListRepositoryImp(api, mapper, mapperImageDog, mapperNewImageDog, mapperSubBreed, mapperSubBreedImage)
    }

    @Provides

    fun provideApiService(): RaceApi{
        val okHttpClient = OkHttpClient()
        return ApiServiceFactory.build(okHttpClient,RaceApi::class.java,"https://dog.ceo/api/")
    }
}