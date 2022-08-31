package com.proyecto.dogapi.data.repository.mapper

import com.proyecto.dogapi.data.entity.SubBreedEntity
import com.proyecto.dogapi.domain.model.SubBreed
import com.proyecto.dogapi.utils.Mapper
import javax.inject.Inject

class SubBreedEntityToDomainMapper @Inject constructor(): Mapper<SubBreedEntity, SubBreed>(){

    override fun map(value: SubBreedEntity?): SubBreed? {
        value?.let {
            return SubBreed(it.message, it.status)
        }
        return null
    }

    override fun reverseMap(value: SubBreed?): SubBreedEntity? {
        value?.let {
            return SubBreedEntity(it.message, it.status)
        }
        return null
    }
}