package com.starwarscharacter.app.features.starships.data.model.dto


import com.google.gson.annotations.SerializedName

data class StarShipsResult(
    @SerializedName("name")
    val name: String,
    @SerializedName("model")
    val model: String,
    @SerializedName("manufacturer")
    val manufacturer: String,
    @SerializedName("cost_in_credits")
    val costInCredits: String,
    @SerializedName("length")
    val length: List<String>,
    @SerializedName("crew")
    val crew: String,
    @SerializedName("passengers")
    val passengers: String,
    @SerializedName("cargo_capacity")
    val cargoCapacity: String,
    @SerializedName("starship_class")
    val starshipClass: String,

)