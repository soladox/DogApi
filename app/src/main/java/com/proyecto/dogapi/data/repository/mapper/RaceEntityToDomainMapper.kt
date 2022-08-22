package com.proyecto.dogapi.data.repository.mapper

import com.proyecto.dogapi.data.entity.RaceEntity
import com.proyecto.dogapi.domain.model.Race
import com.proyecto.dogapi.utils.Mapper
import javax.inject.Inject

class RaceEntityToDomainMapper @Inject constructor(): Mapper<RaceEntity, Race>() {

    override fun map(value: RaceEntity?): Race? {
        value?.let {
            return Race(it.status, it.message)
        }
        return null
    }

    override fun reverseMap(value: Race?): RaceEntity? {
        value?.let {
            return RaceEntity(it.status, it.message)
        }
        return null
    }
}