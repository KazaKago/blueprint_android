package com.kazakago.cleanarchitecture.data.mapper

import com.kazakago.cleanarchitecture.data.entity.city.PrefEntity
import com.kazakago.cleanarchitecture.domain.model.city.CityModel

/**
 * Created by tamura_k on 2016/12/08.
 */
object CityMapper {

    fun execute(entity: PrefEntity): List<CityModel> {
        val prefTitle = entity.title
        return entity.cityList.map {
            val city = CityModel()
            city.id = it.id
            city.name = prefTitle + " " + it.title
            city
        }
    }

}
