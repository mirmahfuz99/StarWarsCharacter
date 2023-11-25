package com.starwarscharacter.app.features.planet.data.model.dto


import com.google.gson.annotations.SerializedName

data class PlanetsResult(
    @SerializedName("name")
    val name: String?,
    @SerializedName("rotation_period")
    val rotationPeriod: String?,
    @SerializedName("orbital_period")
    val orbitalPeriod: String?,
    @SerializedName("diameter")
    val diameter: String?,
    @SerializedName("climate")
    val climate: String?,
    @SerializedName("gravity")
    val gravity: String?,
    @SerializedName("terrain")
    val terrain: String?,
    @SerializedName("surface_water")
    val surfaceWater: String?,
    @SerializedName("population")
    val population: String?,
    @SerializedName("residents")
    val residents: ArrayList<String>,
    @SerializedName("films")
    val films: ArrayList<String>,
    @SerializedName("created")
    val created: String?,
    @SerializedName("edited")
    val edited: String?,
    @SerializedName("url")
    val url: String?,
)