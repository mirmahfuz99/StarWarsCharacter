package com.starwarscharacter.app.core.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.ui.graphics.vector.ImageVector

data class NavItem(
    val label: String,
    val icon: ImageVector,
    val route: String,
)

val listOfNavItems: List<NavItem> = listOf(
    NavItem(
        label = "Character",
        icon = Icons.Default.Person,
        route = Screens.CharacterScreen.name
    ),

    NavItem(
        label = "StarShips",
        icon = Icons.Default.Star,
        route = Screens.StarShipsScreen.name
    ),

    NavItem(
        label = "Planets",
        icon = Icons.Default.ThumbUp,
        route = Screens.PlanetsScreen.name
    ),
)