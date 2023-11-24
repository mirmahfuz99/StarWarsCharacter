package com.starwarscharacter.app.features.starships.data.model.dto

import com.google.gson.annotations.SerializedName

data class StarShipsDto(
    @SerializedName("count")
    val count: Int,
    @SerializedName("next")
    val next: String,
    @SerializedName("previous")
    val previous: Any,
    @SerializedName("results")
    val results: List<StarShipsResult>
)
