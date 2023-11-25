package com.starwarscharacter.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.starwarscharacter.app.core.navigation.AppNavigation
import com.starwarscharacter.app.features.character.presentation.CharacterScreen
import com.starwarscharacter.app.features.planet.presentation.PlanetsScreen
import com.starwarscharacter.app.features.starships.presentation.StarShipsScreen
import com.starwarscharacter.app.ui.theme.StarWarsCharacterTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StarWarsCharacterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
//                    CharacterScreen()
//                    StarShipsScreen()
//                    PlanetsScreen()
                    AppNavigation()
                }

            }
        }
    }
}