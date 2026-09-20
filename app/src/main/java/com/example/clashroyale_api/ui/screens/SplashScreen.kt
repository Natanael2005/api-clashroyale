package com.example.clashroyale_api.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.clashroyale_api.R
import com.example.clashroyale_api.ui.theme.RoyaleBlue
import com.example.clashroyale_api.ui.theme.RoyaleGold
import com.example.clashroyale_api.ui.theme.RoyaleNavy
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {
    LaunchedEffect(Unit) {
        delay(1800)
        navController.navigate("home") { popUpTo("splash") { inclusive = true } }
    }
    Column(Modifier.fillMaxSize().background(Brush.verticalGradient(listOf(RoyaleBlue.copy(.35f), RoyaleNavy))), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        Image(painterResource(R.drawable.logo_clash), "Logo de Royale API", Modifier.size(250.dp))
        Spacer(Modifier.height(20.dp))
        Text("ROYALE API", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Black, letterSpacing = 2.sp)
        Text("TU COMPENDIO DE CARTAS", color = RoyaleGold, fontSize = 11.sp, fontWeight = FontWeight.Bold, letterSpacing = 1.sp)
        Spacer(Modifier.height(34.dp))
        CircularProgressIndicator(color = RoyaleGold, strokeWidth = 2.dp, modifier = Modifier.size(24.dp))
    }
}
