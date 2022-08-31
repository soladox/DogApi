package com.proyecto.dogapi.data.repository.mapper

import com.proyecto.dogapi.data.entity.SubBreedImageEntity
import com.proyecto.dogapi.domain.model.SubBreedImage
import com.proyecto.dogapi.utils.Mapper
import javax.inject.Inject

class SubBreedImageEntityToDomainMapper @Inject constructor(): Mapper<SubBreedImageEntity, SubBreedImage>(){

    override fun map(value: SubBreedImageEntity?): SubBreedImage? {
        value?.let {
            return SubBreedImage(it.message, it.status)
        }
        return null
    }

    override fun reverseMap(value: SubBreedImage?): SubBreedImageEntity? {
        value?.let {
            return SubBreedImageEntity(it.message, it.status)
        }
        return null
    }
}