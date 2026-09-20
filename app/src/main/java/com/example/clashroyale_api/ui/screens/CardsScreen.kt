package com.example.clashroyale_api.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.clashroyale_api.viewmodel.CardsViewModel

@Composable
fun CardsScreen(
    navController: NavController,
    viewModel: CardsViewModel
) {
    val cards by viewModel.cards.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    var searchQuery by remember { mutableStateOf("") }

    val rarityOrder = mapOf(
        "common" to 1,
        "rare" to 2,
        "epic" to 3,
        "legendary" to 4,
        "champion" to 5
    )

    val filteredCards = cards.filter {
        it.name.contains(searchQuery, ignoreCase = true)
    }

    val groupedCards = filteredCards.groupBy { it.rarity.lowercase() }
    val sortedRarities = groupedCards.keys.sortedBy { rarityOrder[it] ?: 99 }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 16.dp)
            .systemBarsPadding()
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        // Fila para agrupar la flecha de texto y el título
        // Fila para agrupar la flecha de texto y el título
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(bottom = 8.dp)
        ) {
            Text(
                text = "‹", // Símbolo chevron minimalista
                fontSize = 45.sp, // Ligeramente más grande para compensar su delgadez
                fontWeight = FontWeight.Medium, // Más delgado para coincidir con tu imagen
                color = Color.Black,
                modifier = Modifier
                    .padding(end = 5.dp)
                    .clip(CircleShape)
                    .clickable { navController.popBackStack() }
                    .padding(horizontal = 12.dp) // Área táctil generosa
            )

            Text(
                text = "CATÁLOGO",
                fontSize = 32.sp,
                fontWeight = FontWeight.Black,
                color = Color.Black
            )
        }

        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            label = { Text("Buscar carta...") },
            singleLine = true,
            shape = RoundedCornerShape(16.dp)
        )

        if (isLoading) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(color = Color(0xFFD500F9))
            }
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                sortedRarities.forEach { rarity ->
                    item {
                        Text(
                            text = rarity.uppercase(),
                            fontSize = 16.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = when (rarity) {
                                "common" -> Color(0xFF546E7A)
                                "rare" -> Color(0xFFF57C00)
                                "epic" -> Color(0xFF8E24AA)
                                "legendary" -> Color(0xFFFBC02D)
                                "champion" -> Color(0xFFFF8F00)
                                else -> Color.Black
                            },
                            modifier = Modifier.padding(top = 12.dp, bottom = 4.dp, start = 8.dp)
                        )
                    }

                    items(groupedCards[rarity]?.sortedBy { it.name } ?: emptyList()) { card ->
                        CardItem(
                            card = card,
                            onClick = { navController.navigate("card_detail/${card.id}") }
                        )
                    }
                }

                item {
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}

@Composable
fun CardItem(
    card: com.example.clashroyale_api.model.Card,
    onClick: () -> Unit
) {
    val backgroundGradient = when (card.rarity.lowercase()) {
        "common" -> listOf(Color(0xFF78909C), Color(0xFF455A64))
        "rare" -> listOf(Color(0xFFFFB74D), Color(0xFFF57C00))
        "epic" -> listOf(Color(0xFFCE93D8), Color(0xFF8E24AA))
        "legendary" -> listOf(Color(0xFFFFF176), Color(0xFFFBC02D))
        "champion" -> listOf(Color(0xFFFFD54F), Color(0xFFFF8F00))
        else -> listOf(Color(0xFF9E9E9E), Color(0xFF616161))
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .shadow(8.dp, RoundedCornerShape(20.dp)),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Brush.horizontalGradient(backgroundGradient))
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = card.iconUrls.medium,
                contentDescription = "Imagen de ${card.name}",
                modifier = Modifier
                    .size(80.dp)
                    .shadow(8.dp, CircleShape)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(
                modifier = Modifier
                    .weight(1f)
                    .height(80.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = card.name.uppercase(),
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Black,
                        color = Color.White,
                        lineHeight = 24.sp
                    )
                    Text(
                        text = card.rarity.uppercase(),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White.copy(alpha = 0.7f)
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (card.maxEvolutionLevel != null) {
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .background(Color(0xFF00E676).copy(alpha = 0.8f))
                                .padding(horizontal = 8.dp, vertical = 4.dp)
                        ) {
                            Text(
                                text = "EVO",
                                fontSize = 10.sp,
                                fontWeight = FontWeight.ExtraBold,
                                color = Color.White
                            )
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                    }

                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .background(Color.Black.copy(alpha = 0.3f))
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    ) {
                        Text(
                            text = "NIVEL ${card.maxLevel}",
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }
                }
            }
        }
    }
}