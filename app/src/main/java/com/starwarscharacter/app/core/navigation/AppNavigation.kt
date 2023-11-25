package com.starwarscharacter.app.core.navigation


import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.starwarscharacter.app.features.character.presentation.CharacterScreen
import com.starwarscharacter.app.features.planet.presentation.PlanetsScreen
import com.starwarscharacter.app.features.starships.presentation.StarShipsScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavigation(){
    val navController : NavHostController = rememberNavController()

    Scaffold(
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination

                listOfNavItems.forEach {navItem ->

                NavigationBarItem(
                    selected = currentDestination?.hierarchy?.any{ it.route == navItem.route} == true,
                    onClick = {
                              navController.navigate(navItem.route){
                                  popUpTo(navController.graph.findStartDestination().id){
                                      saveState = true
                                  }
                                  launchSingleTop = true
                                  restoreState = true

                              }
                    },
                    icon = {
                           Icon(
                               imageVector = navItem.icon,
                               contentDescription = null
                           )
                    },
                    label = {
                        Text(text = navItem.label)
                    }
                    )


                }
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Screens.CharacterScreen.name,
            modifier = Modifier.padding(paddingValues)
            ){
                composable(route = Screens.CharacterScreen.name){
                    CharacterScreen()
                }
            composable(route = Screens.StarShipsScreen.name){
                StarShipsScreen()
            }
            composable(route = Screens.PlanetsScreen.name){
                PlanetsScreen()
            }
        }
    }
}

