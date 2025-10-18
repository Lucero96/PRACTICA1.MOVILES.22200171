package dev.layala.practica1moviles22200171.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.layala.practica1moviles22200171.presentation.menu.MenuScreen
import dev.layala.practica1moviles22200171.presentation.agua.AguaScreen
import dev.layala.practica1moviles22200171.presentation.actividad.ActividadScreen
import dev.layala.practica1moviles22200171.presentation.autos.AutosScreen

@Composable
fun AppNavGraph() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.MENU
    ) {
        composable(Routes.MENU) { MenuScreen(navController) }
        composable(Routes.AGUA) { AguaScreen(navController) }
        composable(Routes.ACTIVIDAD) { ActividadScreen(navController) }
        composable(Routes.AUTOS) { AutosScreen(navController) }
    }
}
