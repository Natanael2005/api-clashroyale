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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.clashroyale_api.ui.theme.RoyaleGold
import com.example.clashroyale_api.ui.theme.RoyaleMint
import com.example.clashroyale_api.ui.theme.rarityColor
import com.example.clashroyale_api.viewmodel.CardsViewModel

@Composable
fun CardDetailScreen(navController: NavController, viewModel: CardsViewModel, cardId: Int) {
    val cards by viewModel.cards.collectAsState()
    val card = cards.find { it.id == cardId } ?: return
    val images = buildList {
        add(card.iconUrls.medium to "ESTÁNDAR")
        card.iconUrls.evolutionMedium?.let { add(it to "EVOLUCIÓN") }
        card.iconUrls.heroMedium?.let { add(it to "HÉROE") }
    }
    val pagerState = rememberPagerState(pageCount = { images.size })
    val accent = rarityColor(card.rarity)

    Box(Modifier.fillMaxSize().background(Brush.verticalGradient(listOf(Color(0xFF13233B), Color(0xFF0B1220))))) {
        Column(Modifier.fillMaxSize()) {
            TextButton(onClick = { navController.popBackStack() }, modifier = Modifier.padding(top = 18.dp, start = 10.dp)) { Text("←  VOLVER", color = MaterialTheme.colorScheme.onSurfaceVariant, fontWeight = FontWeight.Bold) }
            HorizontalPager(state = pagerState, modifier = Modifier.fillMaxWidth().height(330.dp)) { page ->
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Box(Modifier.size(300.dp).background(Brush.radialGradient(listOf(accent.copy(alpha = .3f), Color.Transparent))))
                    AsyncImage(model = images[page].first, contentDescription = "Imagen de ${card.name}", modifier = Modifier.size(270.dp))
                }
            }
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) { repeat(images.size) { index -> Box(Modifier.padding(4.dp).size(if (index == pagerState.currentPage) 9.dp else 7.dp).clip(CircleShape).background(if (index == pagerState.currentPage) accent else Color.White.copy(.3f))) } }
            Spacer(Modifier.weight(1f))
            Card(Modifier.fillMaxWidth(), shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp), colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)) {
                Column(Modifier.fillMaxWidth().padding(26.dp)) {
                    Text(card.rarity.uppercase(), style = MaterialTheme.typography.labelSmall, color = accent)
                    Row(Modifier.fillMaxWidth().padding(top = 7.dp), verticalAlignment = Alignment.CenterVertically) {
                        Text(card.name, style = MaterialTheme.typography.headlineSmall, modifier = Modifier.weight(1f))
                        Box(Modifier.size(42.dp).clip(CircleShape).background(RoyaleGold), contentAlignment = Alignment.Center) { Text("${card.elixirCost}", color = Color(0xFF0B1220), fontWeight = FontWeight.Black) }
                    }
                    Spacer(Modifier.height(24.dp))
                    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                        DetailStatBox("NIVEL MÁX", card.maxLevel.toString(), accent, Modifier.weight(1f))
                        card.maxEvolutionLevel?.let { DetailStatBox("EVOLUCIÓN", it.toString(), RoyaleMint, Modifier.weight(1f)) }
                    }
                }
            }
        }
    }
}

@Composable
fun DetailStatBox(label: String, value: String, color: Color, modifier: Modifier = Modifier) {
    Column(modifier.clip(RoundedCornerShape(16.dp)).background(color.copy(alpha = .1f)).padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(value, style = MaterialTheme.typography.headlineSmall, color = color)
        Text(label, style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant, modifier = Modifier.padding(top = 3.dp))
    }
}
