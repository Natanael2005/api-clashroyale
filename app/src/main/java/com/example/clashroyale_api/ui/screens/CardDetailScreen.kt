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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.clashroyale_api.model.Card as ClashCard
import com.example.clashroyale_api.model.IconUrls
import com.example.clashroyale_api.viewmodel.CardsViewModel

@Composable
fun CardDetailScreen(
    navController: NavController,
    viewModel: CardsViewModel,
    cardId: Int
) {
    val cards by viewModel.cards.collectAsState()
    val selectedCard = cards.find { it.id == cardId }

    CardDetailContent(
        selectedCard = selectedCard,
        onBackClick = { navController.popBackStack() }
    )
}

@Composable
fun CardDetailContent(
    selectedCard: ClashCard?,
    onBackClick: () -> Unit
) {
    if (selectedCard != null) {
        val backgroundGradient = when (selectedCard.rarity.lowercase()) {
            "common" -> listOf(Color(0xFF78909C), Color(0xFF37474F))
            "rare" -> listOf(Color(0xFFFFB74D), Color(0xFFE65100))
            "epic" -> listOf(Color(0xFFCE93D8), Color(0xFF6A1B9A))
            "legendary" -> listOf(Color(0xFFFFF176), Color(0xFFF57F17))
            "champion" -> listOf(Color(0xFFFFD54F), Color(0xFFFF8F00))
            else -> listOf(Color(0xFF9E9E9E), Color(0xFF424242))
        }

        val imageList = mutableListOf<Pair<String, String>>()
        imageList.add(Pair(selectedCard.iconUrls.medium, "standard"))
        selectedCard.iconUrls.evolutionMedium?.let { imageList.add(Pair(it, "evolution")) }
        selectedCard.iconUrls.heroMedium?.let { imageList.add(Pair(it, "hero")) }

        val pagerState = rememberPagerState(pageCount = { imageList.size })

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Brush.verticalGradient(backgroundGradient))
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Botón seguro debajo de la barra de estado
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .systemBarsPadding(),
                    horizontalArrangement = Arrangement.Start
                ) {
                    TextButton(
                        onClick = onBackClick,
                    ) {
                        Text(
                            text = "◄ Volver",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = Color.White
                        )
                    }
                }

                //Spacer(modifier = Modifier.height(16.dp))

                // CONTENEDOR PRINCIPAL: Separa el fondo luminoso del carrusel para evitar recortes cuadrados
                // CONTENEDOR PRINCIPAL: Separa el fondo luminoso del carrusel para evitar recortes cuadrados
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .offset(y = (-40).dp), // <--- 1. AQUÍ SUBIMOS TODO EL BLOQUE (Aura, Imagen y Texto)
                    contentAlignment = Alignment.Center
                ) {
                    // 1. EL AURA RADIAL EN EL FONDO (Desvinculada del Pager)
                    val currentImageType = imageList[pagerState.currentPage].second
                    val auraColors = when (currentImageType) {
                        "evolution" -> listOf(
                            Color(0xFFE040FB).copy(alpha = 0.8f),
                            Color(0xFFE040FB).copy(alpha = 0.3f),
                            Color.Transparent
                        )
                        "hero" -> listOf(
                            Color(0xFFFFEA00).copy(alpha = 0.8f),
                            Color(0xFFFFEA00).copy(alpha = 0.3f),
                            Color.Transparent
                        )
                        else -> listOf(
                            Color.Black.copy(alpha = 0.6f),
                            Color.Black.copy(alpha = 0.2f),
                            Color.Transparent
                        )
                    }

                    Box(
                        modifier = Modifier
                            .requiredSize(550.dp)
                            .clip(CircleShape)
                            .background(Brush.radialGradient(colors = auraColors))
                    )

                    // 2. EL CARRUSEL DE IMÁGENES POR ENCIMA DE LA LUZ
                    HorizontalPager(
                        state = pagerState,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(420.dp)
                    ) { page ->
                        val (imageUrl, imageType) = imageList[page]

                        val textoForma = when (imageType) {
                            "evolution" -> "EVOLUTION"
                            "hero" -> "HERO"
                            else -> "COMMON"
                        }

                        val colorForma = when (imageType) {
                            "evolution" -> Color(0xFFE040FB)
                            "hero" -> Color(0xFFFFEA00)
                            else -> Color.White
                        }

                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            // LA IMAGEN LIMPIA
                            AsyncImage(
                                model = imageUrl,
                                contentDescription = "Imagen de la carta",
                                modifier = Modifier
                                    .size(380.dp)
                                    .padding(bottom = 20.dp) // <--- 2. AQUÍ restauramos el padding original (quitamos el offset individual)
                            )

                            // LA ETIQUETA DE TEXTO
                            Text(
                                text = textoForma,
                                color = colorForma,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.ExtraBold,
                                modifier = Modifier
                                    .align(Alignment.BottomCenter)
                                    .padding(bottom = 12.dp)
                                    .clip(RoundedCornerShape(12.dp))
                                    .background(Color.Black.copy(alpha = 0.7f))
                                    .padding(horizontal = 16.dp, vertical = 6.dp)
                            )
                        }
                    }
                }

                // Indicadores del Carrusel (Puntitos)
                if (imageList.size > 1) {
                    Row(
                        modifier = Modifier
                            .wrapContentHeight()
                            .fillMaxWidth()
                            .offset(y = (-40).dp) // <--- 3. AQUÍ SUBIMOS LOS PUNTITOS para que sigan a la imagen
                            .padding(top = 16.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        repeat(pagerState.pageCount) { iteration ->
                            val color = if (pagerState.currentPage == iteration) Color.White else Color.White.copy(alpha = 0.4f)
                            Box(
                                modifier = Modifier
                                    .padding(horizontal = 4.dp)
                                    .clip(CircleShape)
                                    .background(color)
                                    .size(10.dp)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.weight(1f))

                // Panel inferior de estadísticas
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(350.dp),
                    shape = RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    elevation = CardDefaults.cardElevation(defaultElevation = 16.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(32.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = selectedCard.name.uppercase(),
                                fontSize = 32.sp,
                                fontWeight = FontWeight.Black,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Spacer(modifier = Modifier.width(12.dp))
                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier
                                    .size(40.dp)
                                    .clip(CircleShape)
                                    .background(Color(0xFFD500F9))
                            ) {
                                Text(
                                    text = selectedCard.elixirCost.toString(),
                                    color = Color.White,
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.ExtraBold
                                )
                            }
                        }

                        Text(
                            text = selectedCard.rarity.uppercase(),
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Gray,
                            modifier = Modifier.padding(top = 4.dp, bottom = 32.dp)
                        )

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            DetailStatBox(
                                label = "NIVEL MÁX",
                                value = selectedCard.maxLevel.toString(),
                                color = Color(0xFF2196F3)
                            )
                            if (selectedCard.maxEvolutionLevel != null) {
                                DetailStatBox(
                                    label = "EVOLUCIÓN",
                                    value = selectedCard.maxEvolutionLevel.toString(),
                                    color = Color(0xFF00E676)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun DetailStatBox(label: String, value: String, color: Color) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .background(color.copy(alpha = 0.1f))
            .padding(horizontal = 24.dp, vertical = 16.dp)
    ) {
        Text(
            text = value,
            fontSize = 28.sp,
            fontWeight = FontWeight.Black,
            color = color
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = label,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = Color.DarkGray
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CardDetailPreview() {
    val dummyCard = ClashCard(
        id = 26000000,
        name = "Knight",
        maxLevel = 14,
        maxEvolutionLevel = 1,
        elixirCost = 3,
        rarity = "common",
        iconUrls = IconUrls(
            medium = "https://api-assets.clashroyale.com/cards/300/jAj1Q5rc1XxU9kVImGqSJxa4wEMfEhvwNQ_4jiGUuqg.png",
            evolutionMedium = "https://api-assets.clashroyale.com/cardevolutions/300/jAj1Q5rc1XxU9kVImGqSJxa4wEMfEhvwNQ_4jiGUuqg.png",
            heroMedium = null
        )
    )

    MaterialTheme {
        CardDetailContent(
            selectedCard = dummyCard,
            onBackClick = {}
        )
    }
}