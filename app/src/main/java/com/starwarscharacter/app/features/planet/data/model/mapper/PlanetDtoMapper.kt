package com.starwarscharacter.app.features.planet.data.model.mapper

import com.starwarscharacter.app.features.planet.data.model.dto.PlanetsResult
import com.starwarscharacter.app.features.planet.domain.entity.Planets
import java.util.UUID

fun PlanetsResult.mapFromEntity() = Planets(
    id = UUID.randomUUID().toString(),
    name = this.name,
    model = this.model,
    manufacturer = this.manufacturer,
    length = this.length,
    crew = this.crew,
    passengers = this.passengers,
    cargoCapacity = this.cargoCapacity,
    starshipClass = this.starshipClass,
)

fun List<PlanetsResult>.mapPlanetsListModel(): List<Planets>{
    return this.map{
        it.mapFromEntity()
    }
}