package com.example.clashroyale_api.navigation

/**
 * Project: Clash Royale - API
 * From: com.example.clashroyale_api.navigation
 * Created by: felip
 * On: 18/09/2026
 * All rights reserved: 2026
 */

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.clashroyale_api.ui.screens.CardDetailScreen
import com.example.clashroyale_api.ui.screens.CardsScreen
import com.example.clashroyale_api.ui.screens.HomeScreen
import com.example.clashroyale_api.ui.screens.SplashScreen
import com.example.clashroyale_api.ui.screens.EventsScreen
import com.example.clashroyale_api.viewmodel.CardsViewModel
import com.example.clashroyale_api.viewmodel.EventsViewModel

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    // 1. Creamos los ViewModels AQUÍ para compartirlos
    val sharedViewModel: CardsViewModel = viewModel()
    val eventsViewModel: EventsViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = "splash"
    ) {
        composable("splash") { SplashScreen(navController = navController) }
        composable("home") { HomeScreen(navController = navController) }

        composable("cards") {
            // 2. Le pasamos el ViewModel a la pantalla de la lista
            CardsScreen(navController = navController, viewModel = sharedViewModel)
        }

        // 3. RUTA DE DETALLES: Le decimos al navegador que esta ruta recibirá un número (el ID de la carta)
        composable(
            route = "card_detail/{cardId}",
            arguments = listOf(navArgument("cardId") { type = NavType.IntType })
        ) { backStackEntry ->
            // Extraemos el número de la ruta
            val cardId = backStackEntry.arguments?.getInt("cardId") ?: 0

            // Abrimos la pantalla de detalles
            CardDetailScreen(
                navController = navController,
                viewModel = sharedViewModel,
                cardId = cardId
            )
        }

        // 4. NUEVA RUTA: Pantalla de Eventos y Desafíos
        composable("events") {
            EventsScreen(navController = navController, viewModel = eventsViewModel)
        }
    }
}