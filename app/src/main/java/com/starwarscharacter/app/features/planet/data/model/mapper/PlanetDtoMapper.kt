package com.starwarscharacter.app.features.planet.data.model.mapper

import com.starwarscharacter.app.features.planet.data.model.dto.PlanetsResult
import com.starwarscharacter.app.features.planet.domain.entity.Planets
import java.util.UUID

fun PlanetsResult.mapFromEntity() = Planets(
    id = UUID.randomUUID().toString(),
    name = this.name,
    rotationPeriod = this.rotationPeriod,
    orbitalPeriod = this.orbitalPeriod,
    diameter = this.diameter,
    climate = this.climate,
    gravity = this.gravity,
    terrain = this.terrain,
    surfaceWater = this.surfaceWater,
    population = this.population,
)

fun List<PlanetsResult>.mapPlanetsListModel(): List<Planets>{
    return this.map{
        it.mapFromEntity()
    }
}