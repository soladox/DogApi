package com.proyecto.dogapi.data.repository.mapper

import com.proyecto.dogapi.data.entity.ImageDogEntity
import com.proyecto.dogapi.domain.model.ImageDog
import com.proyecto.dogapi.utils.Mapper
import javax.inject.Inject

class ImageDogEntityToDomainMapper @Inject constructor(): Mapper<ImageDogEntity, ImageDog>(){

    override fun map(value: ImageDogEntity?): ImageDog? {
        value?.let {
            return ImageDog(it.message, it.status)
        }
        return null
    }

    override fun reverseMap(value: ImageDog?): ImageDogEntity? {
        value?.let {
            return ImageDogEntity(it.message, it.status)
        }
        return null
    }
}