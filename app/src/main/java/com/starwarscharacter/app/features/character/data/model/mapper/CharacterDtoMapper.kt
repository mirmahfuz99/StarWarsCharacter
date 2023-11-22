package com.starwarscharacter.app.features.character.data.model.mapper

import com.starwarscharacter.app.features.character.data.model.dto.CharacterResult
import com.starwarscharacter.app.features.character.domain.entity.Character
import java.util.UUID

fun CharacterResult.mapFromEntity() = Character(
    id = UUID.randomUUID().toString(),
    birthYear = this.birthYear,
    eyeColor = this.eyeColor,
    gender = this.gender,
    hairColor = this.hairColor,
    height = this.height,
    name = this.name,
    skinColor = this.skinColor
)

fun List<CharacterResult>.mapFromListModel(): List<Character>{
    return this.map{
        it.mapFromEntity()
    }
}