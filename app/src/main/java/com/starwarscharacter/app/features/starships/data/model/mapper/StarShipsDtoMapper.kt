package com.starwarscharacter.app.features.starships.data.model.mapper

import com.starwarscharacter.app.features.starships.data.model.dto.StarShipsResult
import com.starwarscharacter.app.features.starships.domain.entity.StarShips
import java.util.UUID

fun StarShipsResult.mapFromEntity() = StarShips(
    id = UUID.randomUUID().toString(),
    birthYear = this.birthYear,
    eyeColor = this.eyeColor,
    gender = this.gender,
    hairColor = this.hairColor,
    height = this.height,
    name = this.name,
    skinColor = this.skinColor
)

fun List<StarShipsResult>.mapStarShipsFromListModel(): List<StarShips>{
    return this.map{
        it.mapFromEntity()
    }
}