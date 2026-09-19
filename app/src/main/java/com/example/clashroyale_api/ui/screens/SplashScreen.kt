package com.example.clashroyale_api.ui.screens

/**
 * Project: Clash Royale - API
 * From: com.example.clashroyale_api.ui.screens
 * Created by: felip
 * On: 18/09/2026
 * All rights reserved: 2026
 */

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.clashroyale_api.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {
    LaunchedEffect(key1 = true) {
        delay(2500)

        // Navegamos al menú principal ("home")
        navController.navigate("home") {
            popUpTo("splash") { inclusive = true }
        }
    }

    // Estructura visual de la pantalla (Diseño propio)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1E3A8A)), // Un color azul oscuro vibrante
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
             painter = painterResource(id = R.drawable.logo_clash),
             contentDescription = "Logo de la app",
             modifier = Modifier.size(300.dp)
         )



        // Nombre de la aplicación
        Text(
            text = "Royale API App",
            color = Color.White,
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Cargando recursos...",
            color = Color.LightGray,
            fontSize = 16.sp
        )
    }
}