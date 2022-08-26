package com.proyecto.dogapi.data.repository.mapper

import com.proyecto.dogapi.data.entity.NewImageDogEntity
import com.proyecto.dogapi.domain.model.NewImageDog
import com.proyecto.dogapi.utils.Mapper
import javax.inject.Inject

class NewImageDogEntityToDomainMapper @Inject constructor(): Mapper<NewImageDogEntity, NewImageDog>(){

    override fun map(value: NewImageDogEntity?): NewImageDog? {
        value?.let {
            return NewImageDog(it.message, it.status)
        }
        return null
    }

    override fun reverseMap(value: NewImageDog?): NewImageDogEntity? {
        value?.let {
            return NewImageDogEntity(it.message, it.status)
        }
        return null
    }
}