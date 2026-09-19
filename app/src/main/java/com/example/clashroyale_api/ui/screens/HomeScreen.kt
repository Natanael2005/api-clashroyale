package com.example.clashroyale_api.ui.screens

/**
 * Project: Clash Royale - API
 * From: com.example.clashroyale_api.ui.screens
 * Created by: felip
 * On: 18/09/2026
 * All rights reserved: 2026
 */

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun HomeScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Título principal
        Text(
            text = "Universo Royale",
            fontSize = 32.sp,
            fontWeight = FontWeight.ExtraBold,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Subtítulo
        Text(
            text = "Selecciona una categoría para explorar",
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(48.dp))

        // Botón 1: Navegación hacia la API (Requisito principal)
        Button(
            onClick = { navController.navigate("cards") },
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            shape = MaterialTheme.shapes.medium
        ) {
            Text(text = "Explorar Cartas", fontSize = 18.sp, fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Botón 2: Categoría adicional (Cumple con mostrar opciones/categorías)
        OutlinedButton(
            onClick = { /* Acción vacía, solo para diseño */ },
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            shape = MaterialTheme.shapes.medium
        ) {
            Text(text = "Ver Arenas", fontSize = 18.sp)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Botón 3: Categoría adicional
        OutlinedButton(
            onClick = { /* Acción vacía, solo para diseño */ },
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            shape = MaterialTheme.shapes.medium
        ) {
            Text(text = "Clanes Destacados", fontSize = 18.sp)
        }
    }
}