package com.example.clashroyale_api.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.clashroyale_api.ui.theme.RoyaleBlue
import com.example.clashroyale_api.ui.theme.RoyaleGold
import com.example.clashroyale_api.ui.theme.RoyalePanel
import com.example.clashroyale_api.ui.theme.RoyalePanelStrong

@Composable
fun HomeScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background).padding(horizontal = 24.dp, vertical = 28.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text("ROYALE API", style = MaterialTheme.typography.labelSmall, color = RoyaleGold)
        Spacer(Modifier.height(12.dp))
        Text("Tu arena.\nTus cartas.", style = MaterialTheme.typography.displaySmall, color = MaterialTheme.colorScheme.onBackground)
        Spacer(Modifier.height(12.dp))
        Text("Explora el universo de Clash Royale con datos vivos y detalles de cada carta.", style = MaterialTheme.typography.bodyLarge, color = MaterialTheme.colorScheme.onSurfaceVariant)
        Spacer(Modifier.height(32.dp))
        Button(onClick = { navController.navigate("cards") }, modifier = Modifier.fillMaxWidth().height(58.dp), shape = MaterialTheme.shapes.medium, colors = ButtonDefaults.buttonColors(containerColor = RoyaleBlue)) {
            Text("EXPLORAR CARTAS", fontWeight = FontWeight.Black)
        }
        Spacer(Modifier.height(18.dp))
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            HomeTile("ARENAS", "Próximamente", Modifier.weight(1f))
            HomeTile("CLANES", "Próximamente", Modifier.weight(1f))
        }
        Spacer(Modifier.height(26.dp))
        Text("DATOS EN TIEMPO REAL  •  CLASH ROYALE API", style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
    }
}

@Composable
private fun HomeTile(title: String, subtitle: String, modifier: Modifier) {
    Column(modifier = modifier.clip(RoundedCornerShape(18.dp)).background(Brush.linearGradient(listOf(RoyalePanelStrong, RoyalePanel))).padding(16.dp)) {
        Box(Modifier.size(8.dp).clip(RoundedCornerShape(50)).background(RoyaleGold))
        Spacer(Modifier.height(18.dp))
        Text(title, style = MaterialTheme.typography.titleMedium)
        Text(subtitle, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
    }
}
