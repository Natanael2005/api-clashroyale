package com.example.clashroyale_api.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.clashroyale_api.model.Card
import com.example.clashroyale_api.ui.theme.RoyaleGold
import com.example.clashroyale_api.ui.theme.rarityColor
import com.example.clashroyale_api.viewmodel.CardsViewModel

@Composable
fun CardsScreen(navController: NavController, viewModel: CardsViewModel) {
    val cards by viewModel.cards.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    var searchQuery by remember { mutableStateOf("") }
    val filteredCards = cards.filter { it.name.contains(searchQuery, ignoreCase = true) }

    Column(Modifier.fillMaxSize().padding(horizontal = 20.dp, vertical = 28.dp)) {
        Text("COLECCIÓN", style = MaterialTheme.typography.labelSmall, color = RoyaleGold)
        Text("Cartas", style = MaterialTheme.typography.headlineSmall, modifier = Modifier.padding(top = 6.dp))
        Text("${filteredCards.size} cartas descubiertas", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant, modifier = Modifier.padding(top = 4.dp, bottom = 20.dp))
        OutlinedTextField(value = searchQuery, onValueChange = { searchQuery = it }, modifier = Modifier.fillMaxWidth(), placeholder = { Text("Buscar por nombre") }, singleLine = true, shape = MaterialTheme.shapes.medium)
        if (isLoading) {
            Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) { CircularProgressIndicator(color = RoyaleGold) }
        } else {
            LazyColumn(Modifier.fillMaxSize().padding(top = 12.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                items(filteredCards, key = { it.id }) { card -> CardItem(card) { navController.navigate("card_detail/${card.id}") } }
            }
        }
    }
}

@Composable
fun CardItem(card: Card, onClick: () -> Unit) {
    Card(modifier = Modifier.fillMaxWidth().clickable(onClick = onClick), colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant), shape = MaterialTheme.shapes.medium) {
        Row(Modifier.fillMaxWidth().padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
            AsyncImage(model = card.iconUrls.medium, contentDescription = "Imagen de ${card.name}", modifier = Modifier.size(72.dp))
            Spacer(Modifier.width(14.dp))
            Column(Modifier.weight(1f)) {
                Text(card.name, style = MaterialTheme.typography.titleMedium)
                Text(card.rarity.uppercase(), style = MaterialTheme.typography.labelSmall, color = rarityColor(card.rarity), modifier = Modifier.padding(top = 4.dp))
            }
            Text("${card.elixirCost}", style = MaterialTheme.typography.titleLarge, color = RoyaleGold)
        }
    }
}
